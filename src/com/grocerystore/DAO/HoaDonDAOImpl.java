/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.HoaDon;
import com.grocerystore.model.XuatHoaDon;
import connection.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HUYEN PC
 */
public class HoaDonDAOImpl implements IHoaDonDAO{

    @Override
    public Boolean ThemHD(HoaDon hd) {
        CallableStatement cs = null;
        try {
            String sql = "{call ThemHoaDonMoi(?,?)}";
            cs = DatabaseConnection.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, hd.getMaNV());
            cs.setString(2, hd.getMaKH());
            int rs = cs.executeUpdate();
            return rs > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cs != null) cs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean CapnhatTrangthaiTienHD(String MaHD, double TienKhachTra, double TienThua, double TriGiaHoaDon) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE HoaDon SET TienKhachTra = ?, TienThua = ?, TriGiaHoaDon = ?, TrangThai = 'Đã thanh toán' WHERE MaHD = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setDouble(1, TienKhachTra);
            ps.setDouble(2, TienThua);
            ps.setDouble(3, TriGiaHoaDon);
            ps.setString(4, MaHD);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean XoaHD(String MaHD) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM HoaDon WHERE MaHD = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, MaHD);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    @Override
    public String LayMaHDMoiNhat() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT MAX(MaHD) AS MaHD FROM HoaDon";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MaHD");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    @Override
    public List<XuatHoaDon> getXuatHoaDon() {
        List<XuatHoaDon> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM XuatHoaDon";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                XuatHoaDon xhd = new XuatHoaDon();
                xhd.setMaHD(rs.getString("MaHD"));
                xhd.setNgayBanHang(rs.getTimestamp("NgayBanHang").toLocalDateTime());
                xhd.setTriGiaHoaDon(rs.getBigDecimal("TriGiaHoaDon"));
                xhd.setTienKhachTra(rs.getBigDecimal("TienKhachTra"));
                xhd.setTienThua(rs.getBigDecimal("TienThua"));
                xhd.setMaSP(rs.getString("MaSP"));
                xhd.setSoLuong(rs.getInt("Soluong"));
                xhd.setGiaTien(rs.getBigDecimal("GiaTien"));
                xhd.setTongTien(rs.getBigDecimal("TongTien"));
                xhd.setTenSP(rs.getString("TenSP"));
                xhd.setTenNhanVien(rs.getString("TenNhanVien"));
                xhd.setTenKhachHang(rs.getString("TenKhachHang"));
                list.add(xhd);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
