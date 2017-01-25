package Utils;

/**
 * Created by dell on 1/25/2017.
 */
public class LockIDGenerator {
    private static int counter=0;
    public static void setCounter(int newCounter){
        counter=newCounter;
    }
    public static int generateID(){
        return 1+counter;
    }
}
