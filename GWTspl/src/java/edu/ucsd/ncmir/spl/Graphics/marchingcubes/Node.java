package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

/**
 *
 * @author spl
 */

public class Node

{

    private float _x;
    private float _y;
    private float _z;
    private float _value;
    private boolean _valid;
        
    public void setValue( double value )
        
    {
        
        this._value = ( float ) value;
        
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

    double getValue() 

    {

        return this._value;

    }

    boolean isValid() 

    {

        return this._valid;

    }

    public void setX( double x )
        
    {
        
        this._x = ( float ) x;
        
    }

    public void setY( double y )
        
    {
        
        this._y = ( float ) y;
        
    }

    public void setZ( double z )
        
    {
        
        this._z = ( float ) z;
        
    }

    public void setValid( boolean valid )
        
    {
        
        this._valid = valid;
        
    }

}
