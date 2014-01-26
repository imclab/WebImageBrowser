package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKFile
    extends JavaScriptObject

{

    protected XTKFile() {};  // No instantiations allowed.

    public native static XTKFile create()
    /*-{

        return new $wnd.X.file();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

}
