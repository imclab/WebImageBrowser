package edu.ucsd.ncmir.spl.XTK.objects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import edu.ucsd.ncmir.spl.XTK.core.XTKColortable;
import edu.ucsd.ncmir.spl.XTK.core.XTKScalars;
import edu.ucsd.ncmir.spl.XTK.core.XTKTexture;
import edu.ucsd.ncmir.spl.XTK.core.XTKTransform;
import edu.ucsd.ncmir.spl.XTK.core.XTKTriplets;

public class XTKMesh
    extends JavaScriptObject

{

    protected XTKMesh() {}  // No instantiations allowed.

    public native static XTKMesh create()
    /*-{

        return new $wnd.X.mesh();

       }-*/;

    public native final String getCaption()
    /*-{

        return this.caption;

       }-*/;

    public native final void setCaption( String caption )
    /*-{

        this.caption = caption;

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

    public native final double getLinewidth()
    /*-{

        return this.linewidth;

       }-*/;

    public native final void setLinewidth( double width )
    /*-{

        this.linewidth = linewidth;

       }-*/;

    public native final boolean getMagicmode()
    /*-{

        return this.magicmode;

       }-*/;

    public native final void setMagicmode( boolean magicmode )
    /*-{

        this.magicmode = magicmode;

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

    public native final boolean getVisible()
    /*-{

        return this.visible;

       }-*/;

    public native final void setVisible( boolean visible )
    /*-{

        this.visible = visible;

       }-*/;

    public native final void modified( boolean propagateEvent )
    /*-{

        this.modified( propagateEvent );

       }-*/;

}
