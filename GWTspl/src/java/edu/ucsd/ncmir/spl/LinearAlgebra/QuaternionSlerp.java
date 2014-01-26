package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */

public final class QuaternionSlerp

{
    /*
     * Algorithm: Ken Shoemake SIGGRAPH `85 paper, pp. 245-254.
     */

    private Quaternion _q0;
    private Quaternion _q1;
    private double _theta;
    private double _sin_theta;

    public QuaternionSlerp( Quaternion Q0, Quaternion Q1 )

    {

	this._q0 = Q0.normalized();
	this._q1 = Q1.normalized();

	double cos_theta = this._q0.dotProduct( this._q1 );

	if ( cos_theta < -1.0 )
	    cos_theta = -1.0;
	else if ( cos_theta > 1.0 )
	    cos_theta = 1.0;

	this._theta = Math.acos( cos_theta );
	this._sin_theta = Math.sin( this._theta );

    }

    public Quaternion interpolate( double u )

    {

	Quaternion slerp;

	if ( this._sin_theta != 0 ) {

	    double sin_theta_1_minus_u =
		Math.sin( this._theta * ( 1 - u ) ) / this._sin_theta;
	    double sin_theta_u =
		Math.sin( this._theta * u ) / this._sin_theta;

	    Quaternion term1 = this._q0.scalarMultiply( sin_theta_1_minus_u );
	    Quaternion term2 = this._q1.scalarMultiply( sin_theta_u );

	    slerp = term1.add( term2 );

	} else {

	    Quaternion q0conj = this._q0.conjugate();
	    Quaternion product = q0conj.multiply( this._q1 );
	    Quaternion log_product = product.log();
	    Quaternion log_term = log_product.scalarMultiply( u );
	    Quaternion term = log_term.exp();

	    slerp = this._q0.multiply( term );

	}

	return slerp.normalize();

    }

    public static Quaternion squad( Quaternion p, Quaternion a,
				    Quaternion b, Quaternion q,
				    double u )

    {

	Quaternion s1 = new QuaternionSlerp( p, q ).interpolate( u );
	Quaternion s2 = new QuaternionSlerp( a, b ).interpolate( u );

	return new QuaternionSlerp( s1, s2 ).interpolate( ( 2.0 * u ) *
							  ( 1.0 - u ) );

    }

}