/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

/**
 *
 * @author A
 */
public class ChiTietDonNhapHang {
    private String maDNH;
    private String maSP;
    private int soLuong;
    private double donGia;

    public ChiTietDonNhapHang() {
    }

    public ChiTietDonNhapHang(String maDNH, String maSP, int soLuong, double donGia) {
        this.maDNH = maDNH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaDNH() {
        return maDNH;
    }

    public void setMaDNH(String maDNH) {
        this.maDNH = maDNH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
}
