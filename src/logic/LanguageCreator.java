package logic;

import entity.Language;
import entity.LanguageSaver;
import entity.StemsTrie;
import rule.WordCreatorRule;

import java.util.*;

public class LanguageCreator {
    private StemMaker stemMaker;
    private LanguageSaver languageSaver;
    private WordCreatorRule wordCreatorRule;

    public LanguageCreator(StemMaker stemMaker, LanguageSaver languageSaver, WordCreatorRule wordCreatorRule) {
        this.stemMaker = stemMaker;
        this.languageSaver = languageSaver;
        this.wordCreatorRule = wordCreatorRule;
    }

    public Language createNewLanguage(List<String> languages, String mainLanguage){
        Language language = new Language(generateLanguageName(languages));
        List<Language> languageList = loadLanguages(languages);
        List<Language> languageAfterStemmingList = stemMaker.makeListStem(languageList);
        Language main = languageSaver.getLanguage(mainLanguage);
        language.setDictionary(generateDictionary(languageAfterStemmingList, main.getDictionary().keySet()));
        return language;
    }

    private List<Language> loadLanguages(List<String> languages){
        List<Language> languageList = new ArrayList<>();
        for(String l:languages){
            languageList.add(languageSaver.getLanguage(l));
        }
        return languageList;
    }

    private String generateLanguageName(List<String> languages){
        StringBuilder name = new StringBuilder("");
        for (String lan: languages){
            name.append(lan);
        }
        return  name.toString();
    }

    private Map<Integer, String> generateDictionary(List<Language> languages, Set<Integer> base){
        Map<Integer, String> dictionary = new HashMap<>();
        for(Integer i: base){
            List<String> stems = new ArrayList<>();
            for(Language l: languages){
                String word = l.getWord(i);
                if (word != null){
                    stems.add(word);
                }
            }
            dictionary.put(i, generateNewWord(stems));
        }
        return dictionary;
    }

    private String generateNewWord(List<String> stems){
        GenerateStemsTrie generateStemsTrie = new GenerateStemsTrie(null);
        StemsTrie stemsTrie = generateStemsTrie.generateStemsTrie(stems);
        return wordCreatorRule.generateNewWord(stemsTrie);
    }
}
