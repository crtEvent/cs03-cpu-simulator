package model.cpu;

public class RegisterSet {
    private Integer[] resisterSet;

    public RegisterSet() {
        this.resisterSet = new Integer[7];
    }

    public int get(Integer address) {
        return 0;
    }

    public void set(Integer address) {

    }

    public void clear() {
        this.resisterSet = new Integer[7];
    }
}
