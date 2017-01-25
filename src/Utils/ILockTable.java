package Utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell on 1/25/2017.
 */
public interface ILockTable<K,V> {
    void put(K key, V value);
    V get(K key) throws UtilsException;
    void remove(K key);
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    String toString();
    Iterable<Map.Entry<K,V>> getAll();
    Collection<V> values();
    ILockTable<K,V> clone();
    Set<K> keySet();
}
