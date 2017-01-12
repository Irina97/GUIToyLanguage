package Model.Expression;

import Utils.IHeap;
import Utils.ISymbolTable;

/**
 * Created by dell on 11/26/2016.
 */
public class ComparisonExpr implements  Expression {
    private String operator;
    private Expression expr1;
    private Expression expr2;
    public ComparisonExpr(String operator,Expression expr1, Expression expr2){
        this.operator=operator;
        this.expr1=expr1;
        this.expr2=expr2;
    }
    public int evaluate(ISymbolTable<String,Integer> symbolTable, IHeap<Integer,Integer> heap) throws ExpressionException {
        try{
            int res1=expr1.evaluate(symbolTable, heap);
            int res2=expr2.evaluate(symbolTable, heap);
            switch (operator){
                case "<":
                    if(res1<res2)
                        return 1;
                    return 0;
                case "<=":
                    if(res1<=res2)
                        return 1;
                    return 0;
                case ">":
                    if(res1>res2)
                        return 1;
                    return 0;
                case ">=":
                    if(res1>=res2)
                        return 1;
                    return 0;
                case "==":
                    if(res1==res2)
                        return 1;
                    return 0;
                case "!=":
                    if(res1!=res2)
                        return 1;
                    return 0;
                default:
                    throw new ExpressionException("Not a valid operand");
            }
        }catch (Exception e){
            throw new ExpressionException(e.getMessage());
        }
    }
    public String toString(){
        return "("+expr1.toString()+operator+expr2.toString()+")";
    }

}
