package edu.ucsd.ncmir.spl.XTK.injects;

import com.google.gwt.core.client.JavaScriptObject;
import edu.ucsd.ncmir.spl.XTK.objects.XTKObject;

public class XTKConstructable
    extends JavaScriptObject

{

    protected XTKConstructable() {}  // No instantiations allowed.

    public native static XTKConstructable create()
    /*-{

        return new $wnd.X.constructable();

       }-*/;

    public native final XTKObject intersect( XTKObject object )
    /*-{

        return this.intersect( object );

       }-*/;

    public native final XTKObject getInverse()
    /*-{

        return this.inverse;

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
