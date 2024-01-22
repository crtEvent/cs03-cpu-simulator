package model;

import model.cpu.ArithmeticLogicUnit;
import model.cpu.ControlUnit;
import model.cpu.InstructionRegister;
import model.cpu.ProgramCounter;
import model.cpu.RegisterSet;
import model.cpu.code.OpCode;
import model.memory.RandomAccessMemory;

public class Machine {

    private final ControlUnit CU;
    private final ArithmeticLogicUnit ALU;
    private final ProgramCounter PC;
    private final InstructionRegister IR;
    private final RegisterSet resisterSet;
    private final RandomAccessMemory RAM;

    public Machine(ControlUnit CU, ArithmeticLogicUnit ALU, ProgramCounter PC,
        InstructionRegister IR,
        RegisterSet resisterSet, RandomAccessMemory RAM) {
        this.CU = CU;
        this.ALU = ALU;
        this.PC = PC;
        this.IR = IR;
        this.resisterSet = resisterSet;
        this.RAM = RAM;
    }

    /**
     * 기억장치로 부터 명령어 읽어온다. 읽어온 명령어는 CPU의 IR에 저장한다. 명령어를 읽어온 후 Program Counter를 1 증가시킨다.
     */
    public void fetch() {
        int count = PC.getCount();
        int instruction = RAM.getInstruction(count);
        IR.setInstruction(instruction);
        PC.countUp();
    }

    /**
     * IR에 저장된 명령어를 CU에 전달한다. CU는 전달 받은 명령어를 해석한다. 해석된 명령어를 실행한다. 산술 연산, 논리 연산은 ALU가 계산하여 결과를 반환해
     * 준다.
     */
    public void execute() {
        int instruction = IR.getInstruction();
        CU.setInstruction(instruction);
        var controlSignal = CU.interpret();

        switch (controlSignal.getOpCode().getType()) {
            case OpCode.LOAD -> {
                Integer[] operands = controlSignal.getOperands();
                int value = RAM.getValue(operands[1] + operands[2]);
                resisterSet.set(operands[0], value);
            }
            case OpCode.STORE -> {
                Integer[] operands = controlSignal.getOperands();
                int value = resisterSet.get(operands[0]);
                RAM.setValue(operands[1] + operands[2], value);
            }
            case OpCode.OR -> {
                Integer[] operands = controlSignal.getOperands();
                ALU.set(resisterSet.get(operands[1]), resisterSet.get(operands[2]));
                int result = ALU.or();
                resisterSet.set(operands[0], result);
            }
            case OpCode.AND -> {
                Integer[] operands = controlSignal.getOperands();
                ALU.set(resisterSet.get(operands[1]), resisterSet.get(operands[2]));
                int result = ALU.and();
                resisterSet.set(operands[0], result);
            }
            case OpCode.ADD -> {
                Integer[] operands = controlSignal.getOperands();
                ALU.set(resisterSet.get(operands[1]), resisterSet.get(operands[2]));
                int result = ALU.add();
                resisterSet.set(operands[0], result);
            }
            case OpCode.SUB -> {
                Integer[] operands = controlSignal.getOperands();
                ALU.set(resisterSet.get(operands[1]), resisterSet.get(operands[2]));
                int result = ALU.sub();
                resisterSet.set(operands[0], result);
            }
            case OpCode.MOV -> {
                Integer[] operands = controlSignal.getOperands();
                resisterSet.set(operands[0], operands[1]);
            }
            default -> {
                throw new IllegalArgumentException("잘못된 Signal 입니다.");
            }
        }
    }

    /**
     * Resister와 Program Counter 값을 모두 초기화 한다.
     */
    public void reset() {
        CU.clear();
        ALU.clear();
        PC.clear();
        IR.clear();
        resisterSet.clear();
    }

    /**
     * 현재 Resister 값들을 리턴한다.
     */
    public void dump() {

    }

    @Override
    public String toString() {
        String ir1, ir2, cu1, cu2, r1, r2, r3, r4, r5, r6, r7;

        ir1 = (IR.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString((IR.getInstruction() >>> 8) & 0xFF))
            .replace(' ', '0')
            : "        ";
        ir2 = (IR.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString(IR.getInstruction() & 0xFF))
            .replace(' ', '0')
            : "        ";
        cu1 = (CU.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString((CU.getInstruction() >>> 8) & 0xFF))
            .replace(' ', '0')
            : "        ";
        cu2 = (CU.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString(CU.getInstruction() & 0xFF))
            .replace(' ', '0')
            : "        ";
        r1 = resisterSet.existsValue(1) ? String.valueOf(resisterSet.get(1)) : "";
        r2 = resisterSet.existsValue(2) ? String.valueOf(resisterSet.get(2)) : "";
        r3 = resisterSet.existsValue(3) ? String.valueOf(resisterSet.get(3)) : "";
        r4 = resisterSet.existsValue(4) ? String.valueOf(resisterSet.get(4)) : "";
        r5 = resisterSet.existsValue(5) ? String.valueOf(resisterSet.get(5)) : "";
        r6 = resisterSet.existsValue(6) ? String.valueOf(resisterSet.get(6)) : "";
        r7 = resisterSet.existsValue(7) ? String.valueOf(resisterSet.get(7)) : "";

        return String.format(
            "+-CU-------+     +-PC--+ +-IR-------+%n" +
                "| %8s |     | %3d | | %8s |%n" +
                "| %8s |     +-----+ | %8s |%n" +
                "+----------+     +-R1--+ +----------+%n" +
                "                 | %3s |%n" +
                "+ALU+  +---+     +-R2--+-R3--+-R4--+%n" +
                "\\    \\/    /     | %3s | %3s | %3s |%n" +
                " \\        /      +-R5--+-R6--+-R7--+%n" +
                "  \\      /       | %3s | %3s | %3s |%n" +
                "   +----+        +-----+-----+-----+%n",
            cu1, PC.getCount(), ir1,
            cu2, ir2,
            r1,
            r2, r3, r4,
            r5, r6, r7
        );
    }
}
