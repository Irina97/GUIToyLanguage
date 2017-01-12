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
public class ReadFileStmt implements Statement {
    private Expression variableFileID;//Here we have an expression whose evaluation must be the id in fileTable
    private String variableName;
    public ReadFileStmt(Expression variableFileID,String variableName){
        this.variableFileID=variableFileID;
        this.variableName=variableName;
    }
    public ProgramState execute(ProgramState p) throws  StatementException {
        ISymbolTable<String,Integer> symbolTable = p.getTable();
        IFileTable<Integer,FileData> fileTable=p.getFileTable();
        IHeap<Integer,Integer> heap=p.getHeap();
        try {
            int value=variableFileID.evaluate(symbolTable,heap);
            FileData fileData=fileTable.get(value);//We get the fileData that is associated to val in the fileTable
            if (fileData==null){
                throw new StatementException("No value found that is associated to the ID");
            }
            BufferedReader br=fileData.getBr();
            if(br==null){
                throw new StatementException("No BufferedReader found");
            }

            String line=br.readLine();
            int v;
            if(line==null){
                v=0;
            }else{
                v=Integer.parseInt(line);
            }
            symbolTable.put(variableName,v);
        }catch(Exception e) {
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public String toString(){
        return "read("+variableFileID+","+variableName+")";
    }
}


