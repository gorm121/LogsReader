import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LogFileSorterTest {

    @Test
    void testToSaveInvalidLogs(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("test.txt");
        String log = "1648633208-WARNING-301-Moved Permanently";
        try {
            Files.write(tempFile,log.getBytes());
            LogFileReader reader = new LogFileReader();
            reader.readLogFile(tempFile.toString());
            assertEquals(1,reader.getLogRecordsCount());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void TestSortAndSave(@TempDir Path tempDir) throws IOException {

        ArrayList<LogRecord> records = new ArrayList<>();
        records.add(new LogRecord(1648633208, "WARNING", "301", "Moved Permanently"));
        records.add(new LogRecord(1654088926, "INFO", "200", "OK"));

        Path outputFile = tempDir.resolve("sorted.txt");


        LogFileSorter.SortAndSave(records, SortType.BY_CODE, outputFile.toString());

        assertTrue(Files.exists(outputFile));
        String lines = Files.readString(outputFile);

        String [] parts = lines.split("\n");
        assertTrue(parts[0].contains("200"));
        assertTrue(parts[1].contains("301"));
    }
}