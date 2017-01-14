package GUI;

/**
 * Created by dell on 1/13/2017.
 */
public class FileTableGUI {
    private final Integer identifier;
    private final String filename;

    public FileTableGUI(Integer identifier, String filename) {
        this.identifier = identifier;
        this.filename = filename;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getFilename() {
        return filename;
    }
}
