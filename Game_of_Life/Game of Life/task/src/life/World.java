package life;

import java.util.Random;

public class World {
    private static final int[][] neighborOffsets = {
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };
    private final boolean[][] world;
    final int size;

    World(int size) {
        this.size = size;
        world = new boolean[size][size];
    }

    World(int size, long seed) {
        this(size);
        Random random = new Random(seed);
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
            int neighborX = Math.floorMod(x + offset[0], size);
            int neighborY = Math.floorMod(y + offset[1], size);
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
        for (int y = 0; y < size; y++) {
            System.arraycopy(world[y], 0, other.world[y], 0, size);
        }
    }
}
