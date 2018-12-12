package entity;

import java.util.HashMap;
import java.util.Map;

public class LanguageAlphabetSaver {
    private Map<String, Alphabet> languageAlphabetSaver;

    public LanguageAlphabetSaver(){
        languageAlphabetSaver = new HashMap<>();
    }

    public Alphabet getAlphabet(String languageName){
        return languageAlphabetSaver.get(languageName);
    }

    public boolean setAlphabet(String languageName, Alphabet alphabet){
        if (languageAlphabetSaver.containsKey(languageName)){
            return false;
        }
        languageAlphabetSaver.put(languageName, alphabet);
        return true;
    }
}
