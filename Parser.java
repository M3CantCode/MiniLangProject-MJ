import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {

    private final List<TOKEN> tokens;
    private int current = 0;            //what line currently on

    //constructor
    public Parser(List<TOKEN> tokens) {
        this.tokens = tokens;
    }

    public List<STATEMENT> parse() {
        List<STATEMENT> statList = new ArrayList<>();

        int start = tokens.getFirst().getLine()-1;

        for (TOKEN t : tokens) {
            if (start != (t.getLine()-1)) {
                statList.add(new STATEMENT(tokens.get(start).toString(), tokens.subList(start, t.getLine())));
                start = t.getLine()-1;
            }

        }
        return statList;
    }

/*    public List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(statement());
        }
        return statements;
    }*/

    private boolean isAtEnd() { return current >= tokens.size(); }

    private String typeOfStatement() {
        return "";
    }
}
