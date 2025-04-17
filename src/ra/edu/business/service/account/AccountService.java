package ra.edu.business.service.account;


import ra.edu.business.model.account.Account;

public interface AccountService {
    Account login(String username, String password);

}