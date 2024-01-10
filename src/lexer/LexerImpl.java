package lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LexerImpl {

    private static LexerImpl lexerImpl;

    public static LexerImpl getLexer() {
        if (lexerImpl == null) {
            synchronized (LexerImpl.class) {
                if (lexerImpl == null)
                    lexerImpl = new LexerImpl();
            }
        }
        return lexerImpl;
    }

    public List<Word> lex(String code) {
        List<Word> words = new ArrayList<>();
        CodeSpliter codeSpliter = new CodeSpliter(code);
        String[] codeSplitted = codeSpliter.getCodeSplitted().toArray(new String[0]);

        for (String word: codeSplitted) {
            if(word.equals("\"") || word.equals("\'"))
                continue;
            //System.out.println(word);
            WordType wordType = null;
            for (Map.Entry<String, WordType> wt : WordTypes.REGEXES.entrySet()) {
                if (word.matches(wt.getKey())) {
                    wordType = wt.getValue();
                    break;
                }
            }
            if (wordType == null)
                throw new LexerException(word);
            Word w = new Word(word, wordType);
            words.add(w);
        }
        return words;
    }
    private LexerImpl() {
    }
}
