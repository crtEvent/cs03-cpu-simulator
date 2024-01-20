package model.cpu;

/**
 * 값을 임시로 저장할 7개의 레지스터.
 * 레지스터 배열은 개발 편의를 위해 길이를 8로 선언했습니다.
 * 배열 index는 1~7까지만 접근 가능하도록 구현했습니다.
 */
public class RegisterSet {
    private Integer[] resisterSet;

    public RegisterSet() {
        this.resisterSet = new Integer[8];
    }

    public boolean existsValue(Integer address) {
        if (address < 1 || address >= resisterSet.length)
            throw new IllegalArgumentException(String.format("접근 가능한 메모리 주소 범위를 벗어났습니다. (address: %d)", address));

        return resisterSet[address] != null;
    }

    public int get(Integer address) {
        if (address < 1 || address >= resisterSet.length)
            throw new IllegalArgumentException(String.format("접근 가능한 메모리 주소 범위를 벗어났습니다. (address: %d)", address));

        return resisterSet[address];
    }

    public void set(Integer address, Integer value) {
        if (address < 1 || address >= resisterSet.length)
            throw new IllegalArgumentException(String.format("접근 가능한 메모리 주소 범위를 벗어났습니다. (address: %d)", address));

        this.resisterSet[address] = value;
    }

    public void clear() {
        this.resisterSet = new Integer[8];
    }
}
