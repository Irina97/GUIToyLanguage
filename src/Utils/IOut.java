package Utils;
import java.io.Serializable;
import java.util.*;
/**
 * Created by dell on 10/16/2016.
 */
public interface IOut<T> extends Serializable{

    void add(T elem);
    void  remove(T elem);
    boolean isEmpty();
    T get(int index);
    int size();
    String toString();
    Iterable<T> getAll();

}
