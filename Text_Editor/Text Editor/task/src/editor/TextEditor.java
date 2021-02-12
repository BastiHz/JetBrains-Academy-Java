package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JCheckBox useRegexCheckBox;
    private JTextField searchField;
    private final List<SearchResult> searchResults = new ArrayList<>();
    private int searchResultListIndex = 0;

    private final ActionListener saveFileListener = actionEvent -> {
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                System.out.printf("An exception occurred while saving %s\n", e.getMessage());
            }
        }
    };

    private final ActionListener loadFileListener = actionEvent -> {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            Path path = fileChooser.getSelectedFile().toPath();
            String text = "";
            try {
                text = new String(Files.readAllBytes(path));
            } catch (IOException e) {
                System.out.printf("Cannot read file %s\n", e.getMessage());
            }
            textArea.setText(text);
        }
    };

    private final ActionListener startSearchListener = actionEvent -> {
        searchResultListIndex = 0;
        String searchTerm = searchField.getText();
        if (!searchTerm.isEmpty()) {
            TextSearcher textSearcher = new TextSearcher(
                textArea,
                searchTerm,
                useRegexCheckBox.isSelected(),
                searchResults,
                this::markSearchResult
            );
            textSearcher.execute();
        }
    };

    private final ActionListener previousMatchListener = actionEvent -> {
        // Warning: If the text in textArea changes without starting a fresh search
        //  then the results may become invalid.
        if (searchResults.size() > 0) {
            searchResultListIndex = (searchResultListIndex - 1) % searchResults.size();
            if (searchResultListIndex < 0) {
                searchResultListIndex = searchResults.size() - 1;
            }
            markSearchResult(searchResults.get(searchResultListIndex));
        }
    };

    private final ActionListener nextMatchListener = actionEvent -> {
        // Warning: If the text in textArea changes without starting a fresh search
        //  then the results may become invalid.
        if (searchResults.size() > 0) {
            searchResultListIndex = (searchResultListIndex + 1) % searchResults.size();
            markSearchResult(searchResults.get(searchResultListIndex));
        }
    };

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);  // this includes the title bar at the top
        setLocationRelativeTo(null);

        initMenuBar();
        initTopPanel();
        initFileChooser();
        initTextArea();

        setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem loadMenuItem = new JMenuItem("Open");
        loadMenuItem.setName("MenuOpen");
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


        JMenu searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");
        searchMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(searchMenu);

        JMenuItem searchMenuItem = new JMenuItem("Start search");
        searchMenuItem.setName("MenuStartSearch");
        searchMenuItem.addActionListener(startSearchListener);
        searchMenu.add(searchMenuItem);

        JMenuItem previousMatchMenuItem = new JMenuItem("Previous match");
        previousMatchMenuItem.setName("MenuPreviousMatch");
        previousMatchMenuItem.addActionListener(previousMatchListener);
        searchMenu.add(previousMatchMenuItem);

        JMenuItem nextMatchMenuItem = new JMenuItem("Next match");
        nextMatchMenuItem.setName("MenuNextMatch");
        nextMatchMenuItem.addActionListener(nextMatchListener);
        searchMenu.add(nextMatchMenuItem);

        JMenuItem useRegexMenuItem = new JMenuItem("Use regular expressions");
        useRegexMenuItem.setName("MenuUseRegExp");
        useRegexMenuItem.addActionListener(actionEvent ->
            useRegexCheckBox.setSelected(!useRegexCheckBox.isSelected())
        );
        searchMenu.add(useRegexMenuItem);
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(topPanel, BorderLayout.NORTH);

        Dimension buttonSize = new Dimension(30, 30);

        JButton loadButton = new JButton(new ImageIcon("Icons/load_icon.png"));
        loadButton.setName("OpenButton");
        loadButton.setPreferredSize(buttonSize);
        loadButton.addActionListener(loadFileListener);
        topPanel.add(loadButton);

        JButton saveButton = new JButton(new ImageIcon("Icons/save_icon.png"));
        saveButton.setName("SaveButton");
        saveButton.setPreferredSize(buttonSize);
        saveButton.addActionListener(saveFileListener);
        topPanel.add(saveButton);

        searchField = new JTextField();
        searchField.setName("SearchField");
        searchField.setPreferredSize(new Dimension(200, buttonSize.height));
        topPanel.add(searchField);

        JButton searchButton = new JButton(new ImageIcon("Icons/search_icon.png"));
        searchButton.setName("StartSearchButton");
        searchButton.addActionListener(startSearchListener);
        topPanel.add(searchButton);

        JButton previousMatchButton = new JButton(new ImageIcon("Icons/left_arrow_icon.png"));
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.addActionListener(previousMatchListener);
        topPanel.add(previousMatchButton);

        JButton nextMatchButton = new JButton(new ImageIcon("Icons/right_arrow_icon.png"));
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.addActionListener(nextMatchListener);
        topPanel.add(nextMatchButton);

        useRegexCheckBox = new JCheckBox("Use regex");
        useRegexCheckBox.setName("UseRegExCheckbox");
        topPanel.add(useRegexCheckBox);
    }

    private void initTextArea() {
        textArea = new JTextArea();
        textArea.setName("TextArea");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(scrollPane);
    }

    private void initFileChooser() {
        fileChooser = new JFileChooser(System.getProperty("user.dir"));
        fileChooser.setName("FileChooser");
        add(fileChooser);  // Tests require me to add it to the frame.
    }

    private void markSearchResult(SearchResult result) {
        textArea.setCaretPosition(result.endIndex);
        textArea.select(result.startIndex, result.endIndex);
        textArea.grabFocus();
    }
}
