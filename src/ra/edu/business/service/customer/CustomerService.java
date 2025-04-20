package ra.edu.business.service.customer;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.service.AppService;

import java.util.List;

public interface CustomerService extends AppService<Customer> {
    Customer findById(int id);
    List<Customer> findByName(String customerName);
    List<Customer> findByPhone(String customerphone);
    List<Customer> findByEmail(String customeremail);
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

}
