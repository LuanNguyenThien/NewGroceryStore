/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.SanPham;
import java.util.List;

/**
 *
 * @author My PC
 */
public interface ISanPhamDao {
    Boolean add(SanPham sp);
    Boolean soft_delete(String MaSP);
    Boolean update_info(SanPham sp);
    List<SanPham> getAll();
    List<SanPham> getAll_viewSP(String TinhTrang);
    List<SanPham> findByBoLoc(String param, String MaNSX, String MaLSP);
    List<SanPham> findByBoLoc_viewSP(String param, String MaNSX, String MaLSP);
    SanPham findById(String MaSP);
    Boolean update_soluong(String MaSP, int soluong);
}
