package shema;

import entity.Language;
import stemmer.Stemmer;

public interface TextShemaGenerator {
    String generateShemaWithText(String text, Language language, Stemmer stemmer);
    String generateShemaWithText(String text, Language language);
    String generateTextWithShema(String schema, Language language);
}
