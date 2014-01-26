package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKTriplets
    extends JavaScriptObject

{

    protected XTKTriplets() {}  // No instantiations allowed.

    public native static XTKTriplets create( int triplets )
    /*-{

        return new $wnd.X.triplets( triplets * 3 );

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final double getCount()
    /*-{

        return this.count;

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

    public native final double getLength()
    /*-{

        return this.length;

       }-*/;

    public native final double add( double a, double b, double c )
    /*-{

        return this.add( a, b, c );

       }-*/;

    public native final void clear( )
    /*-{

        this.clear( );

       }-*/;

    public native final JsArray get( double id )
    /*-{

        return this.get( id );

       }-*/;

    public native final void remove( double id )
    /*-{

        this.remove( id );

       }-*/;

    public native final void resize( )
    /*-{

        this.resize( );

       }-*/;

}
