/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.CTHD;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HUYEN PC
 */
public class ChiTietHoaDonDAOImpl implements IChiTietHoaDonDAO{

    @Override
    public Boolean ThemCTHD(CTHD cthd) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, TongTien) VALUES (?, ?, ?, ?)";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, cthd.getMaHD());
            ps.setString(2, cthd.getMaSP());
            ps.setInt(3, cthd.getSoLuong());
            ps.setDouble(4, cthd.getTongTien());
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
    
}
