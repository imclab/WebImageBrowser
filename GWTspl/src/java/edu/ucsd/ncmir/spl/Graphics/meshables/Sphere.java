package edu.ucsd.ncmir.spl.Graphics.meshables;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
    
/**
 *
 * @author spl
 */
public class Sphere
    extends TriangleMesh

{

    private static final double MAGIC_ANGLE = Math.toRadians( 30.0 );
    
    private double _radius;
    private Triplet _origin;
    
    public Sphere( double radius, int frequency, Triplet origin )

    {
	
	super();

        this._radius = radius;
	this._origin = origin;

	/* north pole */
	
	double north_y = radius * Math.sin( MAGIC_ANGLE );
	double south_y = -radius * Math.sin( MAGIC_ANGLE );
	
	for ( int pole = 0; pole < 2; pole++ ) {
	    
	    double pole_y;
            int theta_start;
            double this_y;
	    
	    if ( pole == 0 ) {
		
		pole_y = radius;
		this_y = north_y;
		theta_start = 0;
		
	    } else { 
		
		pole_y = -radius;
		this_y = south_y;
		theta_start = 36;
		
	    }
	    
	    for ( int theta = theta_start; theta < 360; theta += 60 ) {
		
		double rtheta = Math.toRadians( theta );
		double rtheta2 = Math.toRadians( theta + 60.0 );
                
		Triplet p1 = new Triplet( 0.0, pole_y, 0.0 );
		Triplet p2 = new Triplet( radius * Math.cos( rtheta ), 
					  this_y,
					  radius * Math.sin( rtheta ) );
		Triplet p3 = new Triplet( radius * Math.cos( rtheta2 ),
					  this_y,
					  radius * Math.sin( rtheta2 ) );
		
		if ( pole == 0 ) {
		    
		    /* make ring go the other way so normals are right */
		    
		    Triplet pt = p3;
		    
		    p3 = p2;
		    p2 = pt;
		    
		}
                
                double den;
		
		den = p1.length3D();
		if ( den != 0.0 )
		    p1 = p1.multiply( radius / den );
		
		den = p2.length3D();
		if ( den != 0.0 )		    
		    p2 = p2.multiply( radius / den );
		
		den = p3.length3D();
		if ( den != 0.0 )		    
		    p3 = p3.multiply( radius / den );
		
		this.subdivideTriangle( p1, p2, p3, frequency  );
		
	    }
	    
	}
	
	/* now the body */
	
	for ( int theta = 0; theta < 360; theta +=  60 ) {
	    
	    double rtheta = Math.toRadians( theta );
	    double rtheta2 = Math.toRadians( theta + 60 );
	    double ntheta = Math.toRadians( theta + 36 );
	    double ntheta2 = Math.toRadians( theta + 96 );
	    
	    Triplet p1 = new Triplet( radius * Math.cos( rtheta ),
			              north_y,
			              radius * Math.sin( rtheta ) );
	    
	    Triplet p2 = new Triplet( radius * Math.cos( rtheta2 ),
				      north_y,
				      radius * Math.sin( rtheta2 ) );
	    
	    Triplet p3 = new Triplet( radius * Math.cos( ntheta ),
				      south_y,
				      radius * Math.sin( ntheta ) );
	    
	    Triplet p4 = new Triplet( radius * Math.cos( ntheta2 ),
				      south_y,
				      radius * Math.sin( ntheta2 ) );
	    
            double den;
            
	    den = p1.length3D();
	    if ( den != 0.0 )
		p1 = p1.multiply( radius / den );
	    
	    den = p2.length3D();
	    if ( den != 0.0 )
		p2 = p2.multiply( radius / den );
	    
	    den = p3.length3D();
	    if ( den != 0.0 )
		p3 = p3.multiply( radius / den );
	    
	    den = p4.length3D();
	    if ( den != 0.0 )
		p4 = p4.multiply( radius / den );
	    
	    
	    this.subdivideTriangle( p1, p2, p3, frequency  );
	    this.subdivideTriangle( p3, p2, p4, frequency  );
	    
	}
	
    }
    
    private void subdivideTriangle( Triplet p1, Triplet p2, Triplet p3, int a )
	
    {
	
	if ( a > 0 ) {
	    
	    double den;

	    Triplet p12 = new Triplet( ( p1.getU() + p2.getU() ) / 2,
				       ( p1.getV() + p2.getV() ) / 2,
				       ( p1.getW() + p2.getW() ) / 2 );
	    den = p12.length3D();
	    if ( den != 0.0 )
		p12 = p12.multiply( this._radius / den );
	    
	    Triplet p13 = new Triplet( ( p1.getU() + p3.getU() ) / 2,
				       ( p1.getV() + p3.getV() ) / 2,
				       ( p1.getW() + p3.getW() ) / 2 );
	    den = p13.length3D();
	    if ( den != 0.0 )
		p13 = p13.multiply( this._radius / den );
	    
	    Triplet p23 = new Triplet( ( p2.getU() + p3.getU() ) / 2,
				       ( p2.getV() + p3.getV() ) / 2,
				       ( p2.getW() + p3.getW() ) / 2 );
	    den = p23.length3D();
	    if ( den != 0.0 )
		p23 = p23.multiply( this._radius / den );
	    
	    this.subdivideTriangle( p1, p12, p13, a - 1 );
	    this.subdivideTriangle( p12, p2, p23, a - 1 );
	    this.subdivideTriangle( p13, p23, p3, a - 1 );
	    this.subdivideTriangle( p12, p23, p13, a - 1 );
	    
	} else	    
	    this.addTriangle( new Triplet[][] {
		    { p1.add( this._origin ), new Triplet( p1 ).unit() },
		    { p2.add( this._origin ), new Triplet( p2 ).unit() },
		    { p3.add( this._origin ), new Triplet( p3 ).unit() } 
		} );
	
    }
    
}
