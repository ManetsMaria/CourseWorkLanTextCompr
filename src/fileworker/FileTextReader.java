package fileworker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTextReader {
    public String readText(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(Paths.get(file.getPath())).forEach((line) -> stringBuilder.append(line));
        return stringBuilder.toString();
    }
}
