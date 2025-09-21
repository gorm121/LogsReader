import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
        return "NonType";
    }
    public String getCode(){
        if (code != null) return code;
        return "NonCode";
    }
    public String getMessage(){
        if (message != null) return message;
        return "NoMessage";
    }

    LogRecord(long timestamp, String type, String code, String message){
        this.timestamp = timestamp;
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public String getFormattedTimestamp() {
        return Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String toStringFile(){
        return timestamp + " " + type + " " + code + " " + message + "\n";
    }

    public String toShow() {
        return getFormattedTimestamp() + " " + type + " " + code + " " + message;
    }

    @Override
    public String toString() {
        return getTimestamp() + " " + getType() + " " + getCode() + " " + getMessage();
    }
}
