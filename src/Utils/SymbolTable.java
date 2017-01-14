package Utils;
import java.util.*;
/**
 * Created by dell on 10/16/2016.
 */
public class SymbolTable<K,V> implements ISymbolTable<K,V> {
    private Map<K,V> map;
    public SymbolTable(){
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
    public ISymbolTable<K,V> clone(){
        ISymbolTable<K,V> newST = new SymbolTable<K, V>();
        for(Map.Entry<K,V> entry: getAll()){
            newST.put(entry.getKey(),entry.getValue());
        }
        return newST;
    }
    public Set<K> keySet(){
        return map.keySet();
    }
}
