package ra.edu.validate;

import java.util.Scanner;

public class CustomerValidator {
    public static String validateCustomerName(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Tên khách hàng không được để trống, Nhập lại: ");
            }
            else if (value.length() < 2 || value.length() > 100) {
                System.err.println("Tên khách hàng phải từ 2 đến 100 ký tự, Nhập lại: ");
            }
            else{
                return value;
            }
        }
    }
    public static String validateProductAddress(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Địa chỉ không được để trống, Nhập lại: ");
            }
            else if (value.length() < 2 || value.length() > 100) {
                System.err.println("Địa chỉ phải từ 2 đến 100 ký tự, Nhập lại: ");
            }
            else{
                return value;
            }
        }
    }
    public static String validatorPhone(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Số điện thoại không được để trống, Nhập lại: ");
            }else if(!Validator.isValidPhoneNumberVN(value)){
                System.err.println("Số điện thoại không đúng định dạng, Nhập lại: ");
            }else{
                return value;
            }
        }
    }
    public static String validateEmail(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Email không được để trống, Nhập lại: ");
            }else if(!Validator.isValidEmail(value)){
                System.err.println("Email không đúng định dạng, Nhập lại: ");
            }else{
                return value;
            }
        }
    }
    public static String validateAddress(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Địa chỉ không được để trống, Nhập lại: ");
            }else{
                return value;
            }
        }
    }
}
