package Repository;
import Model.ProgramState.ProgramState;
import Model.Statement.Statement;
import Utils.ExeStack;
import Utils.IExeStack;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by dell on 10/22/2016.
 */
public interface IRepository {
    void addProgramState(ProgramState p);
     List<ProgramState> getPrgStates();
     void setPrgStates(List<ProgramState> list);
    void logPrgStateExec(ProgramState p) throws RepositoryException;
    String getFilename();
    void writeToFile(String msg) throws RepositoryException;
    void serialize(ProgramState p,String filename) throws RepositoryException;
    ProgramState getProgramState(int index) throws RepositoryException;
    static ProgramState deserialize(String filename) throws RepositoryException{
        try{
            FileInputStream fileIn= new FileInputStream(filename);
            ObjectInputStream in=new ObjectInputStream(fileIn);
            Object o=in.readObject();
            if(o instanceof ProgramState){
                return (ProgramState) o;
            }
            in.close();
            fileIn.close();
            return null;

        }catch(Exception e) {
            throw new RepositoryException(e.getMessage());
        }
    }
    void clearFile() throws RepositoryException;
    int getNrPrg();
    List<String> getProgramStateID();

}
