import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Breaks the input source code into a list of tokens for parsing
public class Lexer {
    private final String source;
    private final List<TOKEN> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    // Key statements to look for
    private static final Map<String, TokenTypes> KEYWORDS = Map.of(
        "let", TokenTypes.LET,
        "display", TokenTypes.DISPLAY,
        "if", TokenTypes.IF,
        "then", TokenTypes.THEN,
        "else", TokenTypes.ELSE,
        "true", TokenTypes.BOOLEAN,
        "false", TokenTypes.BOOLEAN
    );

    //constructor
    public Lexer(String source) {
        this.source = source;
    }

    // Helper methods:

    // Checks if we've reached the end of the source code
    private boolean isAtEnd() { return current >= source.length(); }
    // Consumes the current character and returns it
    private char advance() { return source.charAt(current++); }
    // Peeks at the current character without consuming it
    private char peek() { return isAtEnd() ? '\0' : source.charAt(current); }
    // Peeks at the next character without consuming it
    private char peekNext() { return (current + 1 >= source.length()) ? '\0' : source.charAt(current + 1); }

    // Checks if the current character matches the next character and consumes it if it does
    // Used for multi-character operators like '==', '!=', '<=', '>='
    private boolean match(char expected) {
        if (isAtEnd() || source.charAt(current) != expected) return false;
        current++;
        return true;
    }

    // Checks if a character is a digit (0-9)
    private boolean isDigit(char c) { return c >= '0' && c <= '9'; }
    // Checks if a character is an alphabetic character (a-z, A-Z) or an underscore (_)
    private boolean isAlpha(char c) { return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_'; }
    // Checks if a character is alphanumeric (a-z, A-Z, 0-9) or an underscore (_)
    private boolean isAlphaNumeric(char c) { return isAlpha(c) || isDigit(c); }

    private void addToken(TokenTypes type) { addToken(type, null); }
    private void addToken(TokenTypes type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new TOKEN(type, text, literal, line));
    }

    // end of Helper methods

    // Scans the entire source code and returns a list of tokens
    public List<TOKEN> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new TOKEN(TokenTypes.EOF, "", null, line));
        return tokens;
    }

    // Scans a single token from the source code and adds it to the list of tokens
    private void scanToken() {
        char c = advance();
        switch (c) {
            // Single-character delimiters & arithmetic operators
            case '(' -> addToken(TokenTypes.LPAREN);
            case ')' -> addToken(TokenTypes.RPAREN);
            case ',' -> addToken(TokenTypes.COMMA);
            case ';' -> addToken(TokenTypes.SEMICOLON);
            case '+' -> addToken(TokenTypes.PLUS);
            case '-' -> addToken(TokenTypes.MINUS);
            case '*' -> addToken(TokenTypes.MULTIPLY);
            case '%' -> addToken(TokenTypes.MOD);
            case '^' -> addToken(TokenTypes.EXPONENT);

            // Multi-character operators
            case '=' -> addToken(match('=') ? TokenTypes.EQUALS : TokenTypes.ASSIGN);
            case '!' -> addToken(match('=') ? TokenTypes.NOT_EQUAL : TokenTypes.NOT);
            case '<' -> addToken(match('=') ? TokenTypes.LESS_EQUAL : TokenTypes.LESS);
            case '>' -> addToken(match('=') ? TokenTypes.GREATER_EQUAL : TokenTypes.GREATER);
            case '&' -> {
                if (match('&')) addToken(TokenTypes.AND);

                else throw new RuntimeException("Error (line " + line + "): Unexpected character '&'. Did you mean '&&'?");
            }
            case '|' -> {
                if (match('|')) addToken(TokenTypes.OR);
                else throw new RuntimeException("Error (line " + line + "): Unexpected character '|'. Did you mean '||'?");
            }

            // Division or single-line comment
            case '/' -> {
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(TokenTypes.DIVIDE);
                }
            }

            // Whitespace
            case ' ', '\r', '\t' -> {}
            case '\n' -> line++;

            // String literals
            case '"' -> scanString();

            default -> {
                if (isDigit(c)) {
                    scanNumber();
                } else if (isAlpha(c)) {
                    scanIdentifier();
                } else {
                    throw new RuntimeException("Error (line " + line + "): Invalid character '" + c + "'.");
                }
            }
        }
    }

    // Scans a number literal, which can be an integer or a floating-point number
    private void scanNumber() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            advance(); // consume '.'
            while (isDigit(peek())) advance();
        }
        String text = source.substring(start, current);
        addToken(TokenTypes.NUMBER, Double.parseDouble(text));
    }

    // Scans an identifier or a keyword and adds the appropriate token
    private void scanIdentifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        TokenTypes type = KEYWORDS.getOrDefault(text, TokenTypes.IDENTIFIER);
        if (type == TokenTypes.BOOLEAN) {
            addToken(type, Boolean.parseBoolean(text));
        } else {
            addToken(type);
        }
    }

    // Scans a string literal, handling escape sequences and unterminated strings
    private void scanString() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            throw new RuntimeException("Error (line " + line + "): Unterminated string literal.");
        }
        advance(); // closing '"'
        String value = source.substring(start + 1, current - 1);
        addToken(TokenTypes.STRING, value);
    }


}
