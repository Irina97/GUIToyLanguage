package View;

/**
 * Created by dell on 11/13/2016.
 */
public abstract class Command {
    private int key;
    private String description;

    public Command(int key, String description){
        this.key=key;
        this.description=description;
    }
    public abstract  void execute();
    public int getKey(){return key;}
    public String getDescription(){return description;}
}
