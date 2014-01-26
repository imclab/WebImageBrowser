package edu.ucsd.ncmir.spl.Mogrification;

import java.util.ArrayList;
import java.util.Iterator;


public final class XYUVList
    extends ArrayList<XYUVData>

{

    private ArrayList<? extends Correspondence> _correspondences;

    public XYUVList( ArrayList<? extends Correspondence> correspondences )

    {

	this.setCorrespondences( correspondences );

        for ( Iterator<? extends Correspondence> it = correspondences.iterator();
              it.hasNext(); ) {

            Correspondence corr = it.next();
            this.add( new XYUVData( corr.getP1(), corr.getP2() ) );

        }

    }

    private double _xmin = Double.MAX_VALUE;
    private double _ymin = Double.MAX_VALUE;

    private double _umin = Double.MAX_VALUE;
    private double _vmin = Double.MAX_VALUE;

    private double _xmax = -Double.MAX_VALUE;
    private double _ymax = -Double.MAX_VALUE;

    private double _umax = -Double.MAX_VALUE;
    private double _vmax = -Double.MAX_VALUE;

    @Override
    public boolean add( XYUVData data )

    {

        double[] xy = data.getXY();

        if ( this._xmin > xy[0] )
            this._xmin = xy[0];
        if ( this._ymin > xy[1] )
            this._ymin = xy[1];

        if ( this._xmax < xy[0] )
            this._xmax = xy[0];
        if ( this._ymax < xy[1] )
            this._ymax = xy[1];

        return super.add( data );

    }

    public double[][] getXYLimits()

    {

	return new double[][] {
	    { this._xmin, this._ymin },
	    { this._xmax, this._ymax }
	};

    }

    public double[][] getUVLimits()

    {

	return new double[][] {
	    { this._umin, this._vmin },
	    { this._umax, this._vmax }
	};

    }

    public void getUV( double[] U, double[] V )

    {

	int i = 0;
	for ( XYUVData xyuv_data : this ) {

	    U[i] = xyuv_data.getU();
	    V[i] = xyuv_data.getV();
	    i++;

	}

    }

    public void getXY( double[] X, double[] Y )

    {

	int i = 0;
	for ( XYUVData xyuv_data : this ) {

	    X[i] = xyuv_data.getX();
	    Y[i] = xyuv_data.getY();
	    i++;

	}

    }

    ArrayList<? extends Correspondence> getCorrespondences()

    {

        return this._correspondences;

    }

    final void setCorrespondences( ArrayList<? extends Correspondence> correspondences )

    {

        this._correspondences = correspondences;

    }

    @Override
    public boolean equals( Object o )

    {

        boolean equals = false;

        if ( o instanceof XYUVList ) {

            XYUVList l = ( XYUVList ) o;

            if ( l.size() == this.size() ) {

                XYUVData[] dl = l.toArray( new XYUVData[0] );
                XYUVData[] dt = this.toArray( new XYUVData[0] );

                equals = true;

                for ( int i = 0; equals && ( i < dl.length ); i++ )
                    equals = dl[i].equals( dt[i] );

            }

        }
        return equals;

    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 67 * hash + ( this._correspondences != null ? this._correspondences.hashCode() : 0 );
        return hash;
    }


}
