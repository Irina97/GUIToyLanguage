package GUI;

/**
 * Created by dell on 1/13/2017.
 */
public class HeapTable {
    private final String address;
    private final Integer value;

    public HeapTable(String address, Integer value) {
        this.address = address;
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public Integer getValue() {
        return value;
    }
}
