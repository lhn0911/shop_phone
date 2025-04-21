package ra.edu.presentation;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.validate.ChoiceValidator;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService = new InvoiceServiceImp();
    private final CustomerService customerService = new CustomerServiceImp();

    public void invoiceMenu() {
        do {
            System.out.println("\n====== QUẢN LÝ ĐƠN HÀNG ======");
            System.out.println("1. Hiển thị danh sách đơn hàng");
            System.out.println("2. Thêm mới đơn hàng");
            System.out.println("3. Tìm kiếm hóa đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("===============================");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1 -> displayListInvoice();
                case 2 -> addNewInvoice();
                case 3 -> {
                    // TODO: implement search invoice
                    System.out.println("Chức năng đang được phát triển...");
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ");
            }
        } while (true);
    }

    private void addNewInvoice() {
        int input = ChoiceValidator.validateInput(scanner, "Nhập vào số lượng hóa đơn cần thêm: ");
        for (int i = 0; i < input; i++) {
            System.out.println("\n=== THÊM MỚI ĐƠN HÀNG ===");

            List<Customer> customers = customerService.findAll();
            if (customers.isEmpty()) {
                System.out.println("Không có khách hàng nào trong hệ thống. Vui lòng thêm khách hàng trước!");
                return;
            }

            System.out.println("--- DANH SÁCH KHÁCH HÀNG ---");
            System.out.printf("%-5s %-20s %-15s%n", "ID", "Tên khách hàng", "SĐT");
            for (Customer customer : customers) {
                System.out.printf("%-5d %-20s %-15s%n",
                        customer.getCustomer_id(),
                        customer.getCustomer_name(),
                        customer.getCustomer_phone());
            }

            System.out.print("Chọn ID khách hàng: ");
            int customerId = ChoiceValidator.validateChoice(scanner);
            Customer selectedCustomer = customerService.findById(customerId);
            if (selectedCustomer == null) {
                System.out.println("Khách hàng không tồn tại!");
                return;
            }

            Invoice newInvoice = new Invoice();
            newInvoice.setCustomer_id(customerId);
            newInvoice.setCreated_at(LocalDate.now());
            newInvoice.setTotal_amount(0.0);

            boolean saved = invoiceService.save(newInvoice);
            if (saved) {
                System.out.println("Đã tạo hóa đơn cho khách hàng: " + selectedCustomer.getCustomer_name());

                List<Invoice> invoices = invoiceService.findAll();
                Invoice createdInvoice = null;
                for (int j = invoices.size() - 1; j >= 0; j--) {
                    if (invoices.get(j).getCustomer_id() == customerId) {
                        createdInvoice = invoices.get(j);
                        break;
                    }
                }
                if (createdInvoice != null) {
                    System.out.println("Mã hóa đơn: " + createdInvoice.getInvoice_id());
                    System.out.print("Bạn có muốn thêm sản phẩm vào hóa đơn này? (Y/N): ");
                    String choice = scanner.nextLine().trim().toUpperCase();
                    if (choice.equals("Y")) {
                        new InvoicedtUI().addProductToInvoice(createdInvoice.getInvoice_id());
                    }
                } else {
                    System.out.println("Không thể xác định mã hóa đơn vừa tạo!");
                }
            } else {
                System.out.println("Tạo hóa đơn thất bại!");
            }
        }
    }

    public void displayListInvoice() {
        System.out.println("\n====== DANH SÁCH HÓA ĐƠN ======");
        List<Invoice> invoices = invoiceService.findAll();
        printInvoiceList(invoices);
        System.out.println("================================");
        if (!invoices.isEmpty()) {
            System.out.print("Nhập ID hóa đơn để quản lý (0 để quay lại): ");
            int id = ChoiceValidator.validateChoice(scanner);
            if (id == 0) return;
            Invoice selected = invoiceService.findById(id);
            if (selected != null) {
                new InvoicedtUI().invoiceDetailMenu(selected);
            } else {
                System.out.println("Không tìm thấy hóa đơn có ID: " + id);
            }
        }
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
