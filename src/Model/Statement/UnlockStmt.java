package Model.Statement;

import Model.ProgramState.ProgramState;
import Utils.ILockTable;
import Utils.ISymbolTable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 1/25/2017.
 */
public class UnlockStmt implements Statement {
    private String varName;

    public UnlockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symTable=p.getTable();
        ILockTable<Integer,Integer> lockTable=p.getLockTable();
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            if(symTable.containsKey(varName)==false)
                throw new StatementException("The variable is not in the table(Unlock Statement)");
            int res = symTable.get(varName);

            if(lockTable.containsKey(res)==true ) {
                if (lockTable.get(res) == p.getID()) {
                    lockTable.remove(res);
                    lockTable.put(res, -1);
                }
            }else{

            }


        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }finally {
            lock.unlock();
        }
        return null;
    }

    public String toString(){
        return "unlock("+varName+")";
    }
}
