package ra.edu.business.model.invoice;

import ra.edu.business.model.IApp;
import ra.edu.business.model.customer.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.validate.ChoiceValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Invoice implements IApp {
    private int invoice_id;
    private int Customer_id;
    private LocalDate created_at;
    private double total_amount;

    public Invoice() {
    }

    public Invoice(int invoice_id, int customer_id, LocalDate created_at, double total_amount) {
        this.invoice_id = invoice_id;
        Customer_id = customer_id;
        this.created_at = created_at;
        this.total_amount = total_amount;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", Customer_id=" + Customer_id +
                ", created_at=" + created_at +
                ", total_amount=" + total_amount +
                '}';
    }

    @Override
    public void inputData(Scanner scanner) {
        CustomerService customerService = new CustomerServiceImp();
        List<Customer> customers = customerService.findAll();
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào trong hệ thống.");
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

        this.setCustomer_id(customerId);
        this.setCreated_at(LocalDate.now());
        this.setTotal_amount(0.0);  // Tạm thời gán 0, sẽ cập nhật sau khi thêm sản phẩm
    }

}
