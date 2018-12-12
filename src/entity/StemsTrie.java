package entity;

public class StemsTrie {
    private StemsTrieNode root;

    public StemsTrie(StemsTrieNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "StemsTrie{" +
                "root=" + root +
                '}';
    }

    public StemsTrieNode getRoot() {
        return root;
    }

    public void setRoot(StemsTrieNode root) {
        this.root = root;
    }
}
