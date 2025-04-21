package ra.edu.business.dao.invoice;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.invoice.Invoice;

import java.util.Date;
import java.util.List;

public interface InvoiceDao extends AppDao<Invoice> {
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByDate(Date date);
    List<Invoice> findByMonth(int month, int year);
    List<Invoice> findByYear(int year);
    Invoice findById(int id);
    boolean updateTotalAmount(int invoiceId);

}
