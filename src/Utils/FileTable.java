package Utils;

import java.util.*;

/**
 * Created by dell on 11/13/2016.
 */
public class FileTable<K,V> implements IFileTable<K,V> {
    private Map<K,V> map;
    public FileTable(){
        this.map=new HashMap<K, V>();
    }
    public void put(K key,V value){
        map.put(key,value);
    }
    public V get(K key){
       return map.get(key);
    }
    public void remove(K key){
        map.remove(key);
    }
    public int size(){
        return map.size();
    }
    public boolean isEmpty(){
        return map.isEmpty();
    }
    public String toString(){
        return map.toString();
    }
    public  boolean containsValue(V value){
        return map.containsValue(value);
    }

    public Collection<V> values() {
        return map.values();
    }
    public Iterable<Map.Entry<K,V>> getAll(){
        return map.entrySet();
    }
}
