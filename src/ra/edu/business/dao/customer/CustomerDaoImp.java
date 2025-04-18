package ra.edu.business.dao.customer;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.customer.Customer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImp implements CustomerDao{
    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try( Connection conn = ConnectionDB.openConnection();
             CallableStatement callSt =  conn.prepareCall("{call get_customer_byId(?)}");){
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_phone(rs.getString("customer_phone"));
                customer.setCustomer_address(rs.getString("customer_address"));
            }
        }catch(SQLException e){
            e.fillInStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        try(Connection conn = ConnectionDB.openConnection();
        CallableStatement callSt = conn.prepareCall("{call get_all_customer()}")){
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_phone(rs.getString("customer_phone"));
                customer.setCustomer_address(rs.getString("customer_address"));
                list.add(customer);
            }
        }catch(SQLException e){
            e.fillInStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }
}
