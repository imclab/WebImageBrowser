package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import edu.ucsd.ncmir.spl.Graphics.geometry.GeometryComponent;
import java.util.ArrayList;

/**
 *
 * @author spl
 */

public class MarchingCubes 

{

    private int _xdim;
    private int _ydim;
    private int _zdim;

    private Node[][][] _plane;
    
    /**
     * Creates an instance of MarchingCubes
     * 
     */
     
    public MarchingCubes( int xdim, int ydim, int zdim )

    {

	this._xdim = xdim;
	this._ydim = ydim;
	this._zdim = zdim;

    }

    public GeometryComponent[] march( GeometryComponent parent,
				      VolumeNodeAccessor accessor,
                                      double iso )
        throws Exception

    {

	this._plane = new Node[2][this._ydim][this._xdim];

        for ( int j = 0; j < this._ydim; j++ )
            for ( int i = 0; i < this._xdim; i++ ) {
                
                this._plane[0][j][i] = new Node();
                this._plane[1][j][i] = new Node();
                
            }

	accessor.getPlane( 0, this._plane[0] );

	boolean[] valid_plane = new boolean[2];
	ArrayList<Triangle> triangles = new ArrayList<Triangle>();

	valid_plane[0] = true;

	Node[] V = new Node[8];

	for ( int iz = 1; iz < this._zdim; iz++ ) {
	    
	    int iz0 = ( iz - 1 ) % 2;
	    int iz1 = iz % 2;

	    valid_plane[iz1] = accessor.getPlane( iz, this._plane[iz1] );

	    if ( valid_plane[0] && valid_plane[1] ) {
	    
		for ( int iy = 0; iy < this._ydim - 1; iy++ ) {
		    
		    for ( int ix = 0; ix < this._xdim - 1; ix++ ) {
			
			int flags = 0;
			int[][] vtx = {
			    { ix, iy, iz0 },
			    { ix + 1, iy + 1, iz1 }
			};
			
			for ( int k = 0, kv = 0, f = 0; k < 2; k++ ) {
			    
			    int K = vtx[k][2];
			    for ( int j = 0; j < 2; j++ ) {
				
				int J = vtx[j][1];
				for ( int i = 0; i < 2; i++, f++, kv++ ) {
				    
				    int I = vtx[i][0];
				    
				    Node n = this._plane[K][J][I];

				    V[kv] = n;
				    if ( n.isValid() && 
					 ( n.getValue() >= iso ) )
					flags |= 1 << f;
				    
				}
				
			    }
			    
			}

			int case_number = Permutation.getCaseNumber( flags );
			
			V = Permutation.permute( flags, V );

			Triplet[][] surface_segment = new Triplet[0][0];
                        
			switch ( case_number ) {
			    
			case 0x00:{
			    
			    break;
			    
			}
			case 0x01:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[4] ),
				    this.interpolate( iso, V[0], V[2] ),
				    this.interpolate( iso, V[0], V[1] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x03:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[4] ),
				    this.interpolate( iso, V[0], V[2] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[0], V[2] ),
				    this.interpolate( iso, V[1], V[3] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x06:{

			    double sum =
				V[0].getValue() +
				V[1].getValue() +
				V[2].getValue() +
				V[3].getValue();

			    if ( sum > ( iso * 4 ) )
				surface_segment = new Triplet[][] {
				    {
					this.interpolate( iso, V[2], V[0] ),
					this.interpolate( iso, V[2], V[6] ),
					this.interpolate( iso, V[1], V[0] ),
				    },
				    {
					this.interpolate( iso, V[2], V[6] ),
					this.interpolate( iso, V[1], V[5] ),
					this.interpolate( iso, V[1], V[0] ),
				    },
				    {
					this.interpolate( iso, V[2], V[6] ),
					this.interpolate( iso, V[2], V[3] ),
					this.interpolate( iso, V[1], V[5] ),
				    },
				    {
					this.interpolate( iso, V[2], V[3] ),
					this.interpolate( iso, V[1], V[3] ),
					this.interpolate( iso, V[1], V[5] ),
				    },
				};
			    else
				surface_segment = new Triplet[][] {
				    {
					this.interpolate( iso, V[1], V[3] ),
					this.interpolate( iso, V[1], V[5] ),
					this.interpolate( iso, V[1], V[0] ),
				    },
				    {
					this.interpolate( iso, V[2], V[0] ),
					this.interpolate( iso, V[2], V[6] ),
					this.interpolate( iso, V[2], V[3] ),
				    },
				};

			      break;
			      
			}
			case 0x07:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[4] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[1], V[3] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[2], V[3] ),
				    this.interpolate( iso, V[1], V[3] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x0f:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[4] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x16:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[4], V[0] ),
				},
				{
				    this.interpolate( iso, V[1], V[0] ),
				    this.interpolate( iso, V[1], V[3] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[2], V[0] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[2], V[3] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x17:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[1], V[3] ),
				    this.interpolate( iso, V[1], V[5] ),
				},
				{
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[1], V[3] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[2], V[3] ),
				    this.interpolate( iso, V[1], V[3] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x18:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[4], V[0] ),
				},
				{
				    this.interpolate( iso, V[3], V[2] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[3], V[1] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x19:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[1] ),
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[0], V[2] ),
				},
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[0], V[2] ),
				},
				{
				    this.interpolate( iso, V[3], V[2] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[3], V[1] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x1b:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[3], V[2] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[0], V[2] ),
				},
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[0], V[2] ),
				},
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[1], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				},
				{
				    this.interpolate( iso, V[1], V[5] ),
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x1e:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[4], V[0] ),
				},
				{
				    this.interpolate( iso, V[1], V[5] ),
				    this.interpolate( iso, V[1], V[0] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
				{
				    this.interpolate( iso, V[1], V[0] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
				{
				    this.interpolate( iso, V[1], V[0] ),
				    this.interpolate( iso, V[2], V[0] ),
				    this.interpolate( iso, V[2], V[6] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x1f:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[1], V[5] ),
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[2], V[6] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x27:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[2] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[3], V[2] ),
				},
				{
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[3], V[2] ),
				},
				{
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
				{
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[1], V[5] ),
				    this.interpolate( iso, V[3], V[7] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x3c:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[4], V[0] ),
				    this.interpolate( iso, V[5], V[1] ),
				    this.interpolate( iso, V[4], V[6] ),
				},
				{
				    this.interpolate( iso, V[5], V[1] ),
				    this.interpolate( iso, V[5], V[7] ),
				    this.interpolate( iso, V[4], V[6] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[2], V[0] ),
				},
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[3], V[1] ),
				    this.interpolate( iso, V[2], V[0] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x3d:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[0], V[1] ),
				    this.interpolate( iso, V[5], V[1] ),
				    this.interpolate( iso, V[3], V[1] ),
				},
				{
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[5], V[7] ),
				},
				{
				    this.interpolate( iso, V[2], V[6] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[5], V[7] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x3f:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[5], V[7] ),
				    this.interpolate( iso, V[2], V[6] ),
				},
				{
				    this.interpolate( iso, V[5], V[7] ),
				    this.interpolate( iso, V[4], V[6] ),
				    this.interpolate( iso, V[2], V[6] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x6b:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[6], V[4] ),
				    this.interpolate( iso, V[6], V[2] ),
				    this.interpolate( iso, V[6], V[7] ),
				},
				{
				    this.interpolate( iso, V[0], V[2] ),
				    this.interpolate( iso, V[2], V[3] ),
				    this.interpolate( iso, V[0], V[4] ),
				},
				{
				    this.interpolate( iso, V[2], V[3] ),
				    this.interpolate( iso, V[4], V[5] ),
				    this.interpolate( iso, V[0], V[4] ),
				},
				{
				    this.interpolate( iso, V[2], V[3] ),
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[4], V[5] ),
				},
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[5], V[7] ),
				    this.interpolate( iso, V[4], V[5] ),
				},
			    };
			    
			    break;
			    
			}
			case 0x6f:{
			    
			    double sum =
				V[4].getValue() +
				V[5].getValue() +
				V[6].getValue() +
				V[7].getValue();

			    if ( sum < ( iso * 4 ) )
				surface_segment = new Triplet[][] {
				    {
					this.interpolate( iso, V[0], V[4] ),
					this.interpolate( iso, V[6], V[4] ),
					this.interpolate( iso, V[5], V[4] ),
				    },
				    {
					this.interpolate( iso, V[6], V[4] ),
					this.interpolate( iso, V[5], V[7] ),
					this.interpolate( iso, V[5], V[4] ),
				    },
				    {
					this.interpolate( iso, V[6], V[4] ),
					this.interpolate( iso, V[6], V[7] ),
					this.interpolate( iso, V[5], V[7] ),
				    },
				    {
					this.interpolate( iso, V[6], V[7] ),
					this.interpolate( iso, V[3], V[7] ),
					this.interpolate( iso, V[5], V[7] ),
				    },
				};
			    else
				surface_segment = new Triplet[][] {
				    {
					this.interpolate( iso, V[0], V[4] ),
					this.interpolate( iso, V[6], V[4] ),
					this.interpolate( iso, V[5], V[4] ),
				    },
				    {
					this.interpolate( iso, V[6], V[7] ),
					this.interpolate( iso, V[3], V[7] ),
					this.interpolate( iso, V[5], V[7] ),
				    },
				};
			    
				break;
				
			    }
			    case 0x7e:{
				
				surface_segment = new Triplet[][] {
				    {
					this.interpolate( iso, V[1], V[0] ),
					this.interpolate( iso, V[2], V[0] ),
					this.interpolate( iso, V[4], V[0] ),
				    },
				    {
					this.interpolate( iso, V[3], V[7] ),
					this.interpolate( iso, V[5], V[7] ),
					this.interpolate( iso, V[6], V[7] ),
				    },
				};
				
				break;
				
			    }
			case 0x7f:{
			    
			    surface_segment = new Triplet[][] {
				{
				    this.interpolate( iso, V[3], V[7] ),
				    this.interpolate( iso, V[5], V[7] ),
				    this.interpolate( iso, V[6], V[7] ),
				},
			    };
			    
			    break;
			    
			}
			case 0xff:{
			    
			    break;
			    
			}
			    
			}
			
			for ( int p = 0; p < surface_segment.length; p++ ) {

			    Triplet[] t = surface_segment[p];

			    if ( !this.isDegenerate( t ) )
				triangles.add( new Triangle( parent, t ) );

			}

		    }

		}

	    }

	}
	return triangles.toArray( new Triangle[0] );

    }

    private Triplet interpolate( double iso, Node n1, Node n2 )

    {

	double t = ( iso - n1.getValue() ) / ( n2.getValue() - n1.getValue() );

	return new Triplet( ( ( n2.getX() - n1.getX() ) * t ) + n1.getX(),
			    ( ( n2.getY() - n1.getY() ) * t ) + n1.getY(),
			    ( ( n2.getZ() - n1.getZ() ) * t ) + n1.getZ() );

    }
    
    // Pinched from
    // http://geometryalgorithms.com/Archive/algorithm_0101/algorithm_0101.htm

    // computes the area of a 3D planar polygon
    //    Input:  int n = the number of vertices in the polygon
    //            Point* V = an array of n+2 vertices in a plane
    //                       with V[n]=V[0] and V[n+1]=V[1]
    //            Point N = unit normal vector of the polygon's plane
    //    Return: the area of the polygon

    private boolean isDegenerate( Triplet[] triangle )

    {

	Triplet N = this.normal( triangle );

	double area = 0;

	if ( N != null ) {

	    int   coord;           // coord to ignore: 1=x, 2=y, 3=z
	    
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

	}
	return area == 0;

    }

    private Triplet normal( Triplet[] triangle )
	
    {

	Triplet v0 = triangle[0].subtract( triangle[1] );
	Triplet v1 = triangle[2].subtract( triangle[1] );

	return v1.crossProduct( v0 ).unit();

    }

}
