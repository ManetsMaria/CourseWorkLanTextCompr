package logic;

import entity.Language;
import entity.StemSaver;
import stemmer.Stemmer;

import java.util.ArrayList;
import java.util.List;

public class StemMaker {
    private StemSaver stemSaver;

    public StemMaker(StemSaver stemSaver) {
        this.stemSaver = stemSaver;
    }

    public Language makeStem(Language language){
       Stemmer stemmer = loadStem(language.getLanguageName());
       if (stemmer == null){
           return language;
       }
       return stemmer.makeStem(language);
    }

    public List<Language> makeListStem(List<Language> languages){
        List<Language> languageAfterStemmingList = new ArrayList<>();
        for(Language l: languages){
            languageAfterStemmingList.add(makeStem(l));
        }
        return languageAfterStemmingList;
    }

    private Stemmer loadStem(String languageName){
        return stemSaver.getStemmer(languageName);
    }
}
