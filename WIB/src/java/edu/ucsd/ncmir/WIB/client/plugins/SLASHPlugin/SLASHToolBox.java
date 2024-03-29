package edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin;

import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.LivewireEnableMessage;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.InterpolateMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.EditMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.SLASHDragZoomMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.DrawPolylineMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.DrawPolygonMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.DeleteTraceMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.AddPolylineMessageFactory;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.message_factory.AddPolygonMessageFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Image;
import edu.ucsd.ncmir.WIB.client.core.Configuration;
import edu.ucsd.ncmir.WIB.client.core.message.Message;
import edu.ucsd.ncmir.WIB.client.core.message.MessageListener;
import edu.ucsd.ncmir.WIB.client.core.message.MessageManager;
import edu.ucsd.ncmir.WIB.client.core.messages.SetInteractionFactoryMessage;
import edu.ucsd.ncmir.WIB.client.core.toolbox.AbstractTool;
import edu.ucsd.ncmir.WIB.client.core.toolbox.AbstractToolBox;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.DisableLivewireMessage;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.resources.icons.SLASHToolBoxClientBundle;

/**
 *
 * @author spl
 */
class SLASHToolBox
    extends AbstractToolBox
    implements MessageListener

{

    private final SLASHToolBoxClientBundle _images =
	GWT.create( SLASHToolBoxClientBundle.class );
    private final AbstractTool _livewire_contour;

    public SLASHToolBox()

    {

        super( 10, true );

	MessageManager.registerListener( DisableLivewireMessage.class, this );

	if ( Cookies.isCookieEnabled() ) {

            String cookie_prefix = Configuration.getCookiePrefix();

	    String position = Cookies.getCookie( cookie_prefix +
						 ".toolbox_display_position" );

	    if ( position != null ) {

		String[] p = position.split( " " );

		super.setPopupPosition( Integer.parseInt( p[0] ),
					Integer.parseInt( p[1] ) );


	    }

	}

	super.addTitle( "SLASH Toolbox" );

	SLASHDragZoomMessageFactory dzmf = new SLASHDragZoomMessageFactory();

	new SetInteractionFactoryMessage().send( dzmf );

	super.addRadioTool( new Image( this._images.move() ),
			    "Pan the image.",
			    "draw",
			    dzmf );
	super.addRadioTool( new Image( this._images.draw() ),
			    "Draw a closed object.",
			    "draw",
			    new DrawPolygonMessageFactory() );
 	super.addRadioTool( new Image( this._images.add_contour() ),
			    "Add a closed contour to an object.",
			    "draw",
			    new AddPolygonMessageFactory() );
	super.addRadioTool( new Image( this._images.draw_open() ),
			    "Draw an open object (polyline).",
			    "draw",
			    new DrawPolylineMessageFactory() );
 	super.addRadioTool( new Image( this._images.add_trace() ),
			    "Add an open trace to an object.",
			    "draw",
			    new AddPolylineMessageFactory() );
 	super.addRadioTool( new Image( this._images.interpolate() ),
			    "Interpolate traces.",
			    "draw",
			    new InterpolateMessageFactory() );
 	super.addRadioTool( new Image( this._images.edit() ),
			    "Edit trace or annotation.",
			    "draw",
			    new EditMessageFactory() );
 	super.addRadioTool( new Image( this._images.cut() ),
			    "Delete a trace.",
			    "draw",
			    new DeleteTraceMessageFactory() );

	this._livewire_contour =
	    super.addToggleTool( new Image( this._images.livewire_contour() ),
				 "Trace using Livewire.",
				 new LivewireEnableMessage() );

        super.setControlWidget( new SelectAnnotationWidget() );

    }

    @Override
    public void action( Message m, Object o )

    {

	if ( m instanceof DisableLivewireMessage )
	    this._livewire_contour.setVisible( false );

    }

    @Override
    protected void endDragging( MouseUpEvent event )

    {

	super.endDragging( event );

	if ( Cookies.isCookieEnabled() ) {

            String cookie_prefix = Configuration.getCookiePrefix();

            Cookies.setCookie( cookie_prefix + ".toolbox_display_position",
			       this.getAbsoluteLeft() + " " +
			       this.getAbsoluteTop() );

	}

    }
}
