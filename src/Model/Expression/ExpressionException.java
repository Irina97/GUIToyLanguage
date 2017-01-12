package Model.Expression;

/**
 * Created by dell on 11/21/2016.
 */
public class ExpressionException extends Exception {
    private String msg;
    public ExpressionException(String msg){
        super(msg);
    }
}
