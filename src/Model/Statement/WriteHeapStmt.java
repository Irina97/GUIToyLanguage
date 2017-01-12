package Model.Statement;

import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.IHeap;
import Utils.ISymbolTable;

/**
 * Created by dell on 11/21/2016.
 */
public class WriteHeapStmt implements Statement {
    private String varName;
    private Expression expression;
    public WriteHeapStmt(String varName,Expression expression){
        this.varName=varName;
        this.expression=expression;
    }
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try{
            if(symbolTable.containsKey(varName)==false){
                throw new StatementException("The value does not exist in the symbol table");
            }
            int id=symbolTable.get(varName);
            if(heap.containsKey(id)==false){
                throw new StatementException("The id does not exist in the heap table");
            }
            int val=expression.evaluate(symbolTable,heap);
            heap.put(id,val);

        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public String toString(){
        return "writeHeap("+varName+","+expression.toString()+")";
    }
}
