public class Token {
    private String name;
    private String value;

    // Constructors
    public Token(String name) {
        this.name = name;
        this.value = "";
    }
    
    public Token(String name, String value) {
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