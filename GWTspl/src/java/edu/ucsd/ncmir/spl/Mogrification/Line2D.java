package edu.ucsd.ncmir.spl.Mogrification;

/**
 *
 * @author spl
 */
public class Line2D

{

    private Point2D _p1;
    private Point2D _p2;

    public Line2D()

    {

    }

    public Line2D( Point2D p1, Point2D p2 )

    {

        this._p1 = p1;
        this._p2 = p2;

    }

    public Point2D getP1()

    {

        return this._p1;

    }

    public Point2D getP2()

    {

        return this._p2;

    }

    public void setLine( double x1, double y1, double x2, double y2 )

    {

        this._p1 = new Point2D( x1, y1 );
        this._p2 = new Point2D( x2, y2 );

    }

    public double getX1()

    {

        return this._p1.getX();

    }

    public double getY1()

    {

        return this._p1.getY();

    }

    public double getX2()

    {

        return this._p2.getX();

    }

    public double getY2()

    {

        return this._p2.getY();

    }

}
