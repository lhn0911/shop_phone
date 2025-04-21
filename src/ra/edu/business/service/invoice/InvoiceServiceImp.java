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

    @Override
    public Invoice findById(int id) {
        return invoiceDao.findById(id);
    }
}
