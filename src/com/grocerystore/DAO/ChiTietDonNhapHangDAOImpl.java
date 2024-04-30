/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.ChiTietDonNhapHang;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author A
 */
public class ChiTietDonNhapHangDAOImpl implements IChiTietDonNhapHangDAO{

    @Override
    public Boolean ThemCTDNH(ChiTietDonNhapHang chiTietDonNhapHang) {
        String sql = "INSERT INTO ChiTietDonNhapHang (MaDNH, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, chiTietDonNhapHang.getMaDNH());
            preparedStatement.setString(2, chiTietDonNhapHang.getMaSP());
            preparedStatement.setInt(3, chiTietDonNhapHang.getSoLuong());
            preparedStatement.setDouble(4, chiTietDonNhapHang.getDonGia());
            
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<ChiTietDonNhapHang> listSanPham(String MaDNH) {
        String sql = "SELECT * FROM ChiTietDonNhapHang WHERE MaDNH = ?";
        List<ChiTietDonNhapHang> list = new ArrayList<ChiTietDonNhapHang>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, MaDNH);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                ChiTietDonNhapHang sanpham = new ChiTietDonNhapHang();
                sanpham.setMaDNH(resultSet.getString("MaDNH"));
                sanpham.setMaSP(resultSet.getString("MaSP"));
                sanpham.setSoLuong(resultSet.getInt("SoLuong"));
                sanpham.setDonGia(resultSet.getDouble("DonGia"));

                list.add(sanpham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public Boolean updateSoLuongSP(String MaDNH, String MaSP, int SoLuong) {
        String sql = "UPDATE ChiTietDonNhapHang SET SoLuong = SoLuong + ? WHERE MaDNH = ? AND MaSP = ?";
        PreparedStatement ps = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            
            ps.setInt(1, SoLuong);
            ps.setString(2, MaDNH);
            ps.setString(3, MaSP);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi! " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
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
    public Boolean checkMaSP(String MaDNH, String MaSP) {
        String sql = "SELECT * FROM ChiTietDonNhapHang WHERE MaDNH = ? AND MaSP = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            

            ps.setString(1, MaDNH);
            ps.setString(2, MaSP);

            rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Lỗi! " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            try {
                if (ps != null) ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;    }
    
}
