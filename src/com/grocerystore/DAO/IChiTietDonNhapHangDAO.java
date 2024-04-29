/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.ChiTietDonNhapHang;
import java.util.List;

/**
 *
 * @author A
 */
public interface IChiTietDonNhapHangDAO {
    Boolean ThemCTDNH(ChiTietDonNhapHang chiTietDonNhapHang);
    List<ChiTietDonNhapHang> listSanPham(String MaDNH);
}
