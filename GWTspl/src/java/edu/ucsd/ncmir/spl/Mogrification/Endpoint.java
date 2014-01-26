/*
 * Endpoint.java
 *
 * Created on August 14, 2006, 8:46 AM
 */

package edu.ucsd.ncmir.spl.Mogrification;

/**
 *
 * @author spl
 */
public class Endpoint
    extends Point2D
{

    /**
     * Creates a new instance of Endpoint
     */
    public Endpoint( double x, double y )
    {

        super( x, y );

    }

    public Endpoint( Point2D point2d )

    {

        super( point2d.getX(), point2d.getY() );

    }

}
