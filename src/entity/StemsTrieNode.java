package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StemsTrieNode {
    private String value;
    private List<StemsTrieNode> children;

    public StemsTrieNode(String value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public boolean addChild(StemsTrieNode stemsTrieNode){
        return children.add(stemsTrieNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StemsTrieNode that = (StemsTrieNode) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }

    @Override
    public String toString() {
        return "StemsTrieNode{" +
                "value='" + value + '\'' +
                ", children=" + children +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<StemsTrieNode> getChildren() {
        return children;
    }
}

