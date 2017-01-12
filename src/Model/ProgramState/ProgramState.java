package Model.ProgramState;
import Model.Statement.Statement;
import Model.Statement.StatementException;
import Utils.*;

import java.io.Serializable;

/**
 * Created by dell on 10/19/2016.
 */
public class ProgramState implements Serializable{
    private IExeStack<Statement> exeStack;
    private ISymbolTable<String,Integer> table;
    private IOut<Integer> out;
    private IFileTable<Integer,FileData> fileTable;
    private IHeap<Integer,Integer> heap;
    private Statement stm;
    private int ID;

    public ProgramState(IExeStack<Statement> e,ISymbolTable<String,Integer> t,IOut<Integer> o,IFileTable<Integer,FileData> f,
            IHeap<Integer,Integer> h,Statement s){
        exeStack=e;
        table=t;
        out=o;
        fileTable=f;
        heap=h;
        stm=s;
        exeStack.push(stm);
        this.ID=PrgStateIDGenerator.generateID();
        PrgStateIDGenerator.setCounter(this.ID);
    }
    public Integer getID(){ return ID;}
    public IExeStack<Statement> getExeStack(){
        return  exeStack;
    }
    public ISymbolTable<String,Integer> getTable(){
        return table;
    }
    public IOut<Integer> getOut(){
        return out;
    }
    public Statement getStm(){
        return stm;
    }
    public IFileTable<Integer,FileData> getFileTable(){return fileTable;}
    public IHeap<Integer,Integer> getHeap(){ return heap;}

    public void setExeStack(IExeStack<Statement> newStack){
        exeStack=newStack;
    }
    public void setTable(ISymbolTable<String,Integer> newTable){
        table=newTable;
    }
    public void setOut(IOut<Integer> newOut){
        out=newOut;
    }
    public void setStm(Statement newStatement){
        stm=newStatement;
    }
    public void setFileTable(IFileTable<Integer,FileData> newFileTable){ fileTable=newFileTable;}
    public void setHeap(IHeap<Integer,Integer> newHeap){ heap=newHeap;}
    public String toString(){
        StringBuffer s= new StringBuffer();
        s.append("PrgState ID: ");
        s.append(this.ID);s.append("\n");
        s.append("Stack:");
        s.append(exeStack );s.append("\n");
        s.append("Dictionary:");
        s.append(table);s.append("\n");
        s.append("Output:");
        s.append(out);s.append("\n");
        s.append("FileTable: ");
        s.append(fileTable);s.append("\n");
        s.append("Heap: ");
        s.append(heap);s.append("\n");
        return s.toString();
    }
    public boolean isNotCompleted(){
        if(exeStack.size()!=0)
            return true;
        return false;
    }
    public ProgramState executeOneStep() throws ProgramStateException {
        if(exeStack.size()!=0){
            Statement s=exeStack.pop();
            try{
               return s.execute(this);
            }catch (Exception e){
                throw new ProgramStateException(e.getMessage());
            }
        }else{
            throw new ProgramStateException("The stack is empty");
        }
    }
}
