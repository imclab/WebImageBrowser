package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 * A matrix.
 * @author spl
 */
public class DoubleSquareMatrix
    extends AbstractDoubleSquareMatrix

{

    public static DoubleSquareMatrix createUnitMatrix( int dim )

    {

        DoubleSquareMatrix dsm = new DoubleSquareMatrix( dim );

        for ( int i = 0; i < dim; i++ )
            dsm.setElement( i, i, 1 );

        return dsm;

    }

    /**
     * Creates an instance of a square matrix.
     * @param dim Rank of the matrix.
     */
    public DoubleSquareMatrix( int dim )

    {

	super( dim );

    }
    
    public DoubleSquareMatrix( double[][] m )
        
    {
        
        this( m.length );
        
        for ( int j = 0; j < m.length; j++ )
	    for ( int i = 0; i < m[j].length; i++ )
		this.setElement( j, i, m[j][i] );

    }	    

}
