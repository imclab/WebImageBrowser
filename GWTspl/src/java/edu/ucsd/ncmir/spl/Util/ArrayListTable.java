package edu.ucsd.ncmir.spl.Util;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayListTable<K,V>
    extends HashMap<K,ArrayList<V>>

{

    public void add( K key, V value )

    {

	ArrayList<V> list = super.get( key );

	if ( list == null )
	    super.put( key, list = new ArrayList<V>() );

	list.add( value );

    }

}