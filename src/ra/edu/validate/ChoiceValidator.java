package ra.edu.validate;

import java.util.Scanner;

public class ChoiceValidator {
    public static int validateChoice(Scanner scanner) {
        System.out.println("Lựa chọn của bạn:");
        while (true) {
            String value = scanner.nextLine();
            if(value.isEmpty()){
                System.err.println("Không được để trống, Nhập lại: ");
            }else if (!Validator.isValidDataType(value, Integer.class)) {
                System.err.println("Phải là số nguyên, Nhập lại: ");
            }else{
                return Integer.parseInt(value);
            }
        }
    }
    public static int validateInput(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("Không được để trống, Nhập lại: ");
            }else if (!Validator.isValidDataType(input, Integer.class)) {
                System.err.println("Phải là số nguyên, Nhập lại: ");
            }else{
                int stock = Integer.parseInt(input);
                if (stock <= 0) {
                    System.err.println("Phải lớn hơn 0, Nhập lại: ");
                } else {
                    return stock;
                }
            }

        }
    }
}