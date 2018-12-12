package view;

import javax.swing.*;
import java.awt.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class MenuPanel extends JPanel {

    FileWorker fileWorker;

    public MenuPanel(FileWorker ownerFrameFileWorker){
        this.fileWorker = ownerFrameFileWorker;
        this.setLayout(new GridLayout(1, 4));
        this.setBackground(Color.black);
        JMenuBar menuBar = new JMenuBar();
        this.add(menuBar);
        this.setBorder(BorderFactory.createEtchedBorder());
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        createMenuItem(fileMenu,"Add language");
        JMenu editMenu = new JMenu("Generate..");
        menuBar.add(editMenu);
        createMenuItem(editMenu, "language");
        createMenuItem(editMenu,"schema");
        createMenuItem(editMenu,"text");
        createMenuItem(fileMenu,"Compress");
    }
    private void createMenuItem (JMenu highMenu, String name){
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(fileWorker);
        highMenu.add(menuItem);
    }
}
