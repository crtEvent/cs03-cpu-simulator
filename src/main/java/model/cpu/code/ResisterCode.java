package model.cpu.code;

public enum ResisterCode {
    R1("001"), R2("010"), R3("011"), R4("100"), R5("101"), R6("110"), R7("111");

    private final String code;

    ResisterCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ResisterCode fromName(String name) {
        for (ResisterCode resisters : ResisterCode.values()) {
            if (resisters.name().equalsIgnoreCase(name)) {
                return resisters;
            }
        }

        throw new IllegalArgumentException("'" + name + "'는 잘못된 레지스터 이름 입니다.");
    }

}
