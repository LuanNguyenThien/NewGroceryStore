package com.grocerystore.form;

import com.grocerystore.main.DataInitializer;
import com.grocerystore.model.SanPham;
import com.raven.component.Product;
import com.raven.model.Model_Product;
import com.raven.swing.ScrollBar;
import com.raven.swing.WrapLayout;
import connection.DatabaseConnection;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import com.grocerystore.DAO.ISanPhamDao;
import com.grocerystore.DAO.SanPhamDAOImpl;
import com.grocerystore.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Form_Product extends javax.swing.JPanel {

    public interface ProductClickListener {
        void onProductClick(SanPham sanpham);
    }
    
    private ProductClickListener listener;
    private int itemsPerPage = 10;
    private int currentPage = 0;
    private List<Product> products;
    private ISanPhamDao sanPhamDao;

    private void init() {
        connect_DB();
        // Set layout and scrollbar
        panel.setLayout(new FlowLayout());
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom(JScrollBar.VERTICAL));
        // Load all cards
//        loadProducts();
        // Load the first page
//        loadPage(0);
    }
    
    private void connect_DB(){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void setProductClickListener(ProductClickListener listener) {
        this.listener = listener;
    }

    private void loadProducts() {
        connect_DB();
        List<SanPham> SanPhamList = sanPhamDao.getAll_viewSP("Đang bán");
        products = new ArrayList<>();
        
        for (SanPham sp : SanPhamList) {
            Product product = new Product(sp);
            product.setProductClickListener(new Product.ProductClickListener() {
                @Override
                public void onProductClick(SanPham product) {
                    if (listener != null) {
                        listener.onProductClick(product);
                    }
                }
            });
            products.add(product);
        }
    }

    private void loadPage(int pageNumber) {
        // Clear the panel
        panel.removeAll();
        panel.setLayout(new WrapLayout(WrapLayout.LEADING));

        // Calculate the start and end indices
        int start = pageNumber * itemsPerPage;
        int end = Math.min(start + itemsPerPage, products.size());

        // Add the cards for the current page
        for (int i = start; i < end; i++) {
            panel.add(products.get(i));
        }

        // Refresh the panel
        panel.revalidate();
        panel.repaint();

        // Update the current page
        currentPage = pageNumber;
    }

    // Call this method to go to the next page
    private void nextPage() {
        int totalPages = (int) Math.ceil((double) products.size() / itemsPerPage);
        if (currentPage < totalPages - 1) {
            loadPage(currentPage + 1);
        }
    }

    // Call this method to go to the previous page
    private void previousPage() {
        if (currentPage > 0) {
            loadPage(currentPage - 1);
        }
    }
    
    public Form_Product() {
        sanPhamDao = new SanPhamDAOImpl();
        initComponents();
        init();
    }

//    private void init() {
////        panel.setLayout(new WrapLayout(WrapLayout.LEADING));
//        panel.setLayout(new FlowLayout());
//        jScrollPane1.setVerticalScrollBar(new ScrollBar());
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/1.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/2.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/3.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/4.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/5.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/6.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/7.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/8.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/9.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/10.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/11.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/12.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/4.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/5.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/6.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/7.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/8.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/9.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/10.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/11.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.add(new Product(new Model_Product(new ImageIcon(getClass().getResource("/com/raven/icon/testing/12.jpg")), "Lean Java UI", "Leaning java\nswing ui design\nlike and Subscribe\nthank for watch")));
//        panel.revalidate();
//        panel.repaint();
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnContinue = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane1.setBorder(null);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/grocerystore/icon/icons8_previous_32px_1.png"))); // NOI18N
        btnBack.setBorder(null);
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/grocerystore/icon/icons8_next_32px_5.png"))); // NOI18N
        btnContinue.setBorder(null);
        btnContinue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnContinueMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        previousPage();
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnContinueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnContinueMouseClicked
        // TODO add your handling code here:
        nextPage();
    }//GEN-LAST:event_btnContinueMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnContinue;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
