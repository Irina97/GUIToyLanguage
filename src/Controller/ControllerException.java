package Controller;

/**
 * Created by dell on 11/13/2016.
 */
public class ControllerException extends Exception {
    private String msg;
    public ControllerException(String msg){
        super(msg);
    }
}
