package Utils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by dell on 11/13/2016.
 */
public interface IFileTable<K,V> extends Serializable{
    void put(K key,V value);
    V get(K key);
    void remove(K key);
    int size();
    boolean containsValue(V value);
    Collection<V> values();
    Iterable<Map.Entry<K,V>> getAll();
    Set<K> keySet();
}
