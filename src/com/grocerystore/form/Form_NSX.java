/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.grocerystore.form;

import com.grocerystore.DAO.LoaiSanPhamDAOImpl;
import com.grocerystore.DAO.NhaSanXuatDAOImpl;
import com.grocerystore.model.LoaiSanPham;
import com.grocerystore.model.NhaSanXuat;
import connection.DatabaseConnection;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author My PC
 */
public class Form_NSX extends javax.swing.JFrame {
    
    /**
     * Creates new form Form_NSX
     */
    String MaNSX_selected;
    private NhaSanXuatDAOImpl nhaSanXuatDao;
    
    public Form_NSX() {
        nhaSanXuatDao = new NhaSanXuatDAOImpl();
        connect_DB();
        initComponents();
        table_NSX.fixTable(jScrollPane1);
        loadData();
        
        lbl_errorInput.setVisible(false);
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                lbl_errorInput.setVisible(false);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                lbl_errorInput.setVisible(false);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                lbl_errorInput.setVisible(false);
            }
        };

        tf_NSX.getDocument().addDocumentListener(documentListener);
        tf_diachi.getDocument().addDocumentListener(documentListener);
        tf_sdt.getDocument().addDocumentListener(documentListener);
    }
    
    private void connect_DB(){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    private void loadData() {
        List<NhaSanXuat> nhaSanXuatList = nhaSanXuatDao.getAll();

        String[] columnNames = {"Mã NSX", "Tên Nhà Sản Xuất", "Địa Chỉ", "SĐT"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // This causes all cells to be non-editable
                return false;
            }
        };

        for (NhaSanXuat nhaSanXuat : nhaSanXuatList) {
            Object[] row = new Object[4];
            row[0] = nhaSanXuat.getMaNSX();
            row[1] = nhaSanXuat.getTenNSX();
            row[2] = nhaSanXuat.getDiaChi();
            row[3] = nhaSanXuat.getSdt();
            model.addRow(row);
        }

        table_NSX.setModel(model);
        table_NSX.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

        for (int column = 0; column < table_NSX.getColumnCount(); column++) {
            TableColumn tableColumn = table_NSX.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            // Consider the header's width
            TableCellRenderer headerRenderer = table_NSX.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table_NSX, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + table_NSX.getIntercellSpacing().width);

            for (int row = 0; row < table_NSX.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table_NSX.getCellRenderer(row, column);
                Component c = table_NSX.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table_NSX.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                // We've exceeded the maximum width, no need to check other rows
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            tableColumn.setMinWidth(preferredWidth+5); // Set minimum width instead of preferred width
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tf_NSX = new javax.swing.JTextField();
        btn_them = new com.raven.swing.Button();
        lbl_errorInput = new javax.swing.JLabel();
        btn_capnhat = new com.raven.swing.Button();
        tf_diachi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tf_sdt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_exit = new com.raven.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_NSX = new com.grocerystore.swing.table.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tên nhà sản xuất");

        tf_NSX.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_NSX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_them.setBackground(new java.awt.Color(51, 102, 255));
        btn_them.setForeground(new java.awt.Color(255, 255, 255));
        btn_them.setText("Thêm   ");
        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_them.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_them.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        lbl_errorInput.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_errorInput.setForeground(new java.awt.Color(255, 51, 51));
        lbl_errorInput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_errorInput.setText("Thông tin không được bỏ trống");

        btn_capnhat.setBackground(new java.awt.Color(51, 102, 255));
        btn_capnhat.setForeground(new java.awt.Color(255, 255, 255));
        btn_capnhat.setText("Cập nhật   ");
        btn_capnhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capnhat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_capnhat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });

        tf_diachi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_diachi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Địa chỉ");

        tf_sdt.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_sdt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Số điện thoại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_errorInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_NSX, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 22, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btn_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_NSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_errorInput)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        btn_exit.setForeground(new java.awt.Color(51, 102, 255));
        btn_exit.setText("X");
        btn_exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NHÀ SẢN XUẤT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                .addGap(248, 248, 248)
                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 255), 1, true));

        table_NSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_NSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_NSXMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_NSX);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        String tenNhaSX = tf_NSX.getText().trim();
        String diachi = tf_diachi.getText().trim();
        String sdt = tf_sdt.getText().trim();
        if (tenNhaSX.isEmpty()||diachi.isEmpty()||sdt.isEmpty()) {
            lbl_errorInput.setText("Thông tin không được bỏ trống");
            lbl_errorInput.setVisible(true);
        } else if (!sdt.matches("\\d{8}|\\d{10}")) {
            lbl_errorInput.setText("Số điện thoại phải là số có 8 hoặc 10 chữ số");
            lbl_errorInput.setVisible(true);
        } else {
            NhaSanXuat nhaSanXuat = new NhaSanXuat();
            nhaSanXuat.setTenNSX(tenNhaSX);
            nhaSanXuat.setDiaChi(diachi);
            nhaSanXuat.setSdt(sdt);
            boolean isAdded = nhaSanXuatDao.add(nhaSanXuat);
            if (isAdded) {
                // Code to handle successful addition
                // For example, you might want to reload the data
                loadData();
                tf_NSX.setText("");
                tf_diachi.setText("");
                tf_sdt.setText("");
                
                lbl_errorInput.setText("Thêm nhà sản xuất thành công");
                lbl_errorInput.setVisible(true);
            } else {
                // Code to handle failure in addition
                // For example, you might want to show an error message
                lbl_errorInput.setText("Thêm nhà sản xuất thất bại");
                lbl_errorInput.setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        // TODO add your handling code here:
        String maNhaSX = MaNSX_selected;
        String tenNhaSX = tf_NSX.getText().trim();
        String diachi = tf_diachi.getText().trim();
        String sdt = tf_sdt.getText().trim();
        if (tenNhaSX.isEmpty()||diachi.isEmpty()||sdt.isEmpty()) {
            lbl_errorInput.setText("Thông tin không được bỏ trống");
            lbl_errorInput.setVisible(true);
        } else if (!sdt.matches("\\d{8}|\\d{10}")) {
            lbl_errorInput.setText("Số điện thoại phải là số có 8 hoặc 10 chữ số");
            lbl_errorInput.setVisible(true);
        }else if(MaNSX_selected == null || MaNSX_selected.isEmpty()){
            lbl_errorInput.setText("Chưa có thông tin nhà sản xuất được chọn");
            lbl_errorInput.setVisible(true);
        } else {
            NhaSanXuat nhaSanXuat = new NhaSanXuat();
            nhaSanXuat.setMaNSX(maNhaSX);
            nhaSanXuat.setTenNSX(tenNhaSX);
            nhaSanXuat.setDiaChi(diachi);
            nhaSanXuat.setSdt(sdt);
            boolean isUpdated = nhaSanXuatDao.update(nhaSanXuat);
            if (isUpdated) {
                loadData();
                tf_NSX.setText("");
                tf_diachi.setText("");
                tf_sdt.setText("");
                MaNSX_selected = null;
                
                lbl_errorInput.setText("Cập nhật nhà sản xuất thành công");
                lbl_errorInput.setVisible(true);
            } else {
                lbl_errorInput.setText("Cập nhật nhà sản xuất thất bại");
                lbl_errorInput.setVisible(true);
            }
        }
    }//GEN-LAST:event_btn_capnhatActionPerformed

    private void table_NSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_NSXMouseClicked
        // TODO add your handling code here:
        int selectedRow = table_NSX.getSelectedRow();
        if (selectedRow != -1) {
            String maNhaSX = table_NSX.getValueAt(selectedRow, 0).toString();
            String tenNhaSX = table_NSX.getValueAt(selectedRow, 1).toString();
            String diaChi = table_NSX.getValueAt(selectedRow, 2).toString();
            String sdt = table_NSX.getValueAt(selectedRow, 3).toString();
            
            MaNSX_selected = maNhaSX;
            tf_NSX.setText(tenNhaSX);
            tf_diachi.setText(diaChi);
            tf_sdt.setText(sdt);
        }
    }//GEN-LAST:event_table_NSXMouseClicked


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
            java.util.logging.Logger.getLogger(Form_NSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_NSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_NSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_NSX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_NSX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_capnhat;
    private com.raven.swing.Button btn_exit;
    private com.raven.swing.Button btn_them;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbl_errorInput;
    private com.grocerystore.swing.table.Table table_NSX;
    private javax.swing.JTextField tf_NSX;
    private javax.swing.JTextField tf_diachi;
    private javax.swing.JTextField tf_sdt;
    // End of variables declaration//GEN-END:variables
}
