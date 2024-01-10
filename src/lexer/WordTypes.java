package lexer;

import java.util.LinkedHashMap;
import java.util.Map;

public class WordTypes {
    public static final Map<String, WordType> REGEXES = new LinkedHashMap<>();

    private static void putWordTypeToMap(WordType word) {
        REGEXES.put(word.getRegex(), word);
    }

    public static final WordType MAIN_WORD_BEGIN
            = new WordType("main_begin", "почетак");
    public static final WordType MAIN_WORD_END
            = new WordType("main_end", "крај");

    public static final WordType ASSIGN_WORD
            = new WordType("assign", "је|je");

    public static final WordType NAME_WORD
            = new WordType("name", "[a-zA-ZА-Яа-яЁё_][a-zA-Z0-9А-Яа-яЁё_]*");

    public static final WordType IF_WORD
            = new WordType("if", "ако");
    public static final WordType ELIF_WORD
            = new WordType("elif", "иначе ако");
    public static final WordType ELSE_WORD
            = new WordType("else", "иначе");

    public static final WordType SCAN_WORD
            = new WordType("scan", "прочитај");
    public static final WordType SCAN_WORD_DEST
            = new WordType("scanDest", "и унеси у");
    public static final WordType PRINT_WORD
            = new WordType("print", "пиши");

    public static final WordType INT_TYPE_WORD
            = new WordType("int", "цели број");
    public static final WordType DOUBLE_TYPE_WORD
            = new WordType("double", "реалан број");
    public static final WordType CHAR_TYPE_WORD
            = new WordType("char", "слово");
    public static final WordType STRING_TYPE_WORD
            = new WordType("string", "текст");
    public static final WordType ARRAY_TYPE_WORD
            = new WordType("array", "низ");

    private static final WordType BEGINNING_OF_AN_INT_WORD
            = new WordType("int-first-digit", "[1-9]");

    public static final WordType INT_WORD
            = new WordType("int_word", BEGINNING_OF_AN_INT_WORD.getRegex() + "[0-9]*|0");
    public static final WordType DOUBLE_WORD
            = new WordType("double_word", "(0|[1-9][0-9]*)\\.[0-9]*[1-9]");
    public static final WordType CHAR_WORD
            = new WordType("char_word", "\\'.\\'");
    public static final WordType STRING_WORD
            = new WordType("string_word", "\\\".*\\\"");

    public static final WordType PLUS_WORD
            = new WordType("plus", "плус");
    public static final WordType MINUS_WORD
            = new WordType("minus", "минус");
    public static final WordType TIMES_WORD
            = new WordType("times", "пута");
    public static final WordType DIV_WORD
            = new WordType("div", "подели");
    public static final WordType MOD_WORD
            = new WordType("mod", "остатак");

    public static final WordType EQUALS_SIGN
            = new WordType("equals", "једнак");
    public static final WordType NOT_EQUALS_SIGN
            = new WordType("not_equals", "неједнак");
    public static final WordType LESS_THAN_SIGN
            = new WordType("less", "мањи");
    public static final WordType LESS_OR_EQUALS_SIGN
            = new WordType("less_or_equals", "мањи једнак");
    public static final WordType GREATER_THAN_SIGN
            = new WordType("greater", "већи");
    public static final WordType GREATER_OR_EQUALS_SIGN
            = new WordType("greater_or_equals", "већи једнак");


    public static final WordType LOGICAL_AND_WORD
            = new WordType("logand", "ии");
    public static final WordType LOGICAL_OR_WORD
            = new WordType("logor", "или");
    public static final WordType LOGICAL_NOT_WORD
            = new WordType("lognot", "не");

    public static final WordType OPEN_CURLY_BRACKET_WORD
            = new WordType("open_curly_bracket", "\\{");
    public static final WordType CLOSED_CURLY_BRACKET_WORD
            = new WordType("closed_curly_bracket", "\\}");
    public static final WordType OPEN_BRACKET_WORD
            = new WordType("open_bracket", "\\(");
    public static final WordType CLOSED_BRACKET_WORD
            = new WordType("closed_bracket", "\\)");
    public static final WordType OPEN_SQUARE_BRACKET_WORD
            = new WordType("open_square_bracket", "\\[");
    public static final WordType CLOSED_SQUARE_BRACKET_WORD
            = new WordType("closed_square_bracket", "\\]");

    public static final WordType FUNCTION_WORD
            = new WordType("function", "функција");
    public static final WordType RETURN_WORD
            = new WordType("return", "врати");


    public static final WordType FOR_WORD
            = new WordType("for", "обрни за");
    public static final WordType WHILE_WORD
            = new WordType("while", "заврни док");

    public static final WordType COMMAND_END_WORD
            = new WordType("command_end", ";");

    public static final WordType COMMA_WORD
            = new WordType("comma", ",");


    static {
        putWordTypeToMap(MAIN_WORD_BEGIN);
        putWordTypeToMap(MAIN_WORD_END);

        putWordTypeToMap(IF_WORD);
        putWordTypeToMap(ELIF_WORD);
        putWordTypeToMap(ELSE_WORD);

        putWordTypeToMap(SCAN_WORD);
        putWordTypeToMap(SCAN_WORD_DEST);
        putWordTypeToMap(PRINT_WORD);

        putWordTypeToMap(INT_TYPE_WORD);
        putWordTypeToMap(DOUBLE_TYPE_WORD);
        putWordTypeToMap(DOUBLE_WORD);
        putWordTypeToMap(CHAR_TYPE_WORD);
        putWordTypeToMap(STRING_TYPE_WORD);
        putWordTypeToMap(ARRAY_TYPE_WORD);

        putWordTypeToMap(OPEN_CURLY_BRACKET_WORD);
        putWordTypeToMap(CLOSED_CURLY_BRACKET_WORD);
        putWordTypeToMap(OPEN_BRACKET_WORD);
        putWordTypeToMap(CLOSED_BRACKET_WORD);
        putWordTypeToMap(OPEN_SQUARE_BRACKET_WORD);
        putWordTypeToMap(CLOSED_SQUARE_BRACKET_WORD);

        putWordTypeToMap(PLUS_WORD);
        putWordTypeToMap(MINUS_WORD);
        putWordTypeToMap(TIMES_WORD);
        putWordTypeToMap(DIV_WORD);
        putWordTypeToMap(MOD_WORD);

        putWordTypeToMap(ASSIGN_WORD);

        putWordTypeToMap(EQUALS_SIGN);
        putWordTypeToMap(NOT_EQUALS_SIGN);
        putWordTypeToMap(LESS_THAN_SIGN);
        putWordTypeToMap(LESS_OR_EQUALS_SIGN);
        putWordTypeToMap(GREATER_THAN_SIGN);
        putWordTypeToMap(GREATER_OR_EQUALS_SIGN);

        putWordTypeToMap(LOGICAL_AND_WORD);
        putWordTypeToMap(LOGICAL_OR_WORD);
        putWordTypeToMap(LOGICAL_NOT_WORD);

        putWordTypeToMap(STRING_WORD);
        putWordTypeToMap(CHAR_WORD);
        putWordTypeToMap(INT_WORD);

        putWordTypeToMap(NAME_WORD);

        putWordTypeToMap(FOR_WORD);
        putWordTypeToMap(WHILE_WORD);

        putWordTypeToMap(FUNCTION_WORD);
        putWordTypeToMap(RETURN_WORD);

        putWordTypeToMap(COMMAND_END_WORD);
        putWordTypeToMap(COMMA_WORD);
    }
    private WordTypes() {

    }
}
