package edu.ucsd.ncmir.spl.MiniXML;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author spl
 */
public class Element

{

    private String _name;

    public Element( String name )

    {

	this._name = name;

    }

    public Attribute getAttribute( String name )

    {

	Attribute attribute = null;

	String value = this.getAttributeValue( name );

	if ( value != null )
	    attribute = new Attribute( name, value );

	return attribute;

    }

    public String getAttributeValue( String key )

    {

	return !this._attributes.isEmpty() ? this._attributes.get( key ) : null;

    }

    public long getAttributeValueLong( String key )

    {

        return new Long( this.getAttributeValue( key ) ).longValue();

    }


    public int getAttributeValueInt( String key )

    {

        return new Integer( this.getAttributeValue( key ) ).intValue();

    }


    public double getAttributeValueDouble( String key )

    {

        return new Double( this.getAttributeValue( key ) ).doubleValue();

    }

    public boolean getAttributeValueBoolean( String key )

    {

	String s = this.getAttributeValue( key ).toLowerCase();

	return s.equals( "true" );

    }

    public List<Attribute> getAttributes()

    {

	ArrayList<Attribute> list = new ArrayList<Attribute>();

	if ( !this._attributes.isEmpty() )
	    for ( String key : this._attributes.keySet() )
		list.add( this.getAttribute( key ) );

	return list;

    }

    public Element getChild( String name )

    {

	Element child = null;

	if ( !this._children.isEmpty() )
	    for ( Element e : this._children )
		if ( e.getName().equals( name ) ) {

		    child = e;
		    break;

		}

	return child;

    }

    public List<Element> getChildren( String name )

    {

	ArrayList<Element> children = new ArrayList<Element>();

	if ( !this._children.isEmpty() )
	    for ( Iterator<Element> it = this._children.iterator();
                  it.hasNext(); ) {

            Element e = it.next();
            if ( e.getName().equals( name ) )
                children.add( e );

        }

        return children;

    }

    public List<Element> getChildren()

    {

	ArrayList<Element> children = new ArrayList<Element>();

	if ( !this._children.isEmpty() )
	    for ( Element e : this._children )
		children.add( e );

        return children;

    }

    private Document _document = null;

    public Document getDocument()
    {

        return this._document;

    }

    public void removeChild( String name )

    {

	if ( !this._children.isEmpty() )
	    for ( Element e : this._children )
		if ( e.getName().equals( name ) ) {

		    this._children.remove( e );
		    break;

		}

    }

    public Element setAttribute( Attribute attribute )

    {

        this._attributes.put( attribute.getName(), attribute.getValue() );

        return this;

    }

    private String _text = "";

    public void setText( String text )

    {

	this._text = text;

    }

    public String getText()

    {

	return this._text;

    }

    public String getValue()

    {

	return ( this._text != null ) ? this._text.trim() : null;

    }

    public void addContent( String content )

    {

	this._text += content;

    }

    boolean _closed = false;

    private HashMap<String,String> _attributes =
	new HashMap<String,String>( 3 );

    public Element setAttribute( String name, String value )

    {

	this._attributes.put( name, value == null ? "" : value );

	return this;

    }

    private ArrayList<Element> _children = new ArrayList<Element>( 5 );

    public Element addContent( Element child )

    {

	this._children.add( child );

	return this;

    }

    public Element addContent( Collection<Element> list )

    {

	for ( Element e : list )
	    this.addContent( e );

	return this;

    }

    public String getName()

    {

        return this._name;

    }

    void setDocument( Document document )

    {

        this._document = document;

	this._children.trimToSize();
	for ( Element e : this._children )
	    e.setDocument( document );

	if ( this._text != null ) {

	    this._text = this._text.trim();
	    if ( this._text.length() == 0 )
		this._text = null;

	}

    }

    public Element descendTo( String ename )

    {

	Element r = null;

	for ( Element e : this.getChildren() )
	    if ( ( r = e.getName().equals( ename ) ?
		   e :
		   e.descendTo( ename ) ) != null )
		break;

	return r;

    }

    public void setAttribute( String key, double value )

    {

        this.setAttribute( key, new Double( value ).toString() );

    }

    public void setAttribute( String key, long value )

    {

        this.setAttribute( key, new Long( value ).toString() );

    }

    public void setAttribute( String key, int value )

    {

        this.setAttribute( key, new Integer( value ).toString() );

    }

    public void setAttribute( String key, boolean value )

    {

        this.setAttribute( key, Boolean.toString( value ) );

    }

}
