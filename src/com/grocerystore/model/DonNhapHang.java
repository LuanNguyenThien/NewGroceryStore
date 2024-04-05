/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.sql.Date;

/**
 *
 * @author A
 */
public class DonNhapHang {
    private String maDNH;
    private String maNV;
    private String maNSX;
    private Date ngayLap;
    private String trangThai;

    public DonNhapHang() {
    }

    public DonNhapHang(String maDNH, String maNV, String maNSX, Date ngayLap, String trangThai) {
        this.maDNH = maDNH;
        this.maNV = maNV;
        this.maNSX = maNSX;
        this.ngayLap = ngayLap;
        this.trangThai = trangThai;
    }

    public String getMaDNH() {
        return maDNH;
    }

    public void setMaDNH(String maDNH) {
        this.maDNH = maDNH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
}

