package animals;

import java.time.LocalTime;


class Menu {

    private final KnowledgeTree knowledgeTree;
    private final GuessingGame guessingGame;
    private final String[] GOODBYE = {
        "Bye!", "See you soon!", "Have a nice day!",
        "Talk to you later!", "Thank you and goodbye!", "See you later!"
    };

    Menu(String mapperType) {
        this.knowledgeTree = new KnowledgeTree(mapperType);
        this.guessingGame = new GuessingGame(knowledgeTree);
    }

    void run() {
        greet();
        knowledgeTree.load();
        System.out.println("Welcome to the animal expert system!");
        menu();
        knowledgeTree.save();
        bye();
    }

    private void menu() {
        while (true) {
            System.out.println();
            System.out.println("What do you want to do:");
            System.out.println();
            System.out.println("1. Play the guessing game");
            System.out.println("2. List of all animals");
            System.out.println("3. Search for an animal");
            System.out.println("4. Calculate statistics");
            System.out.println("5. Print the Knowledge Tree");
            System.out.println("0. Exit");
            String choice = Helpers.scanner.nextLine();
            if ("0".equals(choice)) {
                return;
            }
            switch (choice) {
                case "1":
                    guessingGame.play();
                    break;
                case "2":
                    knowledgeTree.printAllAnimalNames();
                    break;
                case "3":
                    knowledgeTree.printAllFactsForAnimal();
                    break;
                case "4":
                    knowledgeTree.printStatistics();
                    break;
                case "5":
                    knowledgeTree.print();
                    break;
                default:
                    System.out.println("Invalid input. Please choose a number from the list.");
                    break;
            }
        }
    }

    private void greet() {
        int currentHour = LocalTime.now().getHour();
        if (currentHour < 5) {
            System.out.println("Hi, early bird!");
        } else if (currentHour < 12) {
            System.out.println("Good morning.");
        } else if (currentHour < 18) {
            System.out.println("Good afternoon.");
        } else {
            System.out.println("Good evening.");
        }
        System.out.println();
    }

    private void bye() {
        Helpers.printRandomMessage(GOODBYE);
    }
}
