package edu.ucsd.ncmir.spl.LinearAlgebra;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.SplitResult;

/**
 *
 * @author spl
 */

public final class Quaternion
    implements Comparable

{

    private double _r;
    private double _i;
    private double _j;
    private double _k;

    /**
     * Creates a unit quaternion.
     */

    public Quaternion()

    {

	this( 1, 0, 0, 0 );

    }

    /**
     * Creates a normalized quaternion.
     * @param r Real part.
     * @param i <i>I</i> part.
     * @param j <i>J</i> part.
     * @param k <i>K</i> part.
     */

    public Quaternion( double r, double i, double j, double k )

    {

        this._r = r;
        this._i = i;
        this._j = j;
        this._k = k;

    }

    public Quaternion( double r, Double3Vector v )

    {

	this( r, v.getComponent( 0 ), v.getComponent( 1 ), v.getComponent( 2 ) );
        
    }

    /**
     * Replicates a quaternion from the <code>Quaternion</code>.
     * @param q A quaternion.
     */
    public Quaternion( Quaternion q )

    {

	this( q._r, q._i, q._j, q._k );

    }

    public Quaternion( AbstractDoubleVector v )

    {

	this( 0,
	      v.getComponent( 0 ), v.getComponent( 1 ), v.getComponent( 2 ) );

    }

    public Quaternion( String string )

    {

	String[] s =
	    string.replaceAll( "[^0-9.-]+", " " ).trim().split( " " );

	this._r = Double.parseDouble( s[0] );
	this._i = Double.parseDouble( s[1] );
	this._j = Double.parseDouble( s[2] );
	this._k = Double.parseDouble( s[3] );

    }

    private static
	Quaternion createHalfAngleRotationRadians( AbstractDoubleVector V_about,
						   double half_angle_rad )

    {

	double cos = Math.cos( half_angle_rad );
	double sin = Math.sin( half_angle_rad );

	Quaternion q = new Quaternion( V_about );
	q._r = cos;
	q._i *= sin;
	q._j *= sin;
	q._k *= sin;

	return q;

    }

    public static Quaternion createRotationRadians( AbstractDoubleVector V_about,
						    double angle_rad )

    {

	return Quaternion.createHalfAngleRotationRadians( V_about,
							  angle_rad / 2.0 );

    }

    public static Quaternion createRotationDegrees( AbstractDoubleVector V_about,
						    double angle )

    {

	return
	    Quaternion.createRotationRadians( V_about,
					      Quaternion.toRadians( angle ) );

    }


    private static final double EPSILON = 1.0e-6;

    public Quaternion( DoubleSquareMatrix dsm )

    {

	double w_sqd = 0.25 * ( 1.0 +
				dsm.getElement( 0, 0 ) +
				dsm.getElement( 1, 1 ) +
				dsm.getElement( 2, 2 ) );

	int i;

	if ( w_sqd > Quaternion.EPSILON ) {

	    this._r = Math.sqrt( w_sqd );

	    double r4 = this._r * 4.0;

	    this._i =
		( dsm.getElement( 2, 1 ) - dsm.getElement( 1, 2 ) ) / r4;
	    this._j =
		( dsm.getElement( 0, 2 ) - dsm.getElement( 2, 0 ) ) / r4;
	    this._k =
		( dsm.getElement( 1, 0 ) - dsm.getElement( 0, 1 ) ) / r4;

	} else {

	    double x_sqd =
		-0.5 * ( dsm.getElement( 1, 1 ) + dsm.getElement( 2, 2 ) );

	    this._r = 0.0;

	    if ( x_sqd > Quaternion.EPSILON ) {

		this._i = Math.sqrt( x_sqd );
		this._j = dsm.getElement( 1, 0 ) / ( 2.0 * this._i );
		this._k = dsm.getElement( 2, 0 ) / ( 2.0 * this._i );

	    } else {

		double y_sqd = 0.5 * ( 1 - dsm.getElement( 2, 2 ) );

		this._i = 0.0;

		if ( y_sqd > Quaternion.EPSILON ) {

		    this._j = Math.sqrt( y_sqd );
		    this._k = dsm.getElement( 2, 1 ) / ( 2.0 * this._j );

		} else {

		    this._j = 0.0;
		    this._k = 1.0;

		}

	    }

	}
	this.normalize();

    }

    @Override
    public String toString()

    {

	return this.getFormatted( "[%g,%g,%g,%g]" );

    }

    public String getFormatted( String format )

    {

	return Quaternion.format( format, this._r, this._i, this._j, this._k );

    }

    // TODO: This is somewhat cheesy but it will work for the moment.
    // Eventually it should be replaced with something more robust.

    private static String format( final String format,
				  final Object... args)

    {

	final RegExp regex = RegExp.compile("%[a-z]");

	final SplitResult split = regex.split(format);
	final StringBuffer msg = new StringBuffer();

	for ( int pos = 0; pos < split.length() - 1; pos += 1 ) {

	    msg.append( split.get( pos ) );
	    msg.append( args[pos].toString() );

	}

	msg.append(split.get(split.length() - 1));

	return msg.toString();

    }


    public double dotProduct( Quaternion q )

    {

	return
	    ( this._r * q._r ) +
	    ( this._i * q._i ) +
	    ( this._j * q._j ) +
	    ( this._k * q._k );

    }

    public Quaternion multiplyByScalar( double s )

    {

	this._r *= s;
	this._i *= s;
	this._j *= s;
	this._k *= s;

	return this;

    }

    public double angularDifferenceRadians( Quaternion q )

    {

	return Math.acos( this.dotProduct( q ) );

    }

    public Quaternion scalarMultiply( double s )

    {

	return new Quaternion( this ).multiplyByScalar( s );

    }

    public Quaternion sumWith( Quaternion q )

    {

	this._r += q._r;
	this._i += q._i;
	this._j += q._j;
	this._k += q._k;

	return this;

    }

    public Quaternion add( Quaternion q )

    {

	return new Quaternion( this ).sumWith( q );

    }

    public double modulus()

    {

	return Math.sqrt( ( this._r * this._r ) +
			  ( this._i * this._i ) +
			  ( this._j * this._j ) +
			  ( this._k * this._k ) );

    }

    public Quaternion normalize()

    {

	double l = this.modulus();

	if ( l > 0 ) {

	    this._r /= l;
	    this._i /= l;
	    this._j /= l;
	    this._k /= l;

	}

	return this;

    }

    public Quaternion normalized()

    {

	return new Quaternion( this ).normalize();

    }

    public DoubleSquareMatrix getMatrix()

    {

	DoubleSquareMatrix dsm = new DoubleSquareMatrix( 4 );

	dsm.setElement( 0, 0,
			  1.0 - ( 2.0 *
				  ( this._j * this._j + this._k * this._k ) ) );
	dsm.setElement( 0, 1,
			  2.0 * ( this._i * this._j - this._r * this._k ) );
	dsm.setElement( 0, 2,
			  2.0 * ( this._i * this._k + this._r * this._j ) );
	dsm.setElement( 0, 3,
			  0.0 );

	dsm.setElement( 1, 0,
			  2.0 * ( this._i * this._j + this._r * this._k ) );
	dsm.setElement( 1, 1,
			  1.0 - ( 2.0 *
				  ( this._i * this._i + this._k * this._k ) ) );
	dsm.setElement( 1, 2,
			  2.0 * ( this._j * this._k - this._r * this._i ) );
	dsm.setElement( 1, 3,
			  0.0 );

	dsm.setElement( 2, 0,
			  2.0 * ( this._i * this._k - this._r * this._j ) );
	dsm.setElement( 2, 1,
			  2.0 * ( this._j * this._k + this._r * this._i ) );
	dsm.setElement( 2, 2,
			  1.0 - ( 2.0 *
				  ( this._i * this._i + this._j * this._j ) ) );
	dsm.setElement( 2, 3,
			  0.0 );

	dsm.setElement( 3, 0,
			  0.0 );
	dsm.setElement( 3, 1,
			  0.0 );
	dsm.setElement( 3, 2,
			  0.0 );
	dsm.setElement( 3, 3,
			  1.0 );

	return dsm;

    }

    public Quaternion multiplyBy( Quaternion q )

    {

	double r =
	    ( this._r * q._r ) -
	    ( this._i * q._i ) -
	    ( this._j * q._j ) -
	    ( this._k * q._k );
	double i =
	    ( this._r * q._i ) +
	    ( this._i * q._r ) +
	    ( this._j * q._k ) -
	    ( this._k * q._j );
	double j =
	    ( this._r * q._j ) -
	    ( this._i * q._k ) +
	    ( this._j * q._r ) +
	    ( this._k * q._i );
	double k =
	    ( this._r * q._k ) +
	    ( this._i * q._j ) -
	    ( this._j * q._i ) +
	    ( this._k * q._r );

	this._r = r;
	this._i = i;
	this._j = j;
	this._k = k;

	return this;

    }

    public Quaternion logOf()

    {

	double cos_theta = this._r;

	this._r = 0.0;

	if ( cos_theta != 1.0 ) {

	    double theta = Math.acos( cos_theta );
	    double sin_theta = Math.sin( theta );

	    this._i = ( this._i * sin_theta ) * theta;
	    this._j = ( this._j * sin_theta ) * theta;
	    this._k = ( this._k * sin_theta ) * theta;

	} else
	    this._i = this._j = this._k = 0;

	return this;

    }

    public Quaternion log()

    {

	return new Quaternion( this ).logOf();

    }

    public Quaternion expOf()

    {

	double theta = Math.sqrt( this.dotProduct( this ) );

	if ( theta != 0.0 ) {

	    double sin_theta = Math.sin( theta );
            double exp_a = Math.exp( this._r );

	    this._r = exp_a * Math.cos( theta );
	    this._i = exp_a * ( this._i / theta ) * sin_theta;
	    this._j = exp_a * ( this._j / theta ) * sin_theta;
	    this._k = exp_a * ( this._k / theta ) * sin_theta;

	} else {

	    this._r = 1.0;
	    this._i = this._j = this._k = 0.0;

	}

	return this;

    }

    public Quaternion exp()

    {

	return new Quaternion( this ).expOf();

    }

    public Quaternion multiply( Quaternion q )

    {

	return new Quaternion( this ).multiplyBy( q );

    }

    private Quaternion rotateByHalfAngleRadians( AbstractDoubleVector V_about,
						 double half_angle_rad )

    {

	double cos_half_angle = Math.cos( half_angle_rad );
	double sin_half_angle = Math.sin( half_angle_rad );
	Quaternion Q_rot =
	    new Quaternion( cos_half_angle,
			    V_about.getComponent( 0 ) * sin_half_angle,
			    V_about.getComponent( 1 ) * sin_half_angle,
			    V_about.getComponent( 2 ) * sin_half_angle );

	Q_rot.normalize();

	this.multiplyBy( Q_rot );

	return this;

    }

    private static double toRadians( double d )

    {

	return d * Math.PI / 180.0;

    }

    public Quaternion rotateByRadians( AbstractDoubleVector V_about, double angle_rad )

    {

	return this.rotateByHalfAngleRadians( V_about, angle_rad / 2.0 );

    }

    public Quaternion rotateByDegrees( AbstractDoubleVector V_about, double angle )

    {

	return this.rotateByRadians( V_about, Quaternion.toRadians( angle ) );

    }

    public static Quaternion axbxc( Quaternion a, Quaternion b, Quaternion c )

    {

	return a.multiply( b.multiply( c ) );

    }

    public Quaternion conjugation()

    {

	this._i = -this._i;
	this._j = -this._j;
	this._k = -this._k;

	return this;

    }

    public Quaternion conjugate()

    {

	return new Quaternion( this ).conjugation();

    }

    /**
     * Computes the rotational transformation of the vector with components
     * <code>x</code>, <code>y</code>, and <code>z</code>.
     * @param x The <i><b>x</b></i> component of the input vector.
     * @param y The <i><b>y</b></i> component of the input vector.
     * @param z The <i><b>z</b></i> component of the input vector.
     * @return The transformation of the input, returned as a <code>AbstractDoubleVector</code>.
     */
    public AbstractDoubleVector transformVector( double x, double y, double z )

    {

        return this.transformVector( new AbstractDoubleVector( new double[] { x, y, z } ) );

    }

    public AbstractDoubleVector transformVector( AbstractDoubleVector V )

    {

	Quaternion qvector = new Quaternion( V );

	Quaternion q = Quaternion.axbxc( this, qvector, this.conjugate() );

	return new AbstractDoubleVector( new double[] { q._i, q._j, q._k } );

    }

    public double getR()

    {

        return this._r;

    }

    public double getI()

    {

        return this._i;

    }

    public double getJ()

    {

        return this._j;

    }

    public double getK()

    {

        return this._k;

    }

    @Override
    public boolean equals( Object o )

    {

        boolean equals = false;

	if ( ( o != null ) && ( o instanceof Quaternion ) ) {

	    Quaternion q = ( Quaternion ) o;

	    equals =
		( this._r == q._r ) &&
		( this._i == q._i ) &&
		( this._j == q._j ) &&
		( this._k == q._k );

	}

	return equals;

    }

    @Override
    public int hashCode()

    {

        int hash = 7;

	// Since Double.doubleToRawLongBits(double value) isn't
	// supported. . .

        hash = 13 * hash + Double.toString( this._r ).hashCode();
        hash = 13 * hash + Double.toString( this._i ).hashCode();
        hash = 13 * hash + Double.toString( this._j ).hashCode();
        hash = 13 * hash + Double.toString( this._k ).hashCode();

        return hash;
    }

    @Override
    public int compareTo( Object o )

    {

	Quaternion q = ( Quaternion ) o;

	double diff;

	diff = this._r - q._r;

	if ( diff == 0 )
	    diff = this._i - q._i;
	if ( diff == 0 )
	    diff = this._j - q._j;
	if ( diff == 0 )
	    diff = this._k - q._k;

	return ( diff != 0 ) ? ( ( int ) ( diff / Math.abs( diff ) ) ) : 0;

    }

    public AbstractDoubleSquareMatrix toRotationMatrix()
    {
        
        return this.getMatrix();
        
    }

    public Double3Vector imag()

    {

	return new Double3Vector( this._i, this._j, this._k );

    }

}

