package Model.Statement;
import Model.ArithmeticException;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.*;
/**
 * Created by dell on 10/19/2016.
 */
public class AssignStmt implements Statement {
    private String var;
    private Expression expr;
    public AssignStmt(String v, Expression e){
        var=v;
        expr=e;
    }
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> table=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try {
            int result = expr.evaluate(table, heap);
            table.put(var, result);
            return null;
        }catch(Exception e){
            throw new  StatementException(e.getMessage());
        }
    }
    public String toString(){
        return " "+ var+"="+expr;
    }
}
