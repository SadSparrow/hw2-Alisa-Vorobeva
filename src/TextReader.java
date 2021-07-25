import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextReader {
    private final List<String> wordsList = new ArrayList<>();
    private final Map<String, Integer> wordsMap = new TreeMap<>();
    private boolean wordsListNotEmpty = false;

    //Logic in constructor is a "bad practice", but I haven't found another solution for the requirement "допускается только один полный обход файла".
    public TextReader(String fileName) {
        Objects.requireNonNull(fileName, "FileName must not be null");
        readFile(fileName);
    }

    private void readFile(String fileName) {
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((text = reader.readLine()) != null) {

                List<String> list = Arrays.stream(text.toLowerCase(Locale.ROOT)
                        .split("[^a-zа-я]"))
                        .filter(t -> !t.equals(""))
                        .collect(Collectors.toList());

                wordsList.addAll(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!wordsList.isEmpty()) {
            wordsListNotEmpty = true;
            wordsList.forEach(word -> wordsMap.merge(word, 1, Integer::sum));
        }
    }

    public void printAllWordsSorted() {
        if (checkListEmptyPrint()) {
            Collections.sort(wordsList);
            System.out.println("All words:");
            wordsList.forEach(System.out::println);
        }
    }

    public void printStatistics() {
        if (checkListEmptyPrint()) {
            System.out.println("Statistics:");
            wordsMap.forEach((key, value) -> System.out.println(key + " - " + value));
        }
    }

    public void printMaxCountWords() {
        if (checkListEmptyPrint()) {
            int maxCount = maxCount();
            System.out.println("Max Count: " + maxCount);
            wordsMap.entrySet().stream().filter(e -> e.getValue().equals(maxCount))
                    .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        }
    }

    private int maxCount() {
        return wordsMap.values().stream().mapToInt(i -> i).max().getAsInt();
    }

    private boolean checkListEmptyPrint() {
        if (wordsListNotEmpty) {
            return true;
        } else {
            System.out.println("There are no words in the text. List is empty");
        }
        return false;
    }
}
