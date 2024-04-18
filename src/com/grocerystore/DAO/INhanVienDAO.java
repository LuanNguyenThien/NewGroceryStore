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
    Boolean add (NhanVien nv);
    Boolean update (NhanVien nv);
    NhanVien checkLogin(String TenTK, String MatKhau);
    NhanVien findByID(String MaNV);
    List<NhanVien> findByName(String Hoten);
    List<NhanVien> getAll();
    Boolean delete(String maNV);
    List<NhanVien> find(String Hoten, String Quyen);
    List<NhanVien> findByQuyen(String Quyen);
}
