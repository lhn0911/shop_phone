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
    public List<Product> findAll(){
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
        return false;
    }


}
