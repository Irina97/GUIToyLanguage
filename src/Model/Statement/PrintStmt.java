package Model.Statement;
import Model.ArithmeticException;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.*;
/**
 * Created by dell on 10/23/2016.
 */
    public class PrintStmt  implements Statement {
        private Expression expr;
        public PrintStmt(Expression expr){
            this.expr=expr;
        }
        public ProgramState execute(ProgramState p) throws StatementException {
            ISymbolTable<String,Integer> table=p.getTable();
            IOut<Integer> output=p.getOut();
            IHeap<Integer,Integer> heap=p.getHeap();
            try {
                int val=expr.evaluate(table,heap);
                output.add(val);

            }catch(Exception e){
                throw new StatementException(e.getMessage());
            }
            return null;
        }
        public String toString(){
            return "Print("+ expr.toString()+")";
        }
    }

