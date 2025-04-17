package ra.edu.validate;

import java.util.Scanner;

public class ProductValidator {
    public static String validateProductName(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, Nhập lại: ");
                continue;
            }
            if (value.length() < 2 || value.length() > 100) {
                System.err.println("Tên sản phẩm phải từ 2 đến 100 ký tự, Nhập lại: ");
                continue;
            }
            return value;
        }
    }
    public static String validateProductBrand(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Nhãn hàng không được để trống, Nhập lại: ");
                continue;
            }
            if (value.length() < 2 || value.length() > 50) {
                System.err.println("Nhãn hàng phải từ 2 đến 50 ký tự, Nhập lại: ");
                continue;
            }
            return value;
        }
    }
    public static double validateProductPrice(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (!Validator.isValidDataType(input, Double.class)) {
                System.err.println("Giá phải là số thực, Nhập lại: ");
                continue;
            }
            double price = Double.parseDouble(input);
            if (price <= 0) {
                System.err.println("Giá phải lớn hơn 0, Nhập lại: ");
            } else {
                return price;
            }
        }
    }
    public static int validateProductStock(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (!Validator.isValidDataType(input, Integer.class)) {
                System.err.println("Tồn kho là số nguyên, Nhập lại: ");
                continue;
            }
            int stock = Integer.parseInt(input);
            if (stock <= 0) {
                System.err.println("Tồn kho phải lớn hơn 0, Nhập lại: ");
            } else {
                return stock;
            }
        }
    }
}
