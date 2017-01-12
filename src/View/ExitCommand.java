package View;

/**
 * Created by dell on 11/13/2016.
 */
public class ExitCommand extends Command {
    public ExitCommand(int key, String description){
        super(key,description);
    }
    public void execute(){
        System.exit(0);
    }
}
