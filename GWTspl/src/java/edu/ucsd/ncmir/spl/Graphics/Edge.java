/*
 * Edge.java
 *
 * Created on January 5, 2007, 12:29 PM
 */

package edu.ucsd.ncmir.spl.Graphics;

class Edge
    
{
    
    private double _dx;
    private double _x;
    private int _i;
    
    Edge( double dx, double x, int i )
	
    {
	
	this._dx = dx;
	this._x = x;
	this._i = i;
	
    }
    
    void updateX( double increment )
        
    {
        
        this._x += increment;
        
    }
        
    double getX()
        
    {
        
        return this._x;
        
    }
    
    double getI()
        
    {
        
        return this._i;
        
    }
    
    double getDx()
        
    {
        
        return this._dx;
        
    }
    
}