package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import edu.ucsd.ncmir.spl.XTK.io.XTKInteractor;
import edu.ucsd.ncmir.spl.XTK.objects.XTKObject;

public class XTKRenderer3D
    extends JavaScriptObject

{

    protected XTKRenderer3D() {}  // No instantiations allowed.

    public native static XTKRenderer3D create( String name )
    /*-{

        return new $wnd.X.renderer3D( name );

       }-*/;

    public native static XTKRenderer3D create()
    /*-{

        return new $wnd.X.renderer3D();

       }-*/;

   public native final XTKCamera getCamera()
    /*-{

        return this.camera;

       }-*/;

    public native final String getClassname()
    /*-{

        return this.classname;

       }-*/;

    public native final JavaScriptObject getConfig()
    /*-{

        return this.config;

       }-*/;

    public native final Element getContainer()
    /*-{

        return this.container;

       }-*/;

    public native final void setContainer( Element container )
    /*-{

        this.container = container;

       }-*/;

    public native final String getId()
    /*-{

        return this.id;

       }-*/;

    public native final XTKInteractor getInteractor()
    /*-{

        return this.interactor;

       }-*/;

    public native final boolean getLoadingCompleted()
    /*-{

        return this.loadingCompleted;

       }-*/;

    public native final void add( Object object )
    /*-{

        this.add( object );

       }-*/;
    
    public native final boolean remove( Object object )
    /*-{

      return this.remove( object );

       }-*/;

    public native final void destroy()
    /*-{

        this.destroy();

       }-*/;

    public native final XTKObject getObject( int id )
    /*-{

        return this.get( id );

       }-*/;

    public native final void setOnShowtimeHandler( XTK3DShowtimeHandler h )
    /*-{

        this._on_showtime_handler = h;
	this.onShowtime = function() {

	    if ( this._on_showtime_handler != null )
	        this._on_showtime_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK3DShowtimeHandler::onShowtime(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer3D;)(this);

	}

       }-*/;

    public native final void setOnRenderHandler( XTK3DRenderHandler h )
    /*-{

        this._on_render_handler = h;
	this.onRender = function() {

	    if ( this._on_render_handler != null )
	        this._on_render_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK3DRenderHandler::onRender(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer3D;)(this);

	}

       }-*/;

    public native final void setOnModifiedHandler( XTK3DModifiedHandler h )
    /*-{

        this._on_modified_handler = h;
	this.onModified = function(event) {

	    if ( this._on_modified_handler != null )
	        this._on_modified_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK3DModifiedHandler::onModified(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer3D;Ledu/ucsd/ncmir/spl/XTK/XTKEvent;)(this,event);

	}

       }-*/;

    public native final void setOnProgressHandler( XTK3DProgressHandler h )
    /*-{

        this._on_render_handler = h;
	this.onProgress = function() {

	    if ( this._on_render_handler != null )
	        this._on_render_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK3DProgressHandler::onProgress(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer3D;Ledu/ucsd/ncmir/spl/XTK/XTKEvent;)(this,event);

	}

       }-*/;

    public native final double getPick( double x, double y )
    /*-{

        return this.pick( x, y );

       }-*/;

    public native final void render()
    /*-{

        this.render();

       }-*/;

    public native final void resetBoundingBox()
    /*-{

        this.resetBoundingBox();

       }-*/;

    public native final void resetViewAndRender()
    /*-{

        this.resetViewAndRender();

       }-*/;

    public native final void init()

    /*-{

        this.init();

       }-*/;

    public native final void init( String name )

    /*-{

        this.init( name );

       }-*/;

}
