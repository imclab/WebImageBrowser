package edu.ucsd.ncmir.spl.LinearAlgebra;

/**
 *
 * @author spl
 */
public class Double3Vector
    extends DoubleVector

{

    public Double3Vector()

    {

        super( 3 );

    }
    
    public Double3Vector( double x, double y, double z )
        
    {
        
        this( new double[] { x, y, z } );
        
    }

    public Double3Vector( double[] u )

    {

        super( u );

    }

    public Double3Vector multiply( Double3Vector v )

    {

	return new Double3Vector( ( this._v[1] * v._v[2] ) -
				  ( this._v[2] * v._v[1] ),
				  ( this._v[2] * v._v[0] ) -
				  ( this._v[0] * v._v[2] ),
				  ( this._v[0] * v._v[1] ) -
				  ( this._v[1] * v._v[0] ) );

    }

}