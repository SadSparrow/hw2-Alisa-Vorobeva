import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileNameChecker {

    public static String checkFileName(String fileName) {
        if (checkName(fileName) && isFile(fileName)) {
            return "valid path";
        } else if (checkName(fileName) && !isFile(fileName)) {
            return "valid name";
        } else {
            return "invalid";
        }
    }

    private static boolean checkName(String fileName) {
        return (fileName.length() > 4) && (fileName.endsWith(".txt"));
    }

    private static boolean isFile(String fileName) {
        return new File(fileName).isFile();
    }

    public static List<String> searchFile(String fileName) throws IOException {
        List<String> list = new ArrayList<>();
        Files.walkFileTree(new File("./src").toPath(), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
                File[] files = path.toFile().listFiles();
                for (File value : Objects.requireNonNull(files)) {
                    if (value.isFile() && value.getName().equals(fileName)) {
                        list.add(value.getCanonicalPath());
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return list;
    }
}
