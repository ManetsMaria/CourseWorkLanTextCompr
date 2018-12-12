package rule.imp;

import entity.StemsTrie;
import entity.StemsTrieNode;
import rule.WordCreatorRule;

import java.util.List;

public class WordCreatorRuleDefault implements WordCreatorRule {
    @Override
    public String generateNewWord(StemsTrie stemsTrie) {
        StringBuilder stringBuilder = new StringBuilder();
        dfsGenerateWord(stemsTrie.getRoot(), stringBuilder);
        return stringBuilder.toString();
    }

    private void dfsGenerateWord(StemsTrieNode stemsTrieNode, StringBuilder stringBuilder){
        stringBuilder.append(stemsTrieNode.getValue());
        List<StemsTrieNode> children = stemsTrieNode.getChildren();
        if (children == null){
            return;
        }
        for (StemsTrieNode s: children){
            dfsGenerateWord(s, stringBuilder);
        }
    }
}
