package ra.edu.business.dao.account;

import ra.edu.business.model.account.Account;

public interface AccountDao {
    Account login(String username, String password);
}
