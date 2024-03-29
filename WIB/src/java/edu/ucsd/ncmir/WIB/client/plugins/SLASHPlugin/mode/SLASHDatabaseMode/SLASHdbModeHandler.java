package edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.mode.SLASHDatabaseMode;

import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.SLASHdbLinkMessage;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.Text;
import edu.ucsd.ncmir.WIB.client.debug.Debug;
import edu.ucsd.ncmir.WIB.client.core.Configuration;
import edu.ucsd.ncmir.WIB.client.core.message.Message;
import edu.ucsd.ncmir.WIB.client.core.message.MessageListener;
import edu.ucsd.ncmir.WIB.client.core.message.MessageManager;
import edu.ucsd.ncmir.WIB.client.core.messages.ImageErrorMessage;
import edu.ucsd.ncmir.WIB.client.core.request.AbstractXMLRequestCallback;
import edu.ucsd.ncmir.WIB.client.core.request.HTTPRequest;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.SLASHWorkerManager;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.ObjectListReadyMessage;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.RevertToImageManagerMessage;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.messages.SLASHModeHandlerReadyMessage;
import edu.ucsd.ncmir.WIB.client.plugins.SLASHPlugin.mode.AbstractSLASHModeHandler;
import edu.ucsd.ncmir.spl.XMLUtil.XML;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author spl
 */
public class SLASHdbModeHandler
    extends AbstractSLASHModeHandler

