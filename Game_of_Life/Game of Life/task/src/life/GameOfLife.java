package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private final int CELL_SIZE = 10;
    private final int WORLD_SIZE = 50;
    private final World world = new World(WORLD_SIZE);

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int worldPanelSize = CELL_SIZE * WORLD_SIZE;
        setSize(worldPanelSize, worldPanelSize + 75);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");

        JLabel aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");

        JPanel worldPanel = new WorldPanel(world, CELL_SIZE);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        GameRunner gameRunner = new GameRunner(world, generationLabel, aliveLabel, worldPanel);

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(actionEvent -> {
            world.step();
            gameRunner.draw();
        });

        JToggleButton playToggleButton = new JToggleButton("Start/Pause");
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.addActionListener(actionEvent -> {
            if (gameRunner.isRunning) {
                gameRunner.isRunning = false;
                stepButton.setEnabled(true);
            } else {
                gameRunner.isRunning = true;
                stepButton.setEnabled(false);
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(actionEvent -> {
            if (gameRunner.isRunning) {
                playToggleButton.doClick();  // stop the simulation
            }
            world.newRandom();
            gameRunner.draw();
        });

        add(topPanel, BorderLayout.NORTH);
        topPanel.add(playToggleButton);
        topPanel.add(resetButton);
        topPanel.add(stepButton);
        topPanel.add(textPanel);
        textPanel.add(generationLabel);
        textPanel.add(aliveLabel);
        add(worldPanel, BorderLayout.CENTER);

        setVisible(true);

        Thread gameThread = new Thread(gameRunner);
        gameThread.start();
    }

    public static void main(String[] args) {
        new GameOfLife();
    }
}
