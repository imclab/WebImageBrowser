package edu.ucsd.ncmir.spl.MiniXML;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author spl
 */
public class Comment
    extends Element

{

    private String _comment;

    public Comment( String comment )

    {

	super( null );

        this._comment = comment;

    }

    @Override
    public Attribute getAttribute( String name )

    {

	return null;

    }

    @Override
    public String getAttributeValue( String name )

    {

	return null;

    }

    @Override
    public List<Attribute> getAttributes()

    {

	return null;

    }

    @Override
    public Element getChild( String name )

    {

	return null;

    }

    @Override
    public List<Element> getChildren( String name )

    {

        return null;

    }

    @Override
    public List<Element> getChildren()

    {

        return null;

    }

    @Override
    public void removeChild( String name )

    {

	// noop.

    }

    @Override
    public Element setAttribute( Attribute attribute )

    {

        return null;

    }

    @Override
    public void setText( String text )

    {

	// noop.

    }

    @Override
    public String getText()

    {

	return null;

    }

    @Override
    public String getValue()

    {

	return null;

    }

    @Override
    public void addContent( String content )

    {

	// noop;

    }

    @Override
    public Element setAttribute( String name, String value )

    {

	return null;

    }

    @Override
    public Element addContent( Element child )

    {

	return null;

    }

    @Override
    public Element addContent( Collection<Element> list )

    {

	return null;

    }

    @Override
    public String getName()

    {

        return "";

    }

}
