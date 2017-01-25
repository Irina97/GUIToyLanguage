package Model.Statement;

import Model.ProgramState.ProgramState;
import Utils.IExeStack;
import Utils.ILockTable;
import Utils.ISymbolTable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dell on 1/25/2017.
 */
public class LockStmt implements Statement {
    private String varName;

    public LockStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        ILockTable<Integer,Integer> lockTable=p.getLockTable();
        IExeStack<Statement> exeStack=p.getExeStack();
        ReentrantLock lock = new ReentrantLock();
        try{
            lock.lock();
            if(symbolTable.containsKey(varName)==false)
                throw new StatementException("The variable is not in the symbol table(Lock Statement)");
            int res=symbolTable.get(varName);

            if(lockTable.containsKey(res)==false)
                throw new StatementException("The id is not in the lock table(Lock Statement)");

            if(lockTable.get(res)==-1){
                lockTable.remove(res);
                int prgStateIdentifier=p.getID();
                lockTable.put(res,prgStateIdentifier);
            }else{
                exeStack.push(new LockStmt(varName));
            }

        }catch(Exception e){
            throw new StatementException(e.getMessage());
        }finally {
            lock.unlock();
        }
        return null;
    }

    public String toString(){
        return "lock("+varName+")";
    }
}
