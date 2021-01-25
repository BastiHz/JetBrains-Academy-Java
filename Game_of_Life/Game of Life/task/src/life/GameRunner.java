package life;

import javax.swing.*;

class GameRunner implements Runnable {
    final World world;
    final JLabel generationLabel;
    final JLabel aliveLabel;
    final JPanel worldPanel;

    GameRunner(World world, JLabel generationLabel, JLabel aliveLabel, JPanel worldPanel) {
        this.world = world;
        this.generationLabel = generationLabel;
        this.aliveLabel = aliveLabel;
        this.worldPanel = worldPanel;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                world.step();
                generationLabel.setText("Generation #" + world.generationNumber);
                aliveLabel.setText("Alive: " + world.nLivingCells);
                worldPanel.repaint();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {}
    }
}
