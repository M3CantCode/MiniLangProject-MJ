import java.util.List;

public class STATEMENT {

    private final List<TOKEN> statements;
    private String type;

    public STATEMENT(String statementType, List<TOKEN> tokens) {
        this.statements = tokens;
        this.type = statementType;
    }

    public void setType(String s) {
        this.type = s;
    }

    @Override
    public String toString() {

        return "[PARSE]" + type;
    }

}
