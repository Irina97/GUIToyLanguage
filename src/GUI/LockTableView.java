package GUI;

/**
 * Created by dell on 1/25/2017.
 */
public class LockTableView {
    private final Integer address;
    private final Integer value;

    public LockTableView(Integer address, Integer value) {
        this.address = address;
        this.value = value;
    }

    public Integer getKey() {
        return address;
    }

    public Integer getValue() {
        return value;
    }
}
