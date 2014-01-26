package edu.ucsd.ncmir.spl.Graphics;

/**
 *
 * @author spl
 */
public class ParametricLine

{

    private double[] _xyz;
    private double[] _m;
    private double _length;

    /**
     * Instantiates a parametric line with a starting point at t0
     * and ending at t1.
     *
     * @param t0 start point.
     * @param t1 end point.
     */

    public ParametricLine( Triplet t0, Triplet t1 )

    {

	this._xyz = t0.getUVW();

	Triplet dt = t1.subtract( t0 );

	this._m = dt.getUVW();
	this._length = dt.length3D();

    }

    public ParametricLine( double x0, double y0, double z0,
			   double x1, double y1, double z1 )

    {

	this( new Triplet( x0, y0, z0 ), new Triplet( x1, y1, z1 ) );

    }

    /**
     * Returns the point along the line t0-t1 at parameter t.
     *
     * @param t the parameter.
     * @return the point at t.
     */

    public Triplet at( double t )

    {

	return new Triplet( this._xyz[0] + ( t * this._m[0] ),
			    this._xyz[1] + ( t * this._m[1] ),
			    this._xyz[2] + ( t * this._m[2] ) );

    }

    /**
     * Returns the point along the line t0-t1 at the non-parameterized
     * distance d.
     * @param d the distance.
     * @return the point at d.
     */

    public Triplet along( double d )

    {

	return this.at( d / this._length );

    }

}

