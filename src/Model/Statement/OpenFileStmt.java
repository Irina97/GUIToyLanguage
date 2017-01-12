package Model.Statement;

import Model.ProgramState.ProgramState;
import Utils.IFileTable;
import Utils.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by dell on 11/13/2016.
 */
public class OpenFileStmt implements Statement {
    private String variableFileID;
    private String filename;
    public OpenFileStmt(String variableFileID, String filename){
        this.variableFileID=variableFileID;
        this.filename=filename;
    }
    public ProgramState execute(ProgramState p) throws StatementException {

        IFileTable<Integer,FileData> fileTable=p.getFileTable();
        ISymbolTable<String,Integer> symbolTable=p.getTable();

        for(FileData d: fileTable.values()){
            if(d.getFilename()==filename){
                throw new StatementException("The filename is already in the FileTable.");
            }
        }
        try{
            BufferedReader br=new BufferedReader(new FileReader(filename));
            FileData fileData=new FileData(filename,br);
            int id= FileIDGenerator.generateID();
            FileIDGenerator.setCounter(id);
            fileTable.put(id,fileData);
            symbolTable.put(variableFileID,id);

        }catch(IOException e){
            throw new StatementException(e.getMessage());
        }
        return null;
    }
    public String toString(){
        return "open( "+ variableFileID+"," +filename+")";
    }
}
