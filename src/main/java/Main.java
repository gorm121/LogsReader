import java.io.IOException;
import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
//    private static ArrayList<LogRecord> logs = new ArrayList<>() {};
//    private static ArrayList<LogRecord> logsInvalid = new ArrayList<>();

    private static final String FILEPATH = "logs/logs.txt";
    private static final LogFileReader reader = new LogFileReader();

    public static void main(String[] args) throws IOException {
        reader.readLogFile(FILEPATH);
        Menu menu = new Menu(scanner, reader.getLogRecords(), reader.getLogInvalidRecords(), FILEPATH);
        menu.start();
    }
}

