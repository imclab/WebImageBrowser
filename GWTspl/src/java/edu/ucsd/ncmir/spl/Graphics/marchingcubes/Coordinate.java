package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

import edu.ucsd.ncmir.spl.Graphics.geometry.GeometryComponent;

/**
 *
 * @author spl
 */
public class Coordinate
    implements GeometryComponent

{
    
    private GeometryComponent _parent;
    private double _x;
    private double _y;
    private double _z;

    Coordinate( GeometryComponent parent, double x, double y, double z )

    {

	this.setParent( parent );
	this.setX( x );
	this.setY( y );
	this.setZ( z );

    }
    
    public GeometryComponent[] getGeometryComponents()

    {

	return null;

    }

    public double[][] getComponentValues()

    {

	return new double[][] { { this.getX(), this.getY(), this.getZ() } };

    }

    public GeometryComponent getParent()

    {
	
	return this._parent;

    }

    public void setParent( GeometryComponent parent )

    {

        this._parent = parent;

    }

    public double getX()

    {

        return this._x;

    }

    public void setX( double x )

    {

        this._x = x;

    }

    public double getY()

    {

        return this._y;

    }

    public void setY( double y )

    {

        this._y = y;

    }

    public double getZ()

    {

        return this._z;

    }

    public void setZ( double z )

    {

        this._z = z;

    }

}
