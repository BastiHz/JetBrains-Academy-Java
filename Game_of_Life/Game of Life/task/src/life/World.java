package life;

import java.util.Random;

public class World {
    private static final int[][] neighborOffsets = {
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };
    final int SIZE;
    final boolean[][] world;
    final boolean[][] nextWorld;
    int generationNumber = 0;
    int nLivingCells = 0;

    World(int size) {
        this.SIZE = size;
        world = new boolean[SIZE][SIZE];
        nextWorld = new boolean[SIZE][SIZE];
        newRandom();
    }

    void newRandom() {
        generationNumber = 0;
        nLivingCells = 0;
        Random random = new Random();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                boolean isAlive = random.nextBoolean();
                world[y][x] = isAlive;
                if (isAlive) {
                    nLivingCells++;
                }
            }
        }
    }

    void step() {
        generationNumber++;
        nLivingCells = 0;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int n = getNAliveNeighbors(x, y);
                boolean nextGenAlive;
                if (world[y][x]) {
                    nextGenAlive = n == 2 || n == 3;
                } else {
                    nextGenAlive = n == 3;
                }
                nextWorld[y][x] = nextGenAlive;
                if (nextGenAlive) {
                    nLivingCells++;
                }
            }
        }
        // Copy the contents of nextWorld to world:
        for (int y = 0; y < SIZE; y++) {
            System.arraycopy(nextWorld[y], 0, world[y], 0, SIZE);
        }
    }

    int getNAliveNeighbors(int x, int y) {
        int n = 0;
        for (int[] offset : neighborOffsets) {
            int neighborX = correctNeighborPosition(x + offset[0]);
            int neighborY = correctNeighborPosition(y + offset[1]);
            if (world[neighborY][neighborX]) {
                n++;
            }
        }
        return n;
    }

    int correctNeighborPosition(int pos) {
        return pos > -1 ? pos % SIZE : SIZE + pos;
    }
}
