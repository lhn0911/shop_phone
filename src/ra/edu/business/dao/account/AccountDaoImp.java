package ra.edu.business.dao.account;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.account.Account;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountDaoImp implements AccountDao{
    @Override
    public Account login(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Account account = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call login(?, ?, ?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            callSt.registerOutParameter(3, java.sql.Types.INTEGER);
            callSt.execute();

            int result = callSt.getInt(3);
            if (result == 1) {
                account = new Account();
                account.setUsername(username);
                account.setPassword(password);
            } else {
                System.out.println("Tên đăng nhập hoặc mật khẩu không đúng.Đăng nhập lại: ");
            }
        }catch(SQLException e){
            System.err.println("Lỗi khi đăng nhập: " + e.getMessage());
        }catch(Exception e){
            System.err.println("Lỗi không xác định, Vui lòng đăng nhập lại");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
            return account;
    }
}
