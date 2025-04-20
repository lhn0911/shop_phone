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
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt =  conn.prepareCall("{call get_customer_byId(?)}");
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
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customer;
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_customer_byName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_phone(rs.getString("customer_phone"));
                customer.setCustomer_address(rs.getString("customer_address"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch(Exception e){
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Customer> findByEmail(String email) {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_by_email(?)}");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_phone(rs.getString("customer_phone"));
                customer.setCustomer_address(rs.getString("customer_address"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch(Exception e){
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Customer> findByPhone(String phone) {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_by_phone(?)}");
            callSt.setString(1, phone);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomer_id(rs.getInt("customer_id"));
                customer.setCustomer_name(rs.getString("customer_name"));
                customer.setCustomer_email(rs.getString("customer_email"));
                customer.setCustomer_phone(rs.getString("customer_phone"));
                customer.setCustomer_address(rs.getString("customer_address"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch(Exception e){
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_customer()}");
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
        }catch(Exception e){
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public boolean save(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_customer(?, ?, ?, ?)}");
            callSt.setString(1, customer.getCustomer_name());
            callSt.setString(2, customer.getCustomer_phone());
            callSt.setString(3, customer.getCustomer_email());
            callSt.setString(4, customer.getCustomer_address());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_customer(?, ?, ?, ?, ?)}");
            callSt.setInt(1, customer.getCustomer_id());
            callSt.setString(2, customer.getCustomer_name());
            callSt.setString(3, customer.getCustomer_phone());
            callSt.setString(4, customer.getCustomer_email());
            callSt.setString(5, customer.getCustomer_address());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_customer(?)}");
            callSt.setInt(1, customer.getCustomer_id());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
