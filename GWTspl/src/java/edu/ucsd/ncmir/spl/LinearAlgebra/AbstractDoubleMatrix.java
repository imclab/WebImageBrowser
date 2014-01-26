package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 * Emulation of the JSci class of the same name.  Only the methods
 * necessary to provide support for basic matrix operations.
 * @author spl
 */
public class AbstractDoubleMatrix

{

    private double[][] _m;

    /**
     * Constructs a matrix with the specified rows and columns.
     * @param rows The number of rows in the matrix.
     * @param columns The number of columns in the matrix.
     */
    protected AbstractDoubleMatrix( int rows, int columns )

    {

	this._m = new double[rows][columns];

    }

    /**
     * Assings a value to the specified matrix element.
     * @param row The row in which the value is to be assigned.
     * @param col The column in which the value is to be assigned.
     * @param v The value to be assigned.
     */
    public void setElement( int row, int col, double v )

    {

        this._m[row][col] = v;

    }

    /**
     * Returns an element at the specified row and column.
     * @param row The row of the element.
     * @param col The column of the element.
     * @return The element's value.
     */
    public double getElement( int row, int col )

    {

        return this._m[row][col];

    }

    /**
     * The number of rows in the matrix.
     * @return The number of rows in the matrix.
     */
    public int rows()

    {

	return this._m.length;

    }

    /**
     * The number of columns in the matrix.
     * @return The number of columns in the matrix.
     */
    public int columns()

    {

	return this._m[0].length;

    }

    /**
     * Performs a matrix multiplication of this matrix by another.
     * @param m The multiplier matrix.
     * @return A new matrix containing the result of the
     * multiplication.
     */
    public AbstractDoubleMatrix multiply( AbstractDoubleMatrix m )

    {

	AbstractDoubleMatrix mr = null;

	if ( m.columns() == this.rows() ) {

	    int rows = m.rows();
	    int columns = this.columns();

	    int row_col = this.rows();

	    mr = new AbstractDoubleMatrix( rows, columns );

	    for ( int j = 0; j < rows; j++ )
		for ( int i = 0; i < columns; i++ ) {

		    double r = 0;

		    for ( int k = 0; k < row_col; k++ )
			r += this._m[j][k] * m._m[k][i];

		    mr._m[j][i] = r;

		}

	}

	return mr;

    }

    /**
     * Multiply this matrix by a vector, returning a vector.
     * @param v The vector.
     * @return A new vector containing the result of the
     * multiplication.
     */
    public AbstractDoubleVector multiply( AbstractDoubleVector v )

    {

	AbstractDoubleVector v1 = new AbstractDoubleVector( v.dimension() );

	for ( int row = 0; row < this.rows(); row++ ) {

	    double result = 0;

	    for ( int column = 0; column < this.columns(); column++ )
		result += this.getElement( row, column ) * v.getComponent( column );
	    v1.setComponent( row, result );

	}

        return v1;

    }

    /**
     * Returns a <code>String</code> representation of this matrix.
     * @return The <code>String</code>representation of this matrix.
     */
    @Override
    public String toString()

    {

	String s = "";

	for ( int row = 0; row < this.rows(); row++ ) {

	    s += "[ ";
	    for ( int column = 0; column < this.columns(); column++ )
		s += this._m[row][column] + " ";

	    s += "]\n";

	}

	return s;

    }

}