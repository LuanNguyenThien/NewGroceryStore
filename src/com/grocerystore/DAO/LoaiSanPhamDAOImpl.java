/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.LoaiSanPham;
import connection.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamDAOImpl implements ILoaiSanPham{

    
    @Override
    public Boolean add(LoaiSanPham model) {
        String sql = "Call ThemLoaiSanPham(?)";
        PreparedStatement ps = null;
        try {

            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getTenLoaiSP());
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
    public Boolean update(LoaiSanPham model) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE LoaiSanPham SET TenLoaiSP = ? WHERE MaLoaiSP = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            ps.setString(1, model.getTenLoaiSP());
            ps.setString(2, model.getMaLoaiSP());
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
    public List<LoaiSanPham> getAll() {
                List<LoaiSanPham> loaiSPList = new ArrayList<>();
            Statement st = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM LoaiSanPham";
                st = DatabaseConnection.getInstance().getConnection().createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    LoaiSanPham loaiSP = new LoaiSanPham();
                    loaiSP.setMaLoaiSP(rs.getString("MaLoaiSP"));
                    loaiSP.setTenLoaiSP(rs.getString("TenLoaiSP"));
                    loaiSPList.add(loaiSP);
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
            return loaiSPList;
    }
    
}