{

    private String _uri;
    private final SLASHWorkerManager _worker_manager;

    public SLASHdbModeHandler()
    {

        super();
        this._worker_manager = new SLASHWorkerManager();

    }

    @Override
    public String getURI()
    {

        return this._uri;

    }

    @Override
    public void start()
        
    {

        HTTPRequest.get( "cgi-bin/SLASHdb.pl?request=dataset" +
			 "&id=" + Configuration.parameter( "datasetID" ) +
			 "&model_id=" + Configuration.parameter( "modelID" ),
                         new DatasetHandler( this ) );
	HTTPRequest.get( "cgi-bin/SLASHdb.pl?request=get_objects" +
			 "&id=" + Configuration.parameter( "datasetID" ) +
			 "&model_id=" +
			 Configuration.parameter( "modelID" ),
			 new ObjectHandler( this ) );

    }

    private String[] _list;

    private void setObjectList( String[] list )

    {

	this._list = list;
	new ObjectListReadyMessage().send();

    }

    @Override
    public String[] getObjectList()

    {

        return this._list;

    }

    private static class DatasetHandler
        extends AbstractXMLRequestCallback
	implements MessageListener

    {

        private SLASHdbModeHandler _sdmh;

        DatasetHandler( SLASHdbModeHandler sdmh )
        {

            this._sdmh = sdmh;

	    MessageManager.registerListener( ImageErrorMessage.class,
					     this );

        }

        @Override
	public void action( Message m, Object o )

	{

	    this.startSLASHlink( this._zoomify_xml, false );

	}

	private Element _zoomify_xml = null;

        @Override
        public void handleXMLResponse( Element root )

        {

	    if ( root.getAttribute( "status" ).equals( "success" ) ) {

		this._zoomify_xml = root;

		this.startSLASHlink( this._zoomify_xml, true );

	    } else {

		Element error = XML.find( root, "error" );
		Text cause = ( Text ) error.getFirstChild();

		Debug.message( "Error talking to SLASH database\n" +
			       cause.getNodeValue() );

	    }

	}

	private void startSLASHlink( Element root, boolean initial )

	{

	    Element rp = XML.find( root, "resource_path" );
	    
	    String zf = rp.getAttribute( "zoomify_folder" );

	    String uri = rp.getAttribute( "uri" );
	    
	    new SLASHdbLinkMessage().send( uri );
	    
	    if ( initial && zf.equals( "" ) ) {
		
		this._sdmh._uri = uri;
                
		new RevertToImageManagerMessage().send();
		new SLASHModeHandlerReadyMessage().send();
		
		Window.alert( "Warning!\n" +
			      "\n" +
			      "Dataset ID: " +
			      Configuration.parameter( "datasetID" ) +
			      "\n" +
			      "Model ID: " +
			      Configuration.parameter( "modelID" ) + "\n" +
			      "\n" +
			      "The dataset\n" + uri + "\n" +
			      "has not been preprocessed.\n" +
			      "\n" +
			      "Will attempt to use ImageManager mode.\n" +
			      "\n" +
			      "Performance may be degraded.\n" +
			      "\n" +
			      "Please contact SLASH Administrator." );
		
	    } else {

		if ( initial ) 
		    HTTPRequest.get( "cgi-bin/SLASHlink.pl?request=link" +
				     "&path=" + zf,
				     new ZoomifyHandler( this._sdmh ) );
		else
		    HTTPRequest.get( "cgi-bin/SLASHlink.pl?request=link" +
				     "&path=" + zf );

	    }

	}

    }

    private static class ObjectHandler
        extends AbstractXMLRequestCallback
    {

        private SLASHdbModeHandler _sdmh;

        ObjectHandler( SLASHdbModeHandler sdmh )
        {

            this._sdmh = sdmh;

        }

        @Override
        public void handleXMLResponse( Element root )
        {

	    if ( root.getAttribute( "status" ).equals( "success" ) ) {

		Element objects = XML.find( root, "objects" );
		String[] elements =
                    XML.extractTextFromElement( objects ).split( "\n" );
		this._sdmh.setObjectList( elements );

            } else {

		Element error = XML.find( root, "error" );
		Text cause = ( Text ) error.getFirstChild();

		Debug.message( "Error talking to SLASH database\n" +
			       cause.getNodeValue() );

	    }

	}

    }

    private static class ZoomifyHandler
        extends AbstractXMLRequestCallback
    {

        private SLASHdbModeHandler _sdmh;

        ZoomifyHandler( SLASHdbModeHandler sdmh )
        {

            this._sdmh = sdmh;

        }

        @Override
        public void handleXMLResponse( Element root )
        {

            this._sdmh.zoomifyHandler( root );

        }

    }

    private void zoomifyHandler( Element root )
    {

        if ( root.getAttribute( "status" ).equals( "success" ) ) {

            Element zp = XML.find( root, "zoomify_path" );

            this._uri = "SLASH/" + zp.getAttribute( "path" );

            new SLASHModeHandlerReadyMessage().send();
                          
            KeepLinkAliveTimer klat =             
                new KeepLinkAliveTimer( zp.getAttribute( "key" ) );
            klat.scheduleRepeating( 25000 );                        

        } else {

            Element error = XML.find( root, "error" );
            Text cause = ( Text ) error.getFirstChild();

	    Window.alert( "Error starting path link handler\n" +
			  cause.getNodeValue() + "\n\n" +
			  "Please contact SLASH Administrator." );

        }

    }

    private static class KeepLinkAliveTimer
        extends Timer
    {

        private final String _key;

        KeepLinkAliveTimer( String key )

        {

            this._key = key;

        }

        @Override
        public void run()

        {

            HTTPRequest.get( "cgi-bin/SLASHlink.pl?request=keepalive" +
                             "&key=" + this._key );

        }

    }

    private HashSet<Integer> _in_flight = new HashSet<Integer>();

    @Override
    public void getContours( double x0, double y0, double z0,
                             double x1, double y1, double z1 )
    {


	for ( int z = ( int ) z0; z <= ( int ) z1; z++ )
	    if ( this._in_flight.add( z ) ) {

		String url = "cgi-bin/SLASHdb.pl?request=get_contours" +
		    "&id=" + Configuration.parameter( "datasetID" ) +
		    "&model_id=" + Configuration.parameter( "modelID" ) +
		    "&z=" + z;
		
		HTTPRequest.get( url, new GetContoursCallback( this ) );

	    }

    }
    
    private static class GetContoursCallback
	extends AbstractXMLRequestCallback

    {

	private final SLASHdbModeHandler _sdbmh;

	GetContoursCallback( SLASHdbModeHandler sdbmh )

	{

	    this._sdbmh = sdbmh;

	}

        @Override
        protected void handleXMLResponse( Element root )

        {

	    this._sdbmh.handleGetContours( root );

        }

    }
    
    private HashMap<Integer,String> _md5 = new HashMap<Integer,String>();

    public void handleGetContours( Element root )

    {

	int z = XML.getIntAttribute( root, "z" );

	this._in_flight.remove( z );

	if ( root.getAttribute( "status" ).equals( "success" ) ) {

	    String url = "../cgi-bin/SLASHdb.pl?request=get_contour_data&id=";

	    ElementMap element_map = new ElementMap();
	    for ( Node a = root.getFirstChild();
		  a != null;
		  a = a.getNextSibling() )
		if ( a.getNodeType() == Node.ELEMENT_NODE ) {

		    Element annotation = ( Element ) a;
		    
		    int annotation_id = 
			XML.getIntAttribute( annotation, "id" );
		    int color =
			XML.getIntAttribute( annotation, "color" );
		    String object_name =
			annotation.getAttribute( "object_name" );

		    for ( Node g = annotation.getFirstChild();
			  g != null;
			  g = g.getNextSibling() )
			if ( g.getNodeType() == Node.ELEMENT_NODE ) {

			    Element geometry = ( Element ) g;
			    
			    int geometry_id =
				XML.getIntAttribute( geometry, "geometry_id" );
			    String md5 = geometry.getAttribute( "geometry_id" );
			    
			    if ( !this._md5.containsKey( geometry_id ) ||
				 !this._md5.get( geometry_id ).equals( md5 ) ) {

				url += geometry_id + ",";
				this._md5.put( geometry_id, md5 );
				String user_name =
				    geometry.getAttribute( "user_name" );
				String geometry_type =
				    geometry.getAttribute( "geometry_type" );

				element_map.addInfo( geometry_id,
						     object_name,
						     user_name,
						     annotation_id,
						     geometry_type,
						     color );

			    }

			}

		}
	    if ( url.endsWith( "," ) ) {

		url = url.substring( 0, url.length() - 1 );

		this._worker_manager.request( url, element_map );

	    }

	}

    }

}
