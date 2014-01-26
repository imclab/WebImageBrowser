package edu.ucsd.ncmir.spl.Graphics;

import edu.ucsd.ncmir.spl.LinearAlgebra.AbstractDoubleMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.AbstractDoubleSquareMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleSquareMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.Quaternion;
import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class Transformation
    extends DoubleSquareMatrix

{

    public Transformation()

    {

        super( new double[][] {
            { 1, 0, 0, 0 },
            { 0, 1, 0, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 }
        } );

    }

    public Transformation( double[][] m )

    {

	super( m );

    }

    public void rotation( Quaternion q )

    {

        AbstractDoubleSquareMatrix adsm = q.toRotationMatrix();

        double[][] m = new double[4][4];

	for ( int j = 0; j < adsm.rows(); j++ )
	    for ( int i = 0; i < adsm.columns(); i++ )
		m[j][i] = adsm.getElement( j, i );
	
	m[3][0] = m[3][1] = m[3][2] = 0.0;
	m[3][3] = 1.0;

	this.performMultiplication( new Transformation( m ) );

    }

    public void translation( double x, double y, double z )

    {

	Transformation t = new Transformation();

	t.setElement( 0, 3, x );
	t.setElement( 1, 3, y );
	t.setElement( 2, 3, z );

	this.performMultiplication( t );

    }

    private void performMultiplication( Transformation dsm ) 

    {

	AbstractDoubleMatrix adsm = this.multiply( dsm );

	for ( int j = 0; j < adsm.rows(); j++ )
	    for ( int i = 0; i < adsm.columns(); i++ )
		super.setElement( j, i, adsm.getElement( j, i ) );

    }

    public Triplet transform( Triplet triplet )

    {

	return new Triplet( this.multiply( triplet.toHomogeneousVector() ) );

    }

    public Triplet[] transform( Triplet[] triplets )

    {

	Triplet[] out = new Triplet[triplets.length];

	for ( int i = 0; i < triplets.length; i++ )
	    out[i] = this.transform( triplets[i] );

	return out;

    }

    public PlanarPolygon transform( PlanarPolygon planar_polygon )
        throws ZeroAreaPolygonException

    {

	ArrayList<Triplet> out = new ArrayList<Triplet>();

	for ( Triplet t : planar_polygon )
	    out.add( this.transform( t ) );

	return new PlanarPolygon( out );

    }

    public PlanarPolygonTable transform( PlanarPolygonTable table )
        throws ZeroAreaPolygonException

    {

	PlanarPolygonTable out = new PlanarPolygonTable();

	for ( Double d : table.getKeys() )
	    for ( PlanarPolygon pp : table.get( d ) ) 
		out.add( d, this.transform( pp ) );

	return out;

    }

}