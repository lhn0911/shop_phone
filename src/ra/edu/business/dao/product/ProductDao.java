package ra.edu.business.dao.product;

import ra.edu.business.dao.AppDao;
import ra.edu.business.model.product.Product;

import java.util.List;

public interface ProductDao extends AppDao<Product> {
    Product findById(int id);
    List<Product> findByName(String name);
    List<Product> searchByBrand(String brand);
    List<Product> searchByPrice(double minPrice, double maxPrice);
    List<Product> searchByStock(int minStock, int maxStock);
    List<Product> searchByName(String name);
}
