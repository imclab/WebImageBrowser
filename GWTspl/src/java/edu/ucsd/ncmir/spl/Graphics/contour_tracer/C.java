package edu.ucsd.ncmir.spl.Graphics.contour_tracer;

class C

{

    private static final int[] Sx = {  1,  1,  0, -1, -1, -1,  0,  1 };
    private static final int[] Sy = {  0, -1, -1, -1,  0,  1,  1,  1 };

    private int _Ax;
    private int _Ay;

    private int _x;
    private int _y;

    private byte[][] _p;

    C( byte[][] p, int x, int y )

    {

        this._Ax = this._x = x;
        this._Ay = this._y = y;
        this._p = p;

    }

    boolean test( int S )

    {

        while ( S < 0 )
            S += 8;
        S %= 8;

        int p = Sx[S] + this._x;
        int q = Sy[S] + this._y;

        return this._p[q][p] >= 1;

    }

    TraceElement createTraceElement( int S )

    {

        while ( S < 0 )
            S += 8;
        S %= 8;

        this._x += Sx[S];
        this._y += Sy[S];
        this._p[this._y][this._x]++;

        return new TraceElement( this._x, this._y, S );
        
    }

    boolean closed()

    {

        return ( this._Ax == this._x ) && ( this._Ay == this._y );

    }

}
