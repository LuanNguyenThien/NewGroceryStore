/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.NhaSanXuat;
import connection.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Statement;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NhaSanXuatDAOImpl implements INhaSanXuat{

    

    @Override
    public Boolean add(NhaSanXuat model) {
        String sql = "Call ThemNhaSanXuat(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getTenNSX());
            ps.setString(2, model.getDiaChi());
            ps.setString(3, model.getSdt());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
    public Boolean update(NhaSanXuat model) {
        CallableStatement cs = null;
        try {
            String sql = "{CALL UpdateNhaSanXuat(?, ?, ?, ?)}";
            cs = DatabaseConnection.getInstance().getConnection().prepareCall(sql);
            cs.setString(1, model.getMaNSX());
            cs.setString(2, model.getTenNSX());
            cs.setString(3, model.getDiaChi());
            cs.setString(4, model.getSdt());
            int rowsUpdated = cs.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
    public List<NhaSanXuat> getAll() {
        List<NhaSanXuat> nhaSXList = new ArrayList<>();
            Statement st = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM NhaSanXuat";
                st = DatabaseConnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    NhaSanXuat nhaSX = new NhaSanXuat();
                    nhaSX.setMaNSX(rs.getString("MaNSX"));
                    nhaSX.setTenNSX(rs.getString("TenNSX"));
                    nhaSX.setDiaChi(rs.getString("DiaChi"));
                    nhaSX.setSdt(rs.getString("Sdt"));

                    nhaSXList.add(nhaSX);
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
            return nhaSXList;
        }
    
} 

