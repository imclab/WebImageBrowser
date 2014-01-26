package edu.ucsd.ncmir.spl.Graphics.marchingcubes.surfacer;

import edu.ucsd.ncmir.spl.Graphics.PlanarPolygon;
import edu.ucsd.ncmir.spl.Graphics.PlanarPolygonTable;
import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.Graphics.geometry.GeometryComponent;
import edu.ucsd.ncmir.spl.Graphics.marchingcubes.MarchingCubes;
import edu.ucsd.ncmir.spl.Graphics.meshables.TriangleMesh;

/**
 *
 * @author spl
 */
public class MarchingCubesSurfacer

{

    // Thou shalt not instantiate.

    private MarchingCubesSurfacer()

    {

    }

    public static TriangleMesh generateSurface( PlanarPolygonTable plist,
						Capper capper )
        throws Exception

    {

	TriangleMesh mesh = new TriangleMesh();

	Surface surface = new Surface();

	PlanarPolygon[] polygons = plist.getAllPolygons();

	double xmin = Double.MAX_VALUE;
	double xmax = -Double.MAX_VALUE;

	double ymin = Double.MAX_VALUE;
	double ymax = -Double.MAX_VALUE;

	double dsmin = Double.MAX_VALUE;

	for ( PlanarPolygon polygon : polygons ) {

	    for ( Triplet triplet : polygon ) {

		double x = triplet.getU();
		double y = triplet.getV();

		if ( xmin > x )
		    xmin = x;
		if ( xmax < x )
		    xmax = x;

		if ( ymin > y )
		    ymin = y;
		if ( ymax < y )
		    ymax = y;

	    }

	    double x0 = polygon.get( 0 ).getU();
	    double y0 = polygon.get( 0 ).getV();

	    double x1 = polygon.get( 1 ).getU();
	    double y1 = polygon.get( 1 ).getV();

	    double dx = x1 - x0;
	    double dy = y1 - y0;

	    double ds = Math.sqrt( ( dx * dx ) + ( dy * dy ) );

	    if ( dsmin > ds )
		dsmin = ds;

	}

	Double[] zlist = plist.getKeys();

	double zmin = zlist[0].doubleValue();

	double mindz = zlist[1].doubleValue() - zmin;

	double xrange = xmax - xmin;
	double yrange = ymax - ymin;
	int nz = zlist.length;

	double dgrid = ( ( dsmin < mindz ) ? dsmin : mindz ) * 2.0;

	double nx = xrange / dgrid;
	double ny = yrange / dgrid;

	int z_levels = ( int ) Math.ceil( nz );

	switch ( capper ) {

	case BOTH: {

	    z_levels += 2;
	    break;

	}
	case TOP:
	case BOTTOM: {

	    z_levels++;
	    break;

	}

	}

	MarchingCubes mcube =
	    new MarchingCubes( ( int ) Math.ceil( nx + 10 ),
			       ( int ) Math.ceil( ny + 10 ),
			       z_levels );

	GeometryComponent[] geometry =
	    mcube.march( surface,
			 new NodeAccessor( zlist, plist,
					   dgrid,
					   capper,
					   xmin - ( dgrid * 5 ),
					   ymin - ( dgrid * 5 ),
                                           mindz ),
			 0.5 );

	for ( GeometryComponent polygon : geometry ) {

	    GeometryComponent[] gc = polygon.getGeometryComponents();

	    if ( gc.length == 3 ) {

		Triplet[][] vertex = new Triplet[3][2];

		for ( int i = 0; i < vertex.length; i++ ) {

		    double[][] values = gc[i].getComponentValues();

		    vertex[i][0] = new Triplet( values[0][0],
						values[0][1],
						values[0][2] );

		}

		mesh.addTriangle( vertex );

	    }

	}

        mesh.generateNormals();

        return mesh;

    }

}
