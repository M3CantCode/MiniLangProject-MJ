public class Operator extends Token {
    private String num1;
    private String num2;
    // Constructor
    public Operator(String type) {
        super(type);
    }

    // operate():
    // - takes in a numeric operator and two numbers, returns the result of the operation
    // - type: the operator to be used
    // - num1: the first number to be used in the operation
    // - num2: the second number to be used in the operation
    public int operate(String type, String num1, String num2) {
        int numerator1 = Integer.parseInt(num1);
        int numerator2 = Integer.parseInt(num2);
        if (type.equals("+")) {
            return numerator1 + numerator2;
        } else if (type.equals("-")) {
            return numerator1 - numerator2;
        } else if (type.equals("*")) {
            return numerator1 * numerator2  ;
        } else if (type.equals("/")) {
            return numerator1 / numerator2;
        } else if (type.equals("%")) {
            return numerator1 % numerator2;
        }
        return 0;
    }

    // compare():
    // - takes in a comparison operator and two numbers, returns the result of the comparison
    public boolean compare(String type, String num1, String num2) {
        int numerator1 = Integer.parseInt(num1);
        int numerator2 = Integer.parseInt(num2);
        if (type.equals("==")) {
            return numerator1 == numerator2;
        } else if (type.equals("!=")) {
            return numerator1 != numerator2;
        } else if (type.equals(">")) {
            return numerator1 > numerator2;
        } else if (type.equals("<")) {
            return numerator1 < numerator2;
        } else if (type.equals(">=")) {
            return numerator1 >= numerator2;
        } else if (type.equals("<=")) {
            return numerator1 <= numerator2;
        }
        return false;
    }
    public String getType() {
        return super.getName();
    }
}   
