package print.model;

import java.math.BigDecimal;

public class FieldReportPayment {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public FieldReportPayment(String name, int qty, BigDecimal price, BigDecimal total) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public FieldReportPayment() {
    }

    private String name;
    private int qty;
    private BigDecimal price;
    private BigDecimal total;
}
