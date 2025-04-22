package ge.edu.sangu.logger;

import ge.edu.sangu.logger.appender.Appender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class LoggerTest {

    private Logger log;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private Appender appender;

    @BeforeEach
    void setUp() {
        System.out.println("LoggerTest.setUp");
        log = new Logger("Test");
        byteArrayOutputStream = new ByteArrayOutputStream();
        appender = new Appender() {
            @Override
            public OutputStream getOutputStream() {
                return byteArrayOutputStream;
            }
        };
        Configuration.addAppender(appender);
    }

    @AfterEach
    void tearDown() throws IOException {
        System.out.println("LoggerTest.tearDown");
        byteArrayOutputStream.close();
        Configuration.clearAppenders();
    }

    @Test
    void testTrace() {
        Configuration.setLoggingLevel(Level.TRACE);
        appender.setPatternLayout("{name} - {level}: {message}");
        log.trace("trace");
        Assertions.assertEquals("Test - TRACE: trace", byteArrayOutputStream.toString());
    }

    @Test
    void testInfo() {
        Configuration.setLoggingLevel(Level.INFO);
        appender.setPatternLayout("{name} - {level}: {message}");
        log.info("info");
        Assertions.assertEquals("Test - INFO: info", byteArrayOutputStream.toString());
    }
}