import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String FILEPATH = "logs/logs.txt";
    private static final LogFileReader reader = new LogFileReader();

    public static void main(String[] args) throws IOException {
        reader.readLogFile(FILEPATH);
        Menu menu = new Menu(scanner, reader.getLogRecords(), reader.getLogInvalidRecords(), FILEPATH);
        menu.start();
    }
}

