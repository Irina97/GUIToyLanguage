package Model.Expression;
import Model.ArithmeticException;
import Utils.*;

import java.io.Serializable;

/**
 * Created by dell on 10/19/2016.
 */
public interface Expression extends Serializable{
    public int evaluate(ISymbolTable<String,Integer> s, IHeap<Integer,Integer> h) throws ExpressionException;
}
