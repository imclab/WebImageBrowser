package edu.ucsd.ncmir.spl.XTK.injects;

import edu.ucsd.ncmir.spl.XTK.core.XTKTriplets;
import edu.ucsd.ncmir.spl.XTK.core.XTKTransform;
import edu.ucsd.ncmir.spl.XTK.core.XTKTexture;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKDisplayable
    extends JavaScriptObject

{

    protected XTKDisplayable() {};  // No instantiations allowed.

    public native static XTKDisplayable create()
    /*-{

        return new $wnd.X.displayable();

       }-*/;

    public native final String getCaption()
    /*-{

        return this.caption();

       }-*/;

    public native final void caption( )
    /*-{

        this.caption( );

       }-*/;

    public native final JsArray getColor()
    /*-{

        return this.color();

       }-*/;

    public native final void color( )
    /*-{

        this.color( );

       }-*/;

    public native final XTKTriplets getColors()
    /*-{

        return this.colors();

       }-*/;

    public native final void colors( )
    /*-{

        this.colors( );

       }-*/;

    public native final double getLinewidth()
    /*-{

        return this.linewidth();

       }-*/;

    public native final void linewidth( )
    /*-{

        this.linewidth( );

       }-*/;

    public native final boolean getMagicmode()
    /*-{

        return this.magicmode();

       }-*/;

    public native final void magicmode( )
    /*-{

        this.magicmode( );

       }-*/;

    public native final XTKTriplets getNormals()
    /*-{

        return this.normals();

       }-*/;

    public native final void normals( )
    /*-{

        this.normals( );

       }-*/;

    public native final double getOpacity()
    /*-{

        return this.opacity();

       }-*/;

    public native final void opacity( )
    /*-{

        this.opacity( );

       }-*/;

    public native final XTKTriplets getPoints()
    /*-{

        return this.points();

       }-*/;

    public native final void points( )
    /*-{

        this.points( );

       }-*/;

    public native final double getPointsize()
    /*-{

        return this.pointsize();

       }-*/;

    public native final void pointsize( )
    /*-{

        this.pointsize( );

       }-*/;

    public native final XTKTexture getTexture()
    /*-{

        return this.texture();

       }-*/;

    public native final XTKTransform getTransform()
    /*-{

        return this.transform();

       }-*/;

    public native final String getType()
    /*-{

        return this.type();

       }-*/;

    public native final void setType( String type )
    /*-{

        this.type = type;

       }-*/;

    public native final boolean getVisible()
    /*-{

        return this.visible();

       }-*/;

    public native final void setVisible( boolean visible )
    /*-{

        this.visible = visible;

       }-*/;

}
