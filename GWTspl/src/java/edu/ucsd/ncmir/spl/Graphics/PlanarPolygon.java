package edu.ucsd.ncmir.spl.Graphics;

import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class PlanarPolygon
    extends ArrayList<Triplet>

{

    public PlanarPolygon( int[][] xylist, int z )
        throws ZeroAreaPolygonException

    {

        for ( int[] xy : xylist )
            this.add( new Triplet( xy[0], xy[1], z ) );

        this.compute();

    }


    public PlanarPolygon( ArrayList<Triplet> triplets )
        throws ZeroAreaPolygonException

    {

	super( triplets );
	this.compute();

    }

    public double[][] getXY()

    {

        double[][] xy = new double[this.size()][];

        int i = 0;
        for ( Triplet t : this )
            xy[i++] = t.getUV();

        return xy;

    }

    public Triplet[] getTriplets()

    {

	return this.toArray( new Triplet[0] );

    }

    public double[] getCentroid()

    {

	return new double[] { this._xcentroid, this._ycentroid };

    }

    public double[] getCenter()

    {

	return new double[] { this._xcenter, this._ycenter };

    }

    public double getRadius()

    {

	return this._radius;

    }

    private double _xcentroid = 0;
    private double _ycentroid = 0;

    private double _radius = 0;
    private double _xcenter = 0;
    private double _ycenter = 0;

    private double _xmin = Double.MAX_VALUE;
    private double _ymin = Double.MAX_VALUE;

    private double _xmax = -Double.MAX_VALUE;
    private double _ymax = -Double.MAX_VALUE;

    private void compute()
        throws ZeroAreaPolygonException

    {

	Triplet[] t = this.toArray( new Triplet[0] );

	double area = 0;

	for ( int i = t.length - 1, j = 0; j < t.length; i = j, j++ ) {

	    double xi = t[i].getU();
	    double xip1 = t[j].getU();

	    double yi = t[i].getV();
	    double yip1 = t[j].getV();

	    double weight = ( xi * yip1 ) - ( xip1 * yi );

	    area += weight;

	    this._xcentroid += ( xi + xip1 ) * weight; 
	    this._ycentroid += ( yi + yip1 ) * weight;

	    if ( this._xmin > xi )
		this._xmin = xi;
	    if ( this._ymin > yi )
		this._ymin = yi;

	    if ( this._xmax < xi )
		this._xmax = xi;
	    if ( this._ymax < yi )
		this._ymax = yi;

	}
	area /= 2;

        if ( area == 0 )
            throw new ZeroAreaPolygonException();

	this._xcentroid /= 6 * area;
	this._ycentroid /= 6 * area;

	int I = -1;
	int J = -1;
	double dmax = 0;
	for ( int i = 0; i < t.length; i++ )
	    for ( int j = i + 1; j < t.length; j++ ) {

		double xi = t[i].getU();
		double xj = t[j].getU();
		
		double yi = t[i].getV();
		double yj = t[j].getV();

		double dx = xj - xi;
		double dy = yj - yi;
		
		double d = ( dx * dx ) * ( dy * dy );

		if ( dmax < d ) {

		    dmax = d;
		    I = i;
		    J = j;

		}

	    }

        if ( ( I == -1 ) || ( J == -1 ) )
            System.err.println( "I = " + I + " J = " + J );
	double rx = ( t[J].getU() - t[I].getU() ) / 2.0;
	double ry = ( t[J].getV() - t[I].getV() ) / 2.0;

	this._xcenter = t[I].getU() + rx;
	this._ycenter = t[I].getV() + ry;

	double dsqmax = ( rx * rx ) + ( ry * ry );
	this._radius = Math.sqrt( dsqmax );

	int p = -1;

	for ( int i = 0; i < t.length; i++ ) {

	    double dx = t[i].getU() - this._xcenter;
	    double dy = t[i].getV() - this._ycenter;

	    double d = ( dx * dx ) + ( dy * dy );

	    if ( dsqmax < d ) {

		dsqmax = d;
		p = i;

	    }

	}
	
	if ( p != -1 ) {

	    double[] xy = new double[2];

	    double r = this.circleFromThreePoints( t[I].getU(), t[I].getV(),
						   t[J].getU(), t[J].getV(),
						   t[p].getU(), t[p].getV(),
						   xy );

	    if ( r != Double.MAX_VALUE ) {
		
		this._xcenter = xy[0];
		this._ycenter = xy[1];

		this._radius = r;

	    }

	}

    }

    public double[][] getBounds()

    {

	return new double[][] { 
	    { this._xmin, this._ymin },
	    { this._xmax, this._ymax }
	};

    }
	    
    private double circleFromThreePoints( double xa, double ya,
					  double xb, double yb,
					  double xc, double yc,
					  double[] xy )

    {

	double t00 = 2 * ( xb - xa );
	double t01 = 2 * ( yb - ya );
	
	double t10 = 2 * ( xc - xa );
	double t11 = 2 * ( yc - ya );
	
	double A =
	    ( ( xb * xb ) - ( xa * xa ) ) + ( ( yb * yb ) - ( ya * ya ) );
	double B = 
	    ( ( xc * xc ) - ( xa * xa ) ) + ( ( yc * yc ) - ( ya * ya ) );
	
	double d = this.det( t00, t01,
                             t10, t11 );
	
	double r;
	if ( d != 0 ) {
	    
	    xy[0] = this.det( A, t01,
			      B, t11 ) / d;
	    xy[1] = this.det( t00, A,
			      t10, B ) / d;
	    
	    double dA = xa - xy[0];
	    double dB = ya - xy[1];
	    
	    r = Math.sqrt( ( dA * dA ) + ( dB * dB ) );
	    
	} else
	    r = Double.MAX_VALUE;

	return r;
	
    }

    private double det( double t00, double t01, double t10, double t11 )
	
    {
	
	return ( t00 * t11 ) - ( t01 * t10 );
	
    }
    
}