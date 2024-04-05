/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grocerystore.DAO;

import com.grocerystore.model.NhaSanXuat;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface INhaSanXuat {
    
    	List<NhaSanXuat> getAll();
	Boolean add (NhaSanXuat model);
	Boolean update (NhaSanXuat model);
    
    
    
}
