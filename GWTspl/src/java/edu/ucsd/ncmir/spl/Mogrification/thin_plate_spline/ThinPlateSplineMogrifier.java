package edu.ucsd.ncmir.spl.Mogrification.thin_plate_spline;

import edu.ucsd.ncmir.spl.LinearAlgebra.AbstractDoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleSquareMatrix;
import edu.ucsd.ncmir.spl.LinearAlgebra.DoubleVector;
import edu.ucsd.ncmir.spl.LinearAlgebra.SingularMatrixException;
import edu.ucsd.ncmir.spl.Mogrification.Mogrifier;


/**
 *
 * @author spl
 */

public final class ThinPlateSplineMogrifier
    implements Mogrifier

{

    private double[][][] _corrected;

    public ThinPlateSplineMogrifier( double[][] a, double b[][] )
        throws InsufficientCorrespondencesException,
               SingularMatrixException

    {

	this._corrected = this.eliminateDuplicates( a, b );

	this._Wa = this.computeCoefficients( this._corrected[0],
					     this._corrected[1] );

    }

    @Override
    public double[] mogrify( double[] xy )

    {

	return new double[] {
	    this.computeTransform( xy, this._Wa[0] ),
	    this.computeTransform( xy, this._Wa[1] )
	};

    }

    private AbstractDoubleVector[] _Wa = null;
    private double[][] _x0y0;

    public double[][] getCoefficients()
        throws Exception

    {

	double[][] v = null;

	if ( this._Wa != null ) {

	    int l = this._Wa.length;

	    v = new double[l][];

	    for ( int j = 0; j < l; j++ ) {

		int n = this._Wa[j].dimension();

		v[j] = new double[n];
		for ( int i = 0; i < n; i++ )
		    v[j][i] = this._Wa[j].getComponent( i );

	    }

	} else
            throw new Exception( "Not initialized!" );

	return v;

    }

    public double[][] getX0Y0()

    {

	return this._x0y0;

    }

    public double[][][] eliminateDuplicates( double[][] a, double b[][] )

    {

	boolean[] scoreboard = new boolean[a[0].length];
	for ( int i = 0; i < scoreboard.length; i++ )
	    scoreboard[i] = false;

	int dupes =
	    this.checkDupes( a, scoreboard ) + this.checkDupes( b, scoreboard );

	double[][][] corrected = new double[2][][];

	if ( dupes == 0 ) {

	    corrected[0] = a;
	    corrected[1] = b;

	} else {

	    int n = scoreboard.length - dupes;
	    double[][] new_a = new double[2][n];
	    double[][] new_b = new double[2][n];

	    for ( int i = 0, j = 0; i < scoreboard.length; i++ )
		if ( !scoreboard[i] ) {

		    new_a[0][j] = a[0][i];
		    new_a[1][j] = a[1][i];

		    new_b[0][j] = b[0][i];
		    new_b[1][j] = b[1][i];

		    j++;

		}

	    corrected[0] = new_a;
	    corrected[1] = new_b;

	}

	return corrected;

    }

    private int checkDupes( double[][] x, boolean[] scoreboard )

    {

	int dupes = 0;

	for ( int i = 0; i < x[0].length - 1; i++ )
	    for ( int j = i + 1; j < x[0].length; j++ )
		if ( !scoreboard[j] &&
		     ( ( x[0][i] == x[0][j] ) &&
		       ( x[1][i] == x[1][j] ) ) ) {

		    dupes++;
		    scoreboard[j] = true;

		}

        return dupes;

    }

    private AbstractDoubleVector[] computeCoefficients( double[][] xy,
                                                        double[][] uv )
	throws InsufficientCorrespondencesException,
               SingularMatrixException

    {

	if ( xy[0].length < 3 )
	    throw new InsufficientCorrespondencesException();

	this._x0y0 = xy;

	int n = xy[0].length;
	int Ldim = n + 3;
	DoubleSquareMatrix L = new DoubleSquareMatrix( Ldim );
	DoubleVector X = new DoubleVector( Ldim );
	DoubleVector Y = new DoubleVector( Ldim );

	for ( int j = 0; j < n; j++ ) {

	    L.setElement( j, j, 0 );
	    for ( int i = j + 1; i < n; i++ ) {

		double phival =
		    this.phi( xy[0][i], xy[1][i], xy[0][j], xy[1][j] );
		L.setElement( i, j, phival );
		L.setElement( j, i, phival );

	    }

	    L.setElement( j, n, 1.0 );
	    L.setElement( n, j, 1.0 );

	    L.setElement( n + 1, j, xy[0][j] );
	    L.setElement( j, n + 1, xy[0][j] );

	    L.setElement( n + 2, j, xy[1][j] );
	    L.setElement( j, n + 2, xy[1][j] );

	    X.setComponent( j, uv[0][j] );
	    Y.setComponent( j, uv[1][j] );

	}

	DoubleSquareMatrix Linv = ( DoubleSquareMatrix ) L.inverse();

	return new AbstractDoubleVector[] {

	    Linv.multiply( X ),
	    Linv.multiply( Y )

	};

    }

    private double phi( double x1, double y1, double x2, double y2 )

    {

	double dx = x1 - x2;
	double dy = y1 - y2;
	double r = ( dx * dx ) + ( dy * dy );

	return r * ( ( r > 0 ) ? Math.log( r ) : 0 );

    }


    private double computeTransform( double[] pq, AbstractDoubleVector Wa )

    {

	int n = Wa.dimension() - 3;
	double z =
	    Wa.getComponent( n ) +
	    ( Wa.getComponent( n + 1 ) * pq[0] ) +
	    ( Wa.getComponent( n + 2 ) * pq[1] );

	for ( int i = 0; i < n; i++ )
	    z += Wa.getComponent( i ) *
		this.phi( this._x0y0[0][i], this._x0y0[1][i], pq[0], pq[1] );

	return z;

    }


}
