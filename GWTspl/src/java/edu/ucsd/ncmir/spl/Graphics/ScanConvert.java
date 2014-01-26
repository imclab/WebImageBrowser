/*
 * ScanConvert.java
 *
 * Created on July 26, 2006, 12:19 PM
 */

package edu.ucsd.ncmir.spl.Graphics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author spl
 */

public abstract class ScanConvert

{

    private int _width;
    private int _height;
    private double[][] _xy;

    public ScanConvert( int width, int height, double[][] xy )
        throws IllegalArgumentException

    {

        if ( xy.length == 0 )
            throw new IllegalArgumentException( "No points to scan convert." );

	this._width = width;
	this._height = height;
	this._xy = xy;

    }

    public ScanConvert( int width, int height, int[][] ixy )
        throws IllegalArgumentException

    {

        if ( ixy.length == 0 )
            throw new IllegalArgumentException( "No points to scan convert." );

        this._width = width;
        this._height = height;
        this._xy = new double[ixy.length][2];
        for ( int i = 0; i < ixy.length; i++ ) {

            this._xy[i][0] = ixy[i][0];
            this._xy[i][1] = ixy[i][1];

        }

    }

    public abstract void handler( int x, int y );

    /*
     * The following routines are from Heckbert's scan conversion
     * algorithm in _Graphics Gems_.
     */

    private class Edge
	implements Comparable<Edge>

    {

	double x;
	double dx;
	int i;

        Edge( double dx, double x, int i )

        {

            this.x = x;
            this.dx = dx;
            this.i = i;

        }

        @Override
	public int compareTo( Edge e )

	{

	    double d = this.x - e.x;
	    return ( d > 0 ) ? 1 : ( ( d < 0 ) ? -1 : 0 );

	}

    }

    private class EdgeList
	extends ArrayList<Edge>

    {

        private static final long serialVersionUID = 42L; // To shut up compiler

	void insert( int i, int Y, double[][] xy )

	{

	    int px;
	    int py;
	    int qx;
	    int qy;

	    int j = ( i < ( xy.length - 1 ) ) ? ( i + 1 ) : 0;

	    if ( xy[i][1] < xy[j][1] ) {

		px = ( int ) Math.floor( xy[i][0] );
		py = ( int ) Math.floor( xy[i][1] );

		qx = ( int ) Math.ceil( xy[j][0] );
		qy = ( int ) Math.ceil( xy[j][1] );

	    } else {

		px = ( int ) Math.floor( xy[j][0] );
		py = ( int ) Math.floor( xy[j][1] );

		qx = ( int ) Math.ceil( xy[i][0] );
		qy = ( int ) Math.ceil( xy[i][1] );

	    }

	    double dx = ( double ) ( qx - px ) / ( double ) ( qy - py );
	    this.add( new Edge( dx, ( dx * ( Y - py ) ) + px, i ) );

	}

	public void delete( int i )

	{

	    Edge[] list = this.toArray( new Edge[this.size()] );

	    for ( int l = 0; l < list.length; l++ )
		if ( list[l].i == i ) {

		    super.remove( l );
		    break;

		}

	}

    }

    public void scanConvert()

    {

	int n = this._xy.length;
	int[] ind = new int[this._xy.length];

        for ( int k = 0; k < n; k++ )
	    ind[k] = k;
	this.table_sort( ind, this._xy, 0, n - 1 );

	EdgeList active = new EdgeList();

	int k = 0;

	int y0 = ( int ) Math.ceil( this._xy[ind[0]][1] - 0.5 );
	if ( y0 < 0 )
	    y0 = 0;

	int y1 = ( int ) Math.floor( this._xy[ind[n - 1]][1] + 0.5 );
	if ( y1 >= this._height )
	    y1 = this._height - 1;

	for ( int y = y0; y <= y1; y++ ) {

	    for ( ; k < n && this._xy[ind[k]][1] <= y + 0.5; k++ ) {

		int i = ind[k];

		int j = ( i > 0 ) ? ( i - 1 ) : ( n - 1 );

		if ( this._xy[j][1] <= y - 0.5 )
		    active.delete( j );
		else if ( this._xy[j][1] > y + 0.5 )
		    active.insert( j, y, this._xy );

		j = ( i < ( n - 1 ) ) ? ( i + 1 ) : 0;

		if ( this._xy[j][1] <= y - 0.5 )
		    active.delete( i );
		else if ( this._xy[j][1] > y + 0.5 )
		    active.insert( i, y, this._xy );

	    }

            Edge[] active_list = active.toArray( new Edge[active.size()] );
	    Arrays.sort( active_list );
	    for ( int j = 0; j < active.size() - 1; j += 2 ) {

		int xl = ( int ) Math.ceil( active_list[j].x - 0.5 );
		int xr = ( int ) Math.floor( active_list[j + 1].x + 0.5 );

		if ( xl < 0 )
		    xl = 0;
		if ( xr >= this._width )
		    xr = this._width - 1;
		if ( xl <= xr ) {

		    int x;

		    for ( x = xl; x <= xr; x++ )
			this.handler( x, y );

		}

		active_list[j].x += active_list[j].dx;
		active_list[j + 1].x += active_list[j + 1].dx;

	    }

	}

    }

    private void table_swap( int[] ind, int left, int right )

    {

	int temp = ind[left];

	ind[left] = ind[right];
	ind[right] = temp;

    }

    private void table_sort( int[] ind,
			     double[][] xy,
			     int left, int right )

    {

	if ( left < right ) {

	    this.table_swap( ind, left, ( left + right ) / 2 );

	    int last = left;

	    for ( int i = left + 1; i <= right; i++ )
		if ( xy[ind[i]][1] < xy[ind[left]][1] )
		    this.table_swap( ind, ++last, i );

	    this.table_swap( ind, left, last );
	    this.table_sort( ind, xy, left, last - 1 );
	    this.table_sort( ind, xy, last + 1, right );

	}

    }

}