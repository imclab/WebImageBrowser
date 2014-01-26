package edu.ucsd.ncmir.spl.MiniXML;

/**
 *
 * @author spl
 */
class ElementReader

{

    private Stream _stream;

    public ElementReader( Stream stream )

    {

	this._stream = stream;

    }

    private String _buffer = "";

    private String bufferChop( int buffer_index )

    {

	String s = this._buffer.substring( 0, buffer_index + 1 );

	this._buffer = this._buffer.substring( buffer_index + 1 );

	return s;

    }

    private String append()
	throws EOFException

    {

	String s = this._buffer;

	this.moreBuffer();

	return s;

    }

    private void moreBuffer()
	throws EOFException

    {

	this._buffer = this._stream.getNext( 65536 );
	if ( this._buffer == null )
	    throw new EOFException();

    }

    Element nextElement()
        throws JDOMException,
	       EOFException

    {

	int lbrocket;
	while ( ( lbrocket = this._buffer.indexOf( "<" ) ) == -1 )
	    this.moreBuffer();

	this.bufferChop( lbrocket );
	String element_string = "<";

	boolean done = false;

	while ( !done ) {

	    int quote = this._buffer.indexOf( "\"" );
	    int rbrocket = this._buffer.indexOf( ">" );

	    if ( ( quote == -1 ) && ( rbrocket == -1 ) )
		element_string += this.append();
	    else if ( ( quote != -1 ) && ( rbrocket == -1 ) )
		element_string += this.bufferChop( quote );
	    else if ( ( quote == -1 ) && ( rbrocket != -1 ) ) {

		element_string += this.bufferChop( rbrocket );
		done = true;

	    } else if ( quote < rbrocket )
		element_string += this.bufferChop( quote );
	    else {

		element_string += this.bufferChop( rbrocket );
		done = true;

	    }

	}

	String text = "";

	while ( ( lbrocket = this._buffer.indexOf( "<" ) ) == -1 ) {

	    text += this._buffer;
            try {

                this.moreBuffer();

            } catch ( EOFException e ) {

                break;

            }

	}

        if ( lbrocket != -1 ) {

            text += this._buffer.substring( 0, lbrocket );
            this._buffer = this._buffer.substring( lbrocket );

        }

	Element element = null;

        if ( !element_string.startsWith( "<!--" ) ) {

	    ElementBuffer ebuffer = new ElementBuffer( element_string );

	    String name = ebuffer.nextToken();

	    if ( !name.startsWith( "/" ) ) {

		if ( name == null )
		    throw new JDOMException( "Malformed element." );

		element = new Element( name.trim() );

		element.setText( text );

		for ( String token = ebuffer.nextToken();
		      token.length() > 0;
		      token = ebuffer.nextToken() )
		    if ( token.equals( "/" ) )
			element._closed = true;
		    else {

			int equals = token.indexOf( "=" );

			String attr_name;
			String value;

			if ( equals != -1 ) {

			    attr_name = token.substring( 0, equals );
			    value = token.substring( equals + 1 ).trim();

			} else {

			    attr_name = token;
			    value = "";

			}
			value = value.trim();
			if ( value.startsWith( "\"" ) )
			    value = value.substring( 1 );
			if ( value.endsWith( "\"" ) )
			    value = value.substring( 0, value.length() - 1 );
			element.setAttribute( attr_name.trim(), value.trim() );

		    }

	    }

        } else
	    element = new Comment( element_string );

	return element;

    }

}