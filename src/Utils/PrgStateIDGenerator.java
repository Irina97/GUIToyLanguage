package Utils;

/**
 * Created by dell on 12/12/2016.
 */
public class PrgStateIDGenerator {
    public static int counter=0;
    public static  void setCounter(int newCounter){
        counter=newCounter;
    }
    public static int generateID(){
        return ++counter;
    }
}
