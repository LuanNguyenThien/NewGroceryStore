/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.util.Date;
import java.math.BigDecimal;

public class XuatHoaDon {
    private String maHD;
    private Date ngayBanHang;
    private BigDecimal triGiaHoaDon;
    private BigDecimal tienKhachTra;
    private BigDecimal tienThua;
    private String maSP;
    private int soLuong;
    private BigDecimal giaTien;
    private BigDecimal tongTien;
    private String tenSP;
    private String tenNhanVien;
    private String tenKhachHang;

    // Getters and Setters
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public Date getNgayBanHang() { return ngayBanHang; }
    public void setNgayBanHang(Date ngayBanHang) { this.ngayBanHang = ngayBanHang; }

    public BigDecimal getTriGiaHoaDon() { return triGiaHoaDon; }
    public void setTriGiaHoaDon(BigDecimal triGiaHoaDon) { this.triGiaHoaDon = triGiaHoaDon; }

    public BigDecimal getTienKhachTra() { return tienKhachTra; }
    public void setTienKhachTra(BigDecimal tienKhachTra) { this.tienKhachTra = tienKhachTra; }

    public BigDecimal getTienThua() { return tienThua; }
    public void setTienThua(BigDecimal tienThua) { this.tienThua = tienThua; }

    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getTenNhanVien() { return tenNhanVien; }
    public void setTenNhanVien(String tenNhanVien) { this.tenNhanVien = tenNhanVien; }

    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
}
