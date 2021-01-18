package editor;

import javax.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);  // this includes the title bar at the top
        setLocationRelativeTo(null);
        initComponents();
        setLayout(null);
        setVisible(true);  // has to come last, otherwise the app glitches
    }

    public void initComponents() {
        JTextArea textArea = new JTextArea();
        textArea.setBounds(20, 20, 260, 230);
        textArea.setName("TextArea");
        add(textArea);
    }
}
