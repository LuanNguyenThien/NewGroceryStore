/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.swing;

/**
 *
 * @author My PC
 */
public class Item {
    private String maItem;
    private String tenItem;

    public Item(String maItem, String tenItem) {
        this.maItem = maItem;
        this.tenItem = tenItem;
    }

    public String getMaItem() {
        return maItem;
    }

    public String getTenItem() {
        return tenItem;
    }

    // Override toString method to return the label
    @Override
    public String toString() {
        return tenItem;
    }
}
