package Model.Statement;

import Model.ArithmeticException;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Utils.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by dell on 11/13/2016.
 */
public class CloseFileStmt implements Statement {
    private Expression variableFileID;//Here we have an expression whose evaluation must be the id in fileTable
    public CloseFileStmt(Expression variableFileID){
        this.variableFileID=variableFileID;
    }
    public ProgramState execute(ProgramState p) throws  StatementException {
        ISymbolTable<String,Integer> symbolTable=p.getTable();
        IFileTable<Integer,FileData> fileTable=p.getFileTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try {
            int e = variableFileID.evaluate(symbolTable,heap);
            FileData fileData=fileTable.get(e);
            if (fileData==null){
                throw new StatementException("No FileData was found in FileTable");
            }
            BufferedReader br=fileData.getBr();
            if(br==null){
                throw  new StatementException("No BufferedReader was found.");
            }
            fileTable.remove(e);
            br.close();
        }catch (Exception e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public String toString(){
        return "close("+variableFileID+")";
    }
}
