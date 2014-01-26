package edu.ucsd.ncmir.spl.Graphics.meshables;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.LinearAlgebra.AbstractDoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.Double3Vector;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.Quaternion;
import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class ConicSection 
    extends TriangleMesh

{
    
    public ConicSection( Triplet tv0,
			 Triplet tv1, double r0,
			 Triplet tv2, double r1,
			 Triplet tv3,
			 int sides )

    {

	super( new ArrayList<Triplet[]>(), new ArrayList<int[]>() );

	AbstractDoubleVector v0;

	Double3Vector v1 = tv1.toVector();
	Double3Vector v2 = tv2.toVector();

	AbstractDoubleVector v3;

	if ( tv0 == null )
	    v0 = v1.subtract( v2 ).add( v1 );
	else 
	    v0 = tv0.toVector();

	if ( tv3 == null )
	    v3 = v2.subtract( v1 ).add( v2 );
	else 
	    v3 = tv3.toVector();

	Double3Vector dv2v1 = ( Double3Vector ) v2.subtract( v1 );
	Double3Vector dv1v0 = ( Double3Vector ) v1.subtract( v0 );
	Double3Vector dv2v3 = ( Double3Vector ) v2.subtract( v3 );
	
	DoubleVector hs0;
	
	if ( dv1v0.norm() == 0 )
	    hs0 = this.makePlane( ( Double3Vector ) dv2v1.normalize(), v1 );
	else
	    hs0 = this.findHalfspace( v0, v1, v2 );
	
	DoubleVector hs1;
	
	if ( dv2v3.norm() == 0 )
	    hs1 = this.makePlane( ( Double3Vector ) dv2v1.normalize(), v2 );
	else
	    hs1 = this.findHalfspace( v3, v2, v1 );
	
	Double3Vector difference = dv2v1;
	Double3Vector v = ( Double3Vector ) difference.normalize();
	double length = difference.norm();
	
	Double3Vector[] p0 = new Double3Vector[sides];
	Double3Vector[] p1 = new Double3Vector[sides];

	// Construct the vertex list oriented along the X axis.

	Double3Vector vec = new Double3Vector( 1.0, 0.0, 0.0 );

	Quaternion unit_radius = new Quaternion( 0, 0, 0, 1 );

	if ( ( Math.abs( vec.add( v ).norm() ) < 1.0e-3 ) ||
             ( Math.abs( vec.subtract( v ).norm() ) < 1.0e-3 ) ) {

	    vec = new Double3Vector( 0.0, 1.0, 0.0 );
	    unit_radius = new Quaternion( 0, 1, 0, 0 );

	}

	Double3Vector offset = ( Double3Vector ) vec.scalarMultiply( length );
	
	for ( int i = 0; i < p0.length; i++ ) {

	    double halfangle = ( Math.PI * i ) / p0.length;
	    double hcos = Math.cos( halfangle );
	    double hsin = Math.sin( halfangle );
	    
	    Quaternion q =
		new Quaternion( hcos,
				( Double3Vector ) vec.scalarMultiply( hsin ) );

	    Quaternion qconj = q.conjugate();

	    Double3Vector pt =
		q.multiply( unit_radius.multiply( qconj ) ).imag();
	  
	    p0[i] = ( Double3Vector ) pt.scalarMultiply( r0 );
	    p1[i] = ( Double3Vector ) pt.scalarMultiply( r1 ).add( offset );

	}

	// Compute the axis and angle about which the vertices must be
	// rotated to place them in the proper orientation.

	double halfangle = Math.acos( vec.scalarProduct( v ) ) / 2;

	Double3Vector axis = vec.multiply( v );

	axis = ( Double3Vector ) axis.normalize();
	
	double hcos = Math.cos( halfangle );
	double hsin = Math.sin( halfangle );
	
	// Now construct the quaternion.
	
	Quaternion q =
	    new Quaternion( hcos, 
			    ( Double3Vector ) axis.scalarMultiply( hsin ) );
	Quaternion qconj = q.conjugate();
	
	// Rotate the coordinates and translate them into their proper
	// orientation in space.
	
	for ( int i = 0; i < p0.length; i++ ) {
	    
	    p0[i] = 
		( Double3Vector ) q.multiply( new Quaternion( 0, p0[i] ).
					      multiply( qconj ) ).
		imag().add( v1 );
	    p1[i] = 
		( Double3Vector ) q.multiply( new Quaternion( 0, p1[i] ).
					      multiply( qconj ) ).
		imag().add( v1 );
	    
	}

	// Okay, almost done.  Now we must offset the rays describe by
	// the coordinate pairs into their proper location in space
	// and fire rays along their paths and calculate the
	// intersection between the ray and the two cutting planes.
	//
	// Note that we negative values for t, since the intersection
	// with the cutting plane may allowably be "behind" the origin
	// of the ray.

	Double3Vector n0 = new Double3Vector( hs0.getComponent( 0 ),
					      hs0.getComponent( 1 ),
					      hs0.getComponent( 2 ) );
	double d0 = hs0.getComponent( 3 );

	Double3Vector n1 = new Double3Vector( hs1.getComponent( 0 ),
					      hs1.getComponent( 1 ),
					      hs1.getComponent( 2 ) );
	double d1 = hs1.getComponent( 3 );

	for ( int i = 0; i < p0.length; i++ ) {

	    Double3Vector ray_d = ( Double3Vector ) p1[i].subtract( p0[i] );

	    // Normally we'd test to make sure that the dot product
	    // isn't zero but in this case, it is impossible for the
	    // ray to be parallel to either plane.  Or, at least it
	    // should be.

	    double t0 =
		-( n0.scalarProduct( p0[i] ) + d0 ) / n0.scalarProduct( ray_d );
	    double t1 =
		-( n1.scalarProduct( p0[i] ) + d1 ) / n1.scalarProduct( ray_d );

            Double3Vector pv0 =
                ( Double3Vector ) p0[i].add( ray_d.scalarMultiply( t0 ) );
            Double3Vector pv1 = 
                ( Double3Vector ) p0[i].add( ray_d.scalarMultiply( t1 ) );

            super.addToVertices( new Triplet[] {
                    new Triplet( pv0 ),
                    new Triplet( 0, 0, 0 )
                } );
            super.addToVertices( new Triplet[] {
                    new Triplet( pv1 ),
                    new Triplet( 0, 0, 0 )
                } );
				
	}

	ArrayList<Triplet[]> vertices = super.getVertices();

	// Construct the triangles.  There are two, an upper
	// and a lower triangle.
	
	for ( int i = 0; i < p0.length; i++ ) {
	    
	    int vtx1 = ( i * 2 );
	    int vtx2 = ( i * 2 ) + 1;
	    int vtx3 = ( ( i * 2 ) + 2 ) % ( p0.length * 2 );
	    int vtx4 = ( ( i * 2 ) + 3 ) % ( p0.length * 2 );
	    
	    Triplet[] tvtx1 = vertices.get( vtx1 );
	    Triplet[] tvtx2 = vertices.get( vtx2 );
	    Triplet[] tvtx3 = vertices.get( vtx3 );
	    Triplet[] tvtx4 = vertices.get( vtx4 );

	    // Upper Triangle.
	    
	    super.addToIndices( new int[] { vtx4, vtx2, vtx1 } );

	    // Lower Triangle.
	    
	    super.addToIndices( new int[] { vtx3, vtx4, vtx1 } );
	    
	    Triplet a = tvtx4[0].subtract( tvtx2[0] );
	    Triplet b = tvtx1[0].subtract( tvtx2[0] );

	    Triplet n = a.crossProduct( b ).unit();

	    tvtx1[1] = tvtx1[1].add( n );
	    tvtx2[1] = tvtx2[1].add( n );
	    tvtx3[1] = tvtx3[1].add( n );
	    tvtx4[1] = tvtx4[1].add( n );

	}

	super.unitNormals();

    }

    private DoubleVector makePlane( AbstractDoubleVector v, 
				    AbstractDoubleVector p )

    {

	double vx = v.getComponent( 0 );
	double vy = v.getComponent( 1 );
	double vz = v.getComponent( 2 );

	double px = p.getComponent( 0 );
	double py = p.getComponent( 1 );
	double pz = p.getComponent( 2 );

	double d = -( ( vx * px ) + ( vy * py ) + ( vz * pz ) );

	return new DoubleVector( new double[] { vx, vy, vz, d } );

    }

    private DoubleVector findHalfspace( AbstractDoubleVector v0, 
					AbstractDoubleVector v1, 
					AbstractDoubleVector v2 )

    {

	Double3Vector v0v1 = ( Double3Vector ) v0.subtract( v1 ).normalize();
	Double3Vector v2v1 = ( Double3Vector ) v2.subtract( v1 ).normalize();

	Double3Vector sum = ( Double3Vector ) v0v1.add( v2v1 );

	Double3Vector planevec;

	if ( sum.norm() == 0 )
	    planevec = v2v1;
	else {

	    Double3Vector normal = v0v1.multiply( v2v1 );

	    Double3Vector mid = 
		( Double3Vector ) sum.scalarDivide( 2.0 ).normalize();

	    planevec = ( Double3Vector ) normal.multiply( mid ).normalize();

	}

	return this.makePlane( planevec, v1 );

    }

}