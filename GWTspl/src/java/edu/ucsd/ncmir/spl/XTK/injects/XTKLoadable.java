package edu.ucsd.ncmir.spl.XTK.injects;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKLoadable
    extends JavaScriptObject

{

    protected XTKLoadable() {};  // No instantiations allowed.

    public native static XTKLoadable create()
    /*-{

        return new $wnd.X.loadable();

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

}
