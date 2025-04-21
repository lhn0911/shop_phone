package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.invoice.Invoice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDao{


    @Override
    public List<Invoice> findByCustomerName(String name) {
        return List.of();
    }

    @Override
    public List<Invoice> findByDate(Date date) {
        return List.of();
    }

    @Override
    public List<Invoice> findByMonth(int month, int year) {
        return List.of();
    }

    @Override
    public List<Invoice> findByYear(int year) {
        return List.of();
    }

    @Override
    public Invoice findById(int id) {
        Invoice invoice = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_invoice_ById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                invoice.setCustomer_id(rs.getInt("customer_id"));
            }

        }catch(SQLException e){
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_invoice()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("id"));
                invoice.setCustomer_id(rs.getInt("customer_id"));
                invoice.setCreated_at(rs.getDate("created_at").toLocalDate());;
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                list.add(invoice);
            }

        }catch(SQLException e){
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public boolean save(Invoice invoice) {
        return false;
    }

    @Override
    public boolean update(Invoice invoice) {
        return false;
    }

    @Override
    public boolean delete(Invoice invoice) {
        return false;
    }
}
