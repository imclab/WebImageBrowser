package edu.ucsd.ncmir.spl.Graphics.smoothers;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.Graphics.meshables.TriangleMesh;
import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class UnweightedSmoothing
    implements Smoother

{

    @Override
    public TriangleMesh smooth( TriangleMesh mesh )

    {

	ArrayList<Triplet[]> triplets = mesh.getVertices();
	ArrayList<int[]> indices = mesh.getIndices();

	double[] x = new double[triplets.size()];
	double[] y = new double[triplets.size()];
	double[] z = new double[triplets.size()];
	double[] wt = new double[triplets.size()];

	for ( int[] inds : indices ) {

	    for ( int i : inds ) {

		Triplet v;

		if ( wt[i] == 0 ) {

		    v = triplets.get( i )[0];

		    x[i] += v.getU();
		    y[i] += v.getV();
		    z[i] += v.getW();

		    wt[i]++;

		}

		for ( int j : inds )
		    if ( i != j ) {

			v = triplets.get( j )[0];

			x[i] += v.getU();
			y[i] += v.getV();
			z[i] += v.getW();

			wt[i]++;

		    }


	    }

	}

	for ( int i = 0; i < triplets.size(); i++ ) {

	    Triplet[] vertex = new Triplet[] {
		new Triplet( x[i] / wt[i],
			     y[i] / wt[i],
			     z[i] / wt[i] ),
		null
	    };

	    triplets.set( i, vertex );

	}

	return mesh;

    }

}
