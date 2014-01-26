package edu.ucsd.ncmir.spl.Mogrification.thin_plate_spline_3d;

import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleSquareMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.SingularMatrixException;
import java.util.ArrayList;



/**
 *
 * @author spl
 */
public class ThinPlateSpline3D

{

    public ThinPlateSpline3D( ArrayList<Double> x,
			      ArrayList<Double> y,
			      ArrayList<Double> z,
			      ArrayList<Double> u,
			      ArrayList<Double> v,
			      ArrayList<Double> w )
        throws SingularMatrixException

    {

	this( x, y, z, u, v, w, false );

    }

    public ThinPlateSpline3D( ArrayList<Double> x,
			      ArrayList<Double> y,
			      ArrayList<Double> z,
			      ArrayList<Double> u,
			      ArrayList<Double> v,
			      ArrayList<Double> w,
			      boolean prescale )
        throws SingularMatrixException

    {

	this( x.toArray( new Double[0] ),
	      y.toArray( new Double[0] ),
	      z.toArray( new Double[0] ),
	      u.toArray( new Double[0] ),
	      v.toArray( new Double[0] ),
	      w.toArray( new Double[0] ),
	      prescale );

    }

    public ThinPlateSpline3D( Double[] X, Double[] Y, Double[] Z,
			      Double[] U, Double[] V, Double[] W )
        throws SingularMatrixException

    {

	this( X, Y, Z, U, V, W, false );

    }

    public ThinPlateSpline3D( Double[] X, Double[] Y, Double[] Z,
			      Double[] U, Double[] V, Double[] W,
			      boolean prescale )
        throws SingularMatrixException

    {

	double[] x = new double[X.length];
	double[] y = new double[Y.length];
	double[] z = new double[Z.length];

	double[] u = new double[U.length];
	double[] v = new double[V.length];
	double[] w = new double[W.length];

	for ( int i = 0; i < x.length; i++ ) {

	    x[i] = X[i].doubleValue();
	    y[i] = Y[i].doubleValue();
	    z[i] = Z[i].doubleValue();

	    u[i] = U[i].doubleValue();
	    v[i] = V[i].doubleValue();
	    w[i] = W[i].doubleValue();

	}

	this.init( x, y, z, u, v, w, prescale );

    }

    public ThinPlateSpline3D( double[] x, double[] y, double[] z,
			      double[] u, double[] v, double[] w )
        throws SingularMatrixException

    {

	this( x, y, z, u, v, w, false );

    }

    public ThinPlateSpline3D( double[] x, double[] y, double[] z,
			      double[] u, double[] v, double[] w,
			      boolean prescale )
        throws SingularMatrixException

    {

	this.init( x, y, z, u, v, w, prescale );

    }


    private double[] _x;
    private double[] _y;
    private double[] _z;

    private DoubleVector _mAu;
    private DoubleVector _mBu;

    private DoubleVector _mAv;
    private DoubleVector _mBv;

    private DoubleVector _mAw;
    private DoubleVector _mBw;

    private double _mx = 1;
    private double _bx = 0;

    private double _my = 1;
    private double _by = 0;

    private double _mz = 1;
    private double _bz = 0;

    private void init( double[] x, double[] y, double[] z,
                       double[] u, double[] v, double[] w,
		       boolean prescale )
        throws SingularMatrixException

    {

	if ( prescale ) {

	    double[] mb = new double[2];

	    x = ThinPlateSpline3D.copyOf( x, x.length );
	    y = ThinPlateSpline3D.copyOf( y, y.length );
	    z = ThinPlateSpline3D.copyOf( z, z.length );

	    u = ThinPlateSpline3D.copyOf( u, u.length );
	    v = ThinPlateSpline3D.copyOf( v, v.length );
	    w = ThinPlateSpline3D.copyOf( w, w.length );

	    this.prescale( x, u, mb );

	    this._mx = mb[0];
	    this._bx = mb[1];

	    this.prescale( y, v, mb );

	    this._my = mb[0];
	    this._by = mb[1];

	    this.prescale( z, w, mb );

	    this._mz = mb[0];
	    this._bz = mb[1];

	}

	this._x = ThinPlateSpline3D.copyOf( x, x.length );
	this._y = ThinPlateSpline3D.copyOf( y, y.length );
	this._z = ThinPlateSpline3D.copyOf( z, z.length );

	DoubleMatrix[] P = new DoubleMatrix[1];
	DoubleSquareMatrix[] Q_inverse = new DoubleSquareMatrix[1];
	DoubleMatrix[] B = new DoubleMatrix[1];
	DoubleSquareMatrix[] A_inverse = new DoubleSquareMatrix[1];

	this.matrix( x, y, z, P, Q_inverse, B, A_inverse );

	DoubleVector[] mA = new DoubleVector[1];
	DoubleVector[] mB = new DoubleVector[1];

	this.createCoefficients( P[0], Q_inverse[0], B[0], A_inverse[0],
				 new DoubleVector( u ),
				 mA, mB );

	this._mAu = mA[0];
	this._mBu = mB[0];

	this.createCoefficients( P[0], Q_inverse[0], B[0], A_inverse[0],
				 new DoubleVector( v ),
				 mA, mB );

	this._mAv = mA[0];
	this._mBv = mB[0];

	this.createCoefficients( P[0], Q_inverse[0], B[0], A_inverse[0],
				 new DoubleVector( w ),
				 mA, mB );

	this._mAw = mA[0];
	this._mBw = mB[0];

    }

