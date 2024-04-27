/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.DoanhThu;
import com.grocerystore.model.SanPham;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author My PC
 */
public interface IThongKeDAO {
    public int GetSumSPBan();
    public BigDecimal GetSumDoanhThu();
    public int GetCountKH();
    public int GetSumDonHang();
    public List<SanPham> Top10SP();
    public List<DoanhThu> Top10Ngay();
}
