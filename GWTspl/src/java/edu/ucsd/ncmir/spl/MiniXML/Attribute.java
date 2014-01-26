package edu.ucsd.ncmir.spl.MiniXML;

/**
 *
 * @author spl
 */
public class Attribute
{
    private String _name;
    private String _value;

    public Attribute( String name, String value )

    {

	this._name = name;
	this._value = value;

    }

    public boolean getBooleanValue()
	throws DataConversionException

    {

	boolean value;

	if ( this._value.toLowerCase().equals( "true" ) )
	    value = true;
	else if ( this._value.toLowerCase().equals( "false" ) )
	    value = false;
	else
	    throw new DataConversionException( "Bad value" );

	return value;

    }

    public int getIntValue()
	throws DataConversionException

    {

	int value;

	try {

	    value = Integer.parseInt( this._value );

	} catch ( NumberFormatException nfe ) {

	    throw new DataConversionException( nfe.getMessage() );

	}

	return value;

    }

    public double getDoubleValue()
	throws DataConversionException

    {

	double value;

	try {

	    value = Double.parseDouble( this._value );

	} catch ( NumberFormatException nfe ) {

	    throw new DataConversionException( nfe.getMessage() );

	}

	return value;

    }

    public String getName()

    {

	return this._name;

    }

    public String getValue()

    {

	return this._value;

    }

    @Override
    public boolean equals( Object o )

    {

	boolean equals = false;

	if ( o instanceof Attribute )
	    equals = this._name.equals( ( ( Attribute ) o )._name );

	return equals;

    }

    @Override
    public int hashCode()

    {

        int hash = 7;
        hash = 43 * hash + ( this._name != null ? this._name.hashCode() : 0 );

        return hash;

    }

}
