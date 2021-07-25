import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter file:");

        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = r.readLine();

            if (checkFileName(fileName)) {
                TextReader textReader = new TextReader(fileName);
                textReader.printAllWordsSorted();
                textReader.printStatistics();
                textReader.printMaxCountWords();
            } else {
                System.out.println("Invalid file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkFileName(String fileName) {
        return (fileName.length() > 6) && (fileName.endsWith(".txt")) && (new File(fileName).isFile());
    }
}
