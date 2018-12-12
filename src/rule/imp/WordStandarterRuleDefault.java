package rule.imp;

import rule.WordStandarterRule;
import stemmer.Stemmer;

public class WordStandarterRuleDefault implements WordStandarterRule {
    Stemmer stemmer;

    public WordStandarterRuleDefault(Stemmer stemmer) {
        this.stemmer = stemmer;
    }

    @Override
    public String standartWord(String word) {
        return word.toLowerCase();
    }
}
