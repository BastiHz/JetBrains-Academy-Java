import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String out = "You have chosen a ";
        switch (n) {
            case 1:
                out += "square";
                break;
            case 2:
                out += "circle";
                break;
            case 3:
                out += "triangle";
                break;
            case 4:
                out += "rhombus";
                break;
            default:
                out = "There is no such shape!";
                break;
        }
        System.out.println(out);
    }
}