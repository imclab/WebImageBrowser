package edu.ucsd.ncmir.spl.Graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class ConvexHull

{

    private ConvexHull() {} 	// No instantation.

    /*
     * From O'Rourke, J, _Computational Geometry in C_, 1994, p. 93
     */

    public static double[][] compute( double[][] xy )

    {

	Point[] p = new Point[xy.length];
	int n = xy.length;

	for ( int i = 0; i < n; i++ )
	    p[i] = new Point( xy[i][0], xy[i][1] );

	int m = 0;

	for ( int i = 1; i < n; i++ )
	    if ( ( p[i].getY() < p[m].getY() ) ||
		 ( ( p[i].getY() == p[m].getY() ) &&
		   ( p[i].getX() > p[m].getX() ) ) )
		m = i;

	Point pt = p[m];

	p[m] = p[0];
	p[0] = pt;

	Point[] ptemp = new Point[n - 1];

	for ( int i = 1; i < n; i++ )
	    ptemp[i - 1] = p[i];

	Arrays.sort( ptemp, new PointComparator( p[0] ) );

	for ( int i = 1; i < n; i++ )
	    p[i] = ptemp[i - 1];

	ArrayList<Point> arp = new ArrayList<Point>();

	// Eliminate dupes.

	arp.add( p[0] );

	for ( int i = 1; i < n; i++ )
	    if ( ( arp.get( arp.size() - 1 ).getX() != p[i].getX() ) ||
		 ( arp.get( arp.size() - 1 ).getY() != p[i].getY() ) )
		arp.add( p[i] );

	Point[] rp = arp.toArray( new Point[0] );

	double[][] chull;

	if ( ( rp.length > 3 ) && ( ConvexHull.parea2( rp ) != 0 ) )
	    chull = ConvexHull.completeConvexHull( ConvexHull.graham( rp ) );
	else {

	    chull = new double[rp.length][2];
	    for ( int i = 0; i < rp.length; i++ ) {

		chull[i][0] = ( double ) rp[i].getX();
		chull[i][1] = ( double ) rp[i].getY();

	    }

	}
	return chull;

    }

    private static double[][] completeConvexHull( PointStack top )

    {

	double[][] chull = new double[top.size()][2];

	int n = 0;
	while ( !top.empty() ) {

	    Point p = top.pop();

	    chull[n][0] = ( double ) p.getX();
	    chull[n][1] = ( double ) p.getY();

	    n++;

	}
	return chull;

    }

    private static class PointStack extends Stack<Point> {}

    private static class PointComparator
	implements Comparator

    {

	private final Point _p0;

	PointComparator( Point p0 )

	{

	    this._p0 = p0;

	}

        @Override
        public int compare( Object o1, Object o2 )
        {

	    int r;

	    Point p1 = ( Point ) o1;
	    Point p2 = ( Point ) o2;

	    double a = Point.area2( this._p0, p1, p2 );

	    if ( a > 0 )
		r = -1;
	    else if ( a < 0 )
		r = 1;
	    else {

		Point r1 = Point.subvec( p1, this._p0 );

		Point r2 = Point.subvec( p2, this._p0 );

		double l1 = Point.length2( r1 );
		double l2 = Point.length2( r2 );

		r = ( l1 < l2 ) ? -1 : ( ( l1 > l2 ) ? 1 : 0 );

	    }

	    return r;

        }

    }

    private static class Point

    {

	private double _x;
	private double _y;

	Point( double x, double y )

	{

	    this._x = x;
	    this._y = y;

	}

        double getX()

        {

            return this._x;

        }

        double getY()

        {

            return this._y;

        }

	static Point subvec( Point a, Point b )

	{

	    return new Point( a.getX() - b.getX(), a.getY() - b.getY() );

	}

	static double dot( Point a, Point b )

	{

	    return ( a.getX() * b.getX() ) + ( a.getY() * b.getY() );

	}

	static double length2( Point a )

	{

	    return dot( a, a );

	}

	private static double area2( Point a, Point b, Point c )

	{

	    return
		( a.getX() * b.getY() ) -
		( a.getY() * b.getX() ) +
		( a.getY() * c.getX() ) -
		( a.getX() * c.getY() ) +
		( b.getX() * c.getY() ) -
		( b.getY() * c.getX() );

	}

    }

    private static double parea2( Point[] p )

    {

	double area2 = 0;

	for ( int i = 0; i < p.length; i++ ) {

	    double xi = p[i].getX();
	    double yi = p[i].getY();
	    int j = ( i + 1 ) % p.length;
	    double xj = p[j].getX();
	    double yj = p[j].getY();

	    area2 += ( xi * yj ) - ( yi * xj );

	}

	return area2;

    }

    private static PointStack graham( Point[] p )

    {

	PointStack stack = new PointStack();

	int n = p.length;

	stack.push( p[n - 1] );
	stack.push( p[0] );

	int i = 1;

	while ( i < n )
	    if ( ConvexHull.left( stack.get( stack.size() - 2 ), 
				  stack.get( stack.size() - 1 ), 
				  p[i] ) ) {

		stack.push( p[i] );
		i++;

	    } else
		stack.pop();

	return stack;

    }

    private static boolean left( Point a, Point b, Point c )

    {

	return Point.area2( a, b, c ) > 0;

    }

}

