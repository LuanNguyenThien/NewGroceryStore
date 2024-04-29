/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.DonNhapHang;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A
 */
public class DonNhapHangDAOImpl implements IDonNhapHangDAO{

    @Override
    public Boolean ThemDNH(DonNhapHang donNhapHang) {
        String sql = "CALL ThemDonNhapHang(?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);

            preparedStatement.setString(1, donNhapHang.getMaNV());
            preparedStatement.setString(2, donNhapHang.getMaNSX());

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
    public List<DonNhapHang> GetDSDNH() {
        String sql = "SELECT * FROM DonNhapHang WHERE TrangThai = N'Chưa nhập'";
        List<DonNhapHang> dsDonNhapHang = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                DonNhapHang donNhapHang = new DonNhapHang();
                donNhapHang.setMaDNH(resultSet.getString("MaDNH"));
                donNhapHang.setMaNV(resultSet.getString("MaNV"));
                donNhapHang.setMaNSX(resultSet.getString("MaNSX"));
                donNhapHang.setNgayLap(resultSet.getDate("NgayLap"));
                donNhapHang.setTrangThai(resultSet.getString("TrangThai"));
                
                dsDonNhapHang.add(donNhapHang);
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
        return dsDonNhapHang;
    }

    @Override
    public Boolean XacNhanDNH(String MaDNH) {
        String sql = "UPDATE DonNhapHang SET TrangThai = ? WHERE MaDNH = ?";
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "Đã nhập");
            preparedStatement.setString(2, MaDNH);
            
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
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
    public Boolean XoaDNH(String MaDNH) {
        String sql = "DELETE FROM DonNhapHang WHERE MaDNH = ?";
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, MaDNH);
            int rowsDeleted = preparedStatement.executeUpdate();
            
            return rowsDeleted > 0;
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
    public String GetMaxDNH() {
        String MaDNH = "";
        String sql = "SELECT MAX(MaDNH) FROM DonNhapHang";
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                MaDNH = resultSet.getString("MAX(MaDNH)");
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
        return MaDNH;
    }
    
}
