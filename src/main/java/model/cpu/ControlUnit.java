package model.cpu;

public class ControlUnit {
    private Integer instruction = null;

    public void setInstruction(Integer instruction) {
        this.instruction = instruction;
    }

    public void interpret() {

    }

    public void clear() {
        instruction = null;
    }

}
