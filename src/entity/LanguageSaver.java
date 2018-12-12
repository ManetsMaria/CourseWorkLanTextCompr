package entity;

import java.util.HashMap;
import java.util.Map;

public class LanguageSaver {
    private Map<String, Language>  languageSaver;

    public LanguageSaver(){
        languageSaver = new HashMap<>();
    }

    public Language getLanguage(String languageName){
        return languageSaver.get(languageName);
    }

    public boolean setLanguage(String languageName, Language language){
        if (languageSaver.containsKey(languageName)){
            return false;
        }
        languageSaver.put(languageName, language);
        return true;
    }
}
