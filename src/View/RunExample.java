package View;

import Controller.*;
import Repository.RepositoryException;

/**
 * Created by dell on 11/13/2016.
 */
public class RunExample extends Command {
    private Controller ctrl;
    public RunExample(int key,String desc,Controller ctrl){
        super(key,desc);
        this.ctrl=ctrl;
    }
    public void execute(){
        try{
            ctrl.executeAll();
        }catch(ControllerException e){
            System.out.println(e.getMessage());
        }
    }
    public Controller getCtrl(){
        return ctrl;
    }
}
