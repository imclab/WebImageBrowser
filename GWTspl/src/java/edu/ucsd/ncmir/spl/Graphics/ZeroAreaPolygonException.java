package edu.ucsd.ncmir.spl.Graphics;

/**
 *
 * @author spl
 */
public class ZeroAreaPolygonException
    extends Exception

{

    public ZeroAreaPolygonException()
    {
    }

    @Override
    public String toString()

    {

        return "Polygon is zero area.";

    }

}
