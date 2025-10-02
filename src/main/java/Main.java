import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String FILEPATH = "logs.txt";
    private static final LogFileReader reader = new LogFileReader();

    public static void main(String[] args) {
        reader.readLogFile(FILEPATH);
        Menu menu = new Menu(scanner, reader.getLogRecords(), reader.getLogInvalidRecords(), FILEPATH);
        menu.start();
    }
}

