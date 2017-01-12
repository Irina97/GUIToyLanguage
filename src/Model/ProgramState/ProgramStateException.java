package Model.ProgramState;

/**
 * Created by dell on 12/12/2016.
 */
public class ProgramStateException extends Exception {
    private String msg;
    public ProgramStateException(String msg){
        super(msg);
    }
}
