package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKTransform
    extends JavaScriptObject

{

    protected XTKTransform() {}  // No instantiations allowed.

    public native static XTKTransform create()
    /*-{

        return new $wnd.X.transform();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

    public native final JsArray getMatrix()
    /*-{

        return this.matrix;

       }-*/;

    public native final void setMatrix( JsArray matrix )
    /*-{

        this.matrix = matrix;

       }-*/;

    public native final void flipX( )
    /*-{

        this.flipX( );

       }-*/;

    public native final void flipY( )
    /*-{

        this.flipY( );

       }-*/;

    public native final void flipZ( )
    /*-{

        this.flipZ( );

       }-*/;

    public native final void modified( )
    /*-{

        this.modified( );

       }-*/;

    public native final void rotateX( double angle )
    /*-{

        this.rotateX( angle );

       }-*/;

    public native final void rotateY( double angle )
    /*-{

        this.rotateY( angle );

       }-*/;

    public native final void rotateZ( double angle )
    /*-{

        this.rotateZ( angle );

       }-*/;

    public native final void translateX( double distance )
    /*-{

        this.translateX( distance );

       }-*/;

    public native final void translateY( double distance )
    /*-{

        this.translateY( distance );

       }-*/;

    public native final void translateZ( double distance )
    /*-{

        this.translateZ( distance );

       }-*/;

}
