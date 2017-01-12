package Model.Expression;
import Model.Expression.Expression;
import Utils.*;
/**
 * Created by dell on 10/19/2016.
 */
public class VarExpr implements Expression {
    private String name;
    public VarExpr(String name){
        this.name=name;
    }
    public int evaluate(ISymbolTable<String,Integer> s, IHeap<Integer,Integer> h) throws  ExpressionException {
        try {
            if (s.get(name) == null) {
                throw new ExpressionException("The object is not in the list");
            } else {
                return s.get(name);
            }
        }catch(UtilsException e){
            throw new ExpressionException(e.getMessage());
        }
    }
    public String toString(){
        return name;
    }
}
