package Utils;
import java.util.*;
/**
 * Created by dell on 10/16/2016.
 */
public class Out<T> implements IOut<T>{
    private List<T> list;
    public Out(){
        list=new ArrayList<T>();
    }

    public void add(T elem){
        list.add(elem);
    }
    public void remove(T elem){
        list.remove(elem);
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public T get(int index){
        return list.get(index);
    }
    public int size(){
        return list.size();
    }
    public String toString(){
        return list.toString();
    }
    public Iterable<T> getAll(){
        return list;
    }
}
