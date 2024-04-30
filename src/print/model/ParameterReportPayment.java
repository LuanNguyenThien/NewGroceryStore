package print.model;

import java.io.InputStream;
import java.util.List;

public class ParameterReportPayment {

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public InputStream getQrcode() {
        return qrcode;
    }

    public void setQrcode(InputStream qrcode) {
        this.qrcode = qrcode;
    }

    public List<FieldReportPayment> getFields() {
        return fields;
    }

    public void setFields(List<FieldReportPayment> fields) {
        this.fields = fields;
    }

    public ParameterReportPayment(String staff, String customer, String total, InputStream qrcode, List<FieldReportPayment> fields) {
        this.staff = staff;
        this.customer = customer;
        this.total = total;
        this.qrcode = qrcode;
        this.fields = fields;
    }

    public ParameterReportPayment(String idHD, String time, String staff, String customer, String total, String customerpay, String left, InputStream qrcode, List<FieldReportPayment> fields) {
        this.idHD = idHD;
        this.time = time;
        this.staff = staff;
        this.customer = customer;
        this.total = total;
        this.customerpay = customerpay;
        this.left = left;
        this.qrcode = qrcode;
        this.fields = fields;
    }
    
    public ParameterReportPayment() {
    }

    private String idHD;
    private String time;
    private String staff;
    private String customer;
    private String total;
    private String customerpay;
    private String left;

    public String getIdHD() {
        return idHD;
    }

    public void setIdHD(String idHD) {
        this.idHD = idHD;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomerpay() {
        return customerpay;
    }

    public void setCustomerpay(String customerpay) {
        this.customerpay = customerpay;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
    private InputStream qrcode;
    private List<FieldReportPayment> fields;
}
