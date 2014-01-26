package edu.ucsd.ncmir.spl.Graphics.marchingcubes;

/**
 *
 * @author spl
 */
public interface VolumeNodeAccessor

{

    public boolean getPlane( int planeno, Node[][] plane )
        throws Exception;
    
}
