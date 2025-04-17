package ra.edu.business.dao.product;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.product.Product;

import java.util.List;

public interface ProductDao extends AppDao<Product> {
    Product findById(int id);
}
