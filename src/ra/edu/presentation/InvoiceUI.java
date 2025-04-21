package ra.edu.presentation;

import ra.edu.business.dao.invoice.InvoiceDao;
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

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService = new InvoiceServiceImp();
    private final InvoicedtService invoicedtService = new InvoicedtServiceImp();
    private final ProductService productService = new ProductServiceImp();
    public void invoiceMenu() {
        do {
            System.out.println("======QUẢN LÝ ĐƠN HÀNG======");
            System.out.println("1. Hiển thị danh sách đơn hàng");
            System.out.println("2. Thêm mới đơn hàng");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("============================");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    displayListInvoice();
                    break;
                case 2:
                    // TODO: implement add invoice
                    break;
                case 3:
                    // TODO: implement search invoice
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (true);
    }

    public void displayListInvoice() {
        System.out.println("====== DANH SÁCH HÓA ĐƠN ======");
        List<Invoice> invoices = invoiceService.findAll();
        printInvoiceList(invoices);
        System.out.println("================================");
        if (!invoices.isEmpty()) {
            System.out.print("Nhập ID hóa đơn để quản lý (0 để quay lại): ");
            int id = ChoiceValidator.validateChoice(scanner);
            if (id == 0) return;
            Invoice selected = invoiceService.findById(id);
            if (selected != null) {
                invoiceDetailMenu(selected);
            } else {
                System.out.println("Không tìm thấy hóa đơn có ID: " + id);
            }
        }

    }

    public void invoiceDetailMenu(Invoice invoiceId) {
        do {
            System.out.println("\n--- QUẢN LÝ CHI TIẾT HÓA ĐƠN #" + invoiceId.getInvoice_id() + " ---");
            System.out.println("1. Xem chi tiết hóa đơn");
            System.out.println("2. Thêm sản phẩm vào hóa đơn");
            System.out.println("3. Cập nhật hóa đơn");
            System.out.println("4. Xóa hóa đơn");
            System.out.println("5. Quay lại");
            System.out.println("------------------------------------");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    viewInvoiceDetail(invoiceId.getInvoice_id());
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private void viewInvoiceDetail(int invoiceId) {
        System.out.println("\n=== CHI TIẾT HÓA ĐƠN #" + invoiceId + " ===");
        List<InvoiceDetail> details = invoicedtService.findByInvoiceId(invoiceId);
        if (details.isEmpty()) {
            System.out.println("Không có sản phẩm nào trong hóa đơn này!");
        } else {
            System.out.printf("%-5s %-5s %-20s %-10s %-15s %-15s%n",
                    "ID", "Invoice ID", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
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
            System.out.println("TỔNG TIỀN: " + totalAmount);
        }
        System.out.println("Nhấn Enter để tiếp tục...");
        scanner.nextLine();
    }

    public void printInvoiceList(List<Invoice> invoices) {
        if (invoices.isEmpty()) {
            System.out.println("Không có hóa đơn nào để hiển thị.");
        } else {
            System.out.printf("%-5s %-15s %-20s %-15s\n",
                    "ID", "Mã KH", "Ngày tạo", "Tổng tiền");
            for (Invoice invoice : invoices) {
                System.out.printf("%-5d %-15d %-20s %-15s\n",
                        invoice.getInvoice_id(),
                        invoice.getCustomer_id(),
                        invoice.getCreated_at(),
                        formatPrice(invoice.getTotal_amount()));
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
