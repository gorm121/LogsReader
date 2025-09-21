import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class LogFileSorter {

    public static void SortAndSave(ArrayList<LogRecord> logRecords, SortType sortType, String filepath){

        switch (sortType){
            case BY_TIMESTAMP -> logRecords.sort(getComparator(SortType.BY_TIMESTAMP));
            case BY_CODE ->  logRecords.sort(getComparator(SortType.BY_CODE));
            case BY_TYPE -> logRecords.sort(getComparator(SortType.BY_TYPE));
        }
        try (FileWriter writer = new FileWriter(filepath,false)) {
            if (logRecords.isEmpty()){
                System.out.println("Нет логов для сохранения");
                return;
            }
            for (LogRecord logRecord : logRecords) {
                writer.write(logRecord.toStringFile());
            }

        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public static Comparator<LogRecord> getComparator(SortType sortType) {
        return switch (sortType) {
            case BY_TIMESTAMP -> Comparator.comparing(LogRecord::getTimestamp);
            case BY_TYPE -> Comparator.comparing(LogRecord::getType);
            case BY_CODE -> Comparator.comparing(LogRecord::getCode);
        };
    }
}

