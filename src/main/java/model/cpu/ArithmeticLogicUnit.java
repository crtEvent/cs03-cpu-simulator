package model.cpu;

/**
 * Arithmetic and Logic Unit
 */
public class ArithmeticLogicUnit {

    private Integer operand1;
    private Integer operand2;

    public void set(Integer operand1, Integer operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Integer and() {
        return operand1 & operand2;
    };

    public Integer or() {
        return operand1 | operand2;
    };

    public Integer add() {
        return operand1 + operand2;
    };

    public Integer sub() {
        return operand1 - operand2;
    };
}
