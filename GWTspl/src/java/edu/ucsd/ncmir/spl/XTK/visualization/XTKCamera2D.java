package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import edu.ucsd.ncmir.spl.XTK.io.XTKInteractor;
import edu.ucsd.ncmir.spl.XTK.math.XTKVector;

public class XTKCamera2D
    extends JavaScriptObject

{

    protected XTKCamera2D() {};  // No instantiations allowed.

    public native static XTKCamera2D create()
    /*-{

        return new $wnd.X.camera2D();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final JsArray getFocus()
    /*-{

        return this.focus();

       }-*/;

    public native final void focus()
    /*-{

        this.focus();

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

    public native final JsArray getPosition()
    /*-{

        return this.position();

       }-*/;

    public native final void position()
    /*-{

        this.position();

       }-*/;

    public native final JsArray getUp()
    /*-{

        return this.up();

       }-*/;

    public native final void up()
    /*-{

        this.up();

       }-*/;

    public native final JsArray getView()
    /*-{

        return this.view();

       }-*/;

    public native final void view()
    /*-{

        this.view();

       }-*/;

    public native final void observe( XTKInteractor interactor )
    /*-{

        this.observe( interactor );

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

    public native final void reset()
    /*-{

        this.reset();

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
