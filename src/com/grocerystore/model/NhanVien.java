/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

import java.sql.Date;

/**
 *
 * @author My PC
 */
public class NhanVien {
    private String MaNV;
    private String HoTen;
    private Date NgaySinh;
    private String Sdt;
    private String GioiTinh;
    private String DiaChi;
    private Date NgayTuyenDung;
    private String TrangThai;
    private String MaCV;
    private String TenTK;
    private byte[] HinhAnh;
    private String MatKhau;
    private String Quyen;

    public NhanVien() {
    }

    public NhanVien(String HoTen, byte[] HinhAnh, String Quyen) {
        this.HoTen = HoTen;
        this.HinhAnh = HinhAnh;
        this.Quyen = Quyen;
    }
    
    public NhanVien(String MaNV, String HoTen, Date NgaySinh, String Sdt, String GioiTinh, String DiaChi, Date NgayTuyenDung, String TrangThai, String MaCV, String TenTK, byte[] HinhAnh, String MatKhau, String Quyen) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.Sdt = Sdt;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.NgayTuyenDung = NgayTuyenDung;
        this.TrangThai = TrangThai;
        this.MaCV = MaCV;
        this.TenTK = TenTK;
        this.HinhAnh = HinhAnh;
        this.MatKhau = MatKhau;
        this.Quyen = Quyen;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
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

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Date getNgayTuyenDung() {
        return NgayTuyenDung;
    }

    public void setNgayTuyenDung(Date NgayTuyenDung) {
        this.NgayTuyenDung = NgayTuyenDung;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getQuyen() {
        return Quyen;
    }

    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }
}
