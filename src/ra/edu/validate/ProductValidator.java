package ra.edu.validate;

import java.util.Scanner;

public class ProductValidator {
    public static String validateProductName(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Tên sản phẩm không được để trống, Nhập lại: ");
            }
            else if (value.length() > 100) {
                System.err.println("Tên sản phẩm phải từ 1 đến 100 ký tự, Nhập lại: ");
            }
            else{
                return value;
            }
        }
    }
    public static String validateProductBrand(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.err.println("Nhãn hàng không được để trống, Nhập lại: ");
            }
            else if (value.length() > 50) {
                System.err.println("Nhãn hàng phải từ 1 đến 50 ký tự, Nhập lại: ");
            }else{
                return value;
            }

        }
    }
    public static double validateProductPrice(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if(input.isEmpty()){
                System.err.println("Giá không được để trống");
            }else if(!Validator.isValidDataType(input,Double.class)){
                System.err.println("Giá phải là số thực, Nhập lại: ");
            }else{
                if (Double.parseDouble(input) < 0) {
                    System.err.println("Giá phải lớn hơn hoặc bằng 0, Nhập lại: ");
                } else {
                    return Double.parseDouble(input);
                }
            }

        }
    }
    public static int validateProductStock(Scanner scanner, String message) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.err.println("Tồn kho không được để trống, Nhập lại: ");
            }
            else if (!Validator.isValidDataType(input, Integer.class)) {
                System.err.println("Tồn kho là số nguyên, Nhập lại: ");
            }else{
                if (Integer.parseInt(input) < 0) {
                    System.err.println("Tồn kho phải lớn hơn hoặc bằng 0, Nhập lại: ");
                } else {
                    return Integer.parseInt(input);
                }
            }

        }
    }
}
