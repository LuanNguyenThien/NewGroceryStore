/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class KhachHang {
    private String MaKH;
    private String HoTen;
    private String Sdt;
    private String GioiTinh;
    private Date NgayDangKy;
    private BigDecimal TongChiTieu;
    private Boolean DaXoa;

    public KhachHang(){}

    public KhachHang(String MaKH, String HoTen, String Sdt, String GioiTinh, Date NgayDangKy, BigDecimal TongChiTieu) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.Sdt = Sdt;
        this.GioiTinh = GioiTinh;
        this.NgayDangKy = NgayDangKy;
        this.TongChiTieu = TongChiTieu;
    }
 
    public KhachHang(String MaKH, String HoTen, String Sdt, String GioiTinh, Date NgayDangKy, BigDecimal TongChiTieu, Boolean DaXoa) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.Sdt = Sdt;
        this.GioiTinh = GioiTinh;
        this.NgayDangKy = NgayDangKy;
        this.TongChiTieu = TongChiTieu;
        this.DaXoa = DaXoa;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date NgayDangKy) {
        this.NgayDangKy = NgayDangKy;
    }

    public BigDecimal getTongChiTieu() {
        return TongChiTieu;
    }

    public void setTongChiTieu(BigDecimal TongChiTieu) {
        this.TongChiTieu = TongChiTieu;
    }

    public Boolean getDaXoa() {
        return DaXoa;
    }

    public void setDaXoa(Boolean DaXoa) {
        this.DaXoa = DaXoa;
    }
    
    
    
}
