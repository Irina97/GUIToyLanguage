package Model.Expression;
import Model.ArithmeticException;
import Model.Expression.Expression;
import Utils.*;
/**
 * Created by dell on 10/19/2016.
 */
public class AritmExpr implements Expression {
    private char operator;
    private Expression operand1,operand2;
    public AritmExpr(char operator,Expression operand1,Expression operand2){
        this.operator=operator;
        this.operand1=operand1;
        this.operand2=operand2;
    }
    public int evaluate(ISymbolTable<String,Integer> table, IHeap<Integer,Integer> h) throws  ExpressionException {
        try {
            int result1 = operand1.evaluate(table,h);
            int result2 = operand2.evaluate(table,h);
            switch (operator) {
                case '+':
                    return result1 + result2;
                case '-':
                    return result1 - result2;
                case '*':
                    return result1 * result2;
                case '/':
                    if (result2 == 0) {
                        throw new ArithmeticException("Cannot divide by zero.");
                    }
                    return result1 / result2;

                default:
                    throw new RuntimeException("Not a valid operand.");
            }
        }catch(Exception e){
                throw new ExpressionException(e.getMessage());
        }
    }

    public String toString(){
        return "("+ operand1+operator+operand2+")";
    }
}
