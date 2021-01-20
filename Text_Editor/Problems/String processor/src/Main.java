import java.util.Scanner;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String allUpper = line.toUpperCase();
            if (line.equals(allUpper)) {
                break;
            } else {
                System.out.println(allUpper);
            }
        }
        System.out.println("FINISHED");
    }
}