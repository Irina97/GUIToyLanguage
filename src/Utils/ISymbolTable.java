package Utils;
import java.io.Serializable;
import java.util.*;
/**
 * Created by dell on 10/16/2016.
 */
public interface ISymbolTable<K,V> extends Serializable{
    void put(K key, V value);
    V get(K key) throws UtilsException;
    void remove(K key);
    int size();
    boolean isEmpty();
    boolean containsKey(K key);
    String toString();
    Iterable<Map.Entry<K,V>> getAll();
    Collection<V> values();
    ISymbolTable<K,V> clone();
    Set<K> keySet();

}
