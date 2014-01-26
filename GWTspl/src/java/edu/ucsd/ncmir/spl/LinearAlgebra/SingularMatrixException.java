package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 * An exception thrown when a matrix is singular.
 * @author spl
 */
public class SingularMatrixException
    extends Exception
{

    /**
     * An instance of a <code>SingularMatrixException</code>.
     * @param message A message to be reported.
     */
    public SingularMatrixException( String message )

    {

        super( message );

    }

}
