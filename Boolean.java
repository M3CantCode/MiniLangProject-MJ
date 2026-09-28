public class Boolean extends Token {
    private String bool1;
    private String bool2;
    public Boolean(String type, String value) {
        super(type, value);
    }

    // operate():
    // - takes in a value and two booleans, returns the result of the operation
    // - value: the operator to be used
    // - bool1: the first boolean to be used in the operation
    // - bool2: the second boolean to be used in the operation
    public boolean operate(String value, String bool1, String bool2) {
        boolean b1 = java.lang.Boolean.parseBoolean(bool1);
        boolean b2 = java.lang.Boolean.parseBoolean(bool2);
        if (value.equals("&&")) {
            return b1 && b2;
        } else if (value.equals("||")) {
            return b1 || b2;
        } else if (value.equals("!")) {
            return !b1;
        }
        return false;
    }
    
}
