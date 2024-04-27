/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.DoanhThu;
import com.grocerystore.model.SanPham;
import connection.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My PC
 */
public class ThongKeDAOImpl implements IThongKeDAO{

    @Override
    public int GetSumSPBan() {
        int sum = 0;
        String sql = "SELECT SUM(Soluong) FROM ChiTietHoaDon WHERE MaHD IN (SELECT MaHD FROM HoaDon WHERE TrangThai = 'Đã thanh toán')";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                sum = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sum;
    }

    @Override
    public BigDecimal GetSumDoanhThu() {
        BigDecimal sum = BigDecimal.ZERO;
        String sql = "SELECT SUM(TriGiaHoaDon) FROM HoaDon WHERE TrangThai = 'Đã thanh toán'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                sum = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return sum;
    }

    @Override
    public int GetCountKH() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM KhachHang";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public int GetSumDonHang() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM HoaDon WHERE TrangThai = 'Đã thanh toán'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return count;
    }

    @Override
    public List<SanPham> Top10SP() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT sp.MaSP, sp.TenSP, SUM(ct.Soluong) as TotalSold " +
                     "FROM SanPham sp " +
                     "JOIN ChiTietHoaDon ct ON sp.MaSP = ct.MaSP " +
                     "JOIN HoaDon hd ON ct.MaHD = hd.MaHD " +
                     "WHERE hd.TrangThai = 'Đã thanh toán' " +
                     "GROUP BY sp.MaSP, sp.TenSP " +
                     "ORDER BY TotalSold DESC " +
                     "LIMIT 10";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setSoLuong(rs.getInt("TotalSold"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<DoanhThu> Top10Ngay() {
        List<DoanhThu> list = new ArrayList<>();
        String sql = "SELECT DATE(h.NgayBanHang) AS Ngay, SUM(h.TriGiaHoaDon) AS DoanhThu, " +
                    "SUM((SELECT SUM(s.GiaNhap * c.Soluong) FROM ChiTietHoaDon c JOIN SanPham s ON c.MaSP = s.MaSP WHERE c.MaHD = h.MaHD)) AS ChiPhi " +
                    "FROM HoaDon h " +
                    "WHERE h.TrangThai = 'Đã thanh toán' " +
                    "GROUP BY Ngay " +
                    "ORDER BY DoanhThu DESC " +
                    "LIMIT 6";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DoanhThu dt = new DoanhThu();
                dt.setNgay(rs.getDate("Ngay"));
                dt.setDoanhthu(rs.getDouble("DoanhThu"));
                dt.setChiphi(rs.getDouble("ChiPhi"));
                dt.setLoinhuan(dt.getDoanhthu() - dt.getChiphi());
                list.add(dt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }
    
}
