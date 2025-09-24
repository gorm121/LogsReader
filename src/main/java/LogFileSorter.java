import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class LogFileSorter {

    public static void saveInvalidLogs(ArrayList<String> logRecords,String filepath){
        if (logRecords.isEmpty()) return;
        try (FileWriter writer = new FileWriter(filepath,true)) {
            for (String logRecord : logRecords) {
                if (logRecord.equals(logRecords.get(logRecords.size() - 1))){
                    writer.write(logRecord);
                }
                else writer.write(logRecord + "\n");
            }
        } catch (IOException e) { System.out.println(e.getMessage()); }
    }

    public static void SortAndSave(ArrayList<LogRecord> logRecords, SortType sortType, String filepath){
        if (logRecords.isEmpty()){
            System.out.println("Нет логов для сохранения");
            return;
        }

        switch (sortType){
            case BY_TIMESTAMP -> logRecords.sort(getComparator(SortType.BY_TIMESTAMP));
            case BY_CODE ->  logRecords.sort(getComparator(SortType.BY_CODE));
            case BY_TYPE -> logRecords.sort(getComparator(SortType.BY_TYPE));
        }
        try (FileWriter writer = new FileWriter(filepath,false)) {
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

