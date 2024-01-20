package model.cpu;

/**
 * Program Counter
 */
public class ProgramCounter {
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void countUp() {
        count++;
    }

    public void clear() {
        count = 0;
    }
}
