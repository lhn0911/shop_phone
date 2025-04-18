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
        return List.of();
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }
    @Override
    public boolean existsByName(String name) {
        return !customerDao.findByName(name.trim()).isEmpty();
    }
}
