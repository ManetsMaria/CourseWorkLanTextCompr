package rule.imp;

import entity.StemsTrie;
import entity.StemsTrieNode;
import rule.WordCreatorRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCreatorWordLengthDegree implements WordCreatorRule{
    public final double DEGREE_LENGTH = 0.15;
    private Map<StemsTrieNode, Integer> counterSaver;

    public WordCreatorWordLengthDegree(){
        counterSaver = new HashMap<>();
        counterSaver.put(null, 0);
    }

    @Override
    public String generateNewWord(StemsTrie stemsTrie) {
        dfsCounter(stemsTrie.getRoot());
        int commonCounter = counterSaver.get(stemsTrie.getRoot());
        double border = commonCounter*DEGREE_LENGTH;
        return generateWord(stemsTrie.getRoot(), border);
    }

    private int dfsCounter(StemsTrieNode stemsTrieNode){
        if (counterSaver.containsKey(stemsTrieNode)){
            return counterSaver.get(stemsTrieNode);
        }
        List<StemsTrieNode> children = stemsTrieNode.getChildren();
        int counter = 0;
        for (StemsTrieNode s: children){
            counter += dfsCounter(s) + 1;
        }
        counterSaver.put(stemsTrieNode, counter);
        return counter;
    }

    private String generateWord(StemsTrieNode stemsTrieNode, double border){
        if (counterSaver.get(stemsTrieNode) < border){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(stemsTrieNode.getValue());
        List<StemsTrieNode> children = stemsTrieNode.getChildren();
        for (StemsTrieNode s: children){
            stringBuilder.append(generateWord(s, border));
        }
        return stringBuilder.toString();
    }
}
