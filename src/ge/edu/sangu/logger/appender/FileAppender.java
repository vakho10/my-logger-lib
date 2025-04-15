package ge.edu.sangu.logger.appender;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class FileAppender implements Appender {

    private File file;

    public FileAppender(File file) {
        this.file = file;
    }

    public FileAppender(String filePath) {
        this(new File(filePath));
    }

    @Override
    public void print(String message) {
        try (var fileOutputStream = new FileOutputStream(file, true);
             var printStream = new PrintStream(fileOutputStream)) {
            printStream.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
