package edu.ucsd.ncmir.spl.Mogrification.thin_plate_spline;

/**
 *
 * @author spl
 */

public class InsufficientCorrespondencesException
    extends Exception

{

    InsufficientCorrespondencesException()

    {

        super( "Insufficient number of correspondences in set." );

    }

}
