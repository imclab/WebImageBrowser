package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */
public class AbstractDoubleSquareMatrix
    extends AbstractDoubleMatrix
{

    /**
     * Constructs an empty square matrix.
     * @param dim Rank of the matrix.
     */
    public AbstractDoubleSquareMatrix( int dim )

    {

	super( dim, dim );

    }

    /**
     * Computes the inverse of this matrix.
     * @return A new matrix containing the inverse of this matrix.
     * @throws SingularMatrixException if the matrix is singular and, thus,
     * non-invertible.
     */
    public AbstractDoubleSquareMatrix inverse()
        throws SingularMatrixException

    {

        int dim = this.rows();

	AbstractDoubleSquareMatrix inv =  new DoubleSquareMatrix( dim );

	LUDecomposition lu = new LUDecomposition( this );

	double[] b = new double[dim];

	// Invert matrix by solving n simultaneous equations n times
	for ( int i = 0; i < dim; i++ ) {

	    for( int j = 0; j < dim; j++ )
		b[j] = 0.0;

	    b[i] = 1.0;

	    // Into a row of inv: fix later

	    double[] row = lu.luSolve( b );

	    for ( int j = 0; j < dim; j++ )
		inv.setElement( i, j, row[j] );

	}

	// Transpose matrix

	for ( int i = 0; i < dim; i++ )
	    for ( int j = 0; j < i; j++ ) {

		double temp = inv.getElement( i, j );

		inv.setElement( i, j, inv.getElement( j,i ) );
		inv.setElement( j, i, temp );

	    }

	return inv;

    }

}