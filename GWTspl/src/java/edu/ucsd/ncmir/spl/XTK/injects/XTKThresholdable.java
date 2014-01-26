package edu.ucsd.ncmir.spl.XTK.injects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class XTKThresholdable
    extends JavaScriptObject

{

    protected XTKThresholdable() {};  // No instantiations allowed.

    public native static XTKThresholdable create()
    /*-{

        return new $wnd.X.thresholdable();

       }-*/;

    public native final double getLowerThreshold()
    /*-{

        return this.lowerThreshold();

       }-*/;

    public native final void lowerThreshold( )
    /*-{

        this.lowerThreshold( );

       }-*/;

    public native final JsArray getMaxColor()
    /*-{

        return this.maxColor();

       }-*/;

    public native final void maxColor( )
    /*-{

        this.maxColor( );

       }-*/;

    public native final double getMax()
    /*-{

        return this.max();

       }-*/;

    public native final JsArray getMinColor()
    /*-{

        return this.minColor();

       }-*/;

    public native final void minColor( )
    /*-{

        this.minColor( );

       }-*/;

    public native final double getMin()
    /*-{

        return this.min();

       }-*/;

    public native final double getUpperThreshold()
    /*-{

        return this.upperThreshold();

       }-*/;

    public native final void upperThreshold( )
    /*-{

        this.upperThreshold( );

       }-*/;

}
