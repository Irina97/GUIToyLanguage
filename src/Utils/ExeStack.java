package Utils;

import java.util.*;

/**
 * Created by dell on 10/17/2016.
 */
public class ExeStack<T> implements IExeStack<T> {

    private Deque<T> stack;

    public ExeStack() {
        stack =  new ArrayDeque<T>();
    }

    public void push(T elem) {
        stack.push(elem);
    }

    public T pop() {
        return stack.pop();
    }

    public int size(){
        return stack.size();
    }
    public T top(){
        return stack.peek();
    }
    public String toString() {
        return stack.toString();
    }
    public Iterable<T> getAll(){
        return stack;
    }
}

