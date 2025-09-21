
import java.util.ArrayList;


public class LogFilePrinter {
    public static void logFilePrinter(ArrayList<LogRecord> logRecords, int currentPage, int totalRecords){
        if (logRecords.isEmpty()) {
            System.out.println("Логов нет");
            return;
        }

        Menu.showCurrentPage(totalRecords, currentPage);
        currentPage *= 10;
        System.out.println("---------------------------------------------------");
        System.out.println("| Время | Тип | Код | Сообщение                    |");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < logRecords.size(); i++){
            if (i < currentPage && i >= currentPage - 10)
                System.out.println(logRecords.get(i).toString());
        }
        System.out.println("---------------------------------------------------");
    }
}
