package shema.impl;

import entity.Language;
import shema.TextShemaGenerator;
import stemmer.Stemmer;

import java.util.*;

public class DefaultTextShemaGenerator implements TextShemaGenerator {

    @Override
    public String generateShemaWithText(String text, Language language, Stemmer stemmer) {
        String[] textWord = separateText(text);
        textWord = standartWords(textWord, stemmer);
        Map<String, Integer> reverse = makeReverse(textWord, language);
        return generateSchema(reverse, textWord);
    }

    @Override
    public String generateShemaWithText(String text, Language language) {
        String[] textWord = separateText(text);
        Map<String, Integer> reverse = makeReverse(textWord, language);
        return generateSchema(reverse, textWord);
    }

    @Override
    public String generateTextWithShema(String schema, Language language) {
        StringBuilder text = new StringBuilder();
        String[] words = separateText(schema);
        for(String s: words){
            int number = Integer.valueOf(s);
            String word = language.getWord(number);
            if (word != null) {
                text.append(word);
                text.append(" ");
            }
        }
        text.deleteCharAt(text.length() - 1);
        return text.toString();
    }

    private String[] separateText(String text){
      String[] separate = text.split("[\\p{P} \\t\\n\\r]");
      return separate;
    }

    private String[] standartWords(String[] words, Stemmer stemmer){
        String[] standart = new String[words.length];
        int i =0;
        for (String w: words){
            w = w.toLowerCase();
            w = stemmer.stemWord(w);
            standart[i]= w;
            i++;
        }
        return standart;
    }

    private String generateSchema(Map<String, Integer> reverse, String[] words){
        StringBuilder stringBuilder = new StringBuilder();
        for (String str: words){
            String lower = str.toLowerCase();
            Integer number = reverse.get(lower);
            if(number != null){
                stringBuilder.append(number);
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    private Map<String, Integer> makeReverse(String[] words, Language language){
        Set<String> wordsSet = new TreeSet();
        Collections.addAll(wordsSet, words);
        Map <String, Integer> reverse = new HashMap();
        Map<Integer, String> dictionary = language.getDictionary();
        for (Map.Entry<Integer, String> entry: dictionary.entrySet()){
            if (wordsSet.contains(entry.getValue())) {
                reverse.put(entry.getValue(), entry.getKey());
            }
        }
        return reverse;
    }
}
