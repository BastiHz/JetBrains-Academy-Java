package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {
    private JTextField filenameField;
    private JTextArea textArea;
    private final ActionListener saveFileListener = actionEvent -> {
        File file = new File(filenameField.getText());
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            System.out.printf("An exception occurred while saving %s\n", e.getMessage());
        }
    };
    private final ActionListener loadFileListener = actionEvent -> {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filenameField.getText())));
        } catch (IOException e) {
            System.out.printf("Cannot read file %s\n", e.getMessage());
        }
        textArea.setText(text);
    };


    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);  // this includes the title bar at the top
        setLocationRelativeTo(null);

        initMenuBar();
        initSaveLoadPanel();
        initTextArea();

        setVisible(true);  // has to come last to make everything visible immediately
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setName("MenuLoad");
        loadMenuItem.addActionListener(loadFileListener);
        fileMenu.add(loadMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(saveFileListener);
        fileMenu.add(saveMenuItem);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(actionEvent -> System.exit(0));
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void initSaveLoadPanel() {
        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.setLayout(new FlowLayout());
        saveLoadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        add(saveLoadPanel, BorderLayout.NORTH);

        filenameField = new JTextField();
        filenameField.setName("FilenameField");
        saveLoadPanel.add(filenameField);

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(saveFileListener);
        saveLoadPanel.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(loadFileListener);
        saveLoadPanel.add(loadButton);

        filenameField.setPreferredSize(new Dimension(150, saveButton.getPreferredSize().height));
    }

    private void initTextArea() {
        textArea = new JTextArea();
        textArea.setName("TextArea");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        add(scrollPane);
    }

}
