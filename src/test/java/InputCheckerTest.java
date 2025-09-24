import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

class InputCheckerTest {

    @Test
    void testGetValidIntValidInput() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        int result = InputChecker.getValidInt(1, 3, scanner);
        assertEquals(2, result);
    }

    @Test
    void testGetValidIntInvalidThenValid() {

        String input = "5\n" + "0\n" + "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        int result = InputChecker.getValidInt(1, 3, scanner);
        assertEquals(2, result);
    }

    @Test
    void testGetValidIntInvalidStringThenValidInt() {

        String input = "abc\n" + "2\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        int result = InputChecker.getValidInt(1, 3, scanner);
        assertEquals(2, result);
    }
}