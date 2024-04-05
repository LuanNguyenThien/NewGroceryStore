package com.raven.model;

import com.grocerystore.model.*;
import javax.swing.Icon;

public class Model_Product {

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Model_Product(Icon icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }

    public Model_Product() {
    }

    private Icon icon;
    private String title;
    private String description;
}
