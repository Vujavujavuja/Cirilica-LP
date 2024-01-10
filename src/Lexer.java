import lexer.LexerImpl;
import lexer.Word;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Lexer {
    private List<Word> lexedCode;
    private StringBuilder lexedCodeString;

    public Lexer(String code) {
        this.lexedCodeString = new StringBuilder();
        this.lexedCode = LexerImpl.getLexer().lex(code);
        for (Word w : lexedCode) {
            this.lexedCodeString.append(w.getWordType().getWordTypeName() + "->" + w.getWord());
            this.lexedCodeString.append("\n");
        }
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        StringBuilder inputCode = new StringBuilder();

        while (scanner.hasNextLine()) {
            inputCode.append(scanner.nextLine());
            inputCode.append("\n");
        }
        scanner.close();

        Lexer lexer = new Lexer(inputCode.toString());
        String lexedCodeString = lexer.getLexedCodeString();

        System.out.println(lexedCodeString);
    }

    public String getLexedCodeString() {
        return lexedCodeString.toString();
    }
}
