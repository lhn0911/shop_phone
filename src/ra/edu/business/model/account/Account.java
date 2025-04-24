package ra.edu.business.model.account;

import ra.edu.business.model.IApp;

import java.util.Scanner;

public class Account implements IApp {
    private int id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Tên đăng nhập: ");
        this.username = scanner.nextLine();

        System.out.print("Mật khẩu: ");
        this.password = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "Account{" +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
