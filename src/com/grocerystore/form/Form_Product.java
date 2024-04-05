package com.grocerystore.form;

import com.grocerystore.main.DataInitializer;
import com.grocerystore.model.SanPham;
import com.raven.component.Product;
import com.raven.model.Model_Product;
import com.raven.swing.ScrollBar;
import com.raven.swing.WrapLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Form_Product extends javax.swing.JPanel {

    public interface ProductClickListener {
        void onProductClick(SanPham sanpham);
    }
    
    private ProductClickListener listener;
    private int itemsPerPage = 12;
    private int currentPage = 0;
    private List<Product> cards;

    private void init() {
        // Load all cards
        loadCards();

        // Set layout and scrollbar
        panel.setLayout(new FlowLayout());
        jScrollPane1.setVerticalScrollBar(new ScrollBar());

        // Load the first page
        loadPage(0);
    }
    
    public void setProductClickListener(ProductClickListener listener) {
        this.listener = listener;
    }

    private void loadCards() {
        cards = new ArrayList<>();
        SanPham sp1 = DataInitializer.sp1;
        SanPham sp2 = DataInitializer.sp2;
        for (int i = 1; i <= 36; i++) {
            int iconNumber = (i % 12) + 1;
            Product product;
            if(i%2==1){
                sp1.setHinhAnh(util.Util.imageIconToByteArray(new ImageIcon(getClass().getResource("/com/raven/icon/testing/" + iconNumber + ".jpg"))));
                product = new Product(sp1);
            }
            else
            {
                sp2.setHinhAnh(util.Util.imageIconToByteArray(new ImageIcon(getClass().getResource("/com/raven/icon/testing/" + iconNumber + ".jpg"))));
                product = new Product(sp2);
            }
            product.setProductClickListener(new Product.ProductClickListener() {
                @Override
                public void onProductClick(SanPham product) {
                    if (listener != null) {
                        listener.onProductClick(product);
                    }
                }
            });
            cards.add(product);
        }
    }

    private void loadPage(int pageNumber) {
        // Clear the panel
        panel.removeAll();
        panel.setLayout(new WrapLayout(WrapLayout.LEADING));

        // Calculate the start and end indices
        int start = pageNumber * itemsPerPage;
        int end = Math.min(start + itemsPerPage, cards.size());

        // Add the cards for the current page
        for (int i = start; i < end; i++) {
            panel.add(cards.get(i));
        }

        // Refresh the panel
        panel.revalidate();
        panel.repaint();

        // Update the current page
        currentPage = pageNumber;
    }

    // Call this method to go to the next page
    private void nextPage() {
        int totalPages = (int) Math.ceil((double) cards.size() / itemsPerPage);
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

        jScrollPane1.setBorder(null);

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

        btnBack.setIcon(new javax.swing.ImageIcon("D:\\DH_SPKT\\Nam_3\\Ki_1\\Software_Engineering\\DACK\\ToyStore-master\\QuanLyCuaHangBanDoChoi\\Resources\\icons8_previous_32px_1.png")); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        btnContinue.setText("Continue");
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnContinue))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue))
                .addContainerGap())
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
