package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */

public final class QuaternionSpline

{

    private Quaternion _q1;
    private Quaternion _an;
    private Quaternion _anp1;
    private Quaternion _q2;

    public QuaternionSpline( Quaternion q0, Quaternion q1,
			     Quaternion q2, Quaternion q3 )

    {

	this._q1 = new Quaternion( q1 );
	this._an = QuaternionSpline.termAn( q0, q1, q2 );
	this._anp1 = QuaternionSpline.termAn( q1, q2, q3 );
	this._q2 = new Quaternion( q2 );

    }

    private static Quaternion termAn( Quaternion qnm1,
                                      Quaternion qn,
                                      Quaternion qnp1 )

    {

	Quaternion conj = qn.conjugate();

	Quaternion term1 = conj.multiply( qnp1 );
	Quaternion log_term1 = term1.log();

	Quaternion term2 = conj.multiply( qnm1 );
	Quaternion log_term2 = term2.log();

	Quaternion sum = log_term1.add( log_term2 );
	Quaternion term = sum.scalarMultiply( -1.0 / 4.0 );
	Quaternion exponentiated = term.exp();

	return qn.multiply( exponentiated );

    }

    public Quaternion interpolate( double u )

    {

	return QuaternionSlerp.squad( this._q1,
				      this._an,
				      this._anp1,
				      this._q2,
				      u );

    }

}

