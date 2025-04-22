package ge.edu.sangu.logger.appender;

import java.io.OutputStream;

public class ConsoleAppender extends Appender {

    @Override
    public OutputStream getOutputStream() {
        return System.out;
    }
}
