/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.sql.Date;

/**
 *
 * @author HUYEN PC
 */
public class HoaDon {
    private String MaHD;
    private String MaNV;
    private String MaKH;
    private Date NgayBanHang;
    private String TrangThai;
    private double TriGiaHoaDon;
    private double TienKhachTra;
    private double TienThua;
    
    public HoaDon() {
    }
    
    public HoaDon(String MaNV, String MaKH, Date NgayBanHang, String TrangThai, double TriGiaHoaDon, double TienKhachTra, double TienThua) {
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayBanHang = NgayBanHang;
        this.TrangThai = TrangThai;
        this.TriGiaHoaDon = TriGiaHoaDon;
        this.TienKhachTra = TienKhachTra;
        this.TienThua = TienThua;
    }
    
    public String getMaHD() {
        return this.MaHD;
    }
    
    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }
    
    public String getMaNV() {
        return this.MaNV;
    }
    
    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }
    
    public String getMaKH() {
        return this.MaKH;
    }
    
    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }
    
    public Date getNgayBanHang() {
        return this.NgayBanHang;
    }
    
    public void setNgayBanHang(Date NgayBanHang) {
        this.NgayBanHang = NgayBanHang;
    }
    
    public String getTrangThai() {
        return this.TrangThai;
    }
    
    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    public double getTriGiaHoaDon() {
        return this.TriGiaHoaDon;
    }
    
    public void setTriGiaHoaDon(double TriGiaHoaDon) {
        this.TriGiaHoaDon = TriGiaHoaDon;
    }
    
    public double getTienKhachTra() {
        return this.TienKhachTra;
    }
    
    public void setTienKhachTra(double TienKhachTra) {
        this.TienKhachTra = TienKhachTra;
    }
    
    public double getTienThua() {
        return this.TienThua;
    }
    
    public void setTienThua(double TienThua) {
        this.TienThua = TienThua;
    }
}
