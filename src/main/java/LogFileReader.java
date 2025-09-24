import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LogFileReader {
    private final ArrayList<LogRecord> logRecords = new ArrayList<>();
    private final ArrayList<String> logInvalidRecords = new ArrayList<>();

    private int logValidRecordsCount;
    private int logInvalidRecordsCount;

    public int getLogInvalidRecordsCount() {return logInvalidRecordsCount;}
    public int getLogRecordsCount(){ return logValidRecordsCount; }
    public ArrayList<LogRecord> getLogRecords() {return logRecords;}
    public ArrayList<String> getLogInvalidRecords() {return logInvalidRecords;}

    public void readLogFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LogRecord record = CheckValidLog.parseLine(line);
                if (record != null && CheckValidLog.checkTrueLog(record)) {
                    logRecords.add(record);
                    logValidRecordsCount++;
                }
                else {
                    logInvalidRecords.add(line);
                    logInvalidRecordsCount++;
                }
            }
        } catch (IOException e) { System.out.println(e.getMessage());}
    }

    public static String toValidStringLog(String line){
        return LogRecord.getFormattedTimestamp(Long.parseLong(line.substring(0,line.indexOf("-")))) + line.substring(line.indexOf("-"));
    }

    public static boolean checkTimestamp(String line){
        if (!line.contains("-")) return false;
        try {
            long res = Long.parseLong(line.substring(0,line.indexOf("-")));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());;
        }
        return false;
    }
}

