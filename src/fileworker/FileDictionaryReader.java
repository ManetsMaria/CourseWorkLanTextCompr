package fileworker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileDictionaryReader {
    public Map<Integer,String> readLanguageFromFile(File file)throws IOException {
        Map<Integer, String> dictionary = new HashMap<>();
        Files.lines(Paths.get(file.getPath())).forEach((line) -> addWord(line,dictionary));
        return dictionary;
    }

    private void addWord(String line, Map<Integer, String> dict){
        line = line.trim();
        line = line.replace("\uFEFF", "");
        String[] word = line.split("#");
        dict.put(new Integer(word[0].trim()), word[1].trim());
    }
}
