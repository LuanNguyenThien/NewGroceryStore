/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.HoaDon;
import connection.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author HUYEN PC
 */
public class HoaDonDAOImpl implements IHoaDonDAO{

    @Override
    public Boolean ThemHD(HoaDon hd) {
        CallableStatement cs = null;
        try {
            String sql = "{call ThemHoaDonMoi(?,?,?,?,?,?,?)}";
            cs = DatabaseConnection.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, hd.getMaNV());
            cs.setString(2, hd.getMaKH());
            cs.setDate(3, new java.sql.Date(hd.getNgayBanHang().getTime()));
            cs.setString(4, hd.getTrangThai());
            cs.setDouble(5, hd.getTriGiaHoaDon());
            cs.setDouble(6, hd.getTienKhachTra());
            cs.setDouble(7, hd.getTienThua());
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
    public Boolean CapnhatTrangthaiTienHD(String MaHD, double TienKhachTra, double TienThua) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE HoaDon SET TienKhachTra = ?, TienThua = ? WHERE MaHD = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setDouble(1, TienKhachTra);
            ps.setDouble(2, TienThua);
            ps.setString(3, MaHD);
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
    
}
