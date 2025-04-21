package ra.edu.business.dao.invoiceDT;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.invoiceDetail.InvoiceDetail;

import java.util.List;

public interface InvoicedtDao extends AppDao<InvoiceDetail> {
    List<InvoiceDetail> findByInvoiceId(int invoiceId);
}
