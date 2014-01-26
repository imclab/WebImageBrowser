package edu.ucsd.ncmir.spl.Mogrification.weighted_least_squares;

/**
 *
 * @author spl
 */

class WeightedLeastSquaresSolver

{

    private double _delta;
    private double[] _X;
    private double[] _Y;

    private double[] _Z;

    private static final int MAXTERMS = 10;

    private double[][] _A = new double[MAXTERMS][MAXTERMS];

    private double[] _W;
    private int _maxterms;

    public WeightedLeastSquaresSolver( double delta,
                                       double[] X, double[] Y, double[] Z )

    {

	this._delta = delta;
        this._X = X;
        this._Y = Y;
        this._Z = Z;

        int N = X.length;
        this._W = new double[N];

        int terms;
        double minrms = Double.MAX_VALUE;

        for ( terms = 3; terms <= MAXTERMS; terms++ ) {

            double rms = 0;
            for ( int i = 0; i < N; i++ ) {

                // Initialize W: the weights of the N control points.

                for ( int j = 0; j < N; j++ ) {

                    double dx = X[i] - X[j];
                    double dx2 = dx * dx;

                    double dy = Y[i] - Y[j];
                    double dy2 = dy * dy;

		    this._W[j] = 1.0 / Math.sqrt( dx2 + dy2 + delta );

		}

		// Initialize A.

		for ( int j = 0; j < terms; j++ )
		    this._A[j][j] = this.initializeAlpha( j, j );

		for ( int j = 0; j < terms; j++ )
		    for ( int k = 0; k < j; k++ )
			this._A[j][k] = this.initializeAlpha( j, k );

		// Compute error at each control point over all terms.

		double f = 0;
		for ( int t = 0; t < terms; t++ ) {

		    double a = this.coef( t );
		    double p = this.poly( t, X[i], Y[i] );

		    f += a * p;

		}

                double error = f - this._Z[i];

                rms += error * error;

	    }

            rms = Math.sqrt( rms / N );
            if ( minrms > rms ) {

                this._maxterms = terms;
                minrms = rms;

            }

	}

    }

    private double basis( int f, double x, double y )

    {

	double h;

	switch ( f ) {

	case 0: {

	    h = 1.0;
	    break;

	}
	case 1: {

	    h = x;
	    break;

	}
	case 2: {

	    h = y;
	    break;

	}
	case 3: {

	    h = x * x;
	    break;

	}
	case 4: {

	    h = x * y;
	    break;

	}
	case 5: {

	    h = y * y;
	    break;

	}
	case 6: {

	    h = x * x * x;
	    break;

	}
	case 7: {

	    h = x * x * y;
	    break;

	}
	case 8: {

	    h = x * y * y;
	    break;

	}
	case 9: {

	    h = y * y * y;
	    break;

	}
	default: {

            throw new UnsupportedOperationException( "Unexpected f (" +
                                                     f + ")" );

	}

	}

	return h;

    }


    private double coef( int k )

    {

	double num = 0;
	double denum = 0;

	for ( int i = 0; i < this._X.length; i++ ) {

	    double p = this.poly( k, this._X[i], this._Y[i] );

	    num += this._W[i] * this._Z[i] * p;
	    denum += this._W[i] * p * p;

	}

	return num / denum;

    }

    private double initializeAlpha( int j, int k )

    {

	double a;

	if ( k == 0 )
	    a = 1.0;
	else if ( k == j ) {

	    double num = 0;
	    double denum = 0;

	    for ( int i = 0; i < this._X.length; i++ ) {

		double h = this.basis( j, this._X[i], this._Y[i] );

		num += this._W[i];

		denum += this._W[i] * h;

	    }
	    a = -num / denum;

	} else {

	    double num = 0;
	    double denum = 0;

	    for ( int i = 0; i < this._X.length; i++ ) {

		double h = this.basis( j, this._X[i], this._Y[i] );
		double p = this.poly( k, this._X[i], this._Y[i] );

		num += this._W[i] * p * h;
		denum += this._W[i] * p * p;

	    }
	    a = -this._A[j][j] * num / denum;

	}

	return a;

    }

    private double poly( int k, double x, double y )

    {

	double p = 0;

	for ( int i = 0; i < k; i++ )
	    p += this._A[k][i] * this.poly( i, x, y );
	p += this._A[k][k] * this.basis( k, x, y );

	return p;

    }

    public double approximate( double x, double y )

    {

	// Initialize W: the weights of the N control points.

	for ( int i = 0; i < this._X.length; i++ ) {

	    double dx = x - this._X[i];
	    double dx2 = dx * dx;

	    double dy = y - this._Y[i];
	    double dy2 = dy * dy;

	    this._W[i] = 1.0 / Math.sqrt( dx2 + dy2 + this._delta );

	}

	// Initialize A.

	for ( int j = 0; j < this._maxterms; j++ )
	    this._A[j][j] = this.initializeAlpha( j, j );

	for ( int j = 0; j < this._maxterms; j++ )
	    for ( int k = 0; k < j; k++ )
		this._A[j][k] = this.initializeAlpha( j, k );

	// Evaluate the surface at (x,y) over all terms.

	double f = 0;

	for ( int t = 0; t < this._maxterms; t++ ) {

	    double a = this.coef( t );
	    double p = this.poly( t, x, y );

	    f += a * p;

	}

	return f;

    }

}