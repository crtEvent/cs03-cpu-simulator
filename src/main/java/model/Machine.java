package model;

import model.cpu.ArithmeticLogicUnit;
import model.cpu.InstructionRegister;
import model.cpu.ProgramCounter;
import model.cpu.RegisterSet;
import model.memory.RandomAccessMemory;

public class Machine {
    private final ArithmeticLogicUnit ALU;
    private final ProgramCounter PC;
    private final InstructionRegister IR;
    private final RegisterSet resisterSet;
    private final RandomAccessMemory RAM;

    public Machine(ArithmeticLogicUnit ALU, ProgramCounter PC, InstructionRegister IR,
        RegisterSet resisterSet, RandomAccessMemory RAM) {
        this.ALU = ALU;
        this.PC = PC;
        this.IR = IR;
        this.resisterSet = resisterSet;
        this.RAM = RAM;
    }

    /**
     * 기억장치로 부터 명령어 읽어온다.
     * 읽어온 명령어는 CPU의 IR에 저장한다.
     * 명령어를 읽어온 후 Program Counter를 1 증가시킨다.
     */
    public void fetch() {
        int count = PC.getCount();
        int instruction = RAM.getInstruction(count);
        IR.setInstruction(instruction);
        PC.countUp();
    }

    /**
     * IR에 저장된 명령어를 CU에 전달한다.
     * CU는 전달 받은 명령어를 해석한다.
     * 해석된 명령어를 실행한다.
     * 산술 연산, 논리 연산은 ALU가 계산하여 결과를 반환해 준다.
     */
    public void execute() {

    }

    /**
     * Resister와 Program Counter 값을 모두 초기화 한다.
     */
    public void reset() {

    }

    /**
     * 현재 Resister 값들을 리턴한다.
     */
    public void dump() {

    }

    @Override
    public String toString() {
        String ir1, ir2;

        ir1 = (IR.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString((IR.getInstruction() >>> 8) & 0xFF)).replace(' ', '0')
            : "        ";
        ir2 = (IR.getInstruction() != null)
            ? String.format("%8s", Integer.toBinaryString(IR.getInstruction() & 0xFF)).replace(' ', '0')
            : "        ";

        return String.format(
            "+-CU-------+     +-PC--+ +-IR-------+%n" +
            "|          |     | %3d | | %8s |%n" +
            "|          |     +-----+ | %8s |%n" +
            "+----------+     +-R1--+ +----------+%n" +
            "                 |     |%n" +
            "+ALU+  +---+     +-R2--+-R3--+-R4--+%n" +
            "\\    \\/    /     |     |     |     |%n" +
            " \\        /      +-R5--+-R6--+-R7--+%n" +
            "  \\      /       |     |     |     |%n" +
            "   +----+        +-----+-----+-----+%n",
            PC.getCount(),
            ir1,
            ir2
        );
    }
}
