/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.NhaSanXuat;
import connection.DatabaseConnection;
import java.sql.Statement;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

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
    public Boolean update(NhaSanXuat model) {
         PreparedStatement ps = null;
        try {
            String sql = "UPDATE NhaSanXuat SET TenNSX = ?, DiaChi = ?, Sdt = ? WHERE MaNSX = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getTenNSX());
            ps.setString(2, model.getDiaChi());
            ps.setString(3, model.getSdt());
            ps.setString(4, model.getMaNSX());
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

