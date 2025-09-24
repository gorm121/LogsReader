import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void testMenuCreation() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<LogRecord> validLogs = new ArrayList<>();
        ArrayList<String> invalidLogs = new ArrayList<>();
        String outputPath = "test.txt";

        Menu menu = new Menu(scanner, validLogs, invalidLogs, outputPath);

        assertNotNull(menu);
    }

    @Test
    void testShowCurrentPage() {
        // Тестируем статический метод
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Menu.showCurrentPage(25, 2);

        assertTrue(outContent.toString().contains("Текущая страница: 2"));
        System.setOut(System.out);
    }


    @Test
    void testLogsMenuForYear(@TempDir Path tempDir) {

        ArrayList<LogRecord> validLogs = new ArrayList<>();
        validLogs.add(new LogRecord(1648633208, "INFO", "100", "2023 log"));

        ArrayList<String> logs = new ArrayList<>();
        Path outputFile = tempDir.resolve("test.txt");

        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu(scanner, validLogs, logs, outputFile.toString());

        assertNotNull(menu);
        System.setOut(System.out);
    }
}