package model.cpu;

public class InstructionRegister {

    private Integer instruction = null;

    public Integer getInstruction() {
        return instruction;
    }

    public void setInstruction(Integer instruction) {
        this.instruction = instruction;
    }

    public void clear() {
        instruction = null;
    }
}
