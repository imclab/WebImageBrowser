package edu.ucsd.ncmir.spl.MiniXML;


/**
 *
 * @author spl
 */
public class Document

{

    private Element _root;

    public Document( Element root )

    {

        this._root = root;

        root.setDocument( this );

    }

    public Element getRootElement()
    {

        return this._root;

    }

    @Override
    public String toString()

    {

        return this._root.toString();

    }

}
