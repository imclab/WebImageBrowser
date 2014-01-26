/*
 * Triplet.java
 *
 * Created on May 15, 2006, 4:58 PM
 */

package edu.ucsd.ncmir.spl.Graphics;

import edu.ucsd.ncmir.spl.LinearAlgebra.AbstractDoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.Double3Vector;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleVector;

/**
 *
 * @author spl
 */

public class Triplet

{

    protected float _u;
    protected float _v;
    protected float _w;

    /** Creates a new instance of Triplet
     * @param u
     * @param v
     * @param w
     */
    public Triplet( double u, double v, double w )

    {

        this._u = ( float ) u;
        this._v = ( float ) v;
        this._w = ( float ) w;

    }

    public Triplet( double[] p )

    {

        this( p[0], p[1] );

    }

    public Triplet( double u, double v )

    {

	this( u, v, 0.0 );

    }

    public Triplet( Triplet point  )

    {

	this( point.getU(), point.getV(), point.getW() );

    }

    public Triplet( Triplet point, double du, double dv  )

    {

	this( point._u + du, point._v + dv, point._w );

    }

    public Triplet( Double3Vector v )

    {

        this( v.getComponent( 0 ),
              v.getComponent( 1 ),
              v.getComponent( 2 ) );

    }

    public Triplet( AbstractDoubleVector v )

    {

        this( v.getComponent( 0 ),
              v.getComponent( 1 ),
              v.getComponent( 2 ) );

    }

    public void clear()

    {

        this._u = this._v = this._w = 0;

    }

    public double getU()

    {

        return this._u;

    }

    public double getV()

    {

        return this._v;

    }

    public double getW()

    {

        return this._w;

    }

    public double[] getUV()

    {

        return new double[] { this._u, this._v };

    }

    public double[] getUVW()

    {

        return new double[] { this._u, this._v, this._w };

    }

    public double distance2D( Triplet p  )

    {

	double d = Double.MAX_VALUE;

	if ( p != null )
	    d = Math.sqrt( this.distanceSquared2D( p ) );
	return d;

    }

    public double distanceSquared2D( Triplet p  )

    {

	double d = Double.MAX_VALUE;

	if ( p != null ) {

	    double[] this_uv = this.getUV();
	    double[] p_uv = p.getUV();

	    double du = p_uv[0] - this_uv[0];
	    double dv = p_uv[1] - this_uv[1];

	    d = ( du * du ) + ( dv * dv );

	}

	return d;

    }

    public double distance3D( Triplet p  )

    {

	double d = Double.MAX_VALUE;

	if ( p != null )
	    d = Math.sqrt( this.distanceSquared3D( p ) );
	return d;

    }

    public double distanceSquared3D( Triplet p  )

    {

	double d = Double.MAX_VALUE;

	if ( p != null ) {

	    double[] this_uv = this.getUVW();
	    double[] p_uv = p.getUVW();

	    double du = p_uv[0] - this_uv[0];
	    double dv = p_uv[1] - this_uv[1];
	    double dw = p_uv[2] - this_uv[2];

	    d = ( du * du ) + ( dv * dv ) + ( dw * dw );

	}

	return d;

    }

    public double length2D()

    {

        return Math.sqrt( ( this._u * this._u ) +
                          ( this._v * this._v ) );

    }

    public double length3D()

    {

        return Math.sqrt( this.dotProduct( this ) );

    }

    public boolean between( Triplet a, Triplet c  )

    {

	boolean between = false;

	if ( this.colinear( a, c ) ) {

	    double[] uva = a.getUV();
	    double[] uvb = this.getUV();
	    double[] uvc = c.getUV();

	    int idx = ( uva[0] != uvb[0] ) ? 0 : 1;

	    between =
		( ( ( uva[idx] <= uvc[idx] ) &&
		    ( uvc[idx] <= uvb[idx] ) ) ||
		  ( ( uva[idx] >= uvc[idx] ) &&
		    ( uvc[idx] >= uvb[idx] ) ) );

	}

	return between;

    }

    public boolean colinear( Triplet a, Triplet c  )

    {

	return this.area2( a, c ) == 0.0;

    }

    public double area2( Triplet a, Triplet c  )

