package edu.ucsd.ncmir.spl.Interpolator;

/**
 *
 * @author spl
 */

public class ParametricXYInterpolation

{

    private double[] _t;
    private double[] _u;
    private double[] _v;
    private boolean _cyclic;

    public ParametricXYInterpolation( double[][] xy, boolean cyclic )
	throws IllegalArgumentException

    {

        double x[] = new double[xy.length];
        double y[] = new double[xy.length];

         for ( int i = 0; i < xy.length; i++ ) {

            x[i] = xy[i][0];
            y[i] = xy[i][1];

        }

        this.setup( x, y, cyclic );

   }

    public ParametricXYInterpolation( double[] x, double[] y, boolean cyclic )
	throws IllegalArgumentException

    {

        this.setup( x, y, cyclic );

    }

    private void setup( double[] x, double[] y, boolean cyclic )
	throws IllegalArgumentException

    {

	if ( x.length != y.length )
	    throw new IllegalArgumentException( "x length != y length" );

	int size = x.length + ( cyclic ? 1 : 0 );

	this._cyclic = cyclic;
	this._t = new double[size];
	this._u = new double[size];
	this._v = new double[size];
	
	this._t[0] = 0;
	this._u[0] = x[0];
	this._v[0] = y[0];

	for ( int i = 1; i < size; i++ ) {

	    this._u[i] = x[i % x.length];
	    this._v[i] = y[i % y.length];

	    double du = this._u[i] - this._u[i - 1];
	    double dv = this._v[i] - this._v[i - 1];
	    
	    this._t[i] =
                Math.sqrt( ( du * du ) + ( dv * dv ) ) + this._t[i - 1];

	}
	    
    }

    public void interpolate( Instantiator instantiator, double[][] xy )

    {

        double[] x = new double[xy.length];
        double[] y = new double[xy.length];

        this.interpolate( instantiator, x, y );

        for ( int i = 0; i < x.length; i++ ) {

            xy[i][0] = x[i];
            xy[i][1] = y[i];

        }

    }

    public void interpolate( Instantiator instantiator,
			     double[] x, double[] y )

    {

	if ( x.length != y.length )
	    throw new IllegalArgumentException( "x length != y length" );
        else if ( x.length < 2 )
            throw new IllegalArgumentException( "Insufficient output points." );
	
	double l = x.length - ( this._cyclic ? 0 : 1 );
	double dt = this._t[this._t.length - 1] / l;
	this.interpolate( instantiator, this._u, dt, x );
	this.interpolate( instantiator, this._v, dt, y );

    }

    private void interpolate( Instantiator instantiator,
			      double[] in, double dt, double[] out )

    {

	AbstractInterpolator interpolator = 
	    instantiator.instantiate( this._t, in );

	for ( int i = 0; i < out.length; i++ )
	    out[i] = interpolator.evaluate( i * dt );

    }

}