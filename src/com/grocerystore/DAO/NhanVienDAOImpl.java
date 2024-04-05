/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.DatabaseConnection;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author My PC
 */
public class NhanVienDAOImpl implements INhanVienDAO{

    @Override
    public Boolean add(NhanVien model) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, Sdt, GioiTinh, DiaChi, NgayTuyenDung, TrangThai, TenTK, HinhAnh, MatKhau, Quyen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getMaNV());
            ps.setString(2, model.getHoTen());
            ps.setDate(3, new java.sql.Date(model.getNgaySinh().getTime()));
            ps.setString(4, model.getSdt());
            ps.setString(5, model.getGioiTinh());
            ps.setString(6, model.getDiaChi());
            ps.setDate(7, new java.sql.Date(model.getNgayTuyenDung().getTime()));
            ps.setString(8, model.getTrangThai());
            ps.setString(9, model.getTenTK());
            ps.setBytes(10, model.getHinhAnh());
            ps.setString(11, model.getMatKhau());
            ps.setString(12, model.getQuyen());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
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
    public Boolean update(NhanVien model) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE NhanVien SET HoTen = ?, NgaySinh = ?, Sdt = ?, GioiTinh = ?, DiaChi = ?, NgayTuyenDung = ?, TrangThai = ?, TenTK = ?, HinhAnh = ?, MatKhau = ?, Quyen = ? WHERE MaNV = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getHoTen());
            ps.setDate(2, new java.sql.Date(model.getNgaySinh().getTime()));
            ps.setString(3, model.getSdt());
            ps.setString(4, model.getGioiTinh());
            ps.setString(5, model.getDiaChi());
            ps.setDate(6, new java.sql.Date(model.getNgayTuyenDung().getTime()));
            ps.setString(7, model.getTrangThai());
            ps.setString(8, model.getTenTK());
            ps.setBytes(9, model.getHinhAnh());
            ps.setString(10, model.getMatKhau());
            ps.setString(11, model.getQuyen());
            ps.setString(12, model.getMaNV());
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
    public NhanVien checkLogin(String TenTK, String MatKhau) {
        NhanVien nhanVien = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Replace with your database connection info
            String sql = "Select * from `nhanvien` where BINARY(TenTK)=? and BINARY(MatKhau)=? limit 1";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, TenTK);
            ps.setString(2, MatKhau);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Replace with your NhanVien object structure
                nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setHoTen(rs.getString("HoTen"));
                nhanVien.setHinhAnh(rs.getBytes("HinhAnh"));
                nhanVien.setQuyen(rs.getString("Quyen"));
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

        return nhanVien;
    }

    @Override
    public NhanVien findByID(String MaNV) {
        NhanVien nhanVien = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, MaNV);
            rs = ps.executeQuery();
            if (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setHoTen(rs.getString("HoTen"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setSdt(rs.getString("Sdt"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setNgayTuyenDung(rs.getDate("NgayTuyenDung"));
                nhanVien.setTrangThai(rs.getString("TrangThai"));
                nhanVien.setTenTK(rs.getString("TenTK"));
                nhanVien.setHinhAnh(rs.getBytes("HinhAnh"));
                nhanVien.setMatKhau(rs.getString("MatKhau"));
                nhanVien.setQuyen(rs.getString("Quyen"));
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
        return nhanVien;
    }

    @Override
    public List<NhanVien> findByFilter(String HoTen) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE HoTen LIKE ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, "%" + HoTen + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setHoTen(rs.getString("HoTen"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setSdt(rs.getString("Sdt"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setNgayTuyenDung(rs.getDate("NgayTuyenDung"));
                nhanVien.setTrangThai(rs.getString("TrangThai"));
                nhanVien.setTenTK(rs.getString("TenTK"));
                nhanVien.setHinhAnh(rs.getBytes("HinhAnh"));
                nhanVien.setMatKhau(rs.getString("MatKhau"));
                nhanVien.setQuyen(rs.getString("Quyen"));
                nhanVienList.add(nhanVien);
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
        return nhanVienList;
    }

    @Override
        public List<NhanVien> getAll() {
            List<NhanVien> nhanVienList = new ArrayList<>();
            Statement st = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM NhanVien WHERE TrangThai = 'Đang làm'";
                st = DatabaseConnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(rs.getString("MaNV"));
                    nhanVien.setHoTen(rs.getString("HoTen"));
                    nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                    nhanVien.setSdt(rs.getString("Sdt"));
                    nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                    nhanVien.setDiaChi(rs.getString("DiaChi"));
                    nhanVien.setNgayTuyenDung(rs.getDate("NgayTuyenDung"));
                    nhanVien.setTrangThai(rs.getString("TrangThai"));
                    nhanVien.setTenTK(rs.getString("TenTK"));
                    nhanVien.setHinhAnh(rs.getBytes("HinhAnh"));
                    nhanVien.setMatKhau(rs.getString("MatKhau"));
                    nhanVien.setQuyen(rs.getString("Quyen"));
                    nhanVienList.add(nhanVien);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (st != null) st.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return nhanVienList;
        }
}
