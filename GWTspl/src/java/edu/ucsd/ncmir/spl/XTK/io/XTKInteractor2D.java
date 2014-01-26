package edu.ucsd.ncmir.spl.XTK.io;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import edu.ucsd.ncmir.spl.XTK.XTKEvent;

public class XTKInteractor2D
    extends JavaScriptObject

{

    protected XTKInteractor2D() {};  // No instantiations allowed.

    public native static XTKInteractor2D create()
    /*-{

        return new $wnd.X.interactor2D();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final JavaScriptObject getConfig()
    /*-{

        return this.config();

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

    public native final boolean getLeftButtonDown()
    /*-{

        return this.leftButtonDown();

       }-*/;

    public native final boolean getMiddleButtonDown()
    /*-{

        return this.middleButtonDown();

       }-*/;

    public native final JsArray getMousePosition()
    /*-{

        return this.mousePosition();

       }-*/;

    public native final boolean getRightButtonDown()
    /*-{

        return this.rightButtonDown();

       }-*/;

    public native final void init( )
    /*-{

        this.init( );

       }-*/;

    public native final void onKey( XTKEvent event )
    /*-{

        this.onKey( event );

       }-*/;

    public native final void onMouseDown( boolean left, boolean middle, boolean right )
    /*-{

        this.onMouseDown( left, middle, right );

       }-*/;

    public native final void onMouseMove( XTKEvent event )
    /*-{

        this.onMouseMove( event );

       }-*/;

    public native final void onMouseUp( boolean left, boolean middle, boolean right )
    /*-{

        this.onMouseUp( left, middle, right );

       }-*/;

    public native final void onMouseWheel( XTKEvent event )
    /*-{

        this.onMouseWheel( event );

       }-*/;

    public native final void onTouchEnd( )
    /*-{

        this.onTouchEnd( );

       }-*/;

    public native final void onTouchHover( double x, double y )
    /*-{

        this.onTouchHover( x, y );

       }-*/;

    public native final void onTouchHover( )
    /*-{

        this.onTouchHover( );

       }-*/;

    public native final void onTouchMove( XTKEvent event )
    /*-{

        this.onTouchMove( event );

       }-*/;

    public native final void onTouchStart( double x, double y )
    /*-{

        this.onTouchStart( x, y );

       }-*/;

}
