package Utils;

import java.io.BufferedReader;

/**
 * Created by dell on 11/13/2016.
 */
public class FileData {
    private String filename;
    private BufferedReader br;
    public FileData(String filename,BufferedReader br){
        this.filename=filename;
        this.br=br;
    }
    public String getFilename(){
        return filename;
    }
    public BufferedReader getBr(){
        return br;
    }
    public String toString(){
        return filename;
    }
}
