package life;

import javax.swing.*;

class GameRunner implements Runnable {
    final World world;
    final JLabel generationLabel;
    final JLabel aliveLabel;
    final JPanel worldPanel;
    boolean isRunning = false;

    GameRunner(World world, JLabel generationLabel, JLabel aliveLabel, JPanel worldPanel) {
        this.world = world;
        this.generationLabel = generationLabel;
        this.aliveLabel = aliveLabel;
        this.worldPanel = worldPanel;
    }

    @Override
    public void run() {
        while (true) {
            if (isRunning) {
                world.step();
                draw();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    void draw() {
        generationLabel.setText("Generation #" + world.generationNumber);
        aliveLabel.setText("Alive: " + world.nLivingCells);
        worldPanel.repaint();
    }
}
