package edu.ucsd.ncmir.WIB.client.core.drawable;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseEvent;
import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;
import com.googlecode.mgwt.dom.client.event.touch.TouchEvent;
import com.googlecode.mgwt.dom.client.recognizer.pinch.PinchEvent;
import edu.ucsd.ncmir.WIB.client.core.message.Message;
import edu.ucsd.ncmir.WIB.client.core.message.MessageListener;
import edu.ucsd.ncmir.WIB.client.core.messages.UpdateOriginMessage;

/**
 * <code>Point</code> objects created here.
 * @author spl
 */

public final class PointFactory
    implements MessageListener

{

    private static double _xorg = 0;
    private static double _yorg = 0;
    private static ScaleFactor _zoom = new ScaleFactor( 0 );

    @Override
    public void action( Message m, Object o )

    {

	UpdateOriginMessage uom = ( UpdateOriginMessage ) m;

	PointFactory._xorg = uom.getXOrg();
	PointFactory._yorg = uom.getYOrg();
	PointFactory._zoom = uom.getZoom();

    }

    /**
     * Creates a <code>Point</code> object from the coordinates contained
     * within the <code>MouseEvent</code>.
     * @param event The <code>MouseEvent</code>
     * @return An <code>Point</code> object.
     */
    public static Point create( MouseEvent event )

    {

        return PointFactory.create( event.getX(), event.getY() );

    }

    /**
     * Creates a <code>Point</code> object from the coordinates contained
     * within the <code>TouchEvent</code>.
     * @param event The <code>TouchEvent</code>.
     * @return An <code>Point</code> object.
     */
    public static Point create( TouchEvent event )

    {

        LightArray<Touch> touches = event.getChangedTouches();
        Touch t = ( Touch ) touches.get( touches.length() - 1 );
	Element e = event.getRelativeElement();

        return PointFactory.create( t.getPageX() - e.getAbsoluteLeft(),
				    t.getPageY() - e.getAbsoluteTop() );

    }

    public static Point create( PinchEvent event )

    {

        return PointFactory.create( event.getX(), event.getY() );

    }

    // This should be the ONLY place in the application where this
    // calculation needs to be made.

    private static Point create( int x, int y )

    {

	ScaleFactor zoom = PointFactory._zoom;
        double scale = 1 / ( ( zoom != null ) ? zoom.getScale() : 1 );

        return new Point( scale,
			  ( x * scale ) + PointFactory._xorg,
			  ( y * scale ) + PointFactory._yorg,
			  x,
			  y );

    }

}
