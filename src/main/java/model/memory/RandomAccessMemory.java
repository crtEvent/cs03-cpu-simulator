package model.memory;

public class RandomAccessMemory {
    private final Integer[] values;
    private final Integer[] instructions;

    public RandomAccessMemory(int valueMemorySize, int instructionMemorySize) {
        this.values = new Integer[valueMemorySize];
        this.instructions = new Integer[instructionMemorySize];
    }

    public int getValue(int address) {
        validValueAddress(address);

        return values[address];
    }

    public int getInstruction(int address) {
        validInstructionAddress(address);

        return instructions[address];
    }

    public void setValue(int address, int value) {
        validValueAddress(address);

        values[address] = value;
    }

    public void setInstruction(int address, int instruction) {
        validInstructionAddress(address);

        instructions[address] = instruction;
    }

    private void validValueAddress(int address) {
        if (address < 0 || address >= values.length)
            throw new IllegalArgumentException("잘못된 메모리 주소입니다.");
    }

    private void validInstructionAddress(int address) {
        if (address < 0 || address >= instructions.length)
            throw new IllegalArgumentException("잘못된 메모리 주소입니다.");
    }
}
