package life;

import javax.swing.*;

public class GameOfLife extends JFrame {
    private final int CELL_SIZE = 15;
    private final int WORLD_SIZE = 30;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int worldPanelSize = CELL_SIZE * WORLD_SIZE;
        setSize(worldPanelSize, worldPanelSize + 75);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        add(generationLabel);
        JLabel aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        add(aliveLabel);

        World world = new World(WORLD_SIZE);

        JPanel worldPanel = new WorldPanel(world, CELL_SIZE);
        add(worldPanel);

        setVisible(true);

        GameRunner gameRunner = new GameRunner(world, generationLabel, aliveLabel, worldPanel);
        Thread gameThread = new Thread(gameRunner);
        gameThread.start();
    }

    public static void main(String[] args) {
        new GameOfLife();
    }
}