package ra.edu.business.service.product;

import ra.edu.business.model.product.Product;
import ra.edu.business.service.AppService;

public interface ProductService extends AppService<Product> {
    Product findById(int id);
}
