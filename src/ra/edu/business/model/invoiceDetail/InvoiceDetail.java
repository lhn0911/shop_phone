package ra.edu.business.model.invoiceDetail;

public class InvoiceDetail {
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
}
