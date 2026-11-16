public class TOKEN {
    private String name;
    private String value;

    // Constructors
    public TOKEN(String name) {
        this.name = name;
        this.value = "";
    }
    
    public TOKEN(String name, String value) {
        this.name = name;
        this.value = value;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}   