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
import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author My PC
 */
public class NhanVienDAOImpl implements INhanVienDAO{

    @Override
    public Boolean add(NhanVien nv) {
        CallableStatement cs = null;
        try {
            String sql = "{call spThemNhanVien(?,?,?,?,?,?,?,?,?,?)}";
            cs = DatabaseConnection.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, nv.getHoTen());
            cs.setString(2, nv.getSdt());
            cs.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            cs.setString(4, nv.getGioiTinh());
            cs.setString(5, nv.getDiaChi());
            cs.setDate(6, new java.sql.Date(nv.getNgayTuyenDung().getTime()));
            cs.setString(7, nv.getTrangThai());
            cs.setString(8, nv.getTenTK());
            cs.setBytes(9, nv.getHinhAnh());
            cs.setString(10, nv.getQuyen());
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
    public Boolean update(NhanVien nv) {
        CallableStatement cs = null;
        try {
            String sql = "{call UpdateNhanVien(?,?,?,?,?,?,?,?,?,?,?,?)}";
            cs = DatabaseConnection.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, nv.getMaNV());
            cs.setString(2, nv.getHoTen());
            cs.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            cs.setString(4, nv.getSdt());
            cs.setString(5, nv.getGioiTinh());
            cs.setString(6, nv.getDiaChi());
            cs.setDate(7, new java.sql.Date(nv.getNgayTuyenDung().getTime()));
            cs.setString(8, nv.getTrangThai());
            cs.setString(9, nv.getTenTK());
            cs.setBytes(10, nv.getHinhAnh());
            cs.setString(11, nv.getMatKhau());
            cs.setString(12, nv.getQuyen());
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
    public List<NhanVien> findByName(String HoTen) {
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

    @Override
    public Boolean delete(String maNV) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE NhanVien SET TrangThai = ? WHERE MaNV = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, "Đã nghỉ");
            ps.setString(2, maNV);
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
    public List<NhanVien> find(String HoTen, String Quyen) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE HoTen LIKE ? and Quyen = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, "%" + HoTen + "%");
            ps.setString(2, Quyen);
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
    public List<NhanVien> findByQuyen(String Quyen) {
        List<NhanVien> nhanVienList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM NhanVien WHERE Quyen = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, Quyen);
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
}
