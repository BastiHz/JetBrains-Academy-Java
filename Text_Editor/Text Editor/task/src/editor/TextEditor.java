package editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditor extends JFrame {

    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);  // this includes the title bar at the top
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel, BorderLayout.NORTH);

        JTextField filenameField = new JTextField();
        filenameField.setName("FilenameField");
        controlPanel.add(filenameField);

        JTextArea textArea = new JTextArea();
        textArea.setName("TextArea");

        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        saveButton.addActionListener(actionEvent -> {
            File file = new File(filenameField.getText());
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                System.out.printf("An exception occurred while saving %s", e.getMessage());
            }
        });
        controlPanel.add(saveButton);

        filenameField.setPreferredSize(new Dimension(150, saveButton.getPreferredSize().height));

        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(actionEvent -> {
            String text = "";
            try {
                text = new String(Files.readAllBytes(Paths.get(filenameField.getText())));
            } catch (IOException e) {
                System.out.printf("Cannot read file %s", e.getMessage());
            }
            textArea.setText(text);
        });
        controlPanel.add(loadButton);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        add(scrollPane);

        setVisible(true);  // has to come last to make everything visible immediately
    }
}
