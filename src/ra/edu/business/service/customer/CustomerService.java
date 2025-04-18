package ra.edu.business.service.customer;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.service.AppService;

import java.util.List;

public interface CustomerService extends AppService<Customer> {
    Customer findById(int id);
    List<Customer> findByName(String customerName);
    boolean existsByName(String name);
}
