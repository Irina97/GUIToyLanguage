package GUI;

/**
 * Created by dell on 1/13/2017.
 */
public class SymbolTableGUI {
    private final String varName;
    private final int value;

    public SymbolTableGUI(String varName, int value) {
        this.varName = varName;
        this.value = value;
    }

    public String getVarName() {
        return varName;
    }

    public int getValue() {
        return value;
    }
}
