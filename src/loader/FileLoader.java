package loader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileLoader {

    private static FileLoader fileLoader;

    public static FileLoader getFileLoader() {
        if (fileLoader == null) {
            synchronized (FileLoader.class) {
                if (fileLoader == null)
                    fileLoader = new FileLoader();
            }
        }
        return fileLoader;
    }

    private FileLoader() {

    }

    public String load(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            String code = String.join("\n", lines);
            return code.replaceAll("[ \n\t]+", " ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
