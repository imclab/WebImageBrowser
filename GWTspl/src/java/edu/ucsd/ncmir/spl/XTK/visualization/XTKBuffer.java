package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKBuffer
    extends JavaScriptObject

{

    protected XTKBuffer() {};  // No instantiations allowed.

    public native static XTKBuffer create()
    /*-{

        return new $wnd.X.buffer();

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
