import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogFileReader {
    private ArrayList<LogRecord> logRecords = new ArrayList<>();
    private ArrayList<LogRecord> logInvalidRecords = new ArrayList<>();

    private int logValidRecordsCount;
    private int logInvalidRecordsCount;

    public int getLogInvalidRecordsCount() {return logInvalidRecordsCount;}
    public int getLogRecordsCount(){ return logValidRecordsCount; }
    public ArrayList<LogRecord> getLogRecords() {return logRecords;}
    public ArrayList<LogRecord> getLogInvalidRecords() {return logInvalidRecords;}

    public void readLogFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogRecord record = parseLine(line);
                if (record != null && checkTrueLog(record)) {
                    logRecords.add(record);
                    logValidRecordsCount++;
                }
                else if (record != null) {
                    logInvalidRecords.add(record);
                    logInvalidRecordsCount++;
                }
            }
        } catch (IOException e) { System.out.println(e.getMessage());}

    }

    public static LogRecord parseLine(String line){
        try {
            String[] parts = line.split("-" ,4);
            if (parts.length < 3 ) return null;

            return new LogRecord(
                    Long.parseLong(parts[0]),
                    parts[1],
                    parts[2],
                    parts[3]);
        }
        catch (Exception e){return null;}
    }

    public static boolean checkTrueLog(LogRecord log){
        //return !log[0].trim().isEmpty() && !log[1].trim().isEmpty() && !log[2].trim().isEmpty() && !log[3].trim().isEmpty();
        return !log.getMessage().trim().isEmpty()
                && !log.getCode().trim().isEmpty()
                && !log.getType().trim().isEmpty()
                && !log.getFormattedTimestamp().trim().isEmpty();
    }
}

