package sorting;

import java.util.*;

public class Main {
    private enum SortingType {NATURAL, BY_COUNT};
    private enum DataType {WORD, LINE, LONG};
    private static SortingType sType = SortingType.NATURAL;
    private static DataType dType = DataType.WORD;

    public static void main(final String[] args) {
        readArgs(Arrays.asList(args));
        List<String> rawData = readInput();
        if (sType == SortingType.NATURAL) {
            sortedAndPrintNatural(rawData);
        } else {
            sortedAndPrintByCount(rawData);
        }
    }

    private static void readArgs(List<String> argList) {
        int index = argList.indexOf("-sortingType");
        if (index > -1) {
            switch (argList.get(index + 1)) {
                case "natural":
                    sType = SortingType.NATURAL;
                    break;
                case "byCount":
                    sType = SortingType.BY_COUNT;
            }
        }
        index = argList.indexOf("-dataType");
        if (index > -1) {
            switch (argList.get(index + 1)) {
                case "word":
                    dType = DataType.WORD;
                    break;
                case "long":
                    dType = DataType.LONG;
                    break;
                case "line":
                    dType = DataType.LINE;
                    break;
            }
        }
    }

    private static List<String> readInput() {
        Scanner scanner = new Scanner(System.in);
        List<String> data = new ArrayList<>();
        if (dType == DataType.LINE) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } else {
            while (scanner.hasNext()) {
                data.add(scanner.next());
            }
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
        System.out.printf("Total %s: %d.", type, total);
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

    private static void sortedAndPrintNatural(List<String> data) {
        printTotal(data.size());
        String sep = dType == DataType.LINE ? "\n" : " ";
        System.out.print("\nSorted data:");

        if (dType == DataType.LONG) {
            data = sortNumbers(data);
        } else {
            Collections.sort(data);
        }
        for (String x : data) {
            System.out.print(sep + x);
        }
    }

    private static void sortedAndPrintByCount(List<String> data) {
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
                System.out.printf("\n%s: %d time(s), %.0f%%", value, count, percentage);
            }
        }
    }
}
