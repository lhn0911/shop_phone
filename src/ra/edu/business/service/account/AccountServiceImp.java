package ra.edu.business.service.account;

import ra.edu.business.dao.account.AccountDao;
import ra.edu.business.dao.account.AccountDaoImp;
import ra.edu.business.model.account.Account;

public class AccountServiceImp implements AccountService{
    private AccountDao accountDao = new AccountDaoImp();

    @Override
    public Account login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Tên đăng nhập hoặc mật khẩu không hợp lệ.");
            return null;
        }

        return accountDao.login(username, password);
    }
}
