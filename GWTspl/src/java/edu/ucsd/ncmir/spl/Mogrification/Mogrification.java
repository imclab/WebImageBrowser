package edu.ucsd.ncmir.spl.Mogrification;

/**
 *
 * @author spl
 */
public abstract class Mogrification

{

    private XYUVList _xyuv;

    protected Mogrification( XYUVList xyuv )

    {

        this._xyuv = xyuv;

    }

    public double[][] getXYLimits()

    {

        return this._xyuv.getXYLimits();

    }

    public double[][] getUVLimits()

    {

        return this._xyuv.getUVLimits();

    }

    protected double[][] getXY()

    {

        double[][] xy = new double[2][this._xyuv.size()];

        this._xyuv.getXY( xy[0], xy[1] );

        return xy;

    }

    protected double[][] getUV()

    {

        double[][] uv = new double[2][this._xyuv.size()];

        this._xyuv.getUV( uv[0], uv[1] );

        return uv;

    }
    @Override
    public boolean equals( Object o )

    {

	boolean equals = false;

	if ( o instanceof Mogrification ) {

	    Mogrification m = ( Mogrification ) o;

	    equals = m._xyuv.equals( this._xyuv );

	}

	return equals;

    }

    @Override
    public int hashCode()

    {
        int hash = 7;
        hash = 67 * hash + ( this._xyuv != null ? this._xyuv.hashCode() : 0 );
        return hash;

    }

    abstract public Mogrifier getForwardMogrifier()
        throws Exception;
    abstract public Mogrifier getInverseMogrifier()
        throws Exception;

}
