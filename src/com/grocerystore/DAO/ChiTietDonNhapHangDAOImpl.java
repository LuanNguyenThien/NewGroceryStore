/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.ChiTietDonNhapHang;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author A
 */
public class ChiTietDonNhapHangDAOImpl implements IChiTietDonNhapHangDAO{

    @Override
    public Boolean ThemCTDNH(ChiTietDonNhapHang chiTietDonNhapHang) {
        String sql = "Call ThemChiTietDonNhapHang(?, ?, ?)";
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, chiTietDonNhapHang.getMaSP());
            preparedStatement.setInt(2, chiTietDonNhapHang.getSoLuong());
            preparedStatement.setDouble(3, chiTietDonNhapHang.getDonGia());
            
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
    
}
