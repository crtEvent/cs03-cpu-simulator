package model.cpu.code;

public class ControlSignal {
    private final InstructionCode opCode;
    private final Integer[] operands;

    private ControlSignal(InstructionCode opCode, Integer[] operands) {
        this.opCode = opCode;
        this.operands = operands;
    }

    public static ControlSignal makeRegSignal(InstructionCode opCode, int operand1, int operand2, int operand3) {
        return new ControlSignal(opCode, new Integer[]{operand1, operand2, operand3});
    }

    public static ControlSignal makeValueSignal(InstructionCode opCode, int operand1, int operand2, int value) {
        return new ControlSignal(opCode, new Integer[]{operand1, operand2, value});
    }

    public static ControlSignal makeMovSignal(InstructionCode opCode, int operand1, int operand2) {
        return new ControlSignal(opCode, new Integer[]{operand1, operand2});
    }

    public InstructionCode getOpCode() {
        return opCode;
    }

    public Integer[] getOperands() {
        return operands;
    }
}
