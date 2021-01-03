import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        boolean aPositive = a > 0;
        boolean bPositive = b > 0;
        boolean cPositive = c > 0;
        boolean onlyA = aPositive && !(bPositive || cPositive);
        boolean onlyB = bPositive && !(aPositive || cPositive);
        boolean onlyC = cPositive && !(aPositive || bPositive);
        System.out.print(onlyA || onlyB || onlyC);
    }
}