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
    
}
