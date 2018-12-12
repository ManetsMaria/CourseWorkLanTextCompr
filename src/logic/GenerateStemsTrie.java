package logic;

import entity.Alphabet;
import entity.StemsTrie;
import entity.StemsTrieNode;

import java.util.List;

public class GenerateStemsTrie {
    private Alphabet alphabet;

    public GenerateStemsTrie(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public StemsTrie generateStemsTrie(List<String> stems){
        String rootValue = "";
        StemsTrieNode root = new StemsTrieNode(rootValue);
        StemsTrie stemsTrie = new StemsTrie(root);
        for (String stem: stems){
            addStemToTrie(stemsTrie, stem);
        }
        return stemsTrie;
    }

    private void addStemToTrie(StemsTrie stemsTrie, String stem){
        List<String> letters = SplitWordWithAlphabet.split(stem, alphabet);
        inverse(letters);
        StemsTrieNode root = stemsTrie.getRoot();
        fill(root, letters);
    }

    private void inverse(List<String> list){
        for (int i = 0; i < list.size()/2; i++){
            String temp = list.get(i);
            list.set(i, list.get(list.size() - 1 - i));
            list.set(list.size() - 1 - i, temp);
        }
    }

    private void fill(StemsTrieNode stemsTrieNode, List<String> letters){
        if (letters == null || letters.isEmpty()){
            return;
        }
        List<StemsTrieNode> children = stemsTrieNode.getChildren();
        for (StemsTrieNode node: children){
            if (node.getValue().equals(letters.get(letters.size() - 1))){
                letters.remove(letters.size() - 1);
                fill(node, letters);
                return;
            }
        }
        StemsTrieNode newNode = new StemsTrieNode(letters.get(letters.size() - 1));
        stemsTrieNode.addChild(newNode);
        letters.remove(letters.size() - 1);
        fill(newNode, letters);
    }
}
