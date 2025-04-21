package ra.edu.business.service.invoicedt;

import ra.edu.business.model.invoiceDetail.InvoiceDetail;
import ra.edu.business.service.AppService;

import java.util.List;

public interface InvoicedtService extends AppService<InvoiceDetail> {
    List<InvoiceDetail> findByInvoiceId(int invoiceId);
}
