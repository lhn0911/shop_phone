package ra.edu.presentation;

import ra.edu.business.model.product.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.ChoiceValidator;
import ra.edu.validate.ProductValidator;

import java.util.Scanner;

public class ProductUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductServiceImp();
    public void productMenu() {
        do {
            System.out.println("=======QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("=============================");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    displayListProduct();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");

            }
        } while (true);
    }

    private void updateProduct(Scanner scanner) {
        System.out.println("Nhập id sản phẩm cần cập nhật");
        int id = Integer.parseInt(scanner.nextLine());
        Product old = ProductService.findById
    }

    private void addProduct(Scanner scanner) {
        System.out.println("====== Thêm sản phẩm ======");
        String productName = ProductValidator.validateProductName(scanner,"Nhập tên sản phẩm");
        String productBrand = ProductValidator.validateProductBrand(scanner,"Nhập nhãn hàng");
        double productPrice = ProductValidator.validateProductPrice(scanner,"Nhập giá");
        int productStock = ProductValidator.validateProductStock(scanner,"Nhập tồn kho");
        Product p = new Product();
        p.setProduct_name(productName);
        p.setProduct_brand(productBrand);
        p.setProduct_price(productPrice);
        p.setProduct_stock(productStock);
        if (productService.save(p)) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.out.println("Thêm sản phẩm thất bại!");
        }
    }

    public void displayListProduct() {

        System.out.println("====== DANH SÁCH SẢN PHẨM ======");
        var products = productService.findAll();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong danh sách.");
        } else {
            System.out.printf("%-5s %-25s %-15s %-12s %-10s\n",
                    "ID", "Tên sản phẩm", "Nhãn hiệu", "Giá bán", "Tồn kho");
            for (Product p : products) {
                System.out.printf("%-5d %-25s %-15s %-12.2f %-10d\n",
                        p.getProduct_id(), p.getProduct_name(), p.getProduct_brand(), p.getProduct_price(), p.getProduct_stock());
            }
        }
        System.out.println("================================");

    }
}
