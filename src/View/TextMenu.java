package View;
import java.util.*;
/**
 * Created by dell on 11/13/2016.
 */
public class TextMenu {
    private Map<Integer,Command> commands;
    public TextMenu(){
        commands=new HashMap<>();
    }
    public void addCommand(Command c){
        commands.put(c.getKey(),c);
    }
    private void printMenu(){
        for(Command c:commands.values()){
            String line=String.format("%4s:%s",c.getKey(),c.getDescription());
            System.out.println(line);
        }
    }
    public void show(){

        Scanner scanner=new Scanner(System.in);
        while (true){
            try {
                printMenu();
                System.out.println("Input the option: ");
                String key = scanner.nextLine();
                int k = Integer.valueOf(key);
                Command c = commands.get(k);
                if (c == null) {
                    System.out.println("Invalid option");
                    continue;
                }
                c.execute();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public Map<Integer,Command> getMap(){ return commands;}
}
