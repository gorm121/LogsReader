import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import static org.junit.jupiter.api.Assertions.*;

class LogRecordTest {

    @Test
    void testGetFormattedTimestamp(){
        LogRecord log = new LogRecord(1648633208,"WARNING","301","Moved Permanently");
        String check = log.getFormattedTimestamp();
        assertEquals("2022-03-30 12:40:08",check);
    }

    @Test
    void testGetFormattedTimestampWithLong(){
        long timestamp = 1648633208;
        assertEquals("2022-03-30 12:40:08",LogRecord.getFormattedTimestamp(timestamp));
    }

    @Test
    void testToShow() {
        String check = "2022-03-30 12:40:08 WARNING 301 Moved Permanently";
        LogRecord log = new LogRecord(1648633208,"WARNING","301","Moved Permanently");
        assertEquals(check, log.toShow());
    }
}