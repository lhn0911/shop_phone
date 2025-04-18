package ra.edu.business.dao.customer;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.customer.Customer;

import java.util.List;

public interface CustomerDao extends AppDao<Customer> {
    Customer findById(int id);
    List<Customer> findByName(String name);

}
