package ra.edu.presentation;

import ra.edu.business.model.account.Account;
import ra.edu.business.service.account.AccountService;
import ra.edu.business.service.account.AccountServiceImp;

import java.util.Scanner;

public class LoginUI {
    private static final Scanner scanner = new Scanner(System.in);
    private final AccountService accountService = new AccountServiceImp();

    public Account showLogin() {
        System.out.println("=== ĐĂNG NHẬP HỆ THỐNG ===");
        System.out.print("Tên đăng nhập: ");
        String username = scanner.nextLine();

        System.out.print("Mật khẩu: ");
        String password = scanner.nextLine();

        Account account = accountService.login(username, password);

        if (account != null) {
            System.out.println("Đăng nhập thành công!");
        }

        return account;
    }
}
