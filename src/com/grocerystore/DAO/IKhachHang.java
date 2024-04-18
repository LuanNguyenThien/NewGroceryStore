    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.KhachHang;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author pc
 */
public interface IKhachHang {
    Boolean add(KhachHang k);
    Boolean delete(String MaKH);
    Boolean updateInfo(KhachHang k);
    Boolean updateChiTieu(String MaKH, BigDecimal ChiTieu);
    List<KhachHang> getAll();
    KhachHang findById(String MaKH);
    List<KhachHang> findByName(String HoTen);
    int count();
    List<KhachHang> searchByParam(String searchParam);

}
