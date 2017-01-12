package Model.Statement;

import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.IExeStack;
import Utils.IHeap;
import Utils.ISymbolTable;

/**
 * Created by dell on 11/26/2016.
 */
public class WhileStmt implements  Statement {
    private Expression expr;
    private Statement stmt;
    public WhileStmt(Expression expr,Statement stmt){
        this.expr=expr;
        this.stmt=stmt;
    }
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        IExeStack<Statement> stack=p.getExeStack();
        try{
            int result=expr.evaluate(symbolTable,heap);
            if( result !=0){
                stack.push(this);
                stack.push(stmt);
            }

        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public String toString(){
        return "while("+expr.toString()+")"+ stmt.toString();
    }
}
