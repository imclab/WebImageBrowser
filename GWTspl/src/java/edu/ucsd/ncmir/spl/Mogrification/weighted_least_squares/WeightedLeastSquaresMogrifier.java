package edu.ucsd.ncmir.spl.Mogrification.weighted_least_squares;

import edu.ucsd.ncmir.spl.Mogrification.Mogrifier;


/**
 *
 * @author spl
 */
public class WeightedLeastSquaresMogrifier
    implements Mogrifier

{

    private WeightedLeastSquaresSolver _x_solver;
    private WeightedLeastSquaresSolver _y_solver;

    public WeightedLeastSquaresMogrifier( double[][] a, double[][] b, double delta )

    {

	double[] X = a[0];
	double[] Y = a[1];

	double[] Z1 = b[0];
	double[] Z2 = b[1];

	this._x_solver = new WeightedLeastSquaresSolver( delta,
							 X, Y,
							 Z1 );

	this._y_solver = new WeightedLeastSquaresSolver( delta,
							 X, Y,
							 Z2 );

    }

    @Override
    public double[] mogrify( double[] xy )
    {

	return new double[] {
	    this._x_solver.approximate( xy[0], xy[1] ),
	    this._y_solver.approximate( xy[0], xy[1] )
	};

    }

}
