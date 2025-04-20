package ra.edu.business.service.customer;

import ra.edu.business.dao.customer.CustomerDao;
import ra.edu.business.dao.customer.CustomerDaoImp;
import ra.edu.business.model.customer.Customer;

import java.util.List;

public class CustomerServiceImp implements CustomerService{
    private final CustomerDao customerDao;

    public CustomerServiceImp() {
        this.customerDao = new CustomerDaoImp();
    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findByName(String customerName) {
        return customerDao.findByName(customerName);
    }

    @Override
    public List<Customer> findByPhone(String customerphone) {
        return customerDao.findByPhone(customerphone);
    }

    @Override
    public List<Customer> findByEmail(String customeremail) {
        return customerDao.findByEmail(customeremail);
    }


    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean save(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDao.delete(customer);
    }
    @Override
    public boolean existsByPhone(String phone) {
        return !customerDao.findByPhone(phone.trim()).isEmpty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return !customerDao.findByEmail(email.trim()).isEmpty();
    }


}
