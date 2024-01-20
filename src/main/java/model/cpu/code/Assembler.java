package model.cpu.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Assembler {

    private static final Function<String, String> COMMAND_ERROR_MESSAGE = (String command)
        -> String.format("'%s'는 잘못된 명령어 입니다.", command);

    public List<Integer> assembly(List<String> instructions) {
        List<Integer> lines = new ArrayList<>(instructions.size());
        StringBuilder builder = new StringBuilder();

        for (String instruction : instructions) {
            String[] tokens = instruction.split(" ");

            if (tokens.length == 4) {
                switch (tokens[0]) {
                    case OpCode.LOAD -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = makeBinaryForOperand3(tokens[3]);
                        String opCode = operand3.charAt(0) == '0'
                            ? InstructionCode.LOAD_REG.getCode()
                            : InstructionCode.LOAD_VALUE.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    case OpCode.STORE -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = makeBinaryForOperand3(tokens[3]);
                        String opCode = operand3.charAt(0) == '0'
                            ? InstructionCode.STORE_REG.getCode()
                            : InstructionCode.STORE_VALUE.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    case OpCode.ADD -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = makeBinaryForOperand3(tokens[3]);
                        String opCode = operand3.charAt(0) == '0'
                            ? InstructionCode.ADD_REG.getCode()
                            : InstructionCode.ADD_VALUE.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    case OpCode.SUB -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = makeBinaryForOperand3(tokens[3]);
                        String opCode = operand3.charAt(0) == '0'
                            ? InstructionCode.SUB_REG.getCode()
                            : InstructionCode.SUB_VALUE.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    case OpCode.AND -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = "000" + ResisterCode.fromName(tokens[3]).getCode();
                        String opCode = InstructionCode.AND.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    case OpCode.OR -> {
                        String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                        String operand2 = ResisterCode.fromName(tokens[2]).getCode();
                        String operand3 = "000" + ResisterCode.fromName(tokens[3]).getCode();
                        String opCode = InstructionCode.OR.getCode();

                        int decimal = Integer.parseInt(opCode + operand1 + operand2 + operand3, 2);
                        lines.add(decimal);
                    }
                    default -> throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(instruction));
                }
            } else if (tokens.length == 3 && OpCode.MOV.equalsIgnoreCase(tokens[0])) {
                String operand1 = ResisterCode.fromName(tokens[1]).getCode();
                String operand2 = convertTo9DigitBinary(tokens[2]);
                String opCode = InstructionCode.MOV.getCode();

                int decimal = Integer.parseInt(opCode + operand1 + operand2, 2);
                lines.add(decimal);
            } else {
                throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(instruction));
            }
        }

        return lines;
    }

    private String makeBinaryForOperand2(String token) {
        try {
            return ResisterCode.fromName(token).getCode();
        } catch (Exception ignore) {
        }

        return convertTo9DigitBinary(token);
    }

    private String makeBinaryForOperand3(String token) {
        try {
            return String.format("000%s", ResisterCode.fromName(token).getCode());
        } catch (Exception ignore) {
        }

        return String.format("1%s", convertTo5DigitBinary(token));
    }

    private String convertTo5DigitBinary(String token) {
        if (token != null && !token.isEmpty() && token.charAt(0) == '#') {
            try {
                int number = Integer.parseInt(token.substring(1));
                String binary = Integer.toBinaryString(number);

                return String.format("%05d", Integer.parseInt(binary));
            } catch (NumberFormatException ignore) {
            }
        }

        throw new IllegalArgumentException("'" + token + "'은 올바른 토큰이 아닙니다.");
    }

    private String convertTo9DigitBinary(String token) {
        if (token != null && !token.isEmpty() && token.charAt(0) == '#') {
            try {
                int number = Integer.parseInt(token.substring(1));
                String binary = Integer.toBinaryString(number);

                return String.format("%09d", Integer.parseInt(binary));
            } catch (NumberFormatException ignore) {
            }
        }

        throw new IllegalArgumentException("'" + token + "'은 올바른 토큰이 아닙니다.");
    }
}
