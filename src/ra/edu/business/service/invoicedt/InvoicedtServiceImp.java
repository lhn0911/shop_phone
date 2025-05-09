package ra.edu.business.service.invoicedt;

import ra.edu.business.dao.invoiceDT.InvoicedtDao;
import ra.edu.business.dao.invoiceDT.InvoicedtDaoImp;
import ra.edu.business.model.invoiceDetail.InvoiceDetail;

import java.util.List;

public class InvoicedtServiceImp implements InvoicedtService{
    private final InvoicedtDao invoicedtDao;

    public InvoicedtServiceImp() {
        this.invoicedtDao = new InvoicedtDaoImp();
    }

    @Override
    public List<InvoiceDetail> findAll() {
        return invoicedtDao.findAll();
    }

    @Override
    public boolean save(InvoiceDetail invoiceDetail) {
        return invoicedtDao.save(invoiceDetail);
    }

    @Override
    public boolean update(InvoiceDetail invoiceDetail) {
        return invoicedtDao.update(invoiceDetail);
    }

    @Override
    public boolean delete(InvoiceDetail invoiceDetail) {
        return invoicedtDao.delete(invoiceDetail);
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(int invoiceId) {
        return invoicedtDao.findByInvoiceId(invoiceId);
    }
}