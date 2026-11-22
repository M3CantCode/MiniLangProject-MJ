
// Enum (alphabet) representing the different types of tokens in the language
public enum TokenTypes {
    // Keywords
    LET, DISPLAY, IF, THEN, ELSE,

    // Literals & Identifiers
    IDENTIFIER, NUMBER, STRING, BOOLEAN,

    // Arithmetic Operators
    PLUS, MINUS, MULTIPLY, DIVIDE, MOD, EXPONENT,

    // Relational & Logical Operators
    ASSIGN, EQUALS,
    NOT, NOT_EQUAL,
    LESS, LESS_EQUAL,
    GREATER, GREATER_EQUAL,
    AND, OR,

    // Delimiters
    LPAREN, RPAREN, COMMA, SEMICOLON,

    // End of File
    EOF
}
