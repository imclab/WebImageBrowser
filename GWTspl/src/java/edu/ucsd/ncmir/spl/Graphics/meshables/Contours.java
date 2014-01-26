package edu.ucsd.ncmir.spl.Graphics.meshables;

import edu.ucsd.ncmir.spl.Graphics.Triplet;
import java.util.ArrayList;

/**
 *
 * @author spl
 */
public class Contours
    implements Meshable

{

    private ArrayList<ArrayList<Triplet>> _contours =
	new ArrayList<ArrayList<Triplet>>();

    @Override
    public boolean hasData()

    {

	return this._contours.size() > 0;

    }

    public void addContour( ArrayList<Triplet> contour )

    {

	this._contours.add( contour );

    }

    public ArrayList<ArrayList<Triplet>> getContours()

    {

	return this._contours;

    }

    public float size()

    {

        return this._contours.size();

    }

}
