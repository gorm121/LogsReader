import java.util.Scanner;

public class InputChecker {
    public static int getValidInt(int a, int b, Scanner scanner) {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (a <= num  && num <= b){
                    return num;
                }
                System.out.println("Ошибка! Вы ввели неправильное число");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Вы ввели неправильное число");
            }
        }
    }
}