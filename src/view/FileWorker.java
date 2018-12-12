package view;

import coefficient.CompressRule;
import compress.lz.LZ77;
import compress.lz.LZ78;
import compress.lz.LZW;
import entity.Language;
import fileworker.FileDictionaryReader;
import fileworker.FileTextReader;
import logic.LanguageCreator;
import logic.StemMaker;
import rule.WordCreatorRule;
import rule.imp.WordCreatorWordLengthDegree;
import shema.TextShemaGenerator;
import shema.impl.DefaultTextShemaGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class FileWorker implements ActionListener {

    private MainFrame mainFrame;

    public FileWorker(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            JMenuItem menuItem = (JMenuItem) actionEvent.getSource();
            String menuItemType = menuItem.getText();
            switch (menuItemType){
                case "Add language":
                    addLanguage();
                    break;
                case "language":
                    generateLanguage();
                    break;
                case "schema":
                    generateShema();
                    break;
                case "text":
                    generateText();
                    break;
                case "Compress":
                    compress();
                    break;
                default:
                    break;
            }
        }
        catch (ClassCastException e) {
            Logger.getLogger(MainFrame.class.getName()).log(new LogRecord(Level.WARNING, "Unknow type"));
        }
    }

    private void addLanguage(){
        String input = JOptionPane.showInputDialog(mainFrame, "Input language name");
        if (input == null)
            return;
        Scanner scanner = new Scanner(input);
        try {
            String name = scanner.next();
            JFileChooser fileOpen = new JFileChooser();
            TextFileFilter textFileFilter = new TextFileFilter();
            fileOpen.setFileFilter(textFileFilter);
            int ret = fileOpen.showDialog(null, "Choose dictionary");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileOpen.getSelectedFile();
                textFileFilter.exceptionChecker(file);
                FileDictionaryReader fileDictionaryReader = new FileDictionaryReader();
                Map<Integer, String> dict = fileDictionaryReader.readLanguageFromFile(file);
                Language language = new Language(name);
                language.setDictionary(dict);
                if (mainFrame.getStemSaver().getStemmer(input) != null){
                    language = mainFrame.getStemSaver().getStemmer(input).makeStem(language);
                }
                mainFrame.getLanguageSaver().setLanguage(name, language);
                mainFrame.addLanguage(language.toString());
            }
        }catch (NoSuchElementException | IOException | NotTextFileException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     private void generateLanguage(){
         String input = JOptionPane.showInputDialog(mainFrame, "Input languages name");
         String inputMainLan = JOptionPane.showInputDialog(mainFrame, "Input main language name");
         if (input == null || inputMainLan == null)
             return;
         Scanner scanner = new Scanner(input);
         List<String> languages = new ArrayList<>();
         while(scanner.hasNext()){
             languages.add(scanner.next());
         }
         WordCreatorRule wordCreatorRule = new WordCreatorWordLengthDegree();
         StemMaker stemMaker = new StemMaker(mainFrame.getStemSaver());
         LanguageCreator languageCreator = new LanguageCreator(stemMaker, mainFrame.getLanguageSaver(), wordCreatorRule);
         Language newLan = languageCreator.createNewLanguage(languages, inputMainLan);
         mainFrame.getLanguageSaver().setLanguage(newLan.getLanguageName(), newLan);
         mainFrame.addLanguage(newLan.toString());
     }

     private void generateShema(){
         String input = JOptionPane.showInputDialog(mainFrame, "Input language name");
         if (input == null)
             return;
         input = input.trim();
         try {
             JFileChooser fileOpen = new JFileChooser();
             TextFileFilter textFileFilter = new TextFileFilter();
             fileOpen.setFileFilter(textFileFilter);
             int ret = fileOpen.showDialog(null, "Choose text");
             if (ret == JFileChooser.APPROVE_OPTION) {
                 File file = fileOpen.getSelectedFile();
                 textFileFilter.exceptionChecker(file);
                 FileTextReader fileTextReader = new FileTextReader();
                 String text = fileTextReader.readText(file);
                 TextShemaGenerator textShemaGenerator = new DefaultTextShemaGenerator();
                 String schema = textShemaGenerator.generateShemaWithText(text, mainFrame.getLanguageSaver().getLanguage(input), mainFrame.getStemSaver().getStemmer(input));
                 mainFrame.setSchema(schema);
             }
         }catch (NoSuchElementException | IOException | NotTextFileException e){
             JOptionPane.showMessageDialog(null, e);
         }
     }

     private void generateText(){
        String input = JOptionPane.showInputDialog(mainFrame, "Input language name");
        if (input == null)
             return;
        input = input.trim();
        Language language = mainFrame.getLanguageSaver().getLanguage(input);
        String schema = mainFrame.getSchema();
        TextShemaGenerator textShemaGenerator = new DefaultTextShemaGenerator();
        String textGen = textShemaGenerator.generateTextWithShema(schema, language);
        mainFrame.addText(textGen);
     }

     private void compress(){
        String text = mainFrame.getText();
        /*String numb = text.length()+" -> "+'\n';
         LZW lzw = new LZW();
         LZ77 lz77 = new LZ77();
         LZ78 lz78 = new LZ78();
         numb += lzw.compress(text).length() +" "+lzw.compress(text)+'\n';
         String result = lz77.compress(text);
         numb += result.length() +" "+result+'\n';
         result = lz78.compress(text);
         numb += result.length() +" "+result;*/
         CompressRule compressRule = new CompressRule();
        StringBuilder numb = new StringBuilder();
        numb.append(compressRule.RLE(text));
        numb.append('\n');
         numb.append(compressRule.LZW(text));
         numb.append('\n');
         numb.append(compressRule.LZ77(text));
         numb.append('\n');
         numb.append(compressRule.LZ78(text));
         numb.append('\n');
         numb.append(compressRule.RLE_LZ77(text));
         numb.append('\n');
         numb.append(compressRule.RLE_LZ78(text));
         numb.append('\n');
         numb.append(compressRule.RLE_LZW(text));
         numb.append('\n');
         mainFrame.setCompressResult(numb.toString());

     }
}