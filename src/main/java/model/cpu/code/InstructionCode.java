package model.cpu.code;

public enum InstructionCode {
    LOAD_REG("0001"), LOAD_VALUE("0010"),
    STORE_REG("0011"), STORE_VALUE("0100"),
    AND("0101"), OR("0110"),
    ADD_REG("0111"), ADD_VALUE("1000"),
    SUB_REG("1001"), SUB_VALUE("1010"),
    MOV("1011");

    private final String code;

    InstructionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
