/*
 * Correspondence.java
 *
 * Created on August 14, 2006, 8:46 AM
 */

package edu.ucsd.ncmir.spl.Mogrification;

/**
 *
 * @author spl
 */

public class Correspondence
    extends Line2D
{

    private static final long serialVersionUID = 42L; // To shut up compiler

    /** Creates a new instance of Correspondence */
    public Correspondence()

    {

        super();

    }

    public Correspondence( Endpoint p )
    {

	this( p, p );

    }

    public Correspondence( Endpoint p1, Endpoint p2 )

    {

	super( p1, p2 );

    }

    public Correspondence( Point2D p1, Point2D p2 )

    {

        super( p1, p2 );

    }

    public Correspondence( double x, double y, double u, double v )

    {

        super( new Endpoint( x, y ), new Endpoint( u, v ) );

    }

    public boolean equals( Correspondence correspondence    )

    {

	return this.getP1().equals( correspondence.getP1() ) &&
	    this.getP2().equals( correspondence.getP2() );

    }

    public void setEndpoint( Endpoint p )

    {

	this.setLine( this.getX1(), this.getY1(), p.getX(), p.getY() );

    }

}