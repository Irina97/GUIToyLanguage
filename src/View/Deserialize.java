package View;

import Controller.Controller;
import Model.ProgramState.ProgramState;
import Repository.Repository;

/**
 * Created by dell on 11/26/2016.
 */
public class Deserialize extends Command{

    private TextMenu menu;
    public Deserialize(int key, String desc,TextMenu menu){
        super(key,desc);
        this.menu=menu;
    }
    public void execute(){
        try{
            ProgramState p=Controller.deserializeCtrl("serialize.txt");
            Repository repo=new Repository("deserialize.txt");
            repo.addProgramState(p);
            Controller ctrl=new Controller(repo);
            menu.addCommand(new RunExample(16,"Run the deserialized example",ctrl));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
