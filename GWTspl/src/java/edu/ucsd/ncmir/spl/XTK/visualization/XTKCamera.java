package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.typedarrays.client.Float32ArrayNative;
import edu.ucsd.ncmir.spl.XTK.math.XTKVector;

public class XTKCamera
    extends JavaScriptObject

{

    protected XTKCamera() {}  // No instantiations allowed.

    public native static XTKCamera create()
    /*-{

        return new $wnd.X.camera();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final JsArray getFocus()
    /*-{

        return this.focus;

       }-*/;

    public native final void setFocus( JsArray focus )
    /*-{

        this.focus = focus;

       }-*/;

    public native final void setFocus( double x, double y, double z )
    /*-{

        this.focus = [ x, y, z ];

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

    public native final JsArray getPosition()
    /*-{

        return this.position;

       }-*/;

    public native final void setPosition( JsArray position )
    /*-{

        this.position = position;

       }-*/;

    public native final void setPosition( double x, double y, double z )
    /*-{

        this.position = [ x, y, z ];

       }-*/;

    public native final JsArray getUp()
    /*-{

        return this.up;

       }-*/;

    public native final void setUp( JsArray up )
    /*-{

        this.up = up;

       }-*/;

     public native final void setUp( double i, double j, double k )
    /*-{

        this.up = [ i, j, k ];

       }-*/;

   public native final Float32ArrayNative getView()
    /*-{

        return this.view;

       }-*/;

    public native final void setView( Float32ArrayNative view )
    /*-{

        this.view = view;

       }-*/;

    public native final void pan( XTKVector distance )
    /*-{

        this.pan( distance );

       }-*/;

    public native final void pan( JsArrayNumber distance )
    /*-{

        this.pan( distance );

       }-*/;

    public native final void rotate( XTKVector distance )
    /*-{

        this.rotate( distance );

       }-*/;

    public native final void rotate( JsArrayNumber distance )
    /*-{

        this.rotate( distance );

       }-*/;

    public native final void rotate( double xrot, double yrot )
    /*-{

        this.rotate( [ xrot, yrot ] );

       }-*/;

    public native final void zoomIn( boolean fast )
    /*-{

        this.zoomIn( fast );

       }-*/;

    public native final void zoomOut( boolean fast )
    /*-{

        this.zoomOut( fast );

       }-*/;

}
