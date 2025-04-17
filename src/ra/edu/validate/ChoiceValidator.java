package ra.edu.validate;

import java.util.Scanner;

public class ChoiceValidator {
    public static int validateChoice(Scanner scanner) {
        System.out.println("Lựa chọn của bạn:");
        while (true) {
            String value = scanner.nextLine();
            if (Validator.isValidDataType(value, Integer.class)) {
                return Integer.parseInt(value);
            }
        }
    }
}