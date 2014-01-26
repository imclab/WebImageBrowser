package edu.ucsd.ncmir.spl.Interpolator;

/**
 *
 * @author spl
 */
public abstract class AbstractInterpolator

{

    protected double[] _x;
    protected double[] _y;

    public AbstractInterpolator( double[][] xy )

    {

        this._x = new double[xy.length];
        this._y = new double[xy.length];

        for ( int i = 0; i < xy.length; i++ ) {

            this._x[i] = xy[i][0];
            this._y[i] = xy[i][1];

        }

    }

    public AbstractInterpolator( double[] x, double[] y )

    {

        this._x = this.copyOf( x, x.length );
        this._y = this.copyOf( y, y.length );

    }

    public abstract double evaluate( double value );

    // Necessary because Arrays.copyOf() isn't in Java 1.5.  Drat!

    private double[] copyOf( double[] data, int new_length )

    {

	double[] new_data = new double[new_length];

	int l = ( data.length > new_length ) ? new_length : data.length;
        System.arraycopy( data, 0, new_data, 0, l );

	return new_data;

    }

}
