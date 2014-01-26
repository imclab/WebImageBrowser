package edu.ucsd.ncmir.spl.Graphics;

import edu.ucsd.ncmir.spl.Util.AbstractArrayListTable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author spl
 */
public class PlanarPolygonTable
    extends AbstractArrayListTable<Double,PlanarPolygon>

{

    public void addPlanarPolygon( PlanarPolygon pp )

    {

        Double d = new Double( pp.get( 0 ).getW() );

        this.add( d, pp );

    }

    @Override
    public Double[] getKeys()

    {

        Double[] keys = this.keySet().toArray( new Double[0] );
	Arrays.sort( keys );

        return keys;

    }

    @Override
    public PlanarPolygon[] getArray( Double d )

    {

        return this.get( d ).toArray( new PlanarPolygon[0] );

    }

    public PlanarPolygon[] getAllPolygons()

    {

	ArrayList<PlanarPolygon> plist = new ArrayList<PlanarPolygon>();

	for ( ArrayList<PlanarPolygon> pplist : this.values() )
	    plist.addAll( pplist );

	return plist.toArray( new PlanarPolygon[0] );

    }
    
}
