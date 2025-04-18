package ra.edu.presentation;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.validate.ChoiceValidator;
import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerService customerService = new CustomerServiceImp();

    public void CustomerMenu() {
        System.out.println("========QUẢN LÝ KHÁCH HÀNG========");
        System.out.println("1. Hiển thị danh sách khách hàng");
        System.out.println("2. Thêm khách hàng");
        System.out.println("3. Cập nhật khách hàng");
        System.out.println("4. Xóa khách hàng");
        System.out.println("5. Tìm kiếm khách hàng theo tên");
        System.out.println("6. Quay lại menu chính");
        System.out.println("===================================");
        int choice = ChoiceValidator.validateChoice(scanner);
        switch (choice) {
            case 1:
                displayListCustomer();
                break;
            case 2:
                addCustomer(scanner);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ");
        }
    }

    private void addCustomer(Scanner scanner) {
        int input = ChoiceValidator.validateInput(scanner, "Nhập vào số lượng cần thêm");
        for (int i = 0; i < input; i++) {
            System.out.println("====== Thêm khách hàng ======");

            String customerName;
            while (true) {
                customerName = CustomerValidator.validateCustomerName(scanner, "Nhập tên khách hàng");
                if (customerService.existsByName(customerName)) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác.");
                } else {
                    break;
                }
            }

            String customerPhone = CustomerValidator.validatorPhone(scanner,"Nhập số điện thoại");
            String customerEmail = CustomerValidator.validateEmail(scanner, "Nhập Email");
            String customerAddress = CustomerValidator.validateAddress(scanner, "Nhập địa chỉ");

            Customer c = new Customer();
            c.setCustomer_name(customerName);
            c.setCustomer_phone(customerPhone);
            c.setCustomer_email(customerEmail);
            c.setCustomer_address(customerAddress);

            if (customerService.save(c)) {
                System.out.println("Thêm sản phẩm thành công!");
            } else {
                System.out.println("Thêm sản phẩm thất bại!");
            }
        }
        displayListCustomer();

    }

    private void displayListCustomer() {
        System.out.println("====DANH SÁCH KHÁCH HÀNG====");
        List<Customer> customer = customerService.findAll();
        printCustomerList(customer);
        System.out.println("============================");
    }

    private void printCustomerList(List<Customer> customer) {
        if (customer.isEmpty()) {
            System.out.println("Không có khách hàng nào để hiển thị.");
        } else {
            System.out.printf("%-5s %-25s %-20s %-30s %-30s\n",
                    "ID", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ");
            for (Customer c : customer) {
                System.out.printf("%-5d %-25s %-20s %-30s %-30s\n",
                        c.getCustomer_id(), c.getCustomer_name(), c.getCustomer_phone(),
                        c.getCustomer_email(), c.getCustomer_address());
            }
        }
    }
}
