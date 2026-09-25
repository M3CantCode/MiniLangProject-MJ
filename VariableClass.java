public class VariableClass {

    private String varName = "";
    private int intVal;
    private double doubVal;
    private char charVal;


    public VariableClass(String name) {
        //
        varName = name;
    }

    public String getName() {
        return varName;
    }

    public void setNewName(String name) {
        varName = name;
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
