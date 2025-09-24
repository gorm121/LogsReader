import javax.xml.transform.Templates;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final ArrayList<LogRecord> logRecordsValid;
    private final ArrayList<String> logRecordsInvalid;


    private final String outputPath;
    private int currentPageValid;
    private int currentPageForInvalid;

    private int totalRecordsValid;
    private int totalRecordsInvalid;

    private final int pageSize = 10;
    private int totalPage = 0;
    private int totalPageForInvalid = 0;

    public Menu(Scanner scanner, ArrayList<LogRecord> logRecordsValid,ArrayList<String> logRecordsInvalid, String outputPath) {
        this.scanner = scanner;
        this.outputPath = outputPath;
        this.logRecordsValid = logRecordsValid;
        this.logRecordsInvalid = logRecordsInvalid;
        this.totalRecordsValid = logRecordsValid.size();
        this.totalRecordsInvalid = logRecordsInvalid.size();
    }

    public void start() {
        while (true) {
            showMainMenu();
        }
    }


    private void showMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Показать логи");
        System.out.println("2. Показать невалидные логи");
        System.out.println("3. Показать логи за определенный год");
        System.out.println("4. Отсортировать и сохранить логи");
        System.out.println("5. Выход");
        System.out.print("Ваше действие-> ");

        int choice = InputChecker.getValidInt(1, 5, scanner);

        switch (choice) {
            case 1 -> LogsMenuForValid(logRecordsValid,  currentPageValid);
            case 2 -> LogsMenuForInvalid(logRecordsInvalid, currentPageForInvalid);
            case 3 -> LogsMenuForYear(logRecordsValid,totalRecordsValid);
            case 4 -> SortMenu();
            case 5 -> System.exit(0);
        }
    }

    private void LogsMenuForYear(ArrayList<LogRecord> logs,int totalRecords){
        boolean inLogsMenu = true;

        while (inLogsMenu){
            if (totalRecords == 0) {
                System.out.println("Логи отсутствуют");
                return;
            }

            System.out.print("Введите год: ");
            int choice = InputChecker.getValidInt(2022, 2030, scanner);

            LogFilePrinter.logFilePrinterForYear(logs, totalRecords, choice);
            return;
        }
    }

    private void LogsMenuForInvalid(ArrayList<String> logs, int currentPage) {
        currentPage = 1;
        boolean inLogsMenu = true;

        while (inLogsMenu) {
            if (totalRecordsInvalid== 0) {
                System.out.println("Логи отсутствуют");
                return;
            }

            LogFilePrinter.logFilePrinterForInvalid(logs,currentPage, totalRecordsInvalid);

            System.out.println("\n1. Следующая страница");
            System.out.println("2. Предыдущая страница");
            System.out.println("3. Вернуться в главное меню");
            System.out.print("Ваше действие-> ");

            int choice = InputChecker.getValidInt(1, 3, scanner);

            switch (choice) {
                case 1 -> {
                    if (totalRecordsInvalid/10 + 1 > currentPage) {
                        currentPage++;
                    }
                    else System.out.println("Больше нет логов");
                }
                case 2 -> {
                    currentPage = Math.max(1, currentPage - 1);
                }
                case 3 -> inLogsMenu = false;
            }
        }
    }

    private void LogsMenuForValid(ArrayList<LogRecord> logs, int currentPage) {
        currentPage = 1;
        boolean inLogsMenu = true;

        while (inLogsMenu) {
            if (totalRecordsValid == 0) {
                System.out.println("Логи отсутствуют");
                return;
            }

            LogFilePrinter.logFilePrinterForValid(logs,currentPage, totalRecordsValid);

            System.out.println("\n1. Следующая страница");
            System.out.println("2. Предыдущая страница");
            System.out.println("3. Вернуться в главное меню");
            System.out.print("Ваше действие-> ");

            int choice = InputChecker.getValidInt(1, 3, scanner);

            switch (choice) {
                case 1 -> {
                    if (totalRecordsValid/10 + 1 > currentPage) {
                        currentPage++;
                    }
                    else System.out.println("Больше нет логов");
                }
                case 2 -> {
                    currentPage = Math.max(1, currentPage - 1);
                }
                case 3 -> inLogsMenu = false;
            }
        }
    }

    public static void showCurrentPage(int totalRecords, int currentPage){
        System.out.println("\nТекущая страница: " + currentPage + " из: " + (int)(Math.ceil((totalRecords/10) + 0.5)));
    }
    private void SortMenu() {
        if (totalRecordsValid == 0) {
            System.out.println("Логи отсутствуют");
            return;
        }

        System.out.println("\n=== ВЫБОР СОРТИРОВКИ ===");
        System.out.println("1. Отсортировать по времени");
        System.out.println("2. Отсортировать по типу сообщения");
        System.out.println("3. Отсортировать по коду сообщения");
        System.out.println("4. Вернуться назад");

        int choice = InputChecker.getValidInt(1, 4, scanner);

        if (choice == 4) return;

        SortType sortType = switch (choice) {
            case 1 -> SortType.BY_TIMESTAMP;
            case 2 -> SortType.BY_TYPE;
            case 3 -> SortType.BY_CODE;
            default -> throw new IllegalStateException();
        };

        LogFileSorter.SortAndSave(logRecordsValid, sortType, outputPath);
        LogFileSorter.saveInvalidLogs(logRecordsInvalid,outputPath);
        System.out.println("Логи отсортированы и сохранены в: " + outputPath);
    }
}
