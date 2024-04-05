/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.model;

/**
 *
 * @author HUYEN PC
 */
public class CTHD {
    private String MaHD;
    private String MaSP;
    private int SoLuong;
    private double TongTien;
    
    public CTHD() {
    }
    
    public CTHD(String MaHD, String MaSP, int SoLuong, double TongTien) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.SoLuong = SoLuong;
        this.TongTien = TongTien;
    } 
    
    public String getMaHD() {
        return this.MaHD;
    }
    
    public String getMaSP() {
        return this.MaSP;
    }
    
    public int getSoLuong() {
        return this.SoLuong;
    }
    
    public double getTongTien() {
        return this.TongTien;
    }
}
