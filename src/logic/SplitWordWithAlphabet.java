package logic;

import entity.Alphabet;

import java.util.ArrayList;
import java.util.List;

public class SplitWordWithAlphabet {
    static List<String> split(String word, Alphabet alphabet){
        List<String> letters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++){
            letters.add(String.valueOf(word.charAt(i)));
        }
        return letters;
    }
}
