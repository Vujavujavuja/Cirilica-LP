package lexer;

public class Word {
    private final String word;

    private final WordType wordType;

    public Word(String word, WordType wordType) {
        this.word = word;
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return "Word: " + word + " Word type: " + wordType.getWordTypeName();
    }

    public String getWord() {
        return word;
    }

    public WordType getWordType() {
        return wordType;
    }
}
