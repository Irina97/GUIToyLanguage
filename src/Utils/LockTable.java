package Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell on 1/25/2017.
 */
public class LockTable<K,V> implements ILockTable<K,V> {
    private Map<K,V> map;
    public LockTable(){
        map = new HashMap<K, V>();
    }
    public void put(K key,V value){
        map.put(key,value);
    }
    public V get(K key) throws UtilsException {
        if (map.containsKey(key)==true) {
            return map.get(key);
        }else {
            throw new UtilsException("The value does not exist in the map");
        }
    }
    public int size(){
        return map.size();
    }
    public void remove(K key){
        map.remove(key);
    }
    public boolean isEmpty(){
        return map.isEmpty();
    }
    public String toString(){
        return map.toString();
    }
    public Iterable<Map.Entry<K,V>> getAll(){
        return map.entrySet();
    }
    public boolean containsKey(K key){ return map.containsKey(key);}
    public Collection<V> values(){ return map.values();}
    public ILockTable<K,V> clone(){
        ILockTable<K,V> newST = new LockTable<K, V>();
        for(Map.Entry<K,V> entry: getAll()){
            newST.put(entry.getKey(),entry.getValue());
        }
        return newST;
    }
    public Set<K> keySet(){
        return map.keySet();
    }
}
