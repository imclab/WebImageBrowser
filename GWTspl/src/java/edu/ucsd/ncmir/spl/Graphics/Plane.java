package edu.ucsd.ncmir.spl.Graphics;

import edu.ucsd.ncmir.spl.LinearAlgebra.Double3Vector;

/**
 *
 * @author spl
 */
public class Plane

{

    private double _a;
    private double _b;
    private double _c;
    private double _d;

    public Plane( double a, double b, double c, double d )

    {

	this._a = a;
	this._b = b;
	this._c = c;
	this._d = d;

    }

    public Plane( Triplet t, double d )

    {

	this( t.getU(), t.getV(), t.getW(), d );

    }

    /**
     * Creates a Plane object by computing the dot project of the
     * Triplet p and the Double3Vector n, giving the offset d from the
     * origin.
     *
     * @param p a point on the plane.
     * @param n the normal of the plane.
     * @return a new Plane object.
     */

    public static Plane create( Triplet p, Double3Vector n )

    {

	return create( p, new Triplet( n ) );

    }

    /**
     * Creates a Plane object by computing the dot project of the
     * Triplet p and the Triplet n, giving the offset d from the
     * origin.
     *
     * @param p a point on the plane.
     * @param n the normal of the plane.
     * @return a new Plane object.
     */

    public static Plane create( Triplet p, Triplet n )

    {

	double d = p.dotProduct( n );

	return new Plane( n.getU(), n.getV(), n.getW(), d );

    }
	
    public double intersectLine( ParametricLine parametric_line )

    {

	return this.intersectLine( parametric_line.at( 0.0 ),
				   parametric_line.at( 1.0 ) );

    }

    /**
     * Computes the intersection of this plane and a vector described
     * by two triplets on the vector.
     *
     * @param ta origin or tail of vector.
     * @param tb end or head of vector.
     * @return the parameter t, indicating the distance along the
     * vector where the intersection occurs.  Returns
     * -Double.MAX_VALUE if the points are embedded in the plane,
     * +Double.MAX_VALUE if the vector is parallel to the plane
     * 
     */

    public double intersectLine( Triplet ta, Triplet tb )

    {

	double[] A = ta.getUVW();
	double[] B = tb.getUVW();

	double top =
	    this._d -
	    ( this._a * A[0] ) -
	    ( this._b * A[1] ) -
	    ( this._c * A[2] );

	double bottom =
	    ( this._a * ( B[0] - A[0] ) ) +
	    ( this._b * ( B[1] - A[1] ) ) +
	    ( this._c * ( B[2] - A[2] ) );

	double t;

	if ( ( top == 0 ) && ( bottom == 0 ) )
	    t = -Double.MAX_VALUE; // The points are embedded in the plane.
	else if ( bottom == 0 )
	    t = Double.MAX_VALUE; // The vector is parallel to the plane.
	else
	    t = top / bottom;

	return t;

    }

    /**
     * Convenience method invoking intersectLine( Triplet ta, Triplet tb )
     * @param v0 tail of vector
     * @param v1 head of vector
     * @return parameter t.
     */

    public double intersectLine( Double3Vector v0, Double3Vector v1 )

    {

	return this.intersectLine( new Triplet( v0 ), new Triplet( v1 ) );

    }

    public boolean intersectedBy( Triplet ta, Triplet tb )

    {

	double t = this.intersectLine( ta, tb );
	
	return ( 0.0 <= t ) && ( t <= 1.0 );

    }

    public boolean intersectedBy( ParametricLine parametric_line )

    {

	return this.intersectedBy( parametric_line.at( 0.0 ),
				   parametric_line.at( 1.0 ) );

    }

    public boolean intersectedBy( Double3Vector v0, Double3Vector v1 )

    {

	return this.intersectedBy( new Triplet( v0 ), new Triplet( v1 ) );

    }
    
}
