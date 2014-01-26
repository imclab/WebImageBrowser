package edu.ucsd.ncmir.spl.XTK.math;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;

public class XTKMatrix
    extends JavaScriptObject

{

    protected XTKMatrix() {}  // No instantiations allowed.

    public static native XTKMatrix klone( XTKMatrix matrix )
    /*-{

        return $wnd.X.matrix.clone( matrix );

       }-*/;

    public static native double determinant( XTKMatrix matrix )
    /*-{

        return $wnd.X.matrix.determinant( matrix );

       }-*/;

    public static native XTKMatrix identity( XTKMatrix matrix )
    /*-{

        return $wnd.X.matrix.identity( matrix );

       }-*/;

    public static native XTKMatrix invert( XTKMatrix matrix )
    /*-{

        return $wnd.X.matrix.invert( matrix );

       }-*/;

    public static native XTKMatrix makeFrustum( XTKMatrix matrix,
						double left,
						double right,
						double top,
						double bottom,
						double near,
						double far )
    /*-{

        return $wnd.X.matrix.makeFrustum( matrix,
	                                  left, right,
					  top, bottom,
					  near, far );

       }-*/;

    public static native XTKMatrix makeLookat( XTKMatrix matrix,
					       JsArrayNumber eye_pt,
					       JsArrayNumber center_pt,
					       JsArrayNumber world_up_vec )
    /*-{

        return $wnd.X.matrix.makeLookat( matrix,
	                                 eye_pt, center_pt, world_up_vec );

       }-*/;


    public static native double makeOrtho( XTKMatrix matrix,
					   double left,
					   double right,
					   double top,
					   double bottom,
					   double near,
					   double far )
    /*-{

        return $wnd.X.matrix.makeOrtho( matrix,
	                                left, right,
					top, bottom,
					near, far );

       }-*/;

    public static native double makePerspective( XTKMatrix matrix,
						 double fovy,
						 double aspect,
						 double near,
						 double far )
    /*-{

        return $wnd.X.matrix.makePerspective( matrix,
	                                      fovy, aspect,
					      near, far );

       }-*/;

    public static native XTKMatrix multiply( XTKMatrix m1,
					     XTKMatrix m2,
					     XTKMatrix mr )
    /*-{

        return $wnd.X.matrix.multiply( m0, m1, mr );

       }-*/;

    public static native XTKVector multiplyByVector( XTKMatrix matrix,
						     double x,
						     double y,
						     double z )
    /*-{

        return $wnd.X.matrix.multiplyByVector( matrix, x, y, z );

       }-*/;


    public static native XTKMatrix rotate( XTKMatrix matrix,
					   double angle,
					   double x,
					   double y,
					   double z )
    /*-{

        return $wnd.X.matrix.rotate( matrix, angle, x, y, z );

       }-*/;

    public static native XTKMatrix rotateX( XTKMatrix matrix, double angle )
    /*-{

        return $wnd.X.matrix.rotateX( matrix, angle );

       }-*/;

    public static native XTKMatrix rotateY( XTKMatrix matrix, double angle )
    /*-{

        return $wnd.X.matrix.rotateY( matrix, angle );

       }-*/;

    public static native XTKMatrix rotateZ( XTKMatrix matrix, double angle )
    /*-{

        return $wnd.X.matrix.rotateZ( matrix, angle );

       }-*/;

    public static native XTKMatrix scale( XTKMatrix matrix,
					  double x,
					  double y,
					  double z )
    /*-{

        return $wnd.X.matrix.scale( matrix, x, y, z );

       }-*/;

    public static native XTKMatrix swapCols( XTKMatrix matrix,
					     int col1, int col2 )
    /*-{

        return $wnd.X.matrix.swapCols( matrix, col1, col2 )

       }-*/;

    public static native XTKMatrix swapRows( XTKMatrix matrix,
					     int row1, int row2 )
    /*-{

        return $wnd.X.matrix.swapRows( matrix, row1, row2 )

       }-*/;

    public static native XTKMatrix translate( XTKMatrix matrix,
					      double x,
					      double y,
					      double z )
    /*-{

        return $wnd.X.matrix.translate( matrix, x, y, z );

       }-*/;

    public static native XTKMatrix transpose( XTKMatrix matrix,
					      XTKMatrix transp )
    /*-{

        return $wnd.X.matrix.transpose( matrix, transp );

       }-*/;

}
