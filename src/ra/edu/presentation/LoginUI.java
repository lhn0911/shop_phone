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
        Account account = new Account();
        account.inputData(scanner);

        Account result = accountService.login(account.getUsername(), account.getPassword());

        if (result != null) {
            System.out.println("Đăng nhập thành công!");
        } else {
            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.");
        }

        return result;
    }

}
