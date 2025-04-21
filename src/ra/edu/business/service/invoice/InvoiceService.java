package ra.edu.business.service.invoice;

import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.service.AppService;

public interface InvoiceService extends AppService<Invoice> {
    Invoice findById(int id);
}
