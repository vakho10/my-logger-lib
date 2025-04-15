package ge.edu.sangu.logger.appender;

public class ConsoleAppender implements Appender {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
