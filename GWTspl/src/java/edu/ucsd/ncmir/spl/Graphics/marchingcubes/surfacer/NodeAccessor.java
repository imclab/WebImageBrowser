package edu.ucsd.ncmir.spl.Graphics.marchingcubes.surfacer;

import edu.ucsd.ncmir.spl.Graphics.PlanarPolygon;
import edu.ucsd.ncmir.spl.Graphics.PlanarPolygonTable;
import edu.ucsd.ncmir.spl.Graphics.ScanConvert;
import edu.ucsd.ncmir.spl.Graphics.marchingcubes.Node;
import edu.ucsd.ncmir.spl.Graphics.marchingcubes.VolumeNodeAccessor;
import edu.ucsd.ncmir.spl.Interpolator.AbstractInterpolator;
import edu.ucsd.ncmir.spl.Interpolator.Instantiator;
import edu.ucsd.ncmir.spl.Interpolator.LaGrangianInterpolator;
import edu.ucsd.ncmir.spl.Interpolator.ParametricXYInterpolation;

class NodeAccessor 
    implements VolumeNodeAccessor

{

    private Double[] _keys;
    private PlanarPolygonTable _table;
    private double _dgrid;
    private double _xmin;
    private double _ymin;
    private double _zmin;
    private double _dz;

    NodeAccessor( Double[] keys,
                  PlanarPolygonTable table,
                  double dgrid,
                  Capper capper, double xmin, double ymin, double dz )
    {

        this._keys = keys;
        this._table = table;
        this._dgrid = dgrid;
        this._xmin = xmin;
        this._ymin = ymin;
        this._zmin = keys[0].doubleValue() - ( capper.getCapTop() * dz );
        this._dz = dz;

    }

    @Override
    public boolean getPlane( int planeno, Node[][] plane )

    {

        double z = ( planeno * this._dz ) + this._zmin;

        for ( int j = 0; j < plane.length; j++ )
            for ( int i = 0; i < plane[j].length; i++ ) {

                plane[j][i].setX( this._xmin + ( i * this._dgrid ) );
                plane[j][i].setY( this._ymin + ( j * this._dgrid ) );
                plane[j][i].setZ( z );
                plane[j][i].setValue( 0 );
                plane[j][i].setValid( true );

            }

        if ( this._keys.length > planeno ) {

            Double key = this._keys[planeno];
            PlanarPolygon[] traces = this._table.getArray( key );
            int width =
		( int ) Math.ceil( this._xmin +
				   ( plane[0].length * this._dgrid ) );
            int height = 
		( int ) Math.ceil( this._ymin +
				   ( plane.length * this._dgrid ) );
            for ( PlanarPolygon trace : traces ) {

		double[][] xyin = trace.getXY();

                if ( ( xyin.length / 4 ) > 3 )
                    try {

                        double[][] xyout = new double[xyin.length / 4][2];

                        new ParametricXYInterpolation( xyin, true ).
                            interpolate( new LaGrangianInterpolatorInstatiator(),
                                         xyout );
                        new ParametricXYInterpolation( xyout, true ).
                            interpolate( new LaGrangianInterpolatorInstatiator(),
                                         xyin );

                    } catch ( Throwable t ) {
                        
                        // let's hope this never happens.
                        
                    }

                new Convert( width, height, 
			     xyin,
			     this._xmin, this._ymin,
			     this._dgrid, plane ).scanConvert();

                }
            
        }
	
        return true;

    }

    // This is indeed mildly ugly but I didn't feel like rewriting the 
    // innards of the interpolator.  Maybe later. . .
    
    private static class LaGrangianInterpolatorInstatiator
        implements Instantiator
    
    {

        @Override
        public AbstractInterpolator instantiate( double[] t, double[] x )
        {
            
            return new LaGrangianInterpolator( t, x );
            
        }
        
    }

    private class Convert
	extends ScanConvert

    {

        private double _xmin;
        private double _ymin;
        private double _dgrid;
        private Node[][] _plane;

        Convert( int width, int height,
		 double[][] xy, 
		 double xmin, double ymin,
		 double dgrid, Node[][] plane )

        {

            super( width, height, xy );

            this._xmin = xmin;
            this._ymin = ymin;
            this._dgrid = dgrid;
            this._plane = plane;

        }

        @Override
        public void handler( int x, int y )

        {

            int i = ( int ) Math.floor( ( x - this._xmin ) / this._dgrid );
            int j = ( int ) Math.floor( ( y - this._ymin ) / this._dgrid );

            this._plane[j][i].setValue( 1.0 );

	    int p = ( this._plane[0].length * j ) + i;

        }

    }

}
