package edu.ucsd.ncmir.spl.Graphics.smoothers;

import edu.ucsd.ncmir.spl.Graphics.meshables.TriangleMesh;

/**
 *
 * @author spl
 */
public interface Smoother

{

    public TriangleMesh smooth( TriangleMesh mesh );

}
