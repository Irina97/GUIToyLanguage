package Model.Statement;

import Model.ProgramState.ProgramState;
import Utils.IHeap;
import Utils.ILockTable;
import Utils.ISymbolTable;
import Utils.LockIDGenerator;

/**
 * Created by dell on 1/25/2017.
 */
public class NewLockStmt implements Statement {
    private String varName;

    public NewLockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        ILockTable<Integer,Integer> lockTable=p.getLockTable();
        try{
            if(symbolTable.containsKey(varName)){
                int value=symbolTable.get(varName);
                lockTable.remove(value);

                int id= LockIDGenerator.generateID();
                LockIDGenerator.setCounter(id);
                symbolTable.put(varName,id);
                lockTable.put(id,-1);
            }else {
                int id= LockIDGenerator.generateID();
                LockIDGenerator.setCounter(id);
                symbolTable.put(varName, id);
                lockTable.put(id, -1);
            }

        }catch (Exception e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }

    public String toString(){
        return "newLock("+varName+")";
    }
}
