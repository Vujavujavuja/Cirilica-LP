package lexer;

public class WordType {

    private final String wordTypeName;

    private final String regex;

    WordType(String wordTypeName, String regex) {
        this.wordTypeName = wordTypeName;
        this.regex = regex;
    }

    public String getWordTypeName() {
        return wordTypeName;
    }

    public String getRegex() {
        return regex;
    }
}
