/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.grocerystore.form;

import com.grocerystore.DAO.INhaSanXuat;
import com.grocerystore.DAO.NhaSanXuatDAOImpl;
import com.grocerystore.dialog.Message;
import com.grocerystore.model.NhaSanXuat;
import com.grocerystore.swing.ComboItem;
import connection.DatabaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Form_ChonNCC extends javax.swing.JFrame {
    
    private INhaSanXuat nhasanxuatDAO;
    private ComboItem data;
    private List<ComboItem> listItem = new ArrayList<>();
    private int result;
    private String MaNSX ;
    
    /**
     * Creates new form Form_ChonNCC
     */
    public Form_ChonNCC() {
        connect_DB();
        initComponents();
        setLocationRelativeTo(this);
        loadDataComboBox();
        
        btn_selected.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                result = JOptionPane.showConfirmDialog(btn_selected, "Bạn có xác nhận chọn nhà cung cấp này không","Message",JOptionPane.YES_NO_OPTION);
                if(JOptionPane.YES_OPTION == result){
                    MaNSX = listItem.get(cb_ncc.getSelectedIndex()).getValue();
                    dispose();
                }
            }
        });
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cb_ncc = new com.raven.swing.CustomCombobox();
        btn_close = new javax.swing.JButton();
        btn_ThemNCC = new javax.swing.JButton();
        btn_selected = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        jLabel1.setText("Nhà cung cấp");

        cb_ncc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/grocerystore/icon/icons8_close_window_32px.png"))); // NOI18N
        btn_close.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        btn_ThemNCC.setBackground(new java.awt.Color(51, 255, 51));
        btn_ThemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/grocerystore/icon/icons8_add_32px_1.png"))); // NOI18N
        btn_ThemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemNCCActionPerformed(evt);
            }
        });

        btn_selected.setBackground(new java.awt.Color(0, 153, 255));
        btn_selected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/grocerystore/icon/icons8_ok_32px.png"))); // NOI18N
        btn_selected.setText("Chọn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_selected)
                    .addComponent(btn_close))
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btn_ThemNCC)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_close)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cb_ncc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btn_selected)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void connect_DB(){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public String getMaNSX(){
        return MaNSX;
    }
    
    private void loadDataComboBox(){
        nhasanxuatDAO = new NhaSanXuatDAOImpl();
        List<NhaSanXuat> listNhaSanXuat = nhasanxuatDAO.getAll();
        
        for(NhaSanXuat nsx : listNhaSanXuat){
            cb_ncc.addItem(new ComboItem(nsx.getMaNSX(), nsx.getTenNSX()));
            data = new ComboItem(nsx.getMaNSX(),nsx.getTenNSX());
            listItem.add(data);
        }
    }

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_ThemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemNCCActionPerformed
        Form_ThemNCC newForm = new Form_ThemNCC();
        newForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        newForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_ThemNCCActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_ChonNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_ChonNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_ChonNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_ChonNCC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_ChonNCC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThemNCC;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_selected;
    private com.raven.swing.CustomCombobox cb_ncc;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
