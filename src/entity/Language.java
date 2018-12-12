package entity;

import java.util.HashMap;
import java.util.Map;

public class Language {
    private Map<Integer, String> dictionary;
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
        dictionary = new HashMap<>();
    }

    public boolean addNewWord(int order, String word){
        if (dictionary.containsKey(order)){
            return false;
        }
        dictionary.put(order, word);
        return true;
    }

    public String getLanguageName() {
        return languageName;
    }

    public Map<Integer, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Integer, String> dictionary) {
        this.dictionary = dictionary;
    }

    public String getWord(Integer key){
        return dictionary.get(key);
    }

    @Override
    public String toString() {
        return "Language{" +
                "dictionary=" + dictionary +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
