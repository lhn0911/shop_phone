package ra.edu.presentation;

import ra.edu.business.model.customer.Customer;
import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.validate.ChoiceValidator;
import ra.edu.validate.CustomerValidator;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    private final Scanner scanner = new Scanner(System.in);
    InvoicedtUI invoicedtUI = new InvoicedtUI();
    private final InvoiceService invoiceService = new InvoiceServiceImp();
    private final CustomerService customerService = new CustomerServiceImp();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final int PAGE_SIZE = 5;
    public void invoiceMenu() {
        do {
            System.out.println("+---------- QUẢN LÝ ĐƠN HÀNG ----------+");
            System.out.println("| 1. Hiển thị danh sách đơn hàng       |");
            System.out.println("| 2. Chi tiết đơn hàng                 |");
            System.out.println("| 3. Thêm mới đơn hàng                 |");
            System.out.println("| 4. Tìm kiếm hóa đơn                  |");
            System.out.println("| 5. Quay lại menu chính               |");
            System.out.println("+--------------------------------------+");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    displayListInvoice();
                    break;
                case 2:
                    displaydetail();
                    break;
                case 3:
                    addNewInvoice();
                    break;
                case 4:
                    searchInvoice();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (true);
    }

    private void displaydetail() {
        List<Invoice> invoices = invoiceService.findAll();
        if (!invoices.isEmpty()) {
            System.out.print("Nhập ID hóa đơn để quản lý (0 để quay lại): ");
            int id = ChoiceValidator.validateChoice(scanner);
            if (id == 0) return;
            Invoice selected = invoiceService.findById(id);
            if (selected != null) {
                invoicedtUI.invoiceDetailMenu(selected);
            } else {
                System.out.println("Không tìm thấy hóa đơn có ID: " + id);
            }
        }
    }

    public void searchInvoice() {
        do {
            System.out.println("+--------------Tìm kiếm đơn hàng--------------+");
            System.out.println("| 1. Tìm kiếm theo tên khách hàng             |");
            System.out.println("| 2. Tìm kiếm theo ngày/tháng/năm(dd/mm/yyyy) |");
            System.out.println("| 3. Quay lại menu chính                      |");
            System.out.println("+---------------------------------------------+");
            int choice = ChoiceValidator.validateChoice(scanner);
            List<Invoice> result = new ArrayList<Invoice>();

            switch (choice) {
                case 1:
                    String name = CustomerValidator.validateCustomerName(scanner, "Nhập tên khách hàng");
                    result = invoiceService.findByCustomerName(name);
                    break;
                case 2:
                    System.out.print("Nhập ngày (dd/MM/yyyy): ");
                    String input = scanner.nextLine();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(input, formatter);

                        int day = date.getDayOfMonth();
                        int month = date.getMonthValue();
                        int year = date.getYear();

                        result = invoiceService.findByTime(day, month, year);
                    } catch (DateTimeParseException e) {
                        System.out.println("Ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
                    }
                    break;

                case 3:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    continue;
            }

            if (result.isEmpty()) {
                System.out.println("Không tìm thấy hóa đơn nào phù hợp.");
            } else {
                System.out.println("====== KẾT QUẢ TÌM KIẾM ======");
                System.out.printf("%-5s %-15s %-20s %-15s%n", "ID", "Mã KH", "Ngày tạo", "Tổng tiền");
                for (Invoice invoice : result) {
                    System.out.printf("%-5d %-15s %-20s %-15.2f%n",
                            invoice.getInvoice_id(),
                            invoice.getCustomer_id(),
                            invoice.getCreated_at(),
                            invoice.getTotal_amount());
                }
            }
        } while (true);
    }


    public void addNewInvoice() {
        System.out.println("\n=== THÊM MỚI ĐƠN HÀNG ===");

        Invoice newInvoice = new Invoice();
        newInvoice.inputData(scanner);

        if (newInvoice.getCustomer_id() == 0) {
            return;
        }

        boolean saved = invoiceService.save(newInvoice);
        if (saved) {
            Customer selectedCustomer = customerService.findById(newInvoice.getCustomer_id());
            System.out.println("Đã tạo hóa đơn cho khách hàng: " + selectedCustomer.getCustomer_name());

            List<Invoice> invoices = invoiceService.findAll();
            Invoice createdInvoice = null;
            for (int j = invoices.size() - 1; j >= 0; j--) {
                if (invoices.get(j).getCustomer_id() == newInvoice.getCustomer_id()) {
                    createdInvoice = invoices.get(j);
                    break;
                }
            }

            if (createdInvoice != null) {
                System.out.println("Mã hóa đơn: " + createdInvoice.getInvoice_id());
                new InvoicedtUI().addProductToInvoice(createdInvoice.getInvoice_id());
            } else {
                System.out.println("Không thể xác định mã hóa đơn vừa tạo!");
            }
        } else {
            System.out.println("Tạo hóa đơn thất bại!");
        }
    }


    public void displayListInvoice() {
        System.out.println("\n====== DANH SÁCH HÓA ĐƠN ======");
        List<Invoice> invoices = invoiceService.findAll();
        int totalPages = (int) Math.ceil((double) invoices.size() / PAGE_SIZE);
        int currentPage = 1;

        String line = "+----+-------+-----------------+-----------------+";
        String header = String.format("| %-2s | %-2s | %-15s | %-15s |", "ID", "Mã KH", "Ngày tạo", "Tổng tiền");

        while (true) {
            int start = (currentPage - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, invoices.size());

            System.out.println(line);
            System.out.println(header);
            System.out.println(line);

            for (int i = start; i < end; i++) {
                Invoice invoice = invoices.get(i);
                System.out.printf("| %-2d | %-5d | %-15s | %-15s |\n",
                        invoice.getInvoice_id(),
                        invoice.getCustomer_id(),
                        invoice.getCreated_at().format(formatter),
                        formatPrice(invoice.getTotal_amount()));
                System.out.println(line);
            }

            System.out.printf("Trang %d/%d\n", currentPage, totalPages);
            System.out.println("(n) next, (p) pre, (e) exit:");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("n")) {
                if (currentPage < totalPages) {
                    currentPage++;
                } else {
                    System.out.println("Đây là trang cuối cùng.");
                }
            } else if (input.equals("p")) {
                if (currentPage > 1) {
                    currentPage--;
                } else {
                    System.out.println("Đây là trang đầu tiên.");
                }
            } else if (input.equals("e")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
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
