package Model.Statement;

import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.*;

/**
 * Created by dell on 11/21/2016.
 */
public class NewHeapEntry implements Statement {
    private String varName;
    private Expression expression;
    public NewHeapEntry(String varName,Expression expression){
        this.varName=varName;
        this.expression=expression;
    }
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try{
            int val=expression.evaluate(symbolTable,heap);
            int id= HeapIDGenerator.generateID();
            HeapIDGenerator.setCounter(id);
            heap.put(id,val);
            symbolTable.put(varName,id);

        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public  String toString(){
        return "new("+varName+","+expression.toString()+")";
    }
}
