package edu.ucsd.ncmir.spl.XTK.math;

import com.google.gwt.core.client.JavaScriptObject;

public class XTKVector
    extends JavaScriptObject

{

    protected XTKVector() {}  // No instantiations allowed.

    public native static XTKVector create()
    /*-{

        return new $wnd.X.vector();

       }-*/;

    public native static XTKVector create( double x, double y, double z )
    /*-{

        return new $wnd.X.vector( x, y, z );

       }-*/;

    public native final double getX()
    /*-{

        return this.xx;

       }-*/;

    public native final double getY()
    /*-{

        return this.yy;

       }-*/;

    public native final double getZ()
    /*-{

        return this.zz;

       }-*/;

    public native final XTKVector add( XTKVector b )
    /*-{

        return this.add( b );

       }-*/;

    public native final XTKVector klone()
    /*-{

        return this.clone();

       }-*/;

    public native final XTKVector invert()
    /*-{

        return this.invert();

       }-*/;

    public native final double magnitude()
    /*-{

        return this.magnitude();

       }-*/;

    public native final XTKVector normalize()
    /*-{

        return this.normalize();

       }-*/;

    public native final XTKVector scale( double s )
    /*-{

        return this.scale( s );

       }-*/;

    public native final XTKVector subtract( XTKVector b )
    /*-{

        return this.subtract( b );

       }-*/;

    public static native XTKVector cross( XTKVector a, XTKVector b )
    /*-{

        return $wnd.X.vector.cross( a, b );

       }-*/;

    public static native double dot( XTKVector a, XTKVector b )
    /*-{

        return $wnd.X.vector.dot( a, b );

       }-*/;

    public static native double distance( XTKVector a, XTKVector b )
    /*-{

        return $wnd.X.vector.distance( a, b );

       }-*/;

    public static native double lerp( XTKVector a, XTKVector b )
    /*-{

        return $wnd.X.vector.lerp( a, b );

       }-*/;

}
