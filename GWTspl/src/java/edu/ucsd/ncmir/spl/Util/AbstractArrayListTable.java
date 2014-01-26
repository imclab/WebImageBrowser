package edu.ucsd.ncmir.spl.Util;

public abstract class AbstractArrayListTable<K,V>
    extends ArrayListTable<K,V>

{

    public abstract K[] getKeys();

    public abstract V[] getArray( K key );

}
