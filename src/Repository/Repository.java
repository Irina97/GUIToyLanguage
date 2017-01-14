package Repository;
import Model.ProgramState.ProgramState;
import Model.Statement.Statement;
import Utils.*;

import java.io.*;
import java.util.*;

/**
 * Created by dell on 10/22/2016.
 */
public class Repository implements IRepository {
    private List<ProgramState> repo;
    private String filename;

    public Repository(String filename){
        repo= new ArrayList<>();
        this.filename=filename;
    }
    public void addProgramState(ProgramState p){
        repo.add(p);
    }
    public List<ProgramState> getPrgStates() {return  repo;}
    public void setPrgStates(List<ProgramState> list){ repo=list; }
    public String getFilename(){
        return filename;
    }
    public ProgramState getProgramState(int index) throws RepositoryException {
        if(index >= repo.size()||index <0)
            throw new RepositoryException("The index is out of size");
        return repo.get(index);
    }
    public void logPrgStateExec(ProgramState p) throws RepositoryException {
        PrintWriter logFile=null;
        try{

            logFile=new PrintWriter(new BufferedWriter(new FileWriter(filename,true)));
            int idPrg=p.getID();

            logFile.println("ID: "+idPrg);
            logFile.println();
            Iterable<Statement> stack=p.getExeStack().getAll();
            logFile.println("Execution Stack: ");
            for(Statement s:stack){
                logFile.println(s);
            }
            logFile.println();
            logFile.println("Symbol Table: ");
            Iterable<Map.Entry<String,Integer>> symTable=p.getTable().getAll();
            for(Map.Entry<String,Integer> s:symTable){
                logFile.println(s.getKey()+"->"+s.getValue());
            }
            logFile.println();
            logFile.println("Output: ");
            Iterable<Integer> out=p.getOut().getAll();
            for(Integer i: out){
                logFile.println(i);
            }
            logFile.println();
            logFile.println("File Table: ");
            Iterable<Map.Entry<Integer,FileData>> fileTable=p.getFileTable().getAll();
            for(Map.Entry<Integer,FileData> m: fileTable){
                int id=m.getKey();
                FileData fileData=m.getValue();
                String name=fileData.getFilename();
                logFile.println(id+"->"+name);
            }
            logFile.println();
            logFile.println("Heap: ");
            Iterable<Map.Entry<Integer,Integer>> heap=p.getHeap().getAll();
            for(Map.Entry<Integer,Integer> m:heap){

                logFile.println(m.getKey()+"->"+m.getValue());
            }
            logFile.println("::::::::::::::::::::::::::::");

        }catch(IOException e){
            throw new RepositoryException(e.getMessage());
        }
        finally {
            logFile.close();
        }
    }
    public void writeToFile(String msg) throws RepositoryException {
        PrintWriter logFile=null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            logFile.println(msg);
        }catch(IOException e){
            throw new RepositoryException(e.getMessage());
        }finally {
            logFile.close();
        }
    }
    public void serialize(ProgramState p,String filename) throws RepositoryException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out=new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();

        }catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }
    }
    public void clearFile() throws RepositoryException {
        try {
            FileWriter fileOut = new FileWriter(filename);
            fileOut.write("");
            fileOut.close();
        }catch(Exception e){
            throw new RepositoryException(e.getMessage());
        }
    }
    public int getNrPrg(){
        return repo.size();
    }
    public List<String> getProgramStateID(){
        ArrayList<String> list=new ArrayList<>();
        for(ProgramState pr: repo){
            String str="Program State "+pr.getID();
            list.add(str);
        }
        return list;
    }

}
