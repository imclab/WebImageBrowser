package edu.ucsd.ncmir.spl.Interpolator;

/**
 *
 * @author spl
 */
public interface Instantiator 

{

    public AbstractInterpolator instantiate( double[] t, double[] x );

}
