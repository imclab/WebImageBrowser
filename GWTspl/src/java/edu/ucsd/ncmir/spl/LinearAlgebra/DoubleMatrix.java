package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 * A matrix.  Emulation of the JSci <code>DoubleMatrix</code>.
 * @author spl
 */
public class DoubleMatrix
    extends AbstractDoubleMatrix

{

    /**
     * Creates an instance of a <code>DoubleMatrix</code> with the
     * specified number of rows and columns.
     * @param rows Number of rows in the matrix.
     * @param cols Number of columns in the matrix.
     */
    public DoubleMatrix( int rows, int cols )

    {

	super( rows, cols );

    }

    /**
     * Computes the transpose of the matrix.
     * @return The transpose of the matrix.
     */
    public DoubleMatrix transpose()

    {

        int m = this.rows();
        int n = this.columns();

        DoubleMatrix t = new DoubleMatrix( n, m );

        for ( int j = 0; j < m; j++ )
            for ( int i = 0; i < n; i++ )
                t.setElement( i, j, this.getElement( j, i ) );

        return t;

    }

}
