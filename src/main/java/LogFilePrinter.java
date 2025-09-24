import java.util.ArrayList;


public class LogFilePrinter {
    public static void logFilePrinterForValid(ArrayList<LogRecord> logRecords, int currentPage, int totalRecords){
        if (logRecords.isEmpty()) {
            System.out.println("Логов нет");
            return;
        }

        Menu.showCurrentPage(totalRecords, currentPage);
        currentPage *= 10;
        System.out.println("---------------------------------------------------");
        System.out.println("| Время            | Тип | Код | Сообщение        |");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < logRecords.size(); i++){
            if (i < currentPage && i >= currentPage - 10)
                System.out.println(logRecords.get(i).toShow());
        }
        System.out.println("---------------------------------------------------");
    }

    public static void logFilePrinterForInvalid(ArrayList<String> logRecords, int currentPage, int totalRecords){
        if (logRecords.isEmpty()) {
            System.out.println("Логов нет");
            return;
        }

        Menu.showCurrentPage(totalRecords, currentPage);
        currentPage *= 10;
        System.out.println("---------------------------------------------------");
        System.out.println("| Время            | Тип | Код | Сообщение        |");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < logRecords.size(); i++){
            if (i < currentPage && i >= currentPage - 10 && logRecords.get(i).contains("-"))
                if (LogFileReader.checkTimestamp(logRecords.get(i).substring(0,logRecords.get(i).indexOf("-"))))
                        System.out.println(LogFileReader.toValidStringLog(logRecords.get(i)));
            System.out.println(logRecords.get(i));
        }
        System.out.println("---------------------------------------------------");
    }



    public static void logFilePrinterForYear(ArrayList<LogRecord> logRecords, int totalRecords, int year){
        if (logRecords.isEmpty()) {
            System.out.println("Логов нет");
            return;
        }

        System.out.println("Логи за " + year + " год");

        System.out.println("---------------------------------------------------");
        System.out.println("| Время            | Тип | Код | Сообщение        |");
        System.out.println("---------------------------------------------------");
        for (LogRecord logRecord : logRecords) {
            if (logRecord.getFormattedTimestamp().contains(Integer.toString(year)))
                System.out.println(logRecord.toShow());
        }
        System.out.println("---------------------------------------------------");
    }
}
