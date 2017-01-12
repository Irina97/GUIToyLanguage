package Utils;

import java.io.Serializable;

/**
 * Created by dell on 10/17/2016.
 */
public interface IExeStack<T> extends Serializable {
    T pop() ;
    void push(T elem);
    int size();
    T top();
    Iterable<T> getAll();

}