    {

	double[] uva = a.getUV();
	double[] uvb = this.getUV();
	double[] uvc = c.getUV();

	return
	    ( ( uva[0] * uvb[1] ) - ( uva[1] * uvb[0] ) ) +
	    ( ( uva[1] * uvc[0] ) - ( uva[0] * uvc[1] ) ) +
	    ( ( uvb[0] * uvc[1] ) - ( uvc[0] * uvb[1] ) );

    }

    public boolean left( Triplet a, Triplet c  )

    {

	return this.area2( a, c ) > 0.0;

    }

    public boolean uvEquals( Triplet p  )

    {

	double[] thisuv = this.getUV();
	double[] puv = p.getUV();

	return ( thisuv[0] == puv[0] ) && ( thisuv[1] == puv[1] );

    }

    public Triplet crossProduct( Triplet t )

    {

        return new Triplet( ( this._v * t._w ) - ( this._w * t._v ),
                            ( this._w * t._u ) - ( this._u * t._w ),
                            ( this._u * t._v ) - ( this._v * t._u ) );

    }

    public double dotProduct( Triplet t )

    {

        return
            ( this._u * t._u ) +
            ( this._v * t._v ) +
            ( this._w * t._w );

    }

    public Triplet unit()

    {

        double length = this.length3D();

        Triplet u;

        if ( length != 0 )
            u = new Triplet( this._u / length,
                             this._v / length,
                             this._w / length );
        else
            u = new Triplet( 0, 0, 1 ); // Punt.

        return u;

    }

    /**
     * this - p = new p
     * @param p
     * @return the difference.
     */

    public Triplet subtract( Triplet p )

    {

	return new Triplet( this.getU() - p.getU(),
			    this.getV() - p.getV(),
			    this.getW() - p.getW() );

    }

    public Triplet add( Triplet p  )

    {

	return new Triplet( this.getU() + p.getU(),
			    this.getV() + p.getV(),
			    this.getW() + p.getW() );

    }

    public Triplet multiply( double m )

    {

	return new Triplet( this.getU() * m,
			    this.getV() * m,
			    this.getW() * m );

    }

    public void copyIn( Triplet p  )

    {

	synchronized ( this ) {

	    this._u = p._u;
	    this._v = p._v;
	    this._w = p._w;

	}

    }

    public Double3Vector toVector()

    {

        return new Double3Vector( this._u, this._v, this._w );

    }

    public DoubleVector toHomogeneousVector()

    {

        return new DoubleVector( new double[] { this._u, this._v, this._w, 0 } );

    }

    @Override
    public boolean equals( Object o )

    {

        boolean equal = false;

        if ( ( o != null ) && ( o instanceof Triplet ) ) {

            Triplet t = ( Triplet ) o;

            equal =
                ( this._u == t._u ) &&
                ( this._v == t._v ) &&
                ( this._w == t._w );

        }

        return equal;

    }

    @Override
    public int hashCode()

    {

        int hash = 3;
        hash = 67 * hash + Float.floatToIntBits( this._u );
        hash = 67 * hash + Float.floatToIntBits( this._v );
        hash = 67 * hash + Float.floatToIntBits( this._w );
        return hash;

    }

    /**
     * Determines whether this point is on the same side of the vector
     * a-b as point p2.  All points assumed to be coplanar.
     *
     * @param p2 the point to be compared against.
     * @param a tail of the vector.
     * @param b head of the vector.
     *
     * @return true if they are on the same side, false if not.
     */
    public boolean onSameSide( Triplet p2, Triplet a, Triplet b )

    {

	Triplet ba = b.subtract( a );
	Triplet cp1 = ba.crossProduct( this.subtract( a ) );
	Triplet cp2 = ba.crossProduct( p2.subtract( a ) );

	return cp1.dotProduct( cp2 ) >= 0;

    }

    public boolean isPointInTriangle( Triplet a, Triplet b, Triplet c )

    {

	return
	    this.onSameSide( a, b, c ) &&
	    this.onSameSide( b, a, c ) &&
	    this.onSameSide( c, a, b );

    }

    public static Triplet parseTriplet( String string )

    {

        Triplet triplet = null;

        String[] t = string.trim().split( "[, \t]+" );
        if ( t.length == 3 )
            try {

                triplet = new Triplet( Double.parseDouble( t[0] ),
                                       Double.parseDouble( t[1] ),
                                       Double.parseDouble( t[2] ) );

            } catch ( NumberFormatException nfe ) {

                // Just eat it.

            }

        return triplet;

    }

}