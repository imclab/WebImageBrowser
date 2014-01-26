package edu.ucsd.ncmir.spl.Mogrification.weighted_least_squares;

import edu.ucsd.ncmir.spl.Mogrification.Mogrification;
import edu.ucsd.ncmir.spl.Mogrification.Mogrifier;
import edu.ucsd.ncmir.spl.Mogrification.XYUVList;

public class WeightedLeastSquares
    extends Mogrification

{

    private double _delta;

    public WeightedLeastSquares( XYUVList xyuv, double delta )

    {

        super( xyuv );
	this._delta = delta;

    }

    @Override
    public Mogrifier getForwardMogrifier() throws Exception
    {

        return new WeightedLeastSquaresMogrifier( super.getXY(), super.getUV(),
                                                  this._delta );
    }

    @Override
    public Mogrifier getInverseMogrifier() throws Exception
    {
        throw new UnsupportedOperationException( "Not supported yet." );
    }

}
