/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.KhachHang;
import java.sql.PreparedStatement;
import connection.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class KhachHangDAOImpl implements IKhachHang{

    @Override
    public Boolean add(KhachHang k) {
        String sql = "Call PC_ThemKhachHang(?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            ps.setString(1, k.getHoTen());
            ps.setString(2, k.getSdt());
            ps.setString(3, k.getGioiTinh());
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean delete(String MaKH) {
        String sql = "Update khachhang set DaXoa = 1 where MaKH = ?";
        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            ps.setString(1,MaKH);
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean updateInfo(KhachHang k) {
        String sql = "Update khachhang set HoTen = ? , Sdt = ? , GioiTinh = ? , NgayDangKi = ?, TongChiTieu = ? where MaKH = ?";
        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            ps.setString(1, k.getHoTen());
            ps.setString(2, k.getSdt());
            ps.setString(3, k.getGioiTinh());
            ps.setDate(4, k.getNgayDangKy());
            ps.setBigDecimal(5, k.getTongChiTieu());
            ps.setString(6, k.getMaKH());
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Boolean updateChiTieu(String MaKH, BigDecimal ChiTieu) {
        String sql = "Update khachhang set TongChiTieu = TongChiTieu + ? where MaKH = ?";
        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            ps.setBigDecimal(1, ChiTieu);
            ps.setString(2, MaKH);
           
            
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                KhachHang k = new KhachHang();
                k.setMaKH(rs.getString(1));
                k.setHoTen(rs.getString(2));
                k.setSdt(rs.getString(3));
                k.setGioiTinh(rs.getString(4));
                k.setNgayDangKy(rs.getDate(5));
                k.setTongChiTieu(rs.getBigDecimal(6));
                list.add(k);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return list;
    }

    @Override
    public KhachHang findById(String MaKH) {
        String sql = "SELECT * FROM khachhang WHERE MaKH = ?";
        KhachHang k = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, MaKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                k = new KhachHang();
                k.setMaKH(rs.getString(1));
                k.setHoTen(rs.getString(2));
                k.setSdt(rs.getString(3));
                k.setGioiTinh(rs.getString(4));
                k.setNgayDangKy(rs.getDate(5));
                k.setTongChiTieu(rs.getBigDecimal(6));
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
        return k;
    }

    @Override
    public int count() {
        String sql = "SELECT count(MaKH) FROM khachhang";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
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
        return 0;
    }

    @Override
    public List<KhachHang> findByName(String HoTen) {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE HoTen = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, HoTen);
            rs = ps.executeQuery();
            while(rs.next()){
                KhachHang k = new KhachHang();
                k.setMaKH(rs.getString(1));
                k.setHoTen(rs.getString(2));
                k.setSdt(rs.getString(3));
                k.setGioiTinh(rs.getString(4));
                k.setNgayDangKy(rs.getDate(5));
                k.setTongChiTieu(rs.getBigDecimal(6));
                list.add(k);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return list;
    }

    public List<KhachHang> searchByParam(String searchParam) {
        List<KhachHang> khachHangList = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE HoTen LIKE ? OR Sdt LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, "%" + searchParam + "%");
            ps.setString(2, "%" + searchParam + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang k = new KhachHang();
                k.setMaKH(rs.getString(1));
                k.setHoTen(rs.getString(2));
                k.setSdt(rs.getString(3));
                k.setGioiTinh(rs.getString(4));
                k.setNgayDangKy(rs.getDate(5));
                k.setTongChiTieu(rs.getBigDecimal(6));
                khachHangList.add(k);
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
        return khachHangList;
    }

}
