package edu.ucsd.ncmir.spl.Util;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.Text;
import com.google.gwt.xml.client.XMLParser;

/**
 * Being a collection of semi-useful XML manipulation tools.
 * @author spl
 */
public class XML

{

    private XML() {} // No need to ever instantiate.

    /**
     * Extracts a <code>String</code> from a text element.  Because
     * GWT likes to break up a single <code>TEXT</code> XML
     * <code>Element</code> into multiple chunks, we have to
     * reassemble the string by concatenating multiple
     * <code>Element</code> contents.
     * @param element A <code>TEXT</code> XML <code>Element</code>.
     * @return The reassembled <code>String</code>.
     */
    public static String extractTextFromElement( Element element )

    {

        String adata = "";

        for ( Node n = element.getFirstChild();
              n != null;
              n = n.getNextSibling() )
            if ( n.getNodeType() == Node.TEXT_NODE )
                adata += ( ( Text ) n ).getData();

        return adata.trim();

    }

    /**
     * Extracts and parses a string into substrings delimited by a
     * regular expression.
     * @param element The <code>Element</code> containing the string.
     * @param regexp A regular expression acceptable to
     * <code>String.split()</code>.
     * @return An array of <code>String</code>s.
     */
    public static String[] extractSubstringsFromElement( Element element,
							 String regexp )

    {

	return XML.extractTextFromElement( element ).split( regexp );

    }

    /**
     * Converts a <code>TEXT</code> XML <code>Element</code>
     * containing hex-encoded XML.
     * @param element A <code>TEXT</code> XML <code>Element</code>
     * containing the obfuscated XML.
     * @return The deobfuscated XML <code>Element</code>.
     */
    public static Element extractWrappedXML( Element element )
    {

        return XML.convertToXML( XML.extractTextFromElement( element ) );

    }

    private static final String HEX = "0123456789abcdef";

    /**
     * Converts a hex-encoded <code>String</code> to an XML
     * <code>Element</code>.
     * @param string The <code>String</code>.
     * @return The deobfuscated XML <code>Element</code>.
     */
    public static Element convertToXML( String string )

    {

        string = string.toLowerCase();

        String es = "";

        for ( int i = 0; i < string.length(); i += 2 ) {

            int c1 = XML.HEX.indexOf( string.substring( i, i + 1 ) );
            int c2 = XML.HEX.indexOf( string.substring( i + 1, i + 2 ) );

            char c = ( char ) ( ( c1 << 4 ) | ( c2 ) );

            es += Character.toString( c );

        }

        Element converted;

        try {

            // Just in case the document is gorked.

            converted = ( es.length() > 0 )
                        ? XMLParser.parse( es ).getDocumentElement()
                        : null;

        } catch ( Exception e ) {

            converted = null;

        }

        return converted;

    }

    /**
     * Extracts an <code>Element</code> with the appropriate tag.
     * Searches only one level. To search down the entire tree, use
     * XML.descendTo().
     * @param root The root <code>Element</code> whence we start our search.
     * @param tag The tag we're looking for.
     * @return The <code>Element</code> we're searching for.
     */
    public static Element find( Element root, String tag )

    {

	return ( Element ) root.getElementsByTagName( tag ).item( 0 );

    }

    /**
     * Extracts an <code>Element</code> with the appropriate tag.
     * Searches only one level. To search down the entire tree, use
     * XML.descendTo().
     * @param root The root <code>Element</code> whence we start our search.
     * @param tag The tag we're looking for.
     * @return The <code>Element</code> we're searching for.
     */
    public static Element find( Document root, String tag )

    {

	return ( Element ) root.getElementsByTagName( tag ).item( 0 );

    }

    /**
     * Descends an XML tree to find an element with the appropriate tag.
     * @param root The root <code>Element</code> whence we start our search.
     * @param tag The tag we're looking for.
     * @return The <code>Element</code> we're searching for.
     */

    public static Element descendTo( Node root, String tag )

    {

        if ( !root.getNodeName().equals( tag ) ) {

            NodeList nl = root.getChildNodes();

            root = null;
            if ( nl != null )
                for ( int i = 0; i < nl.getLength(); i++ )
                    if ( ( root = XML.descendTo( nl.item( i ), tag ) ) != null )
                        break;

        }

        return ( Element ) root;

    }

    /**
     * Converts a <code>String</code> to XML and finds the named root
     * <code>Element</code>.
     * @param string A <code>String</code> containing XML.
     * @param tag The tag name of the root <code>Element<code>.
     * @return The requested <code>Element</code> or null if none found.
     */
    public static Element find( String string, String tag )

    {

	return XML.find( XMLParser.parse( string.trim() ), tag );

    }

    /**
     * A convenience method to extract a numeric attribute from an XML
     * <code>Element</code>.
     * @param element The <code>Element</code>.
     * @param name The name of the attribute to be extracted.
     * @return The parsed value of the <code>Element</code>'s
     * attribute.
     */
    public static double getDoubleAttribute( Element element, String name )

    {

        return Double.parseDouble( element.getAttribute( name ) );

    }

}
