package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */
public class Matrix2D
    extends DoubleSquareMatrix
{

    /**
     * Instatiates a unit matrix.
     */
    public Matrix2D()

    {

	this( 1, 0, 0, 1, 0, 0 );

    }

    /**
     * Instantiates a 2D matrix transformation.
     *
     * Note the ordering of the matrix terms here:
     *
     *<pre>
     *  | m11 m21 x |
     *  | m12 m22 y |
     *  |  0   0  1 |
     *</pre>
     *
     * as per
     * <code><a href="http://www.whatwg.org/specs/web-apps/current-work/multipage/the-canvas-element.html#transformations" target="_blank">http://www.whatwg.org/specs/web-apps/current-work/multipage/the-canvas-element.html#transformations</a></code>
     *
     * wherein the transform is defined as
     *
     *<pre>
     *   setTransformation( a, b, c, d, e, f )
     *</pre>
     *
     * resulting in a matrix that looks like
     *
     *<pre>
     *  | a c e |
     *  | b d f |
     *  | 0 0 1 |
     *</pre>
     * @param a m11
     * @param b m12
     * @param c m21
     * @param d m22
     * @param e x offset
     * @param f y offset
     */
    public Matrix2D( double a, double b,
		     double c, double d,
		     double e, double f )

    {

	super( 3 );

	this.setElement( 0, 0, a );	// m11
	this.setElement( 0, 1, c );	// m21
	this.setElement( 0, 2, e );	// x

	this.setElement( 1, 0, b );	// m12
	this.setElement( 1, 1, d );	// m22
	this.setElement( 1, 2, f );	// y

	this.setElement( 2, 0, 0 );
	this.setElement( 2, 1, 0 );
	this.setElement( 2, 2, 1 );

    }

    public double getM11()

    {

	return this.getElement( 0, 0 );

    }

    public double getM21()

    {

	return this.getElement( 0, 1 );

    }

    public double getX()

    {

	return this.getElement( 0, 2 );

    }

    public double getM12()

    {

	return this.getElement( 1, 0 );

    }

    public double getM22()

    {

	return this.getElement( 1, 1 );

    }

    public double getY()

    {

	return this.getElement( 1, 2 );

    }

}
