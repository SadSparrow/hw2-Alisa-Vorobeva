import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter file:");

        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = r.readLine();

            switch (FileNameChecker.checkFileName(fileName)) {
                case "valid path" -> {
                    TextReader textReader = new TextReader(fileName);
                    textReader.printAllWordsSorted();
                    textReader.printStatistics();
                    textReader.printMaxCountWords();
                }
                case "valid name" -> {
                    List<String> list = FileNameChecker.searchFile(fileName);
                    switch (list.size()) {
                        case 0 -> System.out.println("File not found");
                        case 1 -> doHomeWork(0, list);
                        default -> {
                            int size = list.size();
                            System.out.println("Found " + size + " files:");
                            list.forEach(System.out::println);
                            System.out.println("Enter index (0 - " + (size - 1) + "):");
                            int index = parseInt(r.readLine());
                            if (checkBounds(index, size))
                                doHomeWork(index, list);
                        }
                    }
                }
                case "invalid" -> System.out.println("Invalid file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doHomeWork(int index, List<String> list) {
        TextReader textReader = new TextReader(list.get(index));
        textReader.printAllWordsSorted();
        textReader.printStatistics();
        textReader.printMaxCountWords();
    }

    private static int parseInt(String parseIndex) {
        try {
            return Integer.parseInt(parseIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input number. Used index 0");
            return 0;
        }
    }

    private static boolean checkBounds(int index, int size) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds");
            return false;
        }
        return true;
    }
}
