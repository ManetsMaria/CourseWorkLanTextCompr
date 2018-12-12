package entity;

import stemmer.Stemmer;

import java.util.HashMap;
import java.util.Map;

public class StemSaver {
    private Map<String, Stemmer> stemSaver;

    public StemSaver(){
        stemSaver = new HashMap<>();
    }

    public Stemmer getStemmer(String languageName){
        return stemSaver.get(languageName);
    }

    public boolean addStemmer(String languageName, Stemmer stemmer){
        if(stemSaver.containsKey(languageName)){
            return false;
        }
        stemSaver.put(languageName, stemmer);
        return true;
    }
}
