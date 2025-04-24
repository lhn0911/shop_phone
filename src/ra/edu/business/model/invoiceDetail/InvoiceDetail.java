package ra.edu.business.model.invoiceDetail;

import ra.edu.business.model.IApp;
import ra.edu.business.model.product.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.ChoiceValidator;

import java.util.List;
import java.util.Scanner;

public class InvoiceDetail implements IApp {
    private int invoicedt_id;
    private int invoice_id;
    private int product_id;
    private int quantity;
    private double unit_price;

    public InvoiceDetail() {
    }

    public InvoiceDetail(int invoice_id, int product_id, int quantity, double unit_price) {
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }


    public int getInvoicedt_id() {
        return invoicedt_id;
    }

    public void setInvoicedt_id(int invoicedt_id) {
        this.invoicedt_id = invoicedt_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "InvoiceDetail{" +
                "invoicedt_id=" + invoicedt_id +
                ", invoice_id=" + invoice_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }

    @Override
    public void inputData(Scanner scanner) {
        ProductService productService = new ProductServiceImp();

        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm trong hệ thống.");
            return;
        }

        System.out.println("----- DANH SÁCH SẢN PHẨM -----");
        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "Tên sản phẩm", "Giá", "Tồn kho");
        for (Product product : products) {
            System.out.printf("%-5d %-20s %-10.2f %-10d%n",
                    product.getProduct_id(), product.getProduct_name(), product.getProduct_price(), product.getProduct_stock());
        }

        Product selectedProduct = null;
        while (selectedProduct == null) {
            System.out.print("Nhập ID sản phẩm muốn thêm: ");
            int productId = ChoiceValidator.validateChoice(scanner);
            selectedProduct = productService.findById(productId);
            if (selectedProduct == null) {
                System.out.println("Sản phẩm không tồn tại. Vui lòng thử lại.");
            } else {
                this.setProduct_id(productId);
            }
        }

        int availableStock = selectedProduct.getProduct_stock();
        int quantity;
        while (true) {
            quantity = ChoiceValidator.validateInput(scanner, "Nhập số lượng sản phẩm: ");
            if (quantity <= availableStock) {
                break;
            }
            System.out.println("Số lượng vượt quá tồn kho. Tồn kho hiện tại: " + availableStock);
        }

        this.setQuantity(quantity);
        this.setUnit_price(selectedProduct.getProduct_price());
    }



}
