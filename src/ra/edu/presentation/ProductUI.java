package ra.edu.presentation;

import ra.edu.business.model.product.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.ChoiceValidator;
import ra.edu.validate.ProductValidator;

import java.text.DecimalFormat;
import java.util.List;
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
            System.out.println("5. Tìm kiếm theo tên");
            System.out.println("6. Tìm kiếm theo brand");
            System.out.println("7. Tìm kiếm theo khoảng giá");
            System.out.println("8. Tìm kiếm theo tồn kho");
            System.out.println("9. Quay lại menu chính");
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
                    deleteProduct(scanner);
                    break;
                case 5:
                    searByName(scanner);
                    break;
                case 6:
                    searchByBrand(scanner);
                    break;
                case 7:
                    searchByPrice(scanner);
                    break;
                case 8:
                    searchByStock(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");

            }
        } while (true);
    }

    public void searByName(Scanner scanner) {
        String name =  ProductValidator.validateProductName(scanner, "Nhập tên sản phẩm muốn tìm");
        List<Product> resultList = productService.findByName(name);
        if (resultList.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào thuộc tên \"" + name + "\".");
        }else{
            System.out.println("Sản phẩm tên \"" + name + "\" là :");
            printProductList(resultList);
        }
    }

    public void searchByStock(Scanner scanner) {
        int minStock = ProductValidator.validateProductStock(scanner, "Nhập tồn kho tối thiểu:");
        int maxStock = ProductValidator.validateProductStock(scanner, "Nhập tồn kho tối đa:");

        if (minStock > maxStock) {
            System.err.println("Tồn kho tối thiểu phải nhỏ hơn hoặc bằng tồn kho tối đa!");
            return;
        }

        List<Product> resultList = productService.searchByStock(minStock, maxStock);
        System.out.println("Các sản phẩm có tồn kho từ " + minStock + " đến " + maxStock + ":");
        printProductList(resultList);
    }


    public void searchByPrice(Scanner scanner) {
        double minPrice = ProductValidator.validateProductPrice(scanner, "Nhập giá tối thiểu:");
        double maxPrice = ProductValidator.validateProductPrice(scanner, "Nhập giá tối đa:");

        if (minPrice > maxPrice) {
            System.err.println("Giá tối thiểu phải nhỏ hơn hoặc bằng giá tối đa!");
            return;
        }

        List<Product> resultList = productService.searchByPrice(minPrice, maxPrice);
        System.out.println("Các sản phẩm trong khoảng giá từ " + minPrice + " đến " + maxPrice + ":");
        printProductList(resultList);
    }


    public void searchByBrand(Scanner scanner) {
        String brand = ProductValidator.validateProductBrand(scanner, "Nhập nhãn hàng muốn tìm:");
        List<Product> resultList = productService.searchByBrand(brand);

        if (resultList.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào thuộc nhãn hàng \"" + brand + "\".");
        } else {
            System.out.println("Các sản phẩm thuộc nhãn hàng \"" + brand + "\":");
            printProductList(resultList);
        }
    }

    public void deleteProduct(Scanner scanner) {
        int id = ChoiceValidator.validateInput(scanner, "Nhập id sản phẩm muốn xóa");
        Product product = productService.findById(id);

        if (product != null) {
            System.out.println("Bạn có muốn xóa sản phẩm này không? (y: có, n: không)");
            String confirm = scanner.nextLine().trim().toLowerCase();

            if (confirm.equals("y")) {
                if (productService.delete(product)) {
                    System.out.println("Xoá sản phẩm thành công");
                    displayListProduct();
                } else {
                    System.out.println("Xoá sản phẩm thất bại");
                }
            } else if (confirm.equals("n")) {
                System.out.println("Hủy thao tác xóa.");
                displayListProduct();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Hủy thao tác.");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
        }
    }


    public void updateProduct(Scanner scanner) {
        int id = ChoiceValidator.validateInput(scanner, "Nhập id cần sửa");

        Product oldProduct = productService.findById(id);
        if (oldProduct == null) {
            System.err.println("Không tìm thấy sản phẩm với ID: " + id);
            return;
        }

        boolean isUpdated = false;

        do {
            System.out.println("===== CẬP NHẬT SẢN PHẨM =====");
            System.out.println("1. Tên sản phẩm (hiện tại: " + oldProduct.getProduct_name() + ")");
            System.out.println("2. Nhãn hàng (hiện tại: " + oldProduct.getProduct_brand() + ")");
            System.out.println("3. Giá (hiện tại: " + oldProduct.getProduct_price() + ")");
            System.out.println("4. Tồn kho (hiện tại: " + oldProduct.getProduct_stock() + ")");
            System.out.println("5. Lưu và quay lại");
            System.out.println("=================================");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    while (true) {
                        String newName = ProductValidator.validateProductName(scanner, "Nhập tên mới:");
                        if (!newName.equalsIgnoreCase(oldProduct.getProduct_name()) && productService.existsByName(newName)) {
                            System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác.");
                        } else {
                            oldProduct.setProduct_name(newName);
                            isUpdated = true;
                            break;
                        }
                    }
                    break;
                case 2:
                    String newBrand = ProductValidator.validateProductBrand(scanner, "Nhập nhãn hàng mới:");
                    oldProduct.setProduct_brand(newBrand);
                    isUpdated = true;
                    break;
                case 3:
                    double newPrice = ProductValidator.validateProductPrice(scanner, "Nhập giá mới:");
                    oldProduct.setProduct_price(newPrice);
                    isUpdated = true;
                    break;
                case 4:
                    int newStock = ProductValidator.validateProductStock(scanner, "Nhập tồn kho mới:");
                    oldProduct.setProduct_stock(newStock);
                    isUpdated = true;
                    break;
                case 5:
                    if (isUpdated) {
                        if (productService.update(oldProduct)) {
                            System.out.println("Cập nhật sản phẩm thành công!");
                            displayListProduct();
                        } else {
                            System.err.println("Cập nhật sản phẩm thất bại!");
                        }
                    } else {
                        System.out.println("Không có thay đổi nào được thực hiện.");
                    }
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ.");
            }
        } while (true);
    }


    public void addProduct(Scanner scanner) {
        int input = ChoiceValidator.validateInput(scanner, "Nhập vào số lượng cần thêm");
        for (int i = 0; i < input; i++) {
            System.out.println("====== Thêm sản phẩm ======");

            String productName;
            while (true) {
                productName = ProductValidator.validateProductName(scanner, "Nhập tên sản phẩm");
                if (productService.existsByName(productName)) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập tên khác.");
                } else {
                    break;
                }
            }

            String productBrand = ProductValidator.validateProductBrand(scanner, "Nhập nhãn hàng");
            double productPrice = ProductValidator.validateProductPrice(scanner, "Nhập giá");
            int productStock = ProductValidator.validateProductStock(scanner, "Nhập tồn kho");

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
        displayListProduct();
    }


    public void displayListProduct() {
        System.out.println("====== DANH SÁCH SẢN PHẨM ======");
        List<Product> products = productService.findAll();
        printProductList(products);
        System.out.println("================================");
    }

    public void printProductList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào để hiển thị.");
        } else {
            System.out.printf("%-5s %-25s %-15s %-12s %-10s\n",
                    "ID", "Tên sản phẩm", "Nhãn hiệu", "Giá bán", "Tồn kho");
            for (Product p : products) {
                System.out.printf("%-5d %-25s %-15s %-15s %-10d\n",
                        p.getProduct_id(), p.getProduct_name(), p.getProduct_brand(),
                        formatPrice(p.getProduct_price()), p.getProduct_stock());
            }
        }
    }
    public String formatPrice(double price) {
        if (price == (long) price) {
            return String.format("%,d", (long) price);
        } else {
            return new DecimalFormat("#,###.########").format(price);
        }
    }


}
