package edu.ucsd.ncmir.spl.Graphics;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 *
 * @author spl
 */
public class Threader
    implements Enumeration<PlanarPolygonTable>

{


    private Double[] _keys;
    private PlanarPolygonTable _plist;
    
    public Threader( PlanarPolygonTable plist )

    {

        this._plist = plist;

	this._keys = plist.getKeys();
	
	double delta = Double.MAX_VALUE;
	for ( int i = 1; i < this._keys.length; i++ ) {
	    
	    double d =
		this._keys[i].doubleValue() - this._keys[i - 1].doubleValue();
	    
	    if ( d < delta )
		delta = d;

	}
	this.build( delta );

    }

    public boolean hasMoreElements()
    {

	return this._tree_hash.size() > 0;

    }

    public PlanarPolygonTable nextElement()

    {

	if ( !this.hasMoreElements() )
	    throw new NoSuchElementException( "Out of elements!" );

	TreeElement[] telist = this._tree_hash.toArray( new TreeElement[0] );

	return this.table( telist[0] );

    }

    private static final double EPSILON = 1.0e-2;
    private static final double OVERLAP = 0.25;

    private double _epsilon = EPSILON;
    private double _overlap = OVERLAP;

    private HashSet<TreeElement> _tree_hash = new HashSet<TreeElement>();

    private void build( double delta )

    {

	ArrayList<TreeElement> layer0 = this.layer( this._keys[0] );

        for ( int key = 1; key < this._keys.length; key++ ) {

	    double layer_delta =
                this._keys[key].doubleValue() -
		this._keys[key - 1].doubleValue();
	    
	    if ( ( Math.abs( layer_delta - delta ) / delta ) > this._epsilon ) {

		layer0 = this.layer( this._keys[key] );

            } else {

		ArrayList<TreeElement> layer1 = this.layer( this._keys[key] );

		double[][] nearness = new double[layer1.size()][layer0.size()];

		int i0 = 0;
		for ( TreeElement e0 : layer0 ) {

		    PlanarPolygon p0 = e0.getPolygon();

		    double[] xy0 = p0.getCenter();
		    double R = p0.getRadius();
		    double Rsq = R * R;
		    double area0 = Rsq * Math.PI;

		    int i1 = 0;
		    for ( TreeElement e1 : layer1 ) {

			PlanarPolygon p1 = e1.getPolygon();

			double[] xy1 = p1.getCenter();
			double r = p1.getRadius();

			double dx = xy1[0] - xy0[0];
			double dy = xy1[1] - xy0[1];

			double d = Math.sqrt( ( dx * dx ) + ( dy * dy ) );

			if ( d < r + R ) {

			    double dsq = d * d;
			    double rsq = r * r;

			    double t1 = ( dsq + rsq - Rsq ) / ( 2 * d * r );
			    double t2 = ( dsq + Rsq - rsq ) / ( 2 * d * R );
			    double t3 = 
				( -d + r + R ) * ( d + r - R ) *
				( d - r + R ) * ( d + r + R );

			    if ( ( Math.abs( t1 ) <= 1 ) &&
				 ( Math.abs( t2 ) <= 1 ) &&
				 ( t3 >= 0 ) ) {

				double term1 = rsq * Math.acos( t1 );
				double term2 = Rsq * Math.acos( t2 );
				double term3 = Math.sqrt( t3 ) / 2.0;

				double area = term1 + term2 - term3;
				
				double area1 = r * r * Math.PI;
				
				double ratio0 = area / area0;
				double ratio1 = area / area1;
				
				nearness[i1][i0] =
				    Math.max( ratio0, ratio1 );

			    } else
				nearness[i1][i0] = 1.0;

			} else
			    nearness[i1][i0] = -1;

			i1++;

		    }
		    i0++;

		}

		for ( int i = 0; i < layer0.size(); i++ )
		    for ( int j = 0; j < layer1.size(); j++ )
			if ( nearness[j][i] > this._overlap ) {

			    TreeElement tei = layer0.get( i );
			    TreeElement tej = layer1.get( j );

			    tei.addDescendant( tej );
			    tej.addAncestor( tei );

			}

		layer0 = layer1;

            }

	}

    }

    private ArrayList<TreeElement> layer( Double key )
    {

        ArrayList<TreeElement> elements = new ArrayList<TreeElement>();

        for ( PlanarPolygon pp : this._plist.get( key ) ) {

            TreeElement te = new TreeElement( pp, key );
            elements.add( te );
            this._tree_hash.add( te );

        }

        return elements;

    }

    private class TreeElement

    {

	private PlanarPolygon _polygon;
	private Double _d;
	private ArrayList<TreeElement> _ancestors = 
	    new ArrayList<TreeElement>();
	private ArrayList<TreeElement> _descendants = 
	    new ArrayList<TreeElement>();
	
	TreeElement( PlanarPolygon polygon, Double d )

	{

	    this._polygon = polygon;
	    this._d = d;

	}

	PlanarPolygon getPolygon()

	{

	    return this._polygon;

	}

	Double getD()

	{

	    return this._d;

	}

	void addAncestor( TreeElement ancestor )

	{

	    this._ancestors.add( ancestor );

	}

	void addDescendant( TreeElement descendant )

	{

	    this._descendants.add( descendant );

	}

	void removeAncestor( TreeElement ancestor )

	{

	    this._ancestors.remove( ancestor );

	}

	void removeDescendant( TreeElement descendant )

	{

	    this._descendants.remove( descendant );

	}

	ArrayList<TreeElement> getAncestors()

	{

	    return this._ancestors;

	}

	ArrayList<TreeElement> getDescendants()

	{

	    return this._descendants;

	}

        @Override
	public boolean equals( Object o )

	{

	    boolean equals;

	    if ( o instanceof TreeElement ) {

		TreeElement te = ( TreeElement ) o;

		equals = te._polygon == this._polygon;

	    } else
		equals = false;

	    return equals;

	}

        @Override
        public int hashCode()
            
        {

            int hash = 5;
            hash = 73 * hash + this._polygon.hashCode();

            return hash;
            
        }
	    
    }

    private PlanarPolygonTable table( TreeElement te )

    {

	PlanarPolygonTable table = new PlanarPolygonTable();
	this.addToTable( te, table );

	return table;

    }

    private void addToTable( TreeElement element,
			     PlanarPolygonTable table )

    {

	if ( this._tree_hash.remove( element ) ) {

	    ArrayList<TreeElement> ancestors = element.getAncestors();
	    ArrayList<TreeElement> descendants = element.getDescendants();
	    
	    table.add( element.getD(), element.getPolygon() );

	    TreeElement[] alist = ancestors.toArray( new TreeElement[0] );
	    TreeElement[] dlist = descendants.toArray( new TreeElement[0] );

	    for ( TreeElement e : alist )
		this.addToTable( e, table );

	    for ( TreeElement e : dlist )
		this.addToTable( e, table );

	}

    }

}
