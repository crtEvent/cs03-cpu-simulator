package model.cpu;

public class InstructionRegister {

    private Integer instruction = null;

    public int getInstruction() {
        return instruction;
    }

    public void setInstruction(Integer instruction) {
        this.instruction = instruction;
    }

    public void clear() {
        instruction = null;
    }
}
