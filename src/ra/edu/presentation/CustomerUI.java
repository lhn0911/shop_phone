package ra.edu.presentation;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.validate.ChoiceValidator;
import ra.edu.validate.CustomerValidator;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerService customerService = new CustomerServiceImp();

    public void CustomerMenu() {
        do{
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
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    searchByName(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }while(true);
    }

    public void searchByName(Scanner scanner) {
        String name = CustomerValidator.validateCustomerName(scanner, "Nhập tên khách hàng muốn tìm:");
        List<Customer> resultList = customerService.findByName(name);

        if (resultList.isEmpty()) {
            System.out.println("Không tìm thấy khách hàng nào có tên \"" + name + "\".");
        } else {
            System.out.println("Khách hàng có tên \"" + name + "\" là:");
            printCustomerList(resultList);
        }

    }

    public void deleteCustomer(Scanner scanner) {
        int id = ChoiceValidator.validateInput(scanner, "Nhập ID khách hàng muốn xóa:");
        Customer customer = customerService.findById(id);

        if (customer != null) {
            System.out.println("Bạn có muốn xóa khách hàng này không? (y: có, n: không)");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("y")) {
                if (customerService.delete(customer)) {
                    System.out.println("Xóa khách hàng thành công!");
                    displayListCustomer();
                } else {
                    System.out.println("Xóa khách hàng thất bại.");
                }
            } else if (confirm.equals("n")) {
                System.out.println("Đã hủy thao tác xóa.");
                displayListCustomer();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Hủy thao tác.");
            }
        } else {
            System.out.println("Không tìm thấy khách hàng với ID đã nhập.");
        }

    }

    public void updateCustomer(Scanner scanner) {
        int id = ChoiceValidator.validateInput(scanner, "Nhập ID khách hàng cần sửa:");

        Customer oldCustomer = customerService.findById(id);
        if (oldCustomer == null) {
            System.err.println("Không tìm thấy khách hàng với ID: " + id);
            return;
        }

        boolean isUpdated = false;

        do {
            System.out.println("===== CẬP NHẬT THÔNG TIN KHÁCH HÀNG =====");
            System.out.println("1. Họ tên (hiện tại: " + oldCustomer.getCustomer_name() + ")");
            System.out.println("2. Số điện thoại (hiện tại: " + oldCustomer.getCustomer_phone() + ")");
            System.out.println("3. Email (hiện tại: " + oldCustomer.getCustomer_email() + ")");
            System.out.println("4. Địa chỉ (hiện tại: " + oldCustomer.getCustomer_address() + ")");
            System.out.println("5. Lưu và quay lại");
            System.out.println("==========================================");

            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    String newName = CustomerValidator.validateCustomerName(scanner, "Nhập họ tên mới:");
                    oldCustomer.setCustomer_name(newName);
                    isUpdated = true;
                    break;
                case 2:
                    while (true) {
                        String newPhone = CustomerValidator.validatorPhone(scanner, "Nhập số điện thoại mới:");
                        if (!newPhone.equalsIgnoreCase(oldCustomer.getCustomer_phone()) && customerService.existsByPhone(newPhone)) {
                            System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác.");
                        } else {
                            oldCustomer.setCustomer_phone(newPhone);
                            isUpdated = true;
                            break;
                        }
                    }
                    break;
                case 3:
                while (true) {
                    String newEmail = CustomerValidator.validateEmail(scanner, "Nhập email mới:");
                    if (!newEmail.equalsIgnoreCase(oldCustomer.getCustomer_email()) && customerService.existsByEmail(newEmail)) {
                        System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác.");
                    } else {
                        oldCustomer.setCustomer_email(newEmail);
                        isUpdated = true;
                        break;
                    }
                }
                break;
                case 4:
                    String newAddress = CustomerValidator.validateAddress(scanner, "Nhập địa chỉ mới:");
                    oldCustomer.setCustomer_address(newAddress);
                    isUpdated = true;
                    break;
                case 5:
                    if (isUpdated) {
                        if (customerService.update(oldCustomer)) {
                            System.out.println("Cập nhật khách hàng thành công!");
                            displayListCustomer();
                        } else {
                            System.err.println("Cập nhật khách hàng thất bại!");
                        }
                    } else {
                        System.out.println("Không có thay đổi nào được thực hiện.");
                    }
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ.");
            }
        } while (true);

    }

    public void addCustomer(Scanner scanner) {
        int input = ChoiceValidator.validateInput(scanner, "Nhập vào số lượng cần thêm");
        for (int i = 0; i < input; i++) {
            System.out.println("====== Thêm khách hàng ======");
            String customerName = CustomerValidator.validateCustomerName(scanner, "Nhập tên khách hàng");
            String customerPhone;
            while (true) {
                customerPhone = CustomerValidator.validatorPhone(scanner,"Nhập số điện thoại");
                if (customerService.existsByPhone(customerPhone)) {
                    System.err.println("Số điện thoại đã tồn tại, vui lòng nhập số khác.");
                } else {
                    break;
                }
            }
            String customerEmail;
            while(true){
                customerEmail = CustomerValidator.validateEmail(scanner, "Nhập Email");
                if (customerService.existsByEmail(customerEmail)) {
                    System.err.println("Email đã tồn tại, vui lòng nhập email khác.");
                } else {
                    break;
                }
            }
            String customerAddress = CustomerValidator.validateAddress(scanner, "Nhập địa chỉ");

            Customer c = new Customer();
            c.setCustomer_name(customerName);
            c.setCustomer_phone(customerPhone);
            c.setCustomer_email(customerEmail);
            c.setCustomer_address(customerAddress);

            if (customerService.save(c)) {
                System.out.println("Thêm khách hàng thành công!");
            } else {
                System.out.println("Thêm khách hàng thất bại!");
            }
        }
        displayListCustomer();
    }

    public void displayListCustomer() {
        System.out.println("====DANH SÁCH KHÁCH HÀNG====");
        List<Customer> customer = customerService.findAll();
        printCustomerList(customer);
        System.out.println("============================");
    }

    public void printCustomerList(List<Customer> customer) {
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
//    public void printSize(){
//        int page =1;
//        int
//    }
}
