package initialize;

import compress.Compress;
import compress.rle.RLE;
import entity.Language;
import entity.LanguageSaver;
import entity.StemSaver;
import logic.LanguageCreator;
import logic.StemMaker;
import rule.WordCreatorRule;
import rule.imp.WordCreatorWordLengthDegree;
import shema.TextShemaGenerator;
import shema.impl.DefaultTextShemaGenerator;
import stemmer.imp.StemmerEn;
import stemmer.imp.StemmerRu;
import view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args){
        /*Language en = new Language("en");
        Language ru = new Language("ru");
        List<String> languages = new ArrayList<>();
        languages.add("en");
        languages.add("ru");
        en.addNewWord(1, "oooone");
        ru.addNewWord(1, "один");
        en.addNewWord(2, "two");
        ru.addNewWord(2, "два");
        en.addNewWord(3, "penut");
        ru.addNewWord(3, "penit");
        StemSaver stemSaver = new StemSaver();
        stemSaver.addStemmer("en", new StemmerEn());
        stemSaver.addStemmer("ru", new StemmerRu());
        LanguageSaver languageSaver = new LanguageSaver();
        languageSaver.setLanguage("en", en);
        languageSaver.setLanguage("ru", ru);
        WordCreatorRule wordCreatorRule = new WordCreatorWordLengthDegree();
        StemMaker stemMaker = new StemMaker(stemSaver);
        LanguageCreator languageCreator = new LanguageCreator(stemMaker ,languageSaver, wordCreatorRule);
        Language newLan = languageCreator.createNewLanguage(languages, "en");
        System.out.println(newLan);
        String text = "oooone two penut oooone, oooone. Penut! oooone - two me.";
        TextShemaGenerator textShemaGenerator = new DefaultTextShemaGenerator();
        String schema = textShemaGenerator.generateShemaWithText(text, en);
        System.out.println(schema);
        String textGen = textShemaGenerator.generateTextWithShema(schema, newLan);
        System.out.println(textGen);
        Compress compress = new RLE();
        String textRLECompress = compress.compress(textGen);
        System.out.println(textRLECompress);*/
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
