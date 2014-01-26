package edu.ucsd.ncmir.spl.XTK.objects;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayNumber;
import edu.ucsd.ncmir.spl.XTK.core.XTKColortable;
import edu.ucsd.ncmir.spl.XTK.core.XTKScalars;
import edu.ucsd.ncmir.spl.XTK.core.XTKTexture;
import edu.ucsd.ncmir.spl.XTK.core.XTKTransform;
import edu.ucsd.ncmir.spl.XTK.core.XTKTriplets;

public class XTKCube
    extends JavaScriptObject

{

    protected XTKCube() {}  // No instantiations allowed.

    public native static XTKCube create()
    /*-{

        return new $wnd.X.cube();

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

    public native final void setCenter( double x, double y, double z )
    /*-{

        this.center = [x, y, z];

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

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

    public native final double getLengthX()
    /*-{

        return this.lengthX;

       }-*/;

    public native final void setLengthX( double lengthX )
    /*-{

        this.lengthX = lengthX;

       }-*/;

    public native final double getLengthY()
    /*-{

        return this.lengthY;

       }-*/;

    public native final void setLengthY( double lengthY )
    /*-{

        this.lengthY = lengthY;

       }-*/;

    public native final double getLengthZ()
    /*-{

        return this.lengthZ;

       }-*/;

    public native final void setLengthZ( double lengthZ )
    /*-{

        this.lengthZ = lengthZ;

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

    public native final XTKObject getIntersect( XTKObject object )
    /*-{

        return this.intersect( object );

       }-*/;

    public native final XTKObject getInverse()
    /*-{

        return this.inverse;

       }-*/;

    public native final void modified( boolean propagateEvent )
    /*-{

        this.modified( propagateEvent );

       }-*/;

    public native final XTKObject getSubtract( XTKObject object )
    /*-{

        return this.subtract( object );

       }-*/;

   public native final XTKObject getUnion( XTKObject object )
    /*-{

        return this.union( object );

       }-*/;

}
