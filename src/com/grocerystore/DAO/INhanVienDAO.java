/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.NhanVien;
import java.util.List;

/**
 *
 * @author My PC
 */
public interface INhanVienDAO {
    Boolean add (NhanVien model);
    Boolean update (NhanVien model);
    NhanVien checkLogin(String TenTK, String MatKhau);
    NhanVien findByID(String MaNV);
    List<NhanVien> findByFilter(String Hoten);
    List<NhanVien> getAll();
}
