 package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */
public class AbstractDoubleVector

{

    protected double[] _v;

    public AbstractDoubleVector( int l )

    {

        this._v = new double[l];

    }

    public AbstractDoubleVector( double[] v )

    {

        this( v.length );
        System.arraycopy( v, 0, this._v, 0, v.length );

    }

    public int dimension()

    {

        return this._v.length;

    }

    public double getComponent( int i )

    {

        return this._v[i];

    }

    public void setComponent( int i, double v )

    {

        this._v[i] = v;

    }

    public AbstractDoubleVector subtract( AbstractDoubleVector v2 )
        
    {
        
	double[] v = new double[this._v.length];

	for ( int i = 0; i < this._v.length; i++ )
	    v[i] = this._v[i] - v2._v[i];

	return new AbstractDoubleVector( v );
        
    }

    public AbstractDoubleVector add( AbstractDoubleVector v2 )

    {

	double[] v = new double[this._v.length];

	for ( int i = 0; i < this._v.length; i++ )
	    v[i] = this._v[i] + v2._v[i];

	return new AbstractDoubleVector( v );
        
    }

    public double norm()

    {

	double n = this.scalarProduct( this );

	return Math.sqrt( n );

    }

    public AbstractDoubleVector normalize()

    {

	double n = this.norm();

	return this.scalarMultiply( 1 / n );

    }

    public AbstractDoubleVector scalarMultiply( double s )

    {

	double[] v = new double[this._v.length];

	for ( int i = 0; i < this._v.length; i++ )
	    v[i] = this._v[i] * s;

	return new AbstractDoubleVector( v );

    }

    public AbstractDoubleVector scalarDivide( double s )

    {

	return this.scalarMultiply( 1 / s );

    }

    @Override
    public String toString()

    {

	String s = "[ ";

	for ( double v : this._v )
	    s += v + " ";

	return s + "]";

    }

    public double scalarProduct( AbstractDoubleVector v )

    {

	double s = 0;

	for ( int i = 0; i < this._v.length; i++ )
	    s += this._v[i] * v._v[i];
	
	return s;

    }

}
