import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class LogFileReaderTest {



    @Test
    void testReadLogFile(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        String log = "1643177489-ERROR-403-Access Denied";
        Files.write(tempFile, log.getBytes());

        try {
            LogFileReader reader = new LogFileReader();
            reader.readLogFile(tempFile.toString());
            assertEquals(1,reader.getLogRecordsCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testToValidStringLog(){
        LogRecord log = new LogRecord(1648633208,"WARNING","301","Moved Permanently");
        String check = log.getFormattedTimestamp();
        assertEquals("2022-03-30 12:40:08",check);
    }

    @Test
    void testCheckTimestamp(){
        String check = "1643177489-ERROR-403-Access Denied";
        assertTrue(LogFileReader.checkTimestamp(check));
    }
}