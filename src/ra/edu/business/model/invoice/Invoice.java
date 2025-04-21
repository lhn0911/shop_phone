package ra.edu.business.model.invoice;

import java.time.LocalDate;

public class Invoice {
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
}
