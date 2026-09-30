public class Variable extends Token {

    private int intVal;
    private double doubVal;
    private char charVal;

    // Constructor
    public Variable(String name, String value) {
        super(name, value);
    }

    // Getters and Setters
    public String getName() {
        return super.getName();
    }

    public String getValue() {
        return super.getValue();
    }

    public void setNewName(String name) {
        super.setName(name);
    }

    public void setIntVal(int i) {
        intVal = i;
    }

    public void setDoubVal(double d) {
        doubVal = d;
    }

    public void setCharVal(char c) {
        charVal = c;
    }
    
}
