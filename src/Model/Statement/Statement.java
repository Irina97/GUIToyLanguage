package Model.Statement;
import Model.ArithmeticException;
import Model.ProgramState.ProgramState;
import Utils.UtilsException;

import java.io.Serializable;

/**
 * Created by dell on 10/19/2016.
 */
public interface Statement extends Serializable{
    public ProgramState execute(ProgramState p) throws  StatementException;
}
