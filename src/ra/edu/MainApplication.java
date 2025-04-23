package ra.edu;

import ra.edu.business.model.account.Account;
import ra.edu.presentation.*;
import ra.edu.validate.ChoiceValidator;

import java.util.Scanner;

public class MainApplication {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        ProductUI productUI = new ProductUI();
        CustomerUI customerUI = new CustomerUI();
        InvoiceUI invoiceUI = new InvoiceUI();
        RevenueUI revenueUI = new RevenueUI();
        Account currentAccount = null;
        do {
            if (currentAccount == null) {
                System.out.println("=====Hệ thống quản lý cửa hàng====");
                System.out.println("1.Đăng nhập ADMIN");
                System.out.println("2.Thoát");
                System.out.println("==================================");
                int choice = ChoiceValidator.validateChoice(scanner);
                switch (choice) {
                    case 1:
                        currentAccount = loginUI.showLogin();
                        break;
                    case 2:
                        System.out.print("Thoát chương trình");
                        System.exit(0);
                        break;
                    default:
                        System.out.print("Lựa chọn không hợp lệ");

                }
            } else {
                System.out.println("=========Menu chính========");
                System.out.println("1. Quản lý sản phẩm điện thoại");
                System.out.println("2. Quản lý khách hàng");
                System.out.println("3. Quản lý hóa đơn");
                System.out.println("4. Thống kê doanh thu");
                System.out.println("5. Đăng xuất");
                int choice = ChoiceValidator.validateChoice(scanner);
                switch (choice) {
                    case 1:
                        productUI.productMenu();
                        break;
                    case 2:
                        customerUI.CustomerMenu();
                        break;
                    case 3:
                        invoiceUI.invoiceMenu();
                        break;
                    case 4:
                        revenueUI.RevenueMenu();
                        break;
                    case 5:
                        currentAccount = null;
                        System.out.println("Đăng xuất thành công");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                }
            }
        } while (true);
    }
}
