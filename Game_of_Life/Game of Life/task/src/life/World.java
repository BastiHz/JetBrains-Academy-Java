package life;

import java.util.Random;

public class World {
    private static final int[][] neighborOffsets = {
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };
    private final boolean[][] world;
    final int SIZE;

    World(int size) {
        SIZE = size;
        world = new boolean[size][size];
        Random random = new Random();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                world[y][x] = random.nextBoolean();
            }
        }
    }

    boolean getCell(int x, int y) {
        return world[y][x];
    }

    void setCell(int x, int y, boolean alive) {
        world[y][x] = alive;
    }

    int getNAliveNeighbors(int x, int y) {
        int n = 0;
        for (int[] offset : neighborOffsets) {
            int neighborX = Math.floorMod(x + offset[0], SIZE);
            int neighborY = Math.floorMod(y + offset[1], SIZE);
            if (world[neighborY][neighborX]) {
                n++;
            }
        }
        return n;
    }

    void print() {
        for (boolean[] row : world) {
            for (boolean cell : row) {
                System.out.print(cell ? 'O' : ' ');
            }
            System.out.println();
        }
    }

    /**
     * Copy all cells from this world to another.
     * @param other The other World object.
     */
    void copyTo(World other) {
        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(world[y], 0, other.world[y], 0, SIZE);
        }
    }
}
