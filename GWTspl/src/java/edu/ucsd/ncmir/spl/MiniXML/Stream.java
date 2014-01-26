package edu.ucsd.ncmir.spl.MiniXML;

/**
 *
 * @author spl
 */
class Stream

{

    private int _next = 0;
    private final String _text;

    Stream( String text )

    {

	this._text = text;

    }

    String getNext( int n )

    {

	String s;

	if ( this._next >= this._text.length() )
	    s = null;
	else {

	    int nl = this._next + n;

	    if ( nl > this._text.length() )
		nl = this._text.length();
	    s = this._text.substring( this._next, nl );
	    this._next += n;

	}

	return s;

    }

    void rewind()

    {

	this._next = 0;

    }

}
