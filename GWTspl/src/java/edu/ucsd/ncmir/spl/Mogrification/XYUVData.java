package edu.ucsd.ncmir.spl.Mogrification;

public class XYUVData

{

    private double _x;
    private double _y;
    private double _u;
    private double _v;

    XYUVData( double x, double y, double u, double v )

    {

	this._x = x;
	this._y = y;
	this._u = u;
	this._v = v;

    }

    XYUVData( Point2D xy, Point2D uv )

    {

        this( xy.getX(), xy.getY(), uv.getX(), uv.getY() );

    }

    XYUVData( double[] xy, double[] uv )

    {

	this( xy[0], xy[1], uv[0], uv[1] );

    }

    public double[] getXY()

    {

	double[] xy = new double[2];

	xy[0] = this._x;
	xy[1] = this._y;

	return xy;

    }

    public double[] getUV()

    {

	double[] uv = new double[2];

	uv[0] = this._u;
	uv[1] = this._v;

	return uv;

    }

    public double getX()

    {

	return this._x;

    }
    public double getY()

    {

	return this._y;

    }
    public double getU()

    {

	return this._u;

    }
    public double getV()

    {

	return this._v;

    }

    @Override
    public boolean equals( Object o )

    {

	boolean equals = false;

	if ( o instanceof XYUVData ) {

	    XYUVData d = ( XYUVData ) o;

	    equals =
		( d._x == this._x ) &&
		( d._y == this._y ) &&
		( d._u == this._u ) &&
		( d._v == this._v );

	}

	return equals;

    }

    @Override
    public int hashCode()

    {

        int hash = 7;

        hash = 53 * hash + ( this._x + "" ).hashCode();
        hash = 53 * hash + ( this._y + "" ).hashCode();
        hash = 53 * hash + ( this._u + "" ).hashCode();
        hash = 53 * hash + ( this._v + "" ).hashCode();

        return hash;

    }

}
