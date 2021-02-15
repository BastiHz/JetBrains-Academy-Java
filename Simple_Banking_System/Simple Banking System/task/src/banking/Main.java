package banking;

public class Main {

    public static void main(String[] args) {
        if (args.length == 2 && "-fileName".equals(args[0])) {
            new Menu(args[1]).run();
        } else {
            throw new IllegalArgumentException("-fileName argument missing");
        }
    }
}