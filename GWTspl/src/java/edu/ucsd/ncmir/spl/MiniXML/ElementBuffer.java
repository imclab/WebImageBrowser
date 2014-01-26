package edu.ucsd.ncmir.spl.MiniXML;

/**
 *
 * @author spl
 */
class ElementBuffer

{

    private String _element_string;

    public ElementBuffer( String element_string )

    {

	this._element_string =
	    element_string.
	    replaceAll( "^<", "" ). // Strip brockets.
	    replaceAll( ">$", "" ).
            replaceAll( "\r", " " ).
            replaceAll( "\n", " " ).
	    replaceAll( "/$", " /" ). // If there's a "/", make sure
	    trim();		      // it's floating.

	String es;

	do {
	    
	    es = this._element_string;
	    this._element_string =
		es.replaceAll( " =", "=" ).replaceAll( "= ", "=" );

	} while ( !this._element_string.equals( es ) );

    }

    String nextToken()

    {

	int quote = this._element_string.indexOf( "\"" );
	int space = this._element_string.indexOf( " " );
	int tab = this._element_string.indexOf( "\t" );

	int whitespace;
	if ( ( space != -1 ) && ( tab == -1 ) )
	    whitespace = space;
	else if ( ( space == -1 ) && ( tab != -1 ) )
	    whitespace = tab;
	else if ( ( space != -1 ) && ( tab != -1 ) )
	    whitespace = space > tab ? tab : space;
	else
	    whitespace = -1;

	String s;

	if ( whitespace == -1 ) {

	    s = this._element_string;
	    this._element_string = "";

	} else if ( ( quote == -1 ) || ( whitespace < quote ) ) {

	    s = this._element_string.substring( 0, whitespace );
	    this._element_string =
		this._element_string.substring( whitespace + 1 );

	} else if ( quote < whitespace ) {

	    s = this._element_string.substring( 0, quote + 1 );
	    this._element_string =
		this._element_string.substring( quote + 1 );
	    quote = this._element_string.indexOf( "\"" );
	    s += this._element_string.substring( 0, quote + 1 );
	    this._element_string =
		this._element_string.substring( quote + 1 );

	} else {

	    s = this._element_string.substring( 0, whitespace );
	    this._element_string =
		this._element_string.substring( whitespace + 1 );

	}

	this._element_string = this._element_string.trim();

	return s;

    }

}



