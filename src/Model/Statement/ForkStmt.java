package Model.Statement;

import Model.ProgramState.ProgramState;
import Utils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by dell on 12/12/2016.
 */
public class ForkStmt implements Statement {
    private Statement stmt;
    public ForkStmt(Statement stmt){
        this.stmt=stmt;
    }
    public ProgramState execute(ProgramState p) throws StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        ISymbolTable<String, Integer> newSymbolTable = symbolTable.clone();

        IHeap<Integer,Integer> heap = p.getHeap();
        IOut<Integer> out=p.getOut();
        IFileTable<Integer,FileData> fileTable=p.getFileTable();
        ILockTable<Integer,Integer> lockTable=p.getLockTable();
        ProgramState newPrgState=new ProgramState(lockTable,new ExeStack<>(), newSymbolTable, out, fileTable,heap,stmt);
        return newPrgState;

    }
    public String toString(){
        return "fork("+stmt.toString()+")";
    }
}
