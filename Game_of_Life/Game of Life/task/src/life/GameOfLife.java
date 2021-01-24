package life;

public class GameOfLife {
    private final World world;
    private final World nextWorld;

    GameOfLife(int size, long seed) {
        world = new World(size, seed);
        nextWorld = new World(size);
    }

    void run(int nGenerations) {
        for (int i = 0; i < nGenerations; i++) {
            for (int y = 0; y < world.size; y++) {
                for (int x = 0; x < world.size; x++) {
                    int n = world.getNAliveNeighbors(x, y);
                    if (world.getCell(x, y)) {
                        nextWorld.setCell(x, y, n == 2 || n == 3);
                    } else {
                        nextWorld.setCell(x, y, n == 3);
                    }
                }
            }
            nextWorld.copyTo(world);
        }
    }

    void printWorld() {
        world.print();
    }
}
