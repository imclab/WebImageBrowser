package edu.ucsd.ncmir.spl.Graphics.geometry;

import edu.ucsd.ncmir.spl.Graphics.Color;


/**
 *
 * @author spl
 */
public abstract class GeometryObject
    implements GeometryComponent
{

    private String _name;
    private Color _diffuse;
    private Color _ambient;
    private Color _specular;
    private double _shine;

    public GeometryObject( String name,
                           Color diffuse,
                           Color ambient,
                           Color specular,
                           double shine )

    {

        this._name = name;

        this._diffuse = diffuse;
        this._ambient = ambient;
        this._specular = specular;

        this._shine = shine;

    }

    public GeometryObject( String name,
                           Color color )

    {

        this( name, color, 1 );

    }

    public GeometryObject( String name, Color color, double shine )

    {

        this( name, 
              color,
              new Color( color.getRed() / 255.0f * 0.1f,
                         color.getGreen() / 255.0f * 0.1f,
                         color.getBlue() / 255.0f * 0.1f,
                         color.getAlpha() / 255.0f ),
              new Color( 1f, 1f, 1f, color.getAlpha() / 255f ),
              shine );

    }

    /**
     * @return the specular component
     */

    public Color getSpecular()

    {

        return this._specular;

    }

    /**
     * @param specular the specular component
     */

    public void setSpecular( Color specular )

    {

        this._specular = specular;

    }

    /**
     * @return the diffuse component;
     */
    public Color getDiffuse()

    {

        return this._diffuse;
        
    }

    /**
     * @param diffuse the diffuse component
     */
    public void setDiffuse( Color diffuse )

    {

        this._diffuse = diffuse;

    }

    /**
     * @return the ambient component
     */
    public Color getAmbient()

    {

        return this._ambient;

    }

    /**
     * @param ambient the ambient component
     */
    public void setAmbient( Color ambient )

    {

        this._ambient = ambient;

    }

    /**
     * @return the shine component
     */
    public double getShine()

    {

        return this._shine;

    }

    /**
     * @param shine the shine component
     */
    public void setShine( double shine )

    {

        this._shine = shine;

    }

    /**
     * @return the name of the object
     */
    public String getName()

    {

        return _name;

    }

    /**
     * @param name the name of the object
     */
    public void setName( String name )

    {

        this._name = name;

    }

}
