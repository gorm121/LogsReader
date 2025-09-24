public class CheckValidLog {

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
        if (log.getMessage().trim().isEmpty()) return false;
        if (log.getCode().trim().isEmpty()) return false;
        if (log.getType().trim().isEmpty()) return false;
        return !log.getFormattedTimestamp().trim().isEmpty();
    }
}
