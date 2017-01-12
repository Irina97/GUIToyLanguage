package Utils;

import java.util.*;

/**
 * Created by dell on 11/21/2016.
 */
public class Heap<K,V> implements IHeap<K,V> {
    private Map<K,V> heap;
    public Heap(){
        this.heap=new HashMap<K, V>();
    }
    public void put(K key,V value){ heap.put(key,value);}
    public V get(K key){ return heap.get(key);}
    public void remove(K key){ heap.remove(key);}
    public int size(){ return heap.size();}
    public boolean containsValue(V value){ return heap.containsValue(value);}
    public boolean containsKey(K key){ return heap.containsKey(key);}
    public Collection<V> values(){return heap.values();}
    public Iterable<Map.Entry<K,V>> getAll(){ return heap.entrySet();}
    public String toString(){
        return heap.toString();
    }
    public Set<Map.Entry<K,V>> entrySet(){ return heap.entrySet();}
    public  void setHeap(Map<K,V> newHeap){
        heap.clear();
        heap.putAll(newHeap);
    }
    public Map<K,V> getHeap(){
        return heap;
    }
}
