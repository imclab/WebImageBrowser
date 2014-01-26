package edu.ucsd.ncmir.spl.Graphics;

/**
 *
 * @author spl
 */
public class Triangle

{

    private Triplet[] _vertices;
    private Triplet _normal;

    public Triangle( Triplet[] triplets )

    {

	this._vertices = triplets;

	Triplet t1 = triplets[0].subtract( triplets[1] );
	Triplet t2 = triplets[2].subtract( triplets[1] );

	this._normal = t1.crossProduct( t2 ).unit();
	
    }

    public Triplet[] getVertices()

    {

        return this._vertices;

    }

    public double intersectWithRay( Triplet p0, Triplet p1 )

    {

	Triplet ray = p1.subtract( p0 );
	double n_dot_v = this._normal.dotProduct( ray );
        double t = Double.MAX_VALUE;

	if ( n_dot_v != 0 )  {

	    double s =
		this._normal.dotProduct( this._vertices[0].subtract( p0 ) ) /
		n_dot_v;

	    Triplet i = ray.multiply( s ).add( p0 );

	    if ( i.isPointInTriangle( this._vertices[0],
				      this._vertices[1],
				      this._vertices[2] ) )
		t = s;

	}

	return t;

    }

    @Override
    public String toString()

    {

	String s = this._vertices[0].toString();

	for ( int i = 1; i < this._vertices.length; i++ )
	    s += "\n" + this._vertices[i];

	return s;

    }
    
}
