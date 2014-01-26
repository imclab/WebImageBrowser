package edu.ucsd.ncmir.spl.XTK.objects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import edu.ucsd.ncmir.spl.XTK.core.XTKColortable;
import edu.ucsd.ncmir.spl.XTK.core.XTKScalars;
import edu.ucsd.ncmir.spl.XTK.core.XTKTexture;
import edu.ucsd.ncmir.spl.XTK.core.XTKTransform;
import edu.ucsd.ncmir.spl.XTK.core.XTKTriplets;

public class XTKLabelmap
    extends JavaScriptObject

{

    protected XTKLabelmap() {}  // No instantiations allowed.

    public native static XTKLabelmap create()
    /*-{

        return new $wnd.X.labelmap();

       }-*/;

    public native final boolean getBorders()
    /*-{

        return this.borders;

       }-*/;

    public native final void setBorders( boolean borders )
    /*-{

        this.borders = borders;

       }-*/;

    public native final String getCaption()
    /*-{

        return this.caption;

       }-*/;

    public native final void setCaption( String caption )
    /*-{

        this.caption = caption;

       }-*/;

    public native final JsArray getCenter()
    /*-{

        return this.center;

       }-*/;

    public native final void setCenter( JsArray center )
    /*-{

        this.center = center;

       }-*/;

    public native final JsArray getChildren()
    /*-{

        return this.children;

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final JsArrayNumber getColor()
    /*-{

        return this.color;

       }-*/;

    public native final void setColor( JsArrayNumber color )
    /*-{

        this.color = color;

       }-*/;

    public native final void setColor( int red, int green, int blue )
    /*-{

        this.color = [ red / 255.0, green / 255.0, blue / 255.0 ];

       }-*/;

    public native final void setColor( double red, double green, double blue )
    /*-{

        this.color = [ red, green, blue ];

       }-*/;

    public native final XTKTriplets getColors()
    /*-{

        return this.colors;

       }-*/;

    public native final void setColors( XTKTriplets colors )
    /*-{

        this.colors = colors;

       }-*/;

    public native final XTKColortable getColortable()
    /*-{

        return this.colortable;

       }-*/;

    public native final JsArray getDimensions()
    /*-{

        return this.dimensions;

       }-*/;

    public native final String getFile()
    /*-{

        return this.file;

       }-*/;

    public native final void setFile( String filepath )
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

    public native final JsArray getImage()
    /*-{

        return this.image;

       }-*/;

    public native final double getIndexX()
    /*-{

        return this.indexX;

       }-*/;

    public native final void setIndexX( double indexX )
    /*-{

        this.indexX = indexX;

       }-*/;

    public native final double getIndexY()
    /*-{

        return this.indexY;

       }-*/;

    public native final void setIndexY( double indexY )
    /*-{

        this.indexY = indexY;

       }-*/;

    public native final double getIndexZ()
    /*-{

        return this.indexZ;

       }-*/;

    public native final void setIndexZ( double indexZ )
    /*-{

        this.indexZ = indexZ;

       }-*/;

    public native final XTKVolume getLabelmap()
    /*-{

        return this.labelmap;

       }-*/;

    public native final double getLinewidth()
    /*-{

        return this.linewidth;

       }-*/;

    public native final void setLinewidth( double width )
    /*-{

        this.linewidth = linewidth;

       }-*/;

    public native final double getLowerThreshold()
    /*-{

        return this.lowerThreshold;

       }-*/;

    public native final void setLowerThreshold( double lowerThreshold )
    /*-{

        this.lowerThreshold = lowerThreshold;

       }-*/;

    public native final boolean getMagicmode()
    /*-{

        return this.magicmode;

       }-*/;

    public native final void setMagicmode( boolean magicmode )
    /*-{

        this.magicmode = magicmode;

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

    public native final XTKTriplets getNormals()
    /*-{

        return this.normals;

       }-*/;

    public native final void setNormals( XTKTriplets normals )
    /*-{

        this.normals = normals;

       }-*/;

    public native final double getOpacity()
    /*-{

        return this.opacity;

       }-*/;

    public native final void setOpacity( double opacity )
    /*-{

        this.opacity = opacity;

       }-*/;

    public native final XTKTriplets getPoints()
    /*-{

        return this.points;

       }-*/;

    public native final void setPoints( XTKTriplets points )
    /*-{

        this.points = points;

       }-*/;

    public native final double getPointsize()
    /*-{

        return this.pointsize;

       }-*/;

    public native final void setPointsize( double size )
    /*-{

        this.pointsize = pointsize;

       }-*/;

    public native final boolean getReslicing()
    /*-{

        return this.reslicing;

       }-*/;

    public native final void setReslicing( boolean reslicing )
    /*-{

        this.reslicing = reslicing;

       }-*/;

    public native final XTKScalars getScalars()
    /*-{

        return this.scalars;

       }-*/;

    public native final XTKTexture getTexture()
    /*-{

        return this.texture;

       }-*/;

    public native final XTKTransform getTransform()
    /*-{

        return this.transform;

       }-*/;

    public native final String getType()
    /*-{

        return this.type;

       }-*/;

    public native final void setType( String type )
    /*-{

        this.type = type;

       }-*/;

    public native final double getUpperThreshold()
    /*-{

        return this.upperThreshold;

       }-*/;

    public native final void setUpperThreshold( double upperThreshold )
    /*-{

        this.upperThreshold = upperThreshold;

       }-*/;

    public native final boolean getVisible()
    /*-{

        return this.visible;

       }-*/;

    public native final void setVisible( boolean visible )
    /*-{

        this.visible = visible;

       }-*/;

    public native final boolean getVolumeRendering()
    /*-{

        return this.volumeRendering;

       }-*/;

    public native final void setVolumeRendering( boolean volumeRendering )
    /*-{

        this.volumeRendering = volumeRendering;

       }-*/;

    public native final double getWindowHigh()
    /*-{

        return this.windowHigh;

       }-*/;

    public native final void setWindowHigh( double windowHigh )
    /*-{

        this.windowHigh = windowHigh;

       }-*/;

    public native final double getWindowLow()
    /*-{

        return this.windowLow;

       }-*/;

    public native final void setWindowLow( double windowLow )
    /*-{

        this.windowLow = windowLow;

       }-*/;

    public native final void modified( boolean propagateEvent )
    /*-{

        this.modified( propagateEvent );

       }-*/;

}
