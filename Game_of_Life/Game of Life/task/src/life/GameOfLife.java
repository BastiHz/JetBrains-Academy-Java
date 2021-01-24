package life;

public class GameOfLife {
    private final World world;
    private final World nextWorld;
    private int generationNumber = 0;
    private int nLivingCells;


    GameOfLife(int size) {
        world = new World(size);
        nextWorld = new World(size);
    }

    void step() {
        generationNumber++;
        nLivingCells = 0;
        for (int y = 0; y < world.SIZE; y++) {
            for (int x = 0; x < world.SIZE; x++) {
                int n = world.getNAliveNeighbors(x, y);
                boolean nextGenAlive;
                if (world.getCell(x, y)) {
                    nextGenAlive = n == 2 || n == 3;
                } else {
                    nextGenAlive = n == 3;
                }
                nextWorld.setCell(x, y, nextGenAlive);
                if (nextGenAlive) {
                    nLivingCells++;
                }
            }
        }
        nextWorld.copyTo(world);
    }

    void print() {
        System.out.println("Generation #" + generationNumber);
        System.out.println("Alive: " + nLivingCells);
        world.print();
    }
}
