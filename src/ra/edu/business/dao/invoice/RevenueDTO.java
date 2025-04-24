package ra.edu.business.dao.invoice;

import java.time.LocalDate;

public class RevenueDTO {
    private LocalDate date;
    private Integer month;
    private Integer year;
    private Double totalAmount;

    public RevenueDTO() {
    }

    public RevenueDTO(LocalDate date, Integer month, Integer year, Double totalAmount) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.totalAmount = totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
