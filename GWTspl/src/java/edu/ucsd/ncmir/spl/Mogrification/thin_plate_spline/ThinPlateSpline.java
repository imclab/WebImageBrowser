package edu.ucsd.ncmir.spl.Mogrification.thin_plate_spline;

import edu.ucsd.ncmir.spl.Mogrification.Mogrification;
import edu.ucsd.ncmir.spl.Mogrification.Mogrifier;
import edu.ucsd.ncmir.spl.Mogrification.XYUVList;

/**
 *
 * @author spl
 */
public class ThinPlateSpline
    extends Mogrification
{

    public ThinPlateSpline( XYUVList xyuv )

    {

        super( xyuv );

    }

    @Override
    public Mogrifier getForwardMogrifier()
        throws Exception
    {

	return new ThinPlateSplineMogrifier( super.getUV(), super.getXY() );

    }

    @Override
    public Mogrifier getInverseMogrifier()
        throws Exception
    {

	return new ThinPlateSplineMogrifier( super.getXY(), super.getUV() );

    }

}