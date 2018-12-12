package stemmer;

import entity.Language;

public interface Stemmer {
    Language makeStem(Language language);
    String stemWord(String word);
}
