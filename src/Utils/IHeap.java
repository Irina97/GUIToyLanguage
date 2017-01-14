package Utils;
import java.io.Serializable;
import java.util.*;

/**
 * Created by dell on 11/21/2016.
 */
public interface IHeap<K,V> extends Serializable{
    void put(K key,V value);
    V get(K key);
    void remove(K key);
    int size();
    boolean containsValue(V value);
    boolean containsKey(K key);
    Collection<V> values();
    Iterable<Map.Entry<K,V>> getAll();
    String toString();
    Set<Map.Entry<K,V>> entrySet();
    void setHeap(Map<K,V> newHeap);
    Map<K,V> getHeap();
    Set<K> keySet();
}
