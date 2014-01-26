package edu.ucsd.ncmir.spl.MiniXML;

/**
 * Why this package?  Because JDOM and its ilk are obscenely distended
 * piles of bloatware.
 *
 * This package doesn't pretend to subsume all of the functionality of
 * JDOM.  In fact, that's the whole reason for this exercise.
 *
 * @author spl
 */

public class SAXBuilder

{

    public SAXBuilder() {}

    public SAXBuilder( boolean validate ) {}

    public Document build( String text )
	throws JDOMException

    {

	Stream stream = new Stream( text );

	String hbytes = stream.getNext( 5 );

	if ( hbytes.length() < 5 )
	    throw new JDOMException( "Unexpected end of file." );

	if ( hbytes.equals( "<?xml" ) ) {

            String brocket;

            boolean ok = false;
            while ( !ok && ( ( brocket = stream.getNext( 1 ) ) != null ) )
                ok = brocket.equals( ">" );

            if ( !ok )
                throw new JDOMException( "Bad header." );

        } else
	    stream.rewind();

	Element root = this.parse( new ElementReader( stream ) );

	Document document = new Document( root );

	return document;

    }

    private Element parse( ElementReader reader )
        throws JDOMException

    {

	Element element;

        try {

            element = reader.nextElement();

            if ( element != null ) {

		if ( !element._closed ) {

		    if ( !( element instanceof Comment ) ) {

			if ( element.getName().equals( "![CDATA[]]" ) )
			    element = reader.nextElement();
			else {

			    Element child;

			    while ( ( child = this.parse( reader ) ) != null )
				element.addContent( child );

			}

		    }

		}

            }

        } catch ( EOFException e ) {

            element = null;

        }

	return element;

    }

}


