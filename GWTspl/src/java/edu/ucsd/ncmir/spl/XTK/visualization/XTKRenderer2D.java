package edu.ucsd.ncmir.spl.XTK.visualization;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import edu.ucsd.ncmir.spl.XTK.io.XTKInteractor;
import edu.ucsd.ncmir.spl.XTK.objects.XTKObject;

public class XTKRenderer2D
    extends JavaScriptObject

{

    protected XTKRenderer2D() {}  // No instantiations allowed.

    public native static XTKRenderer2D create( String name )
    /*-{

        return new $wnd.X.renderer2D( name );

       }-*/;

    public native static XTKRenderer2D create()
    /*-{

        return new $wnd.X.renderer2D();

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

    public native final String getOrientation()
    /*-{

        return this.orientation;

       }-*/;

    public native final void setOrientation( String orientation )
    /*-{

        this.orientation = orientation;

       }-*/;

    public native final void add( XTKObject object )
    /*-{

        this.add( object );

       }-*/;

    public native final void destroy( )
    /*-{

        this.destroy( );

       }-*/;

    public native final XTKObject getObject( int id )
    /*-{

        return this.get( id );

       }-*/;


    public native final void setOnScrollHandler( XTK2DScrollHandler h )
    /*-{

        this._on_scroll_handler = h;
	this.onScroll = function() {

	    if ( this._on_scroll_handler != null )
	        this._on_scroll_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DScrollHandler::onScroll(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;)(this);

	}

       }-*/;

    public native final void setOnShowtimeHandler( XTK2DShowtimeHandler h )
    /*-{

        this._on_showtime_handler = h;
	this.onShowtime = function() {

	    if ( this._on_showtime_handler != null )
	        this._on_showtime_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DShowtimeHandler::onShowtime(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;)(this);

	}

       }-*/;

    public native final void setOnRenderHandler( XTK2DRenderHandler h )
    /*-{

        this._on_render_handler = h;
	this.onRender = function() {

	    if ( this._on_render_handler != null )
	        this._on_render_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DRenderHandler::onRender(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;)(this);

	}

       }-*/;

    public native final void setOnWindowLevelHandler( XTK2DWindowLevelHandler h )
    /*-{

        this._on_window_level_handler = h;
	this.onWindowLevel = function() {

	    if ( this._on_window_level_handler != null )
	        this._on_window_level_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DWindowLevelHandler::onWindowLevel(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;)(this);

	}

       }-*/;

    public native final void setOnModifiedHandler( XTK2DModifiedHandler h )
    /*-{

        this._on_modified_handler = h;
	this.onModified = function(event) {

	    if ( this._on_modified_handler != null )
	        this._on_modified_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DModifiedHandler::onModified(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;Ledu/ucsd/ncmir/spl/XTK/XTKEvent;)(this,event);

	}

       }-*/;

    public native final void setOnProgressHandler( XTK2DProgressHandler h )
    /*-{

        this._on_render_handler = h;
	this.onProgress = function() {

	    if ( this._on_render_handler != null )
	        this._on_render_handler.@edu.ucsd.ncmir.spl.XTK.visualization.XTK2DProgressHandler::onProgress(Ledu/ucsd/ncmir/spl/XTK/visualization/XTKRenderer2D;Ledu/ucsd/ncmir/spl/XTK/XTKEvent;)(this,event);

	}

       }-*/;

    public native final void render( )
    /*-{

        this.render( );

       }-*/;

    public native final void resetViewAndRender( )
    /*-{

        this.resetViewAndRender( );

       }-*/;

    public native final void rotate( )
    /*-{

        this.rotate( );

       }-*/;

    public native final void rotateCounter( )
    /*-{

        this.rotateCounter( );

       }-*/;

}
