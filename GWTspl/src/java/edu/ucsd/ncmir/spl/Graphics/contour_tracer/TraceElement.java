package edu.ucsd.ncmir.spl.Graphics.contour_tracer;

class TraceElement

{

    private int _x;
    private int _y;
    private int _S;

    TraceElement( int x, int y, int S )

    {

        this._x = x;
        this._y = y;
        this._S = S;

    }

    /**
     * @return the _x
     */

    int getX()

    {

        return this._x;

    }

    /**
     * @return the _y
     */

    int getY()

    {

        return this._y;

    }

    /**
     * @return the _S
     */
    int getS()

    {

        return this._S;

    }

}
