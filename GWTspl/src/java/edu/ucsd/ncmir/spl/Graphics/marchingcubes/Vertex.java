package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

/**
 *
 * @author spl
 */

class Vertex

{

    private double _x;
    private double _y;
    private double _z;
    
    Vertex( double x, double y, double z )
        
    {
        
        this._x = x;
        this._y = y;
        this._z = z;
        
    }

    double getX() 

    {

        return this._x;

    }

    double getY() 

    {

        return this._y;

    }

    double getZ() 

    {

        return this._z;

    }

    void setX( double x )

    {

        this._x = x;

    }

    void setY( double y )

    {

        this._y = y;

    }

    void setZ( double z )

    {

        this._z = z;

    }

}
