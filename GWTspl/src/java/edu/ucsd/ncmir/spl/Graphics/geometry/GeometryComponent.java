package edu.ucsd.ncmir.spl.Graphics.geometry;

public interface GeometryComponent

{

    GeometryComponent[] getGeometryComponents();
    double[][] getComponentValues();
    GeometryComponent getParent();

}