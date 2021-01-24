package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        long seed = scanner.nextLong();
        int nGenerations = scanner.nextInt();
        GameOfLife gameOfLife = new GameOfLife(size, seed);
        gameOfLife.run(nGenerations);
        gameOfLife.printWorld();
    }
}
