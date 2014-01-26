package edu.ucsd.ncmir.spl.Graphics.meshables;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class TriangleMesh
    implements Meshable

{

    private ArrayList<Triplet[]> _vertices;
    private HashSet<Triangle> _indices;
    
    private HashMap<Triplet,Integer> _lut;
    private EdgeTable _edge_table;

    public TriangleMesh()
            
    {
     
        this._vertices = new ArrayList<Triplet[]>();
        this._indices = new HashSet<Triangle>();
    
        this._lut = new HashMap<Triplet, Integer>();
       
    }
    
    /**
     * Creates a list of triangles, composed of a set of vertices and 
     * connecting indices
     * @param vertices Each vertex is an array of length 2.
     * The first element is the vertex coordinate,
     * the second element is the vertex normal.
     * @param indices Each element of the ArrayList is an array of length 3, 
     * with each element in the array pointing into the vertices list.
     */

    public TriangleMesh( ArrayList<Triplet[]> vertices,
			 ArrayList<int[]> indices )

    {

        this();
	this.addVertices( vertices, indices );

    }

    
    public ArrayList<Triplet[]> getVertices()

    {

	return this._vertices;
	
    }

    @Override
    public boolean hasData()
        
    {
        
        return this._vertices.size() > 0;
        
    }
    
    public Triplet[][] getVertexArray()

    {

	return this._vertices.toArray( new Triplet[0][] );

    }

    public ArrayList<int[]> getIndices()

    {

	ArrayList<int[]> list = new ArrayList<int[]>();

	for ( Triangle t : this._indices )
	    list.add( t.getVertices() );

	return list;
	
    }

    public int[][] getIndexArray()

    {

        return this.getIndices().toArray( new int[0][] );

    }

    public void addTriangleMesh( TriangleMesh mesh )

    {

	this.addVertices( mesh.getVertices(), mesh.getIndices() );

    }

    public final void addVertices( ArrayList<Triplet[]> vertices,
			           ArrayList<int[]> indices )

    {

	this.addVertices( vertices.toArray( new Triplet[0][] ),
			  indices.toArray( new int[0][] ) );

    }

    public void addVertices( Triplet[][] verts, int[][] indices )

    {
	
	for ( int[] inds : indices ) {
	    
	    Triplet[][] vertices = new Triplet[3][];

	    for ( int i = 0; i < 3; i++ )
		vertices[i] = verts[inds[i]];
	    this.addTriangle( vertices );
	    
	}
	
    }

    private int _vert_index = 0;
    
    public void addTriangle( Triplet[][] vertices )

    {
	    
	int[] idx = new int[3];

        int i = 0;
	for ( Triplet[] vertex : vertices ) {

	    Integer index = this._lut.get( vertex[0] );
	    
	    if ( index == null ) {
		
		this._lut.put( vertex[0], 
                               index = new Integer( this._vert_index++ ) );
		this._vertices.add( new Triplet[] {
			vertex[0],
			vertex[1],
		    } );
		
	    }
	    
	    idx[i] = index.intValue();   
            i++;
            
	}

	Triangle t = new Triangle( idx );

	this._indices.add( t );


    }

    void addToIndices( int[] vertex_indices )
    {

        this._indices.add( new Triangle( vertex_indices ) );

    }

    void addToVertices( Triplet[] triplet )
    {

        this._vertices.add( triplet );

    }

    void unitNormals()

    {

        for ( Triplet[] t : this._vertices )
            t[1] = t[1].unit();
        
    }

    private void setAllVisited( boolean visited )

    {

	for ( Triangle triangle : this._indices )
	    triangle.setVisited( visited );

    }

    private class Triangle
	
    {

	private int[] _vertices = new int[3];

	Triangle( int[] vertices )

	{

            System.arraycopy( vertices, 0, this._vertices, 0, vertices.length );

	}

	private boolean _visited = false;

	public void setVisited( boolean visited )

	{

	    this._visited = visited;

	}

	public boolean visited()

	{

	    return this._visited;

	}

	private int[] getSorted()

	{
	    
	    int[] vertices = new int[3];

            System.arraycopy( this._vertices, 0, vertices, 0, 3 );

	    for ( int i = 0; i < 2; i++ )
		for ( int j = i + 1; j < 3; j++ )
		    if ( vertices[i] > vertices[j] ) {

			int temp = vertices[j];
			
			vertices[j] = vertices[i];
			vertices[i] = temp;

		    }

	    return vertices;

	}
	   
        @Override
	public boolean equals( Object o )

	{

	    boolean equal = false;

	    if ( o instanceof Triangle ) {

		int[] v0 = this.getSorted();
		int[] v1 = ( ( Triangle ) o ).getSorted();

		equal = true;
		for ( int i = 0; equal && ( i < 3 ); i++ )
		    equal = v0[i] == v1[i];

	    }

	    return equal;

	}

        @Override
        public int hashCode()

        {

	    int hash = 5;

	    for ( int i : this.getSorted() )
		hash = hash * 97 + i;

	    return hash;

        }
   
	Edge[] getEdges()

	{

	    Edge[] edge_list = new Edge[this._vertices.length];

	    for ( int i = 0, j = this._vertices.length - 1;
		  i < this._vertices.length;
		  j = i, i++ )
		edge_list[i] = new Edge( this._vertices[j], this._vertices[i] );

	    return edge_list;

	}

	int[] getVertices()

	{

	    return this._vertices;

	}

	void flip()

	{

	    int temp = this._vertices[0];
	    
	    this._vertices[0] = this._vertices[2];
	    this._vertices[2] = temp;

	}

        @Override
	public String toString()

	{
	    
	    return "[" +
		this._vertices[0] + " " +
		this._vertices[1] + " " +
		this._vertices[2] + "]";

	}

        public double getArea( ArrayList<Triplet[]> vertices )

        {

            double area = 0;

            Triplet[] triangle = new Triplet[3];

            for ( int i = 0; i < 3; i++ )
                triangle[i] = vertices.get( this._vertices[i] )[0];

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

        private boolean isConsistent( Edge e )

        {

            boolean is_conforming = false;

            for ( Edge ec : this.getEdges() )
		if ( ec.equals( e ) ) {

		    is_conforming = ec.isConsistent( e );
                    break;

		}

            return is_conforming;

        }

    }

    private class Edge
	
    {

	private int _e0;	// "Natural" order
	private int _e1;
	
	private int _n0;	// Numerical order for comparision.
	private int _n1;

	Edge( int e0, int e1 )

	{

	    this._e0 = e0;
	    this._e1 = e1;

	    if ( e0 > e1 ) {

		this._n0 = e1;
		this._n1 = e0;

	    } else {

		this._n0 = e0;
		this._n1 = e1;

	    }

	}

	int getE0()

	{

	    return this._e0;
	    
	}

	int getE1()

	{

	    return this._e1;

	}

	boolean isConsistent( Edge e )

	{

	    return ( this._e0 == e._e1 ) && ( this._e1 == e._e0 );

	}

        @Override
	public boolean equals( Object o )

	{

	    boolean equal = false;

	    if ( o instanceof Edge ) {

		Edge e = ( Edge ) o;

		equal = 
		    ( ( e._n0 == this._n0 ) && ( e._n1 == this._n1 ) );

	    }
	    return equal;

	}

        @Override
        public int hashCode()

        {

            int hash = 7;

	    hash = 97 * hash + this._n0;
	    hash = 97 * hash + this._n1;

            return hash;

        }
	
        @Override
	public String toString()

	{
	    
	    return "{" + this._e0 + "->" + this._e1 + "}";

	}

    }

    private class EdgeTable
	extends HashMap<Edge,ArrayList<Triangle>>

    {

	public void removeTriangle( Edge e, Triangle t )

	{

	    ArrayList<Triangle> tlist = this.get( e );
	    tlist.remove( t );

	}

	public Triangle getNextTriangle( Edge e )

	{

	    ArrayList<Triangle> tlist = this.get( e );

	    Triangle t = null;

            if ( tlist.size() > 0 )
                t = tlist.get( 0 );
	    tlist.remove( t );

	    return t;

	}

    }

    private void buildEdgeTable()

    {

	this._edge_table = new EdgeTable();

	for ( Triangle triangle : this._indices )
	    for ( Edge edge : triangle.getEdges() ) {		

		ArrayList<Triangle> tris = this._edge_table.get( edge );

		if ( tris == null )		    
		    this._edge_table.put( edge,
					  tris = new ArrayList<Triangle>() );
		tris.add( triangle );

	    }

    }
    
    @SuppressWarnings("empty-statement")
    public void generateNormals()

    {

	this.buildEdgeTable();
	this.setAllVisited( false );

	while ( this.makeMeshConsistent() )
	    ;

	for ( Triplet[] vertex : this._vertices )
	    vertex[1] = new Triplet( 0, 0, 0 );

	for ( Triangle triangle : this._indices ) {

	    int[] vertices = triangle.getVertices();
	    
	    Triplet[] ta = this._vertices.get( vertices[0] );
	    Triplet[] t0 = this._vertices.get( vertices[1] );
	    Triplet[] tb = this._vertices.get( vertices[2] );

	    Triplet va = ta[0].subtract( t0[0] );
	    Triplet vb = tb[0].subtract( t0[0] );

	    Triplet normal = va.crossProduct( vb ).unit();
	    
	    ta[1] = ta[1].add( normal );
	    t0[1] = t0[1].add( normal );
	    tb[1] = tb[1].add( normal );

	}

	for ( Triplet[] vertex : this._vertices )
	    vertex[1] = vertex[1].unit();

    }

    private Triangle getUnvisitedFace()

    {

	Triangle unvisited = null;

	for ( Triangle t : this._indices )
	    if ( !t.visited() ) {

		unvisited = t;
		break;

	    }

	return unvisited;

    }

    private boolean makeMeshConsistent()

    {

	boolean changed = false;

	Triangle f = this.getUnvisitedFace();

	if ( f != null ) {

	    changed = true;

	    Stack<Triangle> face_stack = new Stack<Triangle>();
	    
	    face_stack.push( f );
	    f.setVisited( true );

	    while ( !face_stack.empty() ) {
		
		f = face_stack.pop();
		for ( Edge e : f.getEdges() ) {

		    this._edge_table.removeTriangle( e, f );

		    Triangle a = this._edge_table.getNextTriangle( e );
		    if ( ( a != null ) && !a.visited() ) {
			
			if ( !a.isConsistent( e ) )
			    a.flip();
			
			face_stack.push( a );
			
			a.setVisited( true );
			
		    }

		}

	    }

	}

	return changed;

    }

}
