package edu.ucsd.ncmir.spl.Graphics.smoothers;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.Graphics.meshables.TriangleMesh;
import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class WeightedSmoothing
    implements Smoother

{

    private class MeshNode
	extends ArrayList<Integer>

    {

	private static final long serialVersionUID = 42L; // To shut up compiler

	private float _u;
	private float _v;
	private float _w;

	private boolean _visited = false;

	MeshNode( Triplet t[] )

	{

	    this._u = ( float ) t[0].getU();
	    this._v = ( float ) t[0].getV();
	    this._w = ( float ) t[0].getW();

	}

	void accumulate( double[] accumulators, double weight )

	{

	    accumulators[0] += this._u * weight;
	    accumulators[1] += this._v * weight;
	    accumulators[2] += this._w * weight;

	    accumulators[3] += weight;
	    this._visited = true;

	}


        boolean isVisited()

	{

            return this._visited;

	}

        void setVisited( boolean visited )

	{

            this._visited = visited;

	}

    }

    private ArrayList<MeshNode> _mesh_nodes = new ArrayList<MeshNode>();

    @Override
    public TriangleMesh smooth( TriangleMesh mesh )

    {

	ArrayList<Triplet[]> triplets = mesh.getVertices();
	ArrayList<int[]> indices = mesh.getIndices();

	for ( int i = 0; i < triplets.size(); i++ )
	    this._mesh_nodes.add( new MeshNode( triplets.get( i ) ) );

	for ( int[] inds : indices ) {

	    for ( int i = 0; i < inds.length; i++ ) {

		MeshNode mn = this._mesh_nodes.get( inds[i] );

		for ( int j = 1; j < inds.length; j++ )
		    mn.add( inds[( i + j ) % inds.length] );

	    }

	}

	int nmn = this._mesh_nodes.size();
	for ( int i = 0; i < nmn; i++ ) {

	    double[] accumulators = new double[4];

	    this.accumulate( accumulators, this._mesh_nodes.get( i ), 1.0, 3 );

	    triplets.set( i, new Triplet[] {
		    new Triplet( accumulators[0] / accumulators[3],
				 accumulators[1] / accumulators[3],
				 accumulators[2] / accumulators[3] ),
		    null
		} );

	}

	return mesh;

    }

    private void accumulate( double[] accumulators,
			     MeshNode mn,
			     double weight,
			     int level )

    {

	mn.accumulate( accumulators, weight );

	if ( --level > 0 ) {

	    weight /= 2;
	    for ( Integer sibling : mn ) {

		MeshNode sibling_node =
		    this._mesh_nodes.get( sibling.intValue() );

		if ( !sibling_node.isVisited() )
		    this.accumulate( accumulators,
				     sibling_node,
				     weight,
				     level );

	    }

	    for ( Integer sibling : mn )
		this._mesh_nodes.get( sibling.intValue() ).setVisited( false );

	}

    }

}
