package ge.edu.sangu.logger.appender;

import java.io.*;

public class FileAppender extends Appender {

    private File file;

    public FileAppender(File file) {
        this.file = file;
    }

    public FileAppender(String filePath) {
        this(new File(filePath));
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
