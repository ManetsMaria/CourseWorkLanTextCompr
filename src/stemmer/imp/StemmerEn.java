package stemmer.imp;

import entity.Language;
import stemmer.Stemmer;

import java.util.Map;

public class StemmerEn implements Stemmer {
    @Override
    public Language makeStem(Language language) {
        Map<Integer, String> d = language.getDictionary();
        for(Map.Entry<Integer, String> e: d.entrySet()){
            d.put(e.getKey(), stemWord(e.getValue()));
        }
        language.setDictionary(d);
        return language;
    }

    @Override
    public String stemWord(String word) {
       AbStemmer abStemmer = new AbStemmer();
       return abStemmer.stem(word);
    }
}
