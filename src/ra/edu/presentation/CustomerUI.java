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
            System.out.println("+---------QUẢN LÝ KHÁCH HÀNG---------+");
            System.out.println("| 1. Hiển thị danh sách khách hàng   |");
            System.out.println("| 2. Thêm khách hàng                 |");
            System.out.println("| 3. Cập nhật khách hàng             |");
            System.out.println("| 4. Xóa khách hàng                  |");
            System.out.println("| 5. Tìm kiếm khách hàng theo tên    |");
            System.out.println("| 6. Quay lại menu chính             |");
            System.out.println("+------------------------------------+");
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
            printCustomerListPaginated(resultList);
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
            String line = "+-----+---------------------------+----------------------------------------+";
            System.out.println(line);
            System.out.println(" CẬP NHẬT THÔNG TIN KHÁCH HÀNG ");
            System.out.println(line);
            System.out.printf("| %-1s | %-25s | %-38s |\n", "STT", "Thông tin", "Giá trị hiện tại");
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "1", "Họ tên", oldCustomer.getCustomer_name());
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "2", "Số điện thoại", oldCustomer.getCustomer_phone());
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "3", "Email", oldCustomer.getCustomer_email());
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "4", "Địa chỉ", oldCustomer.getCustomer_address());
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "5", "Lưu", "");
            System.out.println(line);
            System.out.printf("| %-3s | %-25s | %-38s |\n", "6", "Quay Lại","");
            System.out.println(line);

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
                case 6:
                    isUpdated = false;
                    if (isUpdated) {
                        System.out.println("Các thay đổi đã bị hủy. Không có dữ liệu nào được cập nhật.");
                    } else {
                        System.out.println("Quay lại mà không có thay đổi nào.");
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
            Customer c = new Customer();
            c.inputData(scanner);

            if (customerService.save(c)) {
                System.out.println("Thêm khách hàng thành công!");
            } else {
                System.out.println("Thêm khách hàng thất bại!");
            }
        }
        displayListCustomer();
    }

    public void displayListCustomer() {
        List<Customer> customerList = customerService.findAll();
        printCustomerListPaginated(customerList);
    }

    public void printCustomerListPaginated(List<Customer> customerList) {
        if (customerList.isEmpty()) {
            System.out.println("Không có khách hàng nào để hiển thị.");
            return;
        }
        System.out.println("+----------------------------------------------- DANH SÁCH KHÁCH HÀNG -----------------------------------------------+");

        final int PAGE_SIZE = 5;
        int totalPages = (int) Math.ceil((double) customerList.size() / PAGE_SIZE);
        int currentPage = 1;
        String line = "+-----+---------------------------+----------------------+------------------------------+------------------------------+";
        String header = String.format("| %-3s | %-25s | %-20s | %-28s | %-28s |",
                "ID", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ");

        while (true) {
            int start = (currentPage - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, customerList.size());
            System.out.println(line);
            System.out.println(header);
            System.out.println(line);
            for (int i = start; i < end; i++) {
                Customer c = customerList.get(i);
                System.out.printf("| %-3d | %-25s | %-20s | %-28s | %-28s |\n",
                        c.getCustomer_id(),
                        c.getCustomer_name(),
                        c.getCustomer_phone(),
                        c.getCustomer_email(),
                        c.getCustomer_address());
                System.out.println(line);
            }

            System.out.printf("Trang %d/%d\n", currentPage, totalPages);

            if (currentPage == 1 && totalPages == 1) {
                System.out.println("(e) exit:");
            } else if (currentPage == 1) {
                System.out.println("(n) next, (e) exit:");
            } else if (currentPage == totalPages) {
                System.out.println("(p) pre, (e) exit:");
            } else {
                System.out.println("(n) next, (p) pre, (e) exit:");
            }

            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("n")) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("Đây là trang cuối cùng.");
                }
            } else if (input.equals("p")) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("Đây là trang đầu tiên.");
                }
            } else if (input.equals("e")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}