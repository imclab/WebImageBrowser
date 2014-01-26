package edu.ucsd.ncmir.spl.XTK.core;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKScalars
    extends JavaScriptObject

{

    protected XTKScalars() {}  // No instantiations allowed.

    public native static XTKScalars create()
    /*-{

        return new $wnd.X.scalars();

       }-*/;

    public native final JsArray getArray()
    /*-{

        return this.array;

       }-*/;

    public native final void setArray( JsArray array )
    /*-{

        this.array = array;

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

    public native final double getInterpolation()
    /*-{

        return this.interpolation;

       }-*/;

    public native final void setInterpolation( double value )
    /*-{

        this.interpolation = interpolation;

       }-*/;

    public native final double getLowerThreshold()
    /*-{

        return this.lowerThreshold;

       }-*/;

    public native final void setLowerThreshold( double lowerThreshold )
    /*-{

        this.lowerThreshold = lowerThreshold;

       }-*/;

    public native final JsArray getMaxColor()
    /*-{

        return this.maxColor;

       }-*/;

    public native final void setMaxColor( JsArray maxColor )
    /*-{

        this.maxColor = maxColor;

       }-*/;

    public native final double getMax()
    /*-{

        return this.max;

       }-*/;

    public native final JsArray getMinColor()
    /*-{

        return this.minColor;

       }-*/;

    public native final void setMinColor( JsArray minColor )
    /*-{

        this.minColor = minColor;

       }-*/;

    public native final double getMin()
    /*-{

        return this.min;

       }-*/;

    public native final double getUpperThreshold()
    /*-{

        return this.upperThreshold;

       }-*/;

    public native final void setUpperThreshold( double upperThreshold )
    /*-{

        this.upperThreshold = upperThreshold;

       }-*/;

}
