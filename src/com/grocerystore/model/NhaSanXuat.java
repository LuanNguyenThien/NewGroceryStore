/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

/**
 *
 * @author Admin
 */
public class NhaSanXuat {
    private String MaNSX;
    private String TenNSX;
    private String DiaChi;
    private String Sdt;

    public String getMaNSX() {
        return MaNSX;
    }

    public void setMaNSX(String MaNSX) {
        this.MaNSX = MaNSX;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String TenNSX) {
        this.TenNSX = TenNSX;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public NhaSanXuat() {
    }

    public NhaSanXuat(String MaNSX, String TenNSX, String DiaChi, String Sdt) {
        this.MaNSX = MaNSX;
        this.TenNSX = TenNSX;
        this.DiaChi = DiaChi;
        this.Sdt = Sdt;
    }
    
}
