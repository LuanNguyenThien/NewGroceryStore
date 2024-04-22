/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.HoaDon;
import com.grocerystore.model.XuatHoaDon;
import java.util.List;

/**
 *
 * @author HUYEN PC
 */
public interface IHoaDonDAO {
    Boolean ThemHD(HoaDon hd);
    Boolean CapnhatTrangthaiTienHD(String MaHD, double TienKhachTra, double TienThua);
    Boolean XoaHD(String MaHD);
    String LayMaHDMoiNhat();
    List<XuatHoaDon> getXuatHoaDon();
}
