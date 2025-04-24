package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.invoice.Invoice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDao{


    @Override
    public List<Invoice> findByCustomerName(String name) {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_customer_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                invoice.setCustomer_id(rs.getInt("customer_id"));
                invoice.setCreated_at(rs.getDate("created_at").toLocalDate());
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                list.add(invoice);
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
                invoice.setCreated_at(rs.getDate("created_at").toLocalDate());;
                invoice.setTotal_amount(rs.getDouble("total_amount"));
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
    public boolean updateTotalAmount(int invoiceId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_invoice_totalamount(?)}");
            callSt.setInt(1, invoiceId);
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

    @Override
    public List<Invoice> findByTime(int date, int month, int year) {
        List<Invoice> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_time(?,?,?)}");
            callSt.setInt(1, date);
            callSt.setInt(2, month);
            callSt.setInt(3, year);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoice_id(rs.getInt("invoice_id"));
                invoice.setCustomer_id(rs.getInt("customer_id"));
                invoice.setCreated_at(rs.getDate("created_at").toLocalDate());
                invoice.setTotal_amount(rs.getDouble("total_amount"));
                list.add(invoice);
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
    public List<RevenueDTO> revenueByDay() {
        List<RevenueDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_day()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                RevenueDTO dto = new RevenueDTO();
                dto.setDate(rs.getDate("created_at").toLocalDate());
                dto.setTotalAmount(rs.getDouble("total_amount"));
                list.add(dto);
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
    public List<RevenueDTO> revenueByMonth() {
        List<RevenueDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_month()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                RevenueDTO dto = new RevenueDTO();
                dto.setMonth(rs.getInt("month"));
                dto.setYear(rs.getInt("year"));
                dto.setTotalAmount(rs.getDouble("total_amount"));
                list.add(dto);
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
    public List<RevenueDTO> revenueByYear() {
        List<RevenueDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_year()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                RevenueDTO dto = new RevenueDTO();
                dto.setYear(rs.getInt("year"));
                dto.setTotalAmount(rs.getDouble("total_amount"));
                list.add(dto);
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
                invoice.setInvoice_id(rs.getInt("invoice_id"));
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
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_invoice(?)}");
            callSt.setInt(1, invoice.getCustomer_id());
            callSt.executeUpdate();
            return true;
        }catch(SQLException e){
            e.fillInStackTrace();
        }catch(Exception e){
            e.fillInStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
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
