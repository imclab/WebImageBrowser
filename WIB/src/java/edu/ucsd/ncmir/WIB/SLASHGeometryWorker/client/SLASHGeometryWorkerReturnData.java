package edu.ucsd.ncmir.WIB.SLASHGeometryWorker.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;

/**
 *
 * @author spl
 */
public class SLASHGeometryWorkerReturnData
    extends JavaScriptObject

{

    protected SLASHGeometryWorkerReturnData() {}

    public static native SLASHGeometryWorkerReturnData create( String status )
    /*-{
      return {
      type: 0,
      status: status
      }
      }-*/;
    
    public native final int getType()
    /*-{

      return this.type;

      }-*/;

    public native final String getStatus()
    /*-{

      return this.status;

      }-*/;

    public static native SLASHGeometryWorkerReturnData
	create( int color,
		JsArray<JsArrayNumber> points,
		JsArray<JsArrayNumber> normals,
		JsArray<JsArrayInteger> indices )
    /*-{

      return {
      type : 1,
      color : color,
      points : points,
      normals : normals,
      indices : indices
      };

      }-*/;

    public native final JsArray<JsArrayNumber> getPoints()
    /*-{

      return this.points;

      }-*/;

    public native final JsArray<JsArrayNumber> getNormals()
    /*-{

      return this.normals;

      }-*/;
    public native final JsArray<JsArrayInteger> getIndices()
    /*-{

      return this.indices;

      }-*/;

    public static native SLASHGeometryWorkerReturnData
	create( int color,
		JsArrayInteger z_index,
		JsArray<JsArrayInteger> types,
		JsArray<JsArray<JsArrayNumber>> contourx,
		JsArray<JsArray<JsArrayNumber>> contoury )
    /*-{

      return {
      type : 1,
      color : color,
      types : types,
      z_index : z_index,
      contourx : contourx,
      contoury : contoury,
      };

      }-*/;

    public native final int getColor()
    /*-{

      return this.color;

      }-*/;

    public native final JsArrayInteger getZIndex()
    /*-{

      return this.z_index;

      }-*/;

    public native final JsArray<JsArrayInteger> getTypes()
    /*-{

      return this.types;

      }-*/;

    public native final JsArray<JsArray<JsArrayNumber>> getContourX()
    /*-{

      return this.contourx;

      }-*/;

    public native final JsArray<JsArray<JsArrayNumber>> getContourY()
    /*-{

      return this.contoury;

      }-*/;


}

