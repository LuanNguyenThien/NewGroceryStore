/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.SanPham;
import connection.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author My PC
 */
public class SanPhamDAOImpl implements ISanPhamDao{
    
    @Override
    public Boolean add(SanPham sp) {
        String sql = "{CALL ThemSPMOI(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, sp.getTenSP());
            stmt.setString(2, sp.getMaLoaiSP());
            stmt.setString(3, sp.getMaNSX());
            stmt.setString(4, sp.getDonViTinh());
            stmt.setBigDecimal(5, sp.getGiaTien());
            stmt.setBigDecimal(6, sp.getGiaNhap());
            stmt.setInt(7, sp.getSoLuong());
            stmt.setInt(8, sp.getLoiNhuan());
            stmt.setString(9, sp.getTinhTrang());
            stmt.setBytes(10, sp.getHinhAnh());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            // Handle exception
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public Boolean soft_delete(String MaSP) {
        String sql = "UPDATE SanPham SET TinhTrang = 'Ngừng kinh doanh' WHERE MaSP = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MaSP);

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update_info(SanPham sp) {
        String sql = "UPDATE SanPham SET MaLoaiSP = ?, MaNSX = ?, TenSP = ?, DonViTinh = ?, GiaTien = ?, GiaNhap = ?, SoLuong = ?, LoiNhuan = ?, TinhTrang = ?, HinhAnh = ? WHERE MaSP = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sp.getMaLoaiSP());
            stmt.setString(2, sp.getMaNSX());
            stmt.setString(3, sp.getTenSP());
            stmt.setString(4, sp.getDonViTinh());
            stmt.setBigDecimal(5, sp.getGiaTien());
            stmt.setBigDecimal(6, sp.getGiaNhap());
            stmt.setInt(7, sp.getSoLuong());
            stmt.setInt(8, sp.getLoiNhuan());
            stmt.setString(9, sp.getTinhTrang());
            stmt.setBytes(10, sp.getHinhAnh());
            stmt.setString(11, sp.getMaSP());

            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPham> getAll() {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try  {
            stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setMaLoaiSP(rs.getString("MaLoaiSP"));
                sp.setMaNSX(rs.getString("MaNSX"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setGiaTien(rs.getBigDecimal("GiaTien"));
                sp.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setLoiNhuan(rs.getInt("LoiNhuan"));
                sp.setTinhTrang(rs.getString("TinhTrang"));
                sp.setHinhAnh(rs.getBytes("HinhAnh"));
                sanPhamList.add(sp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return sanPhamList;
    }

    @Override
    public List<SanPham> getAll_viewSP() {
        List<SanPham> fullProductInfoList = new ArrayList<>();
        String sql = "SELECT * FROM FullProductInfo";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SanPham fpi = new SanPham();
                fpi.setMaSP(rs.getString("MaSP"));
                fpi.setMaLoaiSP(rs.getString("MaLoaiSP"));
                fpi.setMaNSX(rs.getString("MaNSX"));
                fpi.setTenSP(rs.getString("TenSP"));
                fpi.setDonViTinh(rs.getString("DonViTinh"));
                fpi.setGiaTien(rs.getBigDecimal("GiaTien"));
                fpi.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                fpi.setSoLuong(rs.getInt("SoLuong"));
                fpi.setLoiNhuan(rs.getInt("LoiNhuan"));
                fpi.setTinhTrang(rs.getString("TinhTrang"));
                fpi.setHinhAnh(rs.getBytes("HinhAnh"));
                fpi.setTenLoaiSP(rs.getString("TenLoaiSP"));
                fpi.setTenNSX(rs.getString("TenNSX"));
                fullProductInfoList.add(fpi);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return fullProductInfoList;
    }

    @Override
    public List<SanPham> findByBoLoc(String param, String MaNSX, String MaLSP) {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE TenSP LIKE ? ";
        
        if (MaNSX != null) {
            sql += "AND MaNSX = ? ";
        }

        if (MaLSP != null) {
            sql += "AND MaLoaiSP = ? ";
        }

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + param + "%");

            if (MaNSX != null) {
                stmt.setString(2, MaNSX);
            }

            if (MaLSP != null) {
                stmt.setString(MaNSX != null ? 3 : 2, MaLSP);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setMaLoaiSP(rs.getString("MaLoaiSP"));
                sp.setMaNSX(rs.getString("MaNSX"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setGiaTien(rs.getBigDecimal("GiaTien"));
                sp.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setLoiNhuan(rs.getInt("LoiNhuan"));
                sp.setTinhTrang(rs.getString("TinhTrang"));
                sp.setHinhAnh(rs.getBytes("HinhAnh"));
                sanPhamList.add(sp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sanPhamList;
    }

    @Override
    public SanPham findById(String MaSP) {
        SanPham sp = null;
        String sql = "SELECT * FROM SanPham WHERE MaSP = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, MaSP);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setMaLoaiSP(rs.getString("MaLoaiSP"));
                sp.setMaNSX(rs.getString("MaNSX"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setGiaTien(rs.getBigDecimal("GiaTien"));
                sp.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setLoiNhuan(rs.getInt("LoiNhuan"));
                sp.setTinhTrang(rs.getString("TinhTrang"));
                sp.setHinhAnh(rs.getBytes("HinhAnh"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sp;
    }

    @Override
    public Boolean update_soluong(String MaSP, int soluong) {
        String sql = "UPDATE SanPham SET SoLuong = SoLuong + ? WHERE MaSP = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, soluong);
            stmt.setString(2, MaSP);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating soluong failed, no rows affected.");
            }

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SanPham> findByMaNSX(String MaNSX) {
        List<SanPham> sanPhamList = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE MaNSX = ? ";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            stmt.setString(1, MaNSX);

            rs = stmt.executeQuery();

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setMaLoaiSP(rs.getString("MaLoaiSP"));
                sp.setMaNSX(rs.getString("MaNSX"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setGiaTien(rs.getBigDecimal("GiaTien"));
                sp.setGiaNhap(rs.getBigDecimal("GiaNhap"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setLoiNhuan(rs.getInt("LoiNhuan"));
                sp.setTinhTrang(rs.getString("TinhTrang"));
                sp.setHinhAnh(rs.getBytes("HinhAnh"));
                sanPhamList.add(sp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return sanPhamList;
    }
    
}
