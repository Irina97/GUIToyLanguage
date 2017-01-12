package Utils;

/**
 * Created by dell on 11/21/2016.
 */
public class HeapIDGenerator {
    private static int counter=0;
    public static void setCounter(int newCounter){
        counter=newCounter;
    }
    public static int generateID(){
        return 1+counter;
    }
}
