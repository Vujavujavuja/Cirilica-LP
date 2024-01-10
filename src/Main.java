import lexer.LexerImpl;
import lexer.Word;
import loader.FileLoader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String code1 = FileLoader.getFileLoader().load("ProgramskiPrevodioci1/src/codes/code3.vuja");


        List<Word> words;
        words = LexerImpl.getLexer().lex(code1);
        for (Word w : words)
            System.out.println(w);
    }
}