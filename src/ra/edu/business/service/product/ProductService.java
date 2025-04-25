package ra.edu.business.service.product;

import ra.edu.business.model.product.Product;
import ra.edu.business.service.AppService;

import java.util.List;

public interface ProductService extends AppService<Product> {
    Product findById(int id);
    List<Product> findByName(String name);
    boolean existsByName(String name);
    List<Product> searchByBrand(String brand);
    List<Product> searchByPrice(double minPrice, double maxPrice);
    List<Product> searchByStock(int minStock, int maxStock);
    List<Product> searchByName(String name);
}
