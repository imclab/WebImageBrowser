package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKTexture
    extends JavaScriptObject

{

    protected XTKTexture() {}  // No instantiations allowed.

    public native static XTKTexture create()
    /*-{

        return new $wnd.X.texture();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final String getFile()
    /*-{

        return this.file;

       }-*/;

    public native final void setFile( String file )
    /*-{

        this.file = file;

       }-*/;

    public native final void setFile( JsArray file )
    /*-{

        this.file = file;

       }-*/;

    public native final String getFiledata()
    /*-{

        return this.filedata;

       }-*/;

    public native final void setFiledata( String filedata )
    /*-{

        this.filedata = filedata;

       }-*/;

    public native final void setFiledata( JsArray filedata )
    /*-{

        this.filedata = filedata;

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

}
