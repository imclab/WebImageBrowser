package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import edu.ucsd.ncmir.spl.XTK.XTKEvent;
import edu.ucsd.ncmir.spl.XTK.io.XTKInteractor;
import edu.ucsd.ncmir.spl.XTK.objects.XTKObject;

public class XTKRenderer
    extends JavaScriptObject

{

    protected XTKRenderer() {};  // No instantiations allowed.

    public native static XTKRenderer create( String name )
    /*-{

        return new $wnd.X.renderer( name );

       }-*/;

    public native static XTKRenderer create()
    /*-{

        return new $wnd.X.renderer();

       }-*/;

    public native final XTKCamera getCamera()
    /*-{

        return this.camera();

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname();

       }-*/;

    public native final JavaScriptObject getConfig()
    /*-{

        return this.config;

       }-*/;

    public native final Element getContainer()
    /*-{

        return this.container;

       }-*/;

    public native final void setContainer( String container )
    /*-{

        this.container = container;

       }-*/;

    public native final void setContainer( Element container )
    /*-{

        this.container = container;

       }-*/;

    public native final String getId()
    /*-{

        return this.id();

       }-*/;

    public native final XTKInteractor getInteractor()
    /*-{

        return this.interactor();

       }-*/;

    public native final boolean getLoadingCompleted()
    /*-{

        return this.loadingCompleted();

       }-*/;

    public native final void add( XTKObject object )
    /*-{

        this.add( object );

       }-*/;

    public native final void destroy( )
    /*-{

        this.destroy( );

       }-*/;

    public native final XTKObject getGet( double id )
    /*-{

        return this.get( id );

       }-*/;

    public native final void onModified( XTKEvent event )
    /*-{

        this.onModified( event );

       }-*/;

    public native final void onProgress( XTKEvent event )
    /*-{

        this.onProgress( event );

       }-*/;

    public native final void onRender( )
    /*-{

        this.onRender( );

       }-*/;

    public native final void onShowtime( )
    /*-{

        this.onShowtime( );

       }-*/;

    public native final void printScene( )
    /*-{

        this.printScene( );

       }-*/;

    public native final void render( )
    /*-{

        this.render( );

       }-*/;

    public native final void resetViewAndRender( )
    /*-{

        this.resetViewAndRender( );

       }-*/;

}
