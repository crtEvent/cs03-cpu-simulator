package model.cpu;

import model.cpu.code.ControlSignal;
import model.cpu.code.InstructionCode;

public class ControlUnit {
    private Integer instruction = null;

    public Integer getInstruction() {
        return instruction;
    }

    public void setInstruction(Integer instruction) {
        this.instruction = instruction;
    }

    public ControlSignal interpret() {
        String binaryString = String.format("%16s", Integer.toBinaryString(instruction)).replace(' ', '0');
        InstructionCode opCode = InstructionCode.fromCode(binaryString.substring(0, 4));
        int operand1 = Integer.parseInt(binaryString.substring(4, 7), 2);

        if (opCode == InstructionCode.MOV) {
            int value = Integer.parseInt(binaryString.substring(7, 16), 2);

            return ControlSignal.makeMovSignal(opCode, operand1, value);
        }

        int operand2 = Integer.parseInt(binaryString.substring(7, 10), 2);
        if (binaryString.charAt(10) == '1') {
            int value = Integer.parseInt(binaryString.substring(11, 16), 2);

            return ControlSignal.makeValueSignal(opCode, operand1, operand2, value);
        } else {
            int operand3 = Integer.parseInt(binaryString.substring(13, 16), 2);

            return ControlSignal.makeRegSignal(opCode, operand1, operand2, operand3);
        }
    }

    public void clear() {
        instruction = null;
    }

}
