package model.cpu.code;

import java.util.Objects;

public enum InstructionCode {
    LOAD_REG("0001", "LOAD"), LOAD_VALUE("0010", "LOAD"),
    STORE_REG("0011", "STORE"), STORE_VALUE("0100", "STORE"),
    AND("0101", "AND"), OR("0110", "OR"),
    ADD_REG("0111", "ADD"), ADD_VALUE("1000", "ADD"),
    SUB_REG("1001", "SUB"), SUB_VALUE("1010", "SUB"),
    MOV("1011", "MOV");

    private final String code;
    private final String type;

    InstructionCode(String code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public static InstructionCode fromCode(String code) {
        for (InstructionCode c : InstructionCode.values()) {
            if (Objects.equals(c.code, code)) {
                return c;
            }
        }
        throw new IllegalArgumentException("'" + code + "'는 잘못된 코드 입니다.");
    }
}
