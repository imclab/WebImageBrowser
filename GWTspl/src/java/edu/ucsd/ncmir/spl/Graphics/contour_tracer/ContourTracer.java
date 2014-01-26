package edu.ucsd.ncmir.spl.Graphics.contour_tracer;

import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class ContourTracer

{

    private byte[][] _p;

    public ContourTracer( byte[][] p )

    {

	this._p = p;

    }

    public ContourTracer( byte[] data, int w, int h )

    {

	this._p = new byte[h][w];

	for ( int j = 0, p = 0; j < h; j++ )
	    for ( int i = 0; i < w; i++, p++ )
		this._p[j][i] = data[p];

    }

    public ArrayList<int[][]> trace( int threshold )

    {

	byte t = ( byte ) ( threshold - 128 );

	int w = this._p[0].length;
	int h = this._p.length;

	byte[][] p = new byte[h][w];

	for ( int j = 0; j < h; j++ )
	    for ( int i = 0; i < w; i++ )
		p[j][i] = ( byte ) ( ( this._p[j][i] == threshold ) ? 1 : 0 );

	ArrayList<int[][]> traces = new ArrayList<int[][]>();

	for ( int y0 = 0; y0 < h; y0++ )
	    for ( int x0 = 0; x0 < w; x0++ )
		if ( ( p[y0][x0] == 1 ) &&
		     ( ( x0 == 0 ) || ( p[y0][x0 - 1] == 0 ) ) ) {

		    PCQueue Q = this.tracer( x0, y0, p );

		    traces.add( Q.dumpTrace() );

		    int c0 = 8;

		    while ( !Q.empty() ) {

			TraceElement te = Q.removeElement();

			int x = te.getX();
			int y = te.getY();
			int c = te.getS();

			if ( c == 8 ) {

			    if ( Q.isEmpty() )
				break;

			    c0 = c;

			    te = Q.removeElement();

			    x = te.getX();
			    y = te.getY();
			    c = te.getS();

			}

			if ( ( ( 4 <= c0 ) && ( c0 <= 7 ) ) &&
			     ( ( 5 <= c ) && ( c <= 7 ) ) ) {

			    for ( int X = x; X <= w - 2; X++ ) {

				int A = p[y][X];
				int B = p[y][X + 1];
				int C = p[y][X + 2];

				if ( ( ( A == 0 ) &&
				       ( B == 1 ) ) ||
				     ( ( A == 0 ) &&
				       ( B == 2 ) &&
				       ( C == 0 ) ) ) {

				    PCQueue trace = this.tracer( X + 1, y, p );

				    traces.add( trace.dumpTrace() );

				    Q.addAll( trace );

				    break;

				} else if ( ( Q.isEmpty() ) &&
					( ( ( A == 0 ) && ( B > 2 ) ) ||
					  ( ( A == 1 ) && ( B == 3 ) ) ) )
				    break;
				else if ( ( A == 0 ) &&
					  ( B == 0 ) &&
					  ( C == 0 ) )
				    break;

			    }

			}

			c0 = c;

		    }

		}

        return traces;

    }

    private PCQueue tracer( int Ax, int Ay, byte[][] p )

    {

	C c = new C( p, Ax, Ay );

	PCQueue trace = new PCQueue();

	trace.add( new TraceElement( Ax, Ay, 8 ) );

	int S = 6;

	do {

	    for ( int i = 0; i < 3; i++ ) {

		if ( c.test( S - 1 ) ) {

		    trace.add( c.createTraceElement( S - 1 ) );
		    S -= 2;
		    break;

		} else if ( c.test( S ) ) {

		    trace.add( c.createTraceElement( S ) );
		    break;

		} else if ( c.test( S + 1 ) ) {

		    trace.add( c.createTraceElement( S + 1 ) );
		    break;

		} else
		    S += 2;

		S = ( S + 8 ) % 8;

	    }

	} while ( !c.closed() );

	return trace;

    }

}
