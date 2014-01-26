package edu.ucsd.ncmir.spl.Graphics;

import edu.ucsd.ncmir.spl.Graphics.meshables.ConicSection;
import edu.ucsd.ncmir.spl.Graphics.meshables.TriangleMesh;

/**
 *
 * @author spl
 */
public class ThreadedObject
    extends TriangleMesh

{

    public ThreadedObject( Triplet[] triplets, double radius )

    {

	super();

	Triplet tv0 = null;
	Triplet tv1 = triplets[0];
	Triplet tv2 = triplets[1];
	Triplet tv3 = ( triplets.length >= 3 ) ? triplets[2] : null;

        for ( int it2 = 1; it2 < triplets.length; it2++ ) {
	    
	    ConicSection cs = new ConicSection( tv0,
						tv1, radius,
						tv2, radius,
						tv3,
						17 );
	    
	    super.addTriangleMesh( cs );
	    
	    tv0 = tv1;
	    tv1 = tv2;
	    tv2 = tv3;
	    int next = it2 + 2;
	    tv3 = ( next < triplets.length ) ? triplets[next] : null;
	    
	}
	
    }

    public ThreadedObject( Triplet[] triplets, double[] radius )

    {

	super();

	if ( triplets.length != radius.length )
	    throw new IllegalArgumentException( "triplet and radius array " +
						"lengths differ." );

	Triplet tv0 = null;
	Triplet tv1 = triplets[0];
	double rv1 = radius[0];
	Triplet tv2 = triplets[1];
	double rv2 = radius[1];
	Triplet tv3 = ( triplets.length >= 3 ) ? triplets[2] : null;
	double rv3 = ( radius.length >= 3 ) ? radius[2] : 0;

        for ( int it2 = 1; it2 < triplets.length; it2++ ) {
	    
	    ConicSection cs = new ConicSection( tv0,
						tv1, rv1,
						tv2, rv2,
						tv3,
						17 );
	    
	    super.addTriangleMesh( cs );
	    
	    int next = it2 + 2;

	    tv0 = tv1;
	    tv1 = tv2;
	    tv2 = tv3;
	    tv3 = ( next < triplets.length ) ? triplets[next] : null;
	    
	    rv1 = rv2;
	    rv2 = rv3;
	    rv3 = ( next < radius.length ) ? radius[next] : 0;
	    
	}
	
    }

}
