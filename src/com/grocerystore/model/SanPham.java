/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.math.BigDecimal;

/**
 *
 * @author My PC
 */
public class SanPham {
    private String maSP;
    private String maLoaiSP;
    private String tenLoaiSP;
    private String maNSX;
    private String tenNSX;
    private String tenSP;
    private String donViTinh;
    private BigDecimal giaTien;
    private BigDecimal giaNhap;
    private int soLuong;
    private int loiNhuan;
    private String tinhTrang;
    private byte[] hinhAnh;

    public SanPham() {
    }

    public SanPham(String maSP, String maLoaiSP, String maNSX, String tenSP, String donViTinh, BigDecimal giaTien, BigDecimal giaNhap, int soLuong, int loiNhuan, String tinhTrang, byte[] hinhAnh) {
        this.maSP = maSP;
        this.maLoaiSP = maLoaiSP;
        this.maNSX = maNSX;
        this.tenSP = tenSP;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.loiNhuan = loiNhuan;
        this.tinhTrang = tinhTrang;
        this.hinhAnh = hinhAnh;
    }

    public SanPham(String maSP, String maLoaiSP, String tenLoaiSP, String maNSX, String tenNSX, String tenSP, String donViTinh, BigDecimal giaTien, BigDecimal giaNhap, int soLuong, int loiNhuan, String tinhTrang, byte[] hinhAnh) {
        this.maSP = maSP;
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.maNSX = maNSX;
        this.tenNSX = tenNSX;
        this.tenSP = tenSP;
        this.donViTinh = donViTinh;
        this.giaTien = giaTien;
        this.giaNhap = giaNhap;
        this.soLuong = soLuong;
        this.loiNhuan = loiNhuan;
        this.tinhTrang = tinhTrang;
        this.hinhAnh = hinhAnh;
    }
    
    

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    // Getters and setters for each field
    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(String maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getMaNSX() {
        return maNSX;
    }

    public void setMaNSX(String maNSX) {
        this.maNSX = maNSX;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(int loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
