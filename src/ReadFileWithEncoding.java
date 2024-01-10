import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileWithEncoding {
    public static void main(String[] args) {
        String path = "ProgramskiPrevodioci1/src/codes/code1.vuja";
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = contentBuilder.toString();
        System.out.println(content);
    }
}
