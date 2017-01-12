package Model.Expression;
import Model.Expression.Expression;
import Utils.*;
/**
 * Created by dell on 10/19/2016.
 */
public class ConstExpr implements Expression {
    private int value;
    public ConstExpr(int value){
        this.value=value;
    }
    public int evaluate(ISymbolTable<String,Integer> s,IHeap<Integer,Integer> heap){
        return value;
    }
    public String toString(){
        return " "+ value;
    }
}
