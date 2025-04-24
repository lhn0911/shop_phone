package ra.edu.presentation;

import ra.edu.business.dao.invoice.RevenueDTO;
import ra.edu.business.model.invoice.Invoice;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.validate.ChoiceValidator;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RevenueUI {
    private final Scanner scanner = new Scanner(System.in);
    private final InvoiceService invoiceService = new InvoiceServiceImp();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void RevenueMenu() {
        do {
            System.out.println("====== THỐNG KÊ DOANH THU ======");
            System.out.println("1. Thống kê doanh thu theo các ngày");
            System.out.println("2. Thống kê doanh thu theo các tháng");
            System.out.println("3. Thống kê doanh thu theo các năm");
            System.out.println("4. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = ChoiceValidator.validateChoice(scanner);
            switch (choice) {
                case 1:
                    RevenueByDay();
                    break;
                case 2:
                    RevenueByMonth();
                    break;
                case 3:
                    RevenueByYear();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (true);
    }

    public void RevenueByDay() {
        List<RevenueDTO> list = invoiceService.revenueByDay();
        printRevenueListPaginated(list, "DOANH THU THEO NGÀY");
    }

    public void RevenueByMonth() {
        List<RevenueDTO> list = invoiceService.revenueByMonth();
        printRevenueListPaginated(list, "DOANH THU THEO THÁNG");
    }

    public void RevenueByYear() {
        List<RevenueDTO> list = invoiceService.revenueByYear();
        printRevenueListPaginated(list, "DOANH THU THEO NĂM");
    }

    public void printRevenueListPaginated(List<RevenueDTO> list, String title) {
        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu doanh thu.");
            return;
        }

        final int PAGE_SIZE = 5;
        int totalPages = (int) Math.ceil((double) list.size() / PAGE_SIZE);
        int currentPage = 1;

        String line = "+---------------------+----------------------+";
        String header = String.format("| %-19s | %-20s |", "Thời gian", "Tổng doanh thu");

        while (true) {
            System.out.println("\n===== " + title + " =====");
            int start = (currentPage - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, list.size());

            System.out.println(line);
            System.out.println(header);
            System.out.println(line);
            for (int i = start; i < end; i++) {
                RevenueDTO dto = list.get(i);
                String timeString;

                if (dto.getDate() != null) {
                    timeString = dto.getDate().format(formatter);
                } else if (dto.getMonth() != null && dto.getYear() != null) {
                    timeString = String.format("%02d/%d", dto.getMonth(), dto.getYear());
                } else if (dto.getYear() != null) {
                    timeString = String.valueOf(dto.getYear());
                } else {
                    timeString = "Không xác định";
                }

                System.out.printf("| %-19s | %-20.2f |\n", timeString, dto.getTotalAmount());
            }

            System.out.println(line);
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
}
