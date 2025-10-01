import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LogRecord {
    private final long timestamp;
    private final String type;
    private final String code;
    private final String message;


    public long getTimestamp(){
        return timestamp;
    }
    public String getType(){
        if (type != null) return type;
        return "NoneType";
    }
    public String getCode(){
        if (code != null) return code;
        return "NoneCode";
    }
    public String getMessage(){
        if (message != null) return message;
        return "NoneMessage";
    }

    LogRecord(long timestamp, String type, String code, String message){
        this.timestamp = timestamp;
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public String getFormattedTimestamp() {
        return new Date(timestamp * 1000).toString();
    }

    public static String getFormattedTimestamp(long timestamp) {
        return new Date(timestamp * 1000).toString();
    }

    public String toStringFile(){
        return timestamp + "-" + type + "-" + code + "-" + message + "\n";
    }

    public String toShow() {
        return getFormattedTimestamp() + " " + type + " " + code + " " + message;
    }


    public String toStr() {
        return getTimestamp() + " " + getType() + " " + getCode() + " " + getMessage();
    }
}
