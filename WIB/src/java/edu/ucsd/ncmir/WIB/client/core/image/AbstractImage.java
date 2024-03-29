package edu.ucsd.ncmir.WIB.client.core.image;

import edu.ucsd.ncmir.WIB.client.core.messages.ImageErrorMessage;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import edu.ucsd.ncmir.WIB.client.core.Configuration;

/**
 *
 * @author spl
 */
public class AbstractImage
    extends AbsolutePanel
    implements ErrorHandler

{

    private final String _url;
    private final Image _image;

    /**
     * Creates a <code>AbstractImage</code> object.
     * @param parms URL parameters.
     */

    protected AbstractImage( String parms, boolean load )

    {

	this._url = Configuration.getPlugin().getTransactionURL() + parms;
	this._image = new Image();
	this._image.addErrorHandler( this );
	if ( load )
	    this.load();

    }

    protected final void load()

    {

	this._image.setUrl( this._url + "?loadcount=" + this._error_count );
	this.add( this._image );

    }

    private int _error_count = 0;

    @Override
    public void onError( ErrorEvent event )

    {

	if ( this._error_count++ < 5 ) {

	    new ImageErrorMessage().send( event );
	    this.remove( this._image );
	    this.load();

	}

    }

    /**
     * Returns the image.
     * @return The Image.
     */
    protected Image getImage()

    {

	return this._image;

    }

}
