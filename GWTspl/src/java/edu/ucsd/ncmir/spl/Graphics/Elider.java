package edu.ucsd.ncmir.spl.Graphics;

/**
 *
 * @author spl
 */
class Elider 
    extends ScanConvert

{

    private final byte[][] _p;

    public Elider( byte[][] p, int[][] points )

    {

	super( p[0].length, p.length, points );
	this._p = p;

    }

    @Override
    public void handler( int x, int y )

    {

        this._p[y][x] = 0x00;

    }

}
