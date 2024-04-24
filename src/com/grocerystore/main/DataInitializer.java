/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.main;

import com.grocerystore.DAO.NhanVienDAOImpl;
import com.grocerystore.model.NhanVien;
import com.grocerystore.model.SanPham;
import com.raven.component.PanelSlide;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author My PC
 */
public class DataInitializer {
    public static NhanVien nhanVien1, curUser;
    public static SanPham sp1, sp2;
    
    static {
        NhanVienDAOImpl nvdao = new NhanVienDAOImpl();
        curUser = nvdao.findByID(PanelSlide.IDCurUser);
        // Tạo dữ liệu mẫu cho NhanVien
        nhanVien1 = new NhanVien(
            "NV0001", 
            "Nguyen Van A", 
            Date.valueOf("1990-01-01"), 
            "0123456789", 
            "Nam", 
            "TP Hanoi, Viet Nam", 
            Date.valueOf("2022-01-01"), 
            "Dang lam", 
            "CV001", 
            "userA", 
            null,
            "passwordA",
            "Admin"
        );
        
        sp1 = new SanPham();
        sp1.setMaSP("SP01");
        sp1.setMaLoaiSP("LSP01");
        sp1.setMaNSX("NSX01");
        sp1.setTenSP("Sản phẩm 1");
        sp1.setDonViTinh("Cái");
        sp1.setGiaTien(new BigDecimal("15000.0"));
        sp1.setGiaNhap(new BigDecimal("10000.0"));
        sp1.setSoLuong(100);
        sp1.setLoiNhuan(5000);
        sp1.setTinhTrang("Còn hàng");


        sp2 = new SanPham();
        sp2.setMaSP("SP02");
        sp2.setMaLoaiSP("LSP02");
        sp2.setMaNSX("NSX02");
        sp2.setTenSP("Sản phẩm 2");
        sp2.setDonViTinh("Cái");
        sp2.setGiaTien(new BigDecimal("20000.0"));
        sp2.setGiaNhap(new BigDecimal("15000.0"));
        sp2.setSoLuong(200);
        sp2.setLoiNhuan(5000);
        sp2.setTinhTrang("Còn hàng");
        // Thêm vào danh sách nhan vien hoặc lưu vào database tạm thời
        // Thêm vào danh sách sản phẩm hoặc lưu vào database tạm thời
    }
}
