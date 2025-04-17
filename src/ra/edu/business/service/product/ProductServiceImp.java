package ra.edu.business.service.product;

import ra.edu.business.dao.product.ProductDao;
import ra.edu.business.dao.product.ProductDaoImp;
import ra.edu.business.model.product.Product;

import java.util.List;

public class ProductServiceImp implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImp() {
        this.productDao = new ProductDaoImp();
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }


    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productDao.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return !productDao.findByName(name.trim()).isEmpty();
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        return productDao.searchByBrand(brand);
    }

    @Override
    public List<Product> searchByPrice(double minPrice, double maxPrice) {
        return productDao.searchByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Product> searchByStock(int minStock, int maxStock) {
        return productDao.searchByStock(minStock, maxStock);
    }

}
