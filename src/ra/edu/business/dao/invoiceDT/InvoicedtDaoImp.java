package ra.edu.business.dao.invoiceDT;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.model.invoiceDetail.InvoiceDetail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoicedtDaoImp implements InvoicedtDao {
    @Override
    public List<InvoiceDetail> findAll() {
        List<InvoiceDetail> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_invoice_detail()}");
            ResultSet rs = callSt.executeQuery();
            while(rs.next()){
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoicedt_id(rs.getInt("id"));
                invoiceDetail.setInvoice_id(rs.getInt("invoice_id"));
                invoiceDetail.setProduct_id(rs.getInt("product_id"));
                invoiceDetail.setQuantity(rs.getInt("quantity"));
                invoiceDetail.setUnit_price(rs.getDouble("unit_price"));
                list.add(invoiceDetail);
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
    public boolean save(InvoiceDetail invoiceDetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_invoice_detail(?, ?, ?, ?)}");
            callSt.setInt(1,invoiceDetail.getInvoice_id());
            callSt.setInt(2,invoiceDetail.getProduct_id());
            callSt.setInt(3,invoiceDetail.getQuantity());
            callSt.setDouble(4,invoiceDetail.getUnit_price());
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
    public boolean update(InvoiceDetail invoiceDetail) {
        return false;
    }

    @Override
    public boolean delete(InvoiceDetail invoiceDetail) {
        return false;
    }


    @Override
    public List<InvoiceDetail> findByInvoiceId(int invoiceId) {
        List<InvoiceDetail> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_invoice_detail_by_invoice_id(?)}");
            callSt.setInt(1, invoiceId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                InvoiceDetail invoicedt = new InvoiceDetail();
                invoicedt.setInvoicedt_id(rs.getInt("invoicedt_id"));
                invoicedt.setInvoice_id(rs.getInt("invoice_id"));
                invoicedt.setProduct_id(rs.getInt("product_id"));
                invoicedt.setQuantity(rs.getInt("quantity"));
                invoicedt.setUnit_price(rs.getDouble("unit_price"));
                list.add(invoicedt);
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
}
