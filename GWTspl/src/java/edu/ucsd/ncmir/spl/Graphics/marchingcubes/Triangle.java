package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.Graphics.geometry.GeometryComponent;

/**
 *
 * @author spl
 */
public class Triangle 
    implements GeometryComponent

{

    private GeometryComponent _parent;
    private Coordinate[] _vertices;
    
    Triangle( GeometryComponent parent, Triplet[] v )
        
    {
        
	this._parent = parent;

        this._vertices = new Coordinate[] {
            new Coordinate( this, v[0].getU(), v[0].getV(), v[0].getW() ),
	    new Coordinate( this, v[1].getU(), v[1].getV(), v[1].getW() ),
	    new Coordinate( this, v[2].getU(), v[2].getV(), v[2].getW() )
	};

    }

    @Override
    public GeometryComponent[] getGeometryComponents()

    {

	return this._vertices;

    }

    @Override
    public double[][] getComponentValues()

    {

	return new double[][] {
	    { this._vertices[0].getX(), 
	      this._vertices[0].getY(), 
	      this._vertices[0].getZ() },
	    { this._vertices[1].getX(), 
	      this._vertices[1].getY(), 
	      this._vertices[1].getZ() },
	    { this._vertices[2].getX(), 
	      this._vertices[2].getY(), 
	      this._vertices[2].getZ() }
	};

    }

    @Override
    public GeometryComponent getParent()

    {

	return this._parent;

    }
    
    @Override
    public String toString()
        
    {
        
        String s = "";
        
        for ( Coordinate c : this._vertices )
            s += c + " ";
        
        return s;
        
    }
    
    static Triangle parse( GeometryComponent parent, String text )
        
    {
        
        String[] s = text.trim().split( " " );
        
        Triplet[] v = new Triplet[3];
        for ( int i = 0, j = 0; i < s.length; i += 3, j++ )
            v[j] = Triangle.parseTriplet( s[i], s[i + 1], s[i + 2] );
        
        return new Triangle( parent, v );
        
    }
    
    private static Triplet parseTriplet( String s1, String s2, String s3 )
        
    {
        
        return new Triplet( Double.parseDouble( s1 ),
			    Double.parseDouble( s2 ),
			    Double.parseDouble( s3 ) );
        
    }

    public double getArea()

    {

        double area = 0;

        Triplet[] triangle = new Triplet[3];

        for ( int i = 0; i < 3; i++ )
            triangle[i] = new Triplet( this._vertices[i].getX(),
                                       this._vertices[i].getY(),
                                       this._vertices[i].getZ() );

 	Triplet N = this.normal( triangle );

        int coord;           // coord to ignore: 1=x, 2=y, 3=z

        // select largest abs coordinate to ignore for projection

        double ax = Math.abs( N.getU() );     // abs x-coord
        double ay = Math.abs( N.getV() );     // abs y-coord
        double az = Math.abs( N.getW() );     // abs z-coord

        coord = 3;		// ignore z-coord
        if ( ax > ay ) {

            if (ax > az)
                coord = 1;	// ignore x-coord

        } else if ( ay > az )
            coord = 2;		// ignore y-coord

        // compute area of the 2D projection

        for ( int i = 1, j = 2, k = 0; k < 3; i = j, j = k, k++ )
            switch ( coord ) {

            case 1: {

                area += triangle[i].getV() *
                    ( triangle[j].getW() - triangle[k].getW() );
                break;
            }
            case 2: {

                area += triangle[i].getU() *
                    ( triangle[j].getW() - triangle[k].getW() );
                break;

            }
            case 3: {

                area += triangle[i].getU() *
                    ( triangle[j].getV() - triangle[k].getV() );
                break;

            }

            }

        // scale to get area before projection
        // length of normal vector

        double an = Math.sqrt( ( ax * ax ) + ( ay * ay ) + ( az * az ) );

        switch ( coord ) {

        case 1: {

            area *= ( an / ( 2 * ax ) );
            break;

        }
        case 2: {

            area *= ( an / ( 2 * ay ) );
            break;

        }
        case 3: {

            area *= ( an / ( 2 * az ) );
            break;

        }

        }

        return area;

    }
    private Triplet normal( Triplet[] triangle )

    {

	Triplet v0 = triangle[0].subtract( triangle[1] );
	Triplet v1 = triangle[2].subtract( triangle[1] );

	return v1.crossProduct( v0 ).unit();

    }

}