    /*
     * Lifted from Dave Eberly's Wild Magic:
     *
     * Geometric Tools, LLC
     * Copyright (c) 1998-2011
     * Distributed under the Boost Software License, Version 1.0.
     * http://www.boost.org/LICENSE_1_0.txt
     * http://www.geometrictools.com/License/Boost/LICENSE_1_0.txt
     *
     * File Version: 5.0.2 (2011/05/22)
     *
     * Wm5IntpThinPlateSpline3.cpp
     *
     * Modified for C from C++ by spl, March 21, 2012
     */

    private void matrix( double[] mX, double[] mY, double[] mZ,
			 DoubleMatrix[] P, DoubleSquareMatrix[] Q_inverse,
			 DoubleMatrix[] B, DoubleSquareMatrix[] A_inverse )
        throws SingularMatrixException

    {

	int mQuantity = mX.length;

	// Compute matrix A = M + lambda*I [NxN matrix].

	DoubleSquareMatrix A = new DoubleSquareMatrix( mQuantity );

	for ( int row = 0; row < mQuantity; ++row )
	    for ( int col = 0; col < mQuantity; ++col ) {

		double m;
		if ( row == col )
		    m = 0;
		else {

		    double dx = mX[row] - mX[col];
		    double dy = mY[row] - mY[col];
		    double dz = mZ[row] - mZ[col];
		    double t = Math.sqrt( ( dx * dx ) +
					  ( dy * dy ) +
					  ( dz * dz ) );

		    m = -Math.abs( t );

		}
		A.setElement( row, col, m );

	    }

	// Compute matrix B [Nx4 matrix].

	B[0] = new DoubleMatrix( mQuantity, 4 );

	for ( int row = 0; row < mQuantity; ++row ) {

	    B[0].setElement( row, 0, 1.0 );
	    B[0].setElement( row, 1, mX[row] );
	    B[0].setElement( row, 2, mY[row] );
	    B[0].setElement( row, 3, mZ[row] );

	}

	// Compute A^{-1}.

	A_inverse[0] = ( DoubleSquareMatrix ) A.inverse();

	// Compute P = B^t A^{-1} [4xN matrix].

	P[0] = ( DoubleMatrix ) B[0].transpose().multiply( A_inverse[0] );

	// Compute Q = P B = B^t A^{-1} B  [4x4 matrix].

	DoubleSquareMatrix Q = ( DoubleSquareMatrix ) P[0].multiply( B[0] );

	// Compute Q^{-1}.

	Q_inverse[0] = ( DoubleSquareMatrix ) Q.inverse();

    }

    private void createCoefficients( DoubleMatrix P,
				     DoubleSquareMatrix Q_inverse,
				     DoubleMatrix B,
				     DoubleSquareMatrix A_inverse,
				     DoubleVector f,
				     DoubleVector[] mA, DoubleVector[] mB )

    {


	// Compute P*w.

	DoubleVector prod = ( DoubleVector ) P.multiply( f );

	// Compute 'b' vector for smooth thin plate spline.

	mB[0] = ( DoubleVector ) Q_inverse.multiply( prod );

	// Compute w-B*b.

	DoubleVector tmp = ( DoubleVector ) f.subtract( B.multiply( mB[0] ) );

	// Compute 'a' vector for smooth thin plate spline.

	mA[0] = ( DoubleVector ) A_inverse.multiply( tmp );

    }

    public double[] transform( double[] xyz )

    {

	return this.transform( xyz[0], xyz[1], xyz[2] );

    }

    public double[] transform( double x, double y, double z )

    {

	x = ( this._mx * x ) + this._bx;
	y = ( this._my * y ) + this._by;
	z = ( this._mz * z ) + this._bz;

	return new double[] {
	    ( this.transformComponent( x, y, z,
				       this._mAu,
				       this._mBu ) - this._bx ) / this._mx,
	    ( this.transformComponent( x, y, z,
				       this._mAv,
				       this._mBv ) - this._by ) / this._my,
	    ( this.transformComponent( x, y, z,
				       this._mAw,
				       this._mBw ) - this._bz ) / this._mz,
	};

    }

    private double transformComponent( double x, double y, double z,
                                       DoubleVector mA, DoubleVector mB )

    {

	double result = mB.getComponent( 0 ) +
	    ( mB.getComponent( 1 ) * x ) +
	    ( mB.getComponent( 2 ) * y ) +
	    ( mB.getComponent( 3 ) * z );

	for ( int i = 0; i < this._x.length; i++ ) {

	    double dx = x - this._x[i];
	    double dy = y - this._y[i];
	    double dz = z - this._z[i];
	    double t = Math.sqrt( ( dx * dx ) + ( dy * dy ) + ( dz * dz ) );

	    result += mA.getComponent( i ) * -Math.abs( t );

	}

	return result;

    }

    private void prescale( double[] a, double[] b, double[] mb )

    {

	double min = a[0];
	double max = a[0];
	for ( int i = 1; i < a.length; i++ )
	    if ( min > a[i] )
		min = a[i];
	    else if ( max < a[i] )
		max = a[i];

	for ( int i = 1; i < b.length; i++ )
	    if ( min > b[i] )
		min = b[i];
	    else if ( max < b[i] )
		max = b[i];

	mb[0] = 1.0 / ( max - min );
	mb[1] = -( mb[0] * min );

	for ( int i = 0; i < a.length; i++ ) {

	    a[i] = ( mb[0] * a[i] ) + mb[1];
	    b[i] = ( mb[0] * b[i] ) + mb[1];

	}

    }

    private static double[] copyOf( double[] in, int length )

    {

	double[] out = new double[length];

        System.arraycopy( in, 0, out, 0, length );

	return out;

    }

}
