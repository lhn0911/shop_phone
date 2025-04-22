package ra.edu.business.service.invoice;

import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.service.AppService;

import java.util.List;

public interface InvoiceService extends AppService<Invoice> {
    Invoice findById(int id);
    boolean updateTotalAmount(int invoiceId);
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByTime(int date, int month, int year);
}
