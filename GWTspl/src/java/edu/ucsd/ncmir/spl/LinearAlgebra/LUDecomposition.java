package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */
public class LUDecomposition

{

    private int[] _ps;
    private double[][] _lu;

    public LUDecomposition( AbstractDoubleSquareMatrix a )
        throws SingularMatrixException

    {

        int n = a.rows();

        this._ps = new int[n];
        this._lu = new double[n][n];

        double[] scales = new double[n];
        double biggest;

        for ( int i = 0; i < n; i++ ) {

            // For each row
            // Find the largest element in each row for row
            // equilibration

            biggest = 0.0;
            for ( int j = 0; j < n; j++ ) {

                double tempf =
		    Math.abs( this._lu[i][j] = a.getElement( i, j ) );

                if ( biggest < tempf )
                    biggest = tempf;

            }
            if ( biggest != 0.0 )
                scales[i] = 1.0 / biggest;
            else
                throw new SingularMatrixException( "Zero row" );

            this._ps[i] = i; // Initialize pivot sequence

        }
        for ( int k = 0; k < n - 1; k++ ) {

            // For each column
            // Find the largest element in each column to pivot around

            biggest = 0.0;
            int pivotindex = -1;
            for ( int i = k; i < n; i++ ) {

                double tempf = Math.abs( this._lu[this._ps[i]][k] ) *
                    scales[this._ps[i]];

                if ( biggest < tempf ) {

                    biggest = tempf;
                    pivotindex = i;

                }

            }
            if ( biggest == 0.0 )
                throw new SingularMatrixException( "Zero column" );
            if ( pivotindex != k ) {

                /* Update pivot sequence */
                int j = this._ps[k];
                this._ps[k] = this._ps[pivotindex];
                this._ps[pivotindex] = j;

            }

            /* Pivot, eliminating an extra variable  each time */
            double pivot = this._lu[this._ps[k]][k];

            for ( int i = k + 1; i < n; i++ ) {

                double mult;

                this._lu[this._ps[i]][k] =
		    mult = this._lu[this._ps[i]][k] / pivot;

                if ( mult != 0.0 )
                    for ( int j = k + 1; j < n; j++ )
                        this._lu[this._ps[i]][j] -=
			    mult * this._lu[this._ps[k]][j];

            }

        }
	if ( this._lu[this._ps[n - 1]][n - 1] == 0.0 )
	    throw new SingularMatrixException( "Matrix size: " + n );

    }

    public double[] luSolve( double[] b )

    {

        int n = b.length;
        double[] x = new double[n];

        // Vector reduction using U triangular matrix

        for ( int i = 0; i < n; i++ ) {

            double dot = 0.0;

            for ( int j = 0; j < i; j++ )
                dot += this._lu[this._ps[i]][j] * x[j];

            x[i] = b[this._ps[i]] - dot;

        }

        // Back substitution, in L triangular matrix

        for ( int i = n - 1; i >= 0; i-- ) {

            double dot = 0.0;

            for ( int j = i + 1; j < n; j++ )
                dot += this._lu[this._ps[i]][j] * x[j];

            x[i] = ( x[i] - dot ) / this._lu[this._ps[i]][i];

        }

        return x;

    }

}
