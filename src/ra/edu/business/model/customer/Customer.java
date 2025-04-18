package ra.edu.business.model.customer;

public class Customer {
    private int Customer_id;
    private String Customer_name;
    private String Customer_phone;
    private String Customer_email;
    private String Customer_address;

    public Customer() {
    }

    public Customer(int customer_id, String customer_name, String customer_phone, String customer_email, String customer_address) {
        Customer_id = customer_id;
        Customer_name = customer_name;
        Customer_phone = customer_phone;
        Customer_email = customer_email;
        Customer_address = customer_address;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return Customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        Customer_phone = customer_phone;
    }

    public String getCustomer_email() {
        return Customer_email;
    }

    public void setCustomer_email(String customer_email) {
        Customer_email = customer_email;
    }

    public String getCustomer_address() {
        return Customer_address;
    }

    public void setCustomer_address(String customer_address) {
        Customer_address = customer_address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Customer_id=" + Customer_id +
                ", Customer_name='" + Customer_name + '\'' +
                ", Customer_phone='" + Customer_phone + '\'' +
                ", Customer_email='" + Customer_email + '\'' +
                ", Customer_address='" + Customer_address + '\'' +
                '}';
    }
}