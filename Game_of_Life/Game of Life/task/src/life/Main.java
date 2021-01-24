package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        GameOfLife gameOfLife = new GameOfLife(size);
        for (int i = 0; i < 15; i++) {  // limit the number of generations for this stage
            gameOfLife.step();
            gameOfLife.print();
            Thread.sleep(500);
        }
    }
}
