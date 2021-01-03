import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        boolean isAscending = a <= b;
        while (scanner.hasNext()) {
            if (b == 0) {
                break;
            }
            if (a != b) {
                if (isAscending) {
                    if (a > b) {
                        System.out.print("false");
                        return;
                    }
                } else if (b > a) {
                    System.out.print("false");
                    return;
                }
            }
            a = b;
            b = scanner.nextInt();
        }
        System.out.print("true");
    }
}