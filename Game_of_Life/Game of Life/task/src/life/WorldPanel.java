package life;

import javax.swing.*;
import java.awt.*;

class WorldPanel extends JPanel {
    private final World world;
    private final int CELL_SIZE;
    private final int PANEL_SIZE;
    private final int[] cellPositions;

    WorldPanel(World world, int cellSize) {
        this.world = world;
        this.CELL_SIZE = cellSize;
        PANEL_SIZE = CELL_SIZE * world.SIZE;
        cellPositions = new int[world.SIZE];
        for (int i = 0; i < cellPositions.length; i++) {
            cellPositions[i] = i * CELL_SIZE;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // clear the panel

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);

        for (int y = 0; y < world.SIZE; y++) {
            int pos = cellPositions[y];
            g2.drawLine(0, pos, PANEL_SIZE, pos);
            g2.drawLine(pos, 0, pos, PANEL_SIZE);

            for (int x = 0; x < world.SIZE; x++) {
                if (world.world[y][x]) {
                    g2.fillRect(x * CELL_SIZE, pos, CELL_SIZE, CELL_SIZE);
                }
            }
        }
        g2.drawLine(0, PANEL_SIZE, PANEL_SIZE, PANEL_SIZE);
        g2.drawLine(PANEL_SIZE, 0, PANEL_SIZE, PANEL_SIZE);
    }
}
