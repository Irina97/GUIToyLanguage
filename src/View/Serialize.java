package View;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dell on 11/26/2016.
 */
public class Serialize extends Command {
    private TextMenu menu;
    public Serialize(int key,String desc,TextMenu menu){
        super(key,desc);
        this.menu = menu;
    }
    public void execute(){
        try{
            Scanner scanner=new Scanner(System.in);
            System.out.println("Input the program: ");
            String key = scanner.nextLine();
            int k = Integer.valueOf(key);
            Map<Integer,Command> menuMap=menu.getMap();
            if(menuMap.containsKey(k)==true){
                Command command=menuMap.get(k);
                if(command instanceof RunExample ){
                    ((RunExample) command).getCtrl().serializeCtrl("serialize.txt");
                }else{
                    System.out.println("Not a running example class");
                }
            }else{
                System.out.println("Not a valid example");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
