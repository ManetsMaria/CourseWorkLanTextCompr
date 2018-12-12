package rule;

import entity.StemsTrie;

public interface WordCreatorRule {
    String generateNewWord(StemsTrie stemsTrie);
}
