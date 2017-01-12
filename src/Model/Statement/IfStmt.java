package Model.Statement;
import Model.ArithmeticException;
import Utils.*;
import Model.Expression.*;
import Model.ProgramState.*;
/**
 * Created by dell on 10/25/2016.
 */
public class IfStmt implements Statement {
    Expression expr;
    Statement thenS;
    Statement elseS;
    public IfStmt(Expression expr,Statement thenS,Statement elseS){
        this.expr = expr;
        this.thenS=thenS;
        this.elseS=elseS;
    }

    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> table=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try {
            int val = expr.evaluate(table,heap);
            if(val!=0){
                thenS.execute(p);
            }else{
                elseS.execute(p);
            }
        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }
        return  null;
    }

    public String toString(){
        return "IF("+expr.toString()+") THEN("+thenS.toString()+") ELSE("+elseS.toString()+")";
    }
}
