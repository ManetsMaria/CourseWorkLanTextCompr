package view;

import entity.LanguageSaver;
import entity.StemSaver;
import stemmer.Stemmer;
import stemmer.imp.StemmerEn;
import stemmer.imp.StemmerRu;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.*;

public class MainFrame extends JFrame{

    private FileWorker fileWorker;
    private JTextArea textAreaLanguage;
    private JTextArea textAreaSchema;
    private LanguageSaver languageSaver;
    private String text;
    private String schema;
    private JTextArea compressResult;
    private StemSaver stemSaver;


    public MainFrame() {
        super();
        initialize();
    }

    private void initialize() {
        initStemSaver();
        BorderLayout main = new BorderLayout();
        this.setLayout(main);
        GridLayout gridLayout = new GridLayout(3,1);
        JPanel panel = new JPanel();
        panel.setLayout(gridLayout);
        this.add(panel, BorderLayout.CENTER);
        text = "";
        schema = "";
        fileWorker = new FileWorker(this);
        textAreaSchema = new JTextArea();
        textAreaLanguage = new JTextArea();
        compressResult = new JTextArea();
        initSystemLookAndFeel();
        this.setSize(500, 500);
        this.add(new MenuPanel(fileWorker), BorderLayout.NORTH);
        panel.add(new JScrollPane(textAreaLanguage));
        panel.add(new JScrollPane(textAreaSchema));
        panel.add(new JScrollPane(compressResult));
        textAreaLanguage.setEditable(false);
        textAreaSchema.setEditable(false);
        compressResult.setEditable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Course show Window");
        languageSaver = new LanguageSaver();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initSystemLookAndFeel() {
        try {
            String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(systemLookAndFeelClassName);
        } catch (UnsupportedLookAndFeelException e) {
            Logger.getLogger(MainFrame.class.getName()).log(new LogRecord(Level.WARNING, "Impossible use this theme"));
        } catch (Exception e) {
            Logger.getLogger(MainFrame.class.getName()).log(new LogRecord(Level.WARNING, "Unknow exception"));
        }
    }

    public void setSchema(String schema){
        textAreaSchema.setText(schema);
        this.schema = schema;
    }

    public void addText(String text){
        this.text = text;
        textAreaSchema.append('\n'+text);
    }

    public void addLanguage(String lang){
        textAreaLanguage.append('\n'+lang);
    }

    public LanguageSaver getLanguageSaver() {
        return languageSaver;
    }

    public String getText() {
        return text;
    }

    public String getSchema() {
        return schema;
    }

    public void setCompressResult(String compressResult){
        this.compressResult.setText(compressResult);
    }

    private void initStemSaver(){
        stemSaver = new StemSaver();
        Stemmer ru = new StemmerRu();
        Stemmer en = new StemmerEn();
        stemSaver.addStemmer("en", en);
        stemSaver.addStemmer("ru", ru);
    }

    public StemSaver getStemSaver() {
        return stemSaver;
    }
}
