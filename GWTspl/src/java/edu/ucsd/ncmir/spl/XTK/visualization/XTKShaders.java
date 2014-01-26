package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKShaders
    extends JavaScriptObject

{

    protected XTKShaders() {};  // No instantiations allowed.

    public native static XTKShaders create()
    /*-{

        return new $wnd.X.shaders();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

    public native final String getFragment()
    /*-{

        return this.fragment();

       }-*/;

    public native final boolean getValidate()
    /*-{

        return this.validate();

       }-*/;

    public native final String getVertex()
    /*-{

        return this.vertex();

       }-*/;

}
