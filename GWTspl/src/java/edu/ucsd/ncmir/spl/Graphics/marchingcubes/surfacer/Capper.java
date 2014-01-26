package edu.ucsd.ncmir.spl.Graphics.marchingcubes.surfacer;

/**
 *
 * @author spl
 */
public enum Capper

{

    NEITHER( "NEITHER", 0, 0 ),
    TOP( "CAP TOP", 1, 0 ),
    BOTTOM( "CAP BOTTOM", 0, 1 ),
    BOTH( "CAP BOTH", 1, 1 );

    private String _type;
    private int _cap_top;
    private int _cap_bottom;

    private Capper( String type,
                    int cap_top,
                    int cap_bottom )
    {

	this._type = type;
        this._cap_top = cap_top;
        this._cap_bottom = cap_bottom;

    }

    @Override
    public String toString()

    {

	return this._type;

    }

    public Capper getNext()

    {

        Capper[] list = Capper.values();

        Capper next_one = null;
        for ( int i = 0; i < list.length; i++ )
            if ( list[i].equals( this ) ) {

                next_one = list[( i + 1 ) % list.length];
                break;

            }

        return next_one;

    }

    /**
     * @return the _cap_top
     */
    public int getCapTop()

    {

        return this._cap_top;

    }

    /**
     * @return the _cap_bottom
     */
    public int getCapBottom()

    {

        return this._cap_bottom;

    }

}