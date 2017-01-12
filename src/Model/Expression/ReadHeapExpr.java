package Model.Expression;
import Utils.*;

/**
 * Created by dell on 11/21/2016.
 */
public class ReadHeapExpr implements Expression {
    private String varName;
    public ReadHeapExpr(String varName){
        this.varName=varName;
    }
    public int evaluate(ISymbolTable<String,Integer> symbolTable, IHeap<Integer,Integer> heap) throws ExpressionException {
        try{
            if(symbolTable.containsKey(varName)==false){
                throw new ExpressionException("The variable name does not exist in the symbol table");
            }
            int id=symbolTable.get(varName);
            if(heap.containsKey(id)==false){
                throw new ExpressionException("The id does not exist in the heap table");
            }
            int val=heap.get(id);
            return val;

        }catch(Exception e){
            throw new ExpressionException(e.getMessage());
        }
    }
    public String toString(){
        return "readHeap("+varName+")";
    }
}
