package Model.Statement;
import Model.ProgramState.ProgramState;
import Utils.*;

/**
 * Created by dell on 10/19/2016.
 */
public class CompStmt implements Statement {
    private Statement first;
    private Statement second;

    public CompStmt(Statement f, Statement s) {
        first = f;
        second = s;
    }

    public ProgramState execute(ProgramState p) {
        IExeStack<Statement> stack = p.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    public String toString() {
        return "{" + first + ";" + second + "}";
    }
}


