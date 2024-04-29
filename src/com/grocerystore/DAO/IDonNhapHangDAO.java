/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.DonNhapHang;
import java.util.List;

/**
 *
 * @author A
 */
public interface IDonNhapHangDAO {
    Boolean ThemDNH(DonNhapHang donNhapHang);
    List<DonNhapHang> GetDSDNH(); //lay trang thai chua nhap
    Boolean XacNhanDNH(String MaDNH);
    Boolean XoaDNH(String MaDNH);
    String GetMaxDNH();
}
