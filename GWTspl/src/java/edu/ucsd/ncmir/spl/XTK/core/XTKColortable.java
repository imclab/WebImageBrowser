package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKColortable
    extends JavaScriptObject

{

    protected XTKColortable() {};  // No instantiations allowed.

    public native static XTKColortable create()
    /*-{

        return new $wnd.X.colortable();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final String getFile()
    /*-{

        return this.file();

       }-*/;

    public native final void file( )
    /*-{

        this.file( );

       }-*/;

    public native final String getFiledata()
    /*-{

        return this.filedata();

       }-*/;

    public native final void filedata( )
    /*-{

        this.filedata( );

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

    public native final void add( double value, String label, double r, double g, double b, double a )
    /*-{

        this.add( value, label, r, g, b, a );

       }-*/;

}
