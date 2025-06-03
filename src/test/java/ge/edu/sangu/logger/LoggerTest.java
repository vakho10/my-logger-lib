package ge.edu.sangu.logger;

import ge.edu.sangu.logger.appender.Appender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoggerTest {

    private Logger log;
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.out.println("LoggerTest.setUp");
        byteArrayOutputStream = new ByteArrayOutputStream();
        Appender appender = mock(Appender.class);
        when(appender.getOutputStream()).thenReturn(byteArrayOutputStream);
        when(appender.getPatternLayout()).thenReturn("{name} - {level}: {message}");
        log = new Logger("Test");
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
        log.trace("trace");
        assertEquals("Test - TRACE: trace", byteArrayOutputStream.toString());
    }

    @Test
    void testInfo() {
        Configuration.setLoggingLevel(Level.INFO);
        log.info("info");
        assertEquals("Test - INFO: info", byteArrayOutputStream.toString());
    }
}