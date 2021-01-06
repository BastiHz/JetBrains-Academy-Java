package sorting;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        List<String> argList = Arrays.asList(args);
        if (argList.contains("-sortIntegers")) {
            sortIntegers();
            return;
        }

        String mode;
        if (argList.contains("-dataType")) {
            mode = argList.get(argList.indexOf("-dataType") + 1);
        } else {
            mode = "word";
        }
        switch (mode) {
            case "word":
                processWords();
                break;
            case "long":
                processLongs();
                break;
            case "line":
                processLines();
                break;
        }
    }

    private static void processWords() {
        int total = 0;
        int longestLength = 0;
        ArrayList<String> longestWords = new ArrayList<>();
        while (scanner.hasNext()) {
            String word = scanner.next();
            total++;
            if (word.length() > longestLength) {
                longestLength = word.length();
                longestWords.clear();
                longestWords.add(word);
            } else if (word.length() == longestLength) {
                longestWords.add(word);
            }
        }
        System.out.printf("Total words: %d.\n", total);
        System.out.print("The longest word: ");
        Collections.sort(longestWords);
        for (String word : longestWords) {
            System.out.print(word + " ");
        }
        double percentage = (double) longestWords.size() / total * 100;
        System.out.printf("(%d time(s), %.0f%%).", longestWords.size(), percentage);
    }

    private static void processLongs() {
        int total = 0;
        int nBiggest = 0;
        long biggest = Long.MIN_VALUE;
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            total++;
            if (number > biggest) {
                biggest = number;
                nBiggest = 1;
            } else if (number == biggest) {
                nBiggest++;
            }
        }
        double percentage = (double) nBiggest / total * 100;
        System.out.printf("Total numbers: %d.\n", total);
        System.out.printf("The greatest number: %d (%d time(s), %.0f%%).", biggest, nBiggest, percentage);
    }

    private static void processLines() {
        int total = 0;
        int longestLength = 0;
        ArrayList<String> longestLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            total++;
            if (line.length() > longestLength) {
                longestLength = line.length();
                longestLines.clear();
                longestLines.add(line);
            } else if (line.length() == longestLength) {
                longestLines.add(line);
            }
        }
        System.out.printf("Total lines: %d.\n", total);
        System.out.print("The longest line:\n");
        Collections.sort(longestLines);
        for (String line : longestLines) {
            System.out.printf("%s\n", line);
        }
        double percentage = (double) longestLines.size() / total * 100;
        System.out.printf("(%d time(s), %.0f%%).", longestLines.size(), percentage);
    }

    private static void sortIntegers() {
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextInt());
        }
        Collections.sort(list);
        System.out.printf("Total numbers: %d.\n", list.size());
        System.out.print("Sorted data:");
        for (int x : list) {
            System.out.print(" " + x);
        }
    }
}
