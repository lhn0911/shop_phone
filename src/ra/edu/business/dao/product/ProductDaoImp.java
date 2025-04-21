package ra.edu.business.dao.product;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.product.Product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {
    @Override
    public Product findById(int id) {
        Product product = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_byId(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_brand(rs.getString("product_brand"));
                product.setProduct_price(rs.getDouble("product_price"));
                product.setProduct_stock(rs.getInt("product_stock"));
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_byName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_brand(rs.getString("product_brand"));
                product.setProduct_price(rs.getDouble("product_price"));
                product.setProduct_stock(rs.getInt("product_stock"));
                list.add(product);
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_product_byBrand(?)}");
            callSt.setString(1, brand);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_brand(rs.getString("product_brand"));
                p.setProduct_price(rs.getDouble("product_price"));
                p.setProduct_stock(rs.getInt("product_stock"));
                list.add(p);
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Product> searchByPrice(double minPrice, double maxPrice) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_product_byPrice(?, ?)}");
            callSt.setDouble(1, minPrice);
            callSt.setDouble(2, maxPrice);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_brand(rs.getString("product_brand"));
                p.setProduct_price(rs.getDouble("product_price"));
                p.setProduct_stock(rs.getInt("product_stock"));
                list.add(p);
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Product> searchByStock(int minStock, int maxStock) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_product_byStock(?, ?)}");
            callSt.setInt(1, minStock);
            callSt.setInt(2, maxStock);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_brand(rs.getString("product_brand"));
                p.setProduct_price(rs.getDouble("product_price"));
                p.setProduct_stock(rs.getInt("product_stock"));
                list.add(p);
            }
        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProduct_id(rs.getInt("product_id"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_brand(rs.getString("product_brand"));
                p.setProduct_price(rs.getDouble("product_price"));
                p.setProduct_stock(rs.getInt("product_stock"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return list;
    }

    @Override
    public boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_product(?, ?, ?, ?)}");
            callSt.setString(1, product.getProduct_name());
            callSt.setString(2, product.getProduct_brand());
            callSt.setDouble(3, product.getProduct_price());
            callSt.setInt(4, product.getProduct_stock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Product p) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?, ?, ?, ?, ?)}");
            callSt.setInt(1, p.getProduct_id());
            callSt.setString(2, p.getProduct_name());
            callSt.setString(3, p.getProduct_brand());
            callSt.setDouble(4, p.getProduct_price());
            callSt.setInt(5, p.getProduct_stock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, product.getProduct_id());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.fillInStackTrace();
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
