package ra.edu.presentation;

import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.model.invoiceDetail.InvoiceDetail;
import ra.edu.business.model.product.Product;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.business.service.invoicedt.InvoicedtService;
import ra.edu.business.service.invoicedt.InvoicedtServiceImp;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.ChoiceValidator;

import java.util.List;
import java.util.Scanner;

public class InvoicedtUI {
    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService = new ProductServiceImp();
    private final InvoicedtService invoicedtService = new InvoicedtServiceImp();
    private final InvoiceService invoiceService = new InvoiceServiceImp();

    public void invoiceDetailMenu(Invoice invoice) {
        do {
            System.out.println("\n--- QUẢN LÝ CHI TIẾT HÓA ĐƠN #" + invoice.getInvoice_id() + " ---");
            System.out.println("1. Xem chi tiết hóa đơn");
            System.out.println("2. Thêm sản phẩm vào hóa đơn");
            System.out.println("3. Cập nhật hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Quay lại");
            System.out.println("------------------------------------");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    viewInvoiceDetail(invoice.getInvoice_id());
                    break;
                case 2:
                    addProductToInvoice(invoice.getInvoice_id());
                    break;
                case 3:
                    // TODO: cập nhật chi tiết hóa đơn (nếu cần)
                    break;
                case 4:
                    // TODO: xóa chi tiết hóa đơn (nếu cần)
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    public void addProductToInvoice(int invoiceId) {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm trong hệ thống.");
            return;
        }

        while (true) {
            System.out.println("----- DANH SÁCH SẢN PHẨM -----");
            System.out.printf("%-5s %-20s %-10s%n", "ID", "Tên sản phẩm", "Giá");
            for (Product product : products) {
                System.out.printf("%-5d %-20s %-10.2f%n", product.getProduct_id(), product.getProduct_name(), product.getProduct_price());
            }

            System.out.print("Nhập ID sản phẩm muốn thêm (0 để thoát): ");
            int productId = ChoiceValidator.validateChoice(scanner);
            if (productId == 0) break;

            Product selectedProduct = productService.findById(productId);
            if (selectedProduct == null) {
                System.out.println("Sản phẩm không tồn tại.");
                continue;
            }

            int quantity = ChoiceValidator.validateInput(scanner, "Nhập số lượng sản phẩm: ");
            double unitPrice = selectedProduct.getProduct_price();

            InvoiceDetail detail = new InvoiceDetail(invoiceId, productId, quantity, unitPrice);
            boolean saved = invoicedtService.save(detail);
            if (saved) {
                System.out.println("Đã thêm sản phẩm vào hóa đơn!");
                boolean updated = invoiceService.updateTotalAmount(invoiceId);
                if (updated) {
                    System.out.println("Tổng tiền hóa đơn sau khi cập nhật: " + invoiceService.findById(invoiceId).getTotal_amount());
                }
            } else {
                System.out.println("Thêm sản phẩm vào hóa đơn thất bại!");
            }
        }
    }

    public void viewInvoiceDetail(int invoiceId) {
        System.out.println("\n=== CHI TIẾT HÓA ĐƠN #" + invoiceId + " ===");
        List<InvoiceDetail> details = invoicedtService.findByInvoiceId(invoiceId);
        if (details.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hóa đơn này!");
        } else {
            System.out.printf("%-5s %-5s %-20s %-10s %-15s %-15s%n",
                    "ID", "Mã DH", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
            for (InvoiceDetail detail : details) {
                Product product = productService.findById(detail.getProduct_id());
                String productName = product != null ? product.getProduct_name() : "Không xác định";
                System.out.printf("%-5d %-5d %-20s %-10d %-15.2f %-15.2f%n",
                        detail.getInvoicedt_id(),
                        detail.getInvoice_id(),
                        productName,
                        detail.getQuantity(),
                        detail.getUnit_price(),
                        detail.getQuantity() * detail.getUnit_price());
            }
            System.out.println("------------------------------------------------------");
            double totalAmount = details.stream()
                    .mapToDouble(detail -> detail.getQuantity() * detail.getUnit_price())
                    .sum();
            System.out.printf("TỔNG TIỀN: %.2f%n", totalAmount);
        }
    }
}
