/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.LoaiSanPham;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ILoaiSanPham {
        Boolean add (LoaiSanPham model);
    	Boolean update (LoaiSanPham model);
        List<LoaiSanPham> getAll();

    
}
