package Model.Statement;

import Model.Expression.ComparisonExpr;
import Model.Expression.Expression;
import Model.Expression.VarExpr;
import Model.ProgramState.ProgramState;
import Utils.IExeStack;

/**
 * Created by dell on 1/25/2017.
 */
public class ForStmt implements Statement{
    private String v;
    private Expression expr1;

    public ForStmt(String v, Expression expr1, Expression expr2, Expression expr3, Statement body) {
        this.v = v;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
        this.body = body;
    }

    private Expression expr2;
    private Expression expr3;
    private Statement body;
    @Override
    public ProgramState execute(ProgramState p) throws StatementException {
        IExeStack<Statement> stack=p.getExeStack();
        Statement s1=new AssignStmt(v,expr1);
        Statement s2=new WhileStmt(new ComparisonExpr("<",new VarExpr(v),expr2),
                new CompStmt(body,new AssignStmt(v,expr3)));
        Statement s=new CompStmt(s1,s2);

        stack.push(s);
        return null;
    }

    public String toString(){
        return "for("+v+"="+expr1+";"+v+"<"+expr2+";"+v+"="+expr3+")"+"{"+body+"}";
    }
}
