package edu.ucsd.ncmir.spl.Graphics.meshables;

import edu.ucsd.ncmir.spl.Graphics.Triplet;

/**
 *
 * @author spl
 */
public class SphereObject
    extends TriangleMesh

{

    public SphereObject( Triplet triplet, double radius )

    {

	super();

	super.addTriangleMesh( new Sphere( radius, 2, triplet ) );
	    
    }

}