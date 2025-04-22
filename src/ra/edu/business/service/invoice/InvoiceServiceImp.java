package ra.edu.business.service.invoice;

import ra.edu.business.dao.invoice.InvoiceDao;
import ra.edu.business.dao.invoice.InvoiceDaoImp;
import ra.edu.business.model.invoice.Invoice;

import java.util.List;

public class InvoiceServiceImp implements InvoiceService{
    private final InvoiceDao invoiceDao;
    public InvoiceServiceImp() {
        this.invoiceDao = new InvoiceDaoImp();
    }
    @Override
    public List<Invoice> findAll() {
        return invoiceDao.findAll();
    }

    @Override
    public boolean save(Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    public boolean update(Invoice invoice) {
        return invoiceDao.update(invoice);
    }

    @Override
    public boolean delete(Invoice invoice) {
        return invoiceDao.delete(invoice);
    }

    @Override
    public Invoice findById(int id) {
        return invoiceDao.findById(id);
    }

    @Override
    public boolean updateTotalAmount(int invoiceId) {
        return invoiceDao.updateTotalAmount(invoiceId);
    }

    @Override
    public List<Invoice> findByCustomerName(String name) {
        return invoiceDao.findByCustomerName(name);
    }

    @Override
    public List<Invoice> findByDate(int date) {
        return invoiceDao.findByDate(date);
    }

    @Override
    public List<Invoice> findByMonth(int month) {
        return invoiceDao.findByMonth(month);
    }

    @Override
    public List<Invoice> findByYear(int year) {
        return invoiceDao.findByYear(year);
    }
}