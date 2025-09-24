import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class LogFilePrinterTest {

    @Test
    void testLogFilePrinterForValid() {

        ArrayList<LogRecord> records = new ArrayList<>();
        records.add(new LogRecord(1651234570, "DEBUG", "100", "Продолжайте обработку"));
        records.add(new LogRecord(1651234567, "ERROR", "404", "Страница не найдена"));
        records.add(new LogRecord(1651234569, "INFO", "200", "Успешный запрос"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LogFilePrinter.logFilePrinterForValid(records, 1,records.size());
            String output = outputStream.toString();

            assertTrue(output.contains("Время"));
            assertTrue(output.contains("Тип"));
            assertTrue(output.contains("Код"));
            assertTrue(output.contains("Сообщение"));
            assertTrue(output.contains("ERROR"));
            assertTrue(output.contains("INFO"));
            assertTrue(output.contains("2022"));
            assertTrue(output.contains(" "));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testLogFilePrinterEmptyListForValid() {
        ArrayList<LogRecord> records = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LogFilePrinter.logFilePrinterForValid(records, 1, records.size());
            String output = outputStream.toString();

            assertTrue(output.contains("Время"));
            assertTrue(output.contains("Тип"));
            assertFalse(output.contains("ERROR"));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testLogFilePrinterForInvalid(){
        ArrayList<String> records = new ArrayList<>();
        records.add("124321-ERROR");
        records.add("124321-ERROR-lol");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LogFilePrinter.logFilePrinterForInvalid(records,1,records.size());

            String out = outputStream.toString();
            assertTrue(out.contains("ERROR"));
            assertTrue(out.contains("124321"));
        }finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testLogFilePrinterEmptyListForInvalid(){
        ArrayList<String> records = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LogFilePrinter.logFilePrinterForInvalid(records,1,records.size());

            String out = outputStream.toString();
            assertTrue(out.contains("ERROR"));
            assertTrue(out.contains("124321"));
        }finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testLogFilePrinterForYearForEmptyList() {
        ArrayList<LogRecord> records = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        try {
            LogFilePrinter.logFilePrinterForYear(records,records.size(),2000);
            String out = outputStream.toString();

            assertFalse(out.contains("2000"));
            assertTrue(out.contains("Логов нет"));
        } finally {
            System.setOut(originalOut);
        }

    }
}