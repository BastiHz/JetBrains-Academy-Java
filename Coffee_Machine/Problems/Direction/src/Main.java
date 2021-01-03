import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int direction = scanner.nextInt();
        String out = "move ";
        switch (direction) {
            case 0 :
                out = "do not move";
                break;
            case 1:
                out += "up";
                break;
            case 2:
                out += "down";
                break;
            case 3:
                out += "left";
                break;
            case 4:
                out += "right";
                break;
            default:
                out = "error!";
                break;
        }
        System.out.println(out);
    }
}