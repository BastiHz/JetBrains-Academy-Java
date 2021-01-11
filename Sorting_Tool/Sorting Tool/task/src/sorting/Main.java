package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    private enum SortingType {NATURAL, BY_COUNT};
    private enum DataType {WORD, LINE, LONG};
    private static SortingType sType = SortingType.NATURAL;
    private static DataType dType = DataType.WORD;
    private static String inputFileName;
    private static String outputFileName;
    private static String output = "";

    public static void main(String[] args) {
        // System.out.println("Working Directory = " + System.getProperty("user.dir"));
        if (!readArgs(args)) {
            return;
        }
        List<String> rawData = readInput();
        if (sType == SortingType.NATURAL) {
            sortedNatural(rawData);
        } else {
            sortedByCount(rawData);
        }
        printResult();
    }

    private static boolean readArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-sortingType":
                    try {
                        readSortingType(args, i);
                        i++;
                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("No sorting type defined!");
                        return false;
                    }
                    break;
                case "-dataType":
                    try {
                        readDataType(args, i);
                        i++;
                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("No data type defined!");
                        return false;
                    }
                    break;
                case "-inputFile":
                    try {
                        inputFileName = args[i + 1];
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No input file defined!");
                        return false;
                    }
                    break;
                case "-outputFile":
                    try {
                        outputFileName = args[i + 1];
                        i++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No output file defined!");
                        return false;
                    }
                    break;
                default:
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", arg);
            }
        }
        return true;
    }

    private static void readDataType(String[] args, int index)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        switch (args[index + 1]) {
            case "word":
                dType = DataType.WORD;
                break;
            case "long":
                dType = DataType.LONG;
                break;
            case "line":
                dType = DataType.LINE;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void readSortingType(String[] args, int index)
            throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        switch (args[index + 1]) {
            case "natural":
                sType = SortingType.NATURAL;
                break;
            case "byCount":
                sType = SortingType.BY_COUNT;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static List<String> readInput() {
        List<String> data = new ArrayList<>();
        if (inputFileName == null) {
            data = scanInput(new Scanner(System.in));
        } else {
            File inputFile = new File(inputFileName);
            try (Scanner scanner = new Scanner(inputFile)) {
                data = scanInput(scanner);
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found.");
            }
        }
        return data;
    }

    private static List<String> scanInput(Scanner scanner) {
        List<String> data = new ArrayList<>();
        switch (dType) {
            case LINE:
                while (scanner.hasNextLine()) {
                    data.add(scanner.nextLine());
                }
                break;
            case WORD:
                while (scanner.hasNext()) {
                    data.add(scanner.next());
                }
                break;
            case LONG:
                while (scanner.hasNext()) {
                    String s = scanner.next();
                    try {
                        Long.parseLong(s);
                        data.add(s);
                    } catch (NumberFormatException e) {
                        System.out.printf("\"%s\" is not a long. It will be skipped.\n", s);
                    }
                }
                break;
        }
        return data;
    }

    private static void printTotal(int total) {
        String type = "";
        switch (dType) {
            case WORD:
                type = "words";
                break;
            case LINE:
                type = "lines";
                break;
            case LONG:
                type = "numbers";
                break;
        }
        output += String.format("Total %s: %d.", type, total);
    }

    private static List<String> sortNumbers(List<String> data) {
        // This looks bad. Is there a better solution than
        // casting to long, sorting, and casting back to string?

        List<Long> longList = new ArrayList<>();
        for (String number : data) {
            longList.add(Long.valueOf(number));
        }
        Collections.sort(longList);
        List<String> sortedData = new ArrayList<>();
        for (long number : longList) {
            sortedData.add(String.valueOf(number));
        }
        return sortedData;
    }

    private static void sortedNatural(List<String> data) {
        printTotal(data.size());
        String sep = dType == DataType.LINE ? "\n" : " ";
        output += "\nSorted data:";

        if (dType == DataType.LONG) {
            data = sortNumbers(data);
        } else {
            Collections.sort(data);
        }
        for (String x : data) {
            output += sep + x;
        }
    }

    private static void sortedByCount(List<String> data) {
        HashMap<String, Integer> dataCount = new HashMap<>();
        for (String key : data) {
            int count = dataCount.getOrDefault(key, 0);
            dataCount.put(key, count + 1);
        }
        TreeMap<Integer, Set<String>> countData = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : dataCount.entrySet()) {
            Integer key = entry.getValue();
            String value = entry.getKey();
            Set<String> values = countData.getOrDefault(key, new TreeSet<>());
            values.add(value);
            countData.put(key, values);
        }

        if (dType == DataType.LONG) {
            for (Map.Entry<Integer, Set<String>> entry : countData.entrySet()) {
                // Ugh, this is ugly. There must be a better way.
                Set<String> sortedValues = new LinkedHashSet<>(
                        sortNumbers(new ArrayList<>(entry.getValue()))
                );
                countData.put(entry.getKey(), sortedValues);
            }
        }

        int total = data.size();
        printTotal(total);

        for (Map.Entry<Integer, Set<String>> entry : countData.entrySet()) {
            int count = entry.getKey();
            double percentage = (double) count / total * 100;
            for (String value : entry.getValue()) {
                output += String.format("\n%s: %d time(s), %.0f%%", value, count, percentage);
            }
        }
    }

    private static void printResult() {
        output += "\n";
        if (outputFileName == null) {
            System.out.print(output);
        } else {
            File outputFile = new File(outputFileName);
            try (FileWriter writer = new FileWriter(outputFile)) {
                writer.write(output);
            } catch (IOException e) {
                System.out.printf("An exception occurred while writing output %s", e.getMessage());
            }
        }
    }
}
