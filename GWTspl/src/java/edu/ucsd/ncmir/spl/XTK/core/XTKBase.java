package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKBase
    extends JavaScriptObject

{

    protected XTKBase() {}  // No instantiations allowed.

    public native static XTKBase create()
    /*-{

        return new $wnd.X.base();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

}
