/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.grocerystore.form;

import com.grocerystore.DAO.ChiTietDonNhapHangDAOImpl;
import com.grocerystore.DAO.DonNhapHangDAOImpl;
import com.grocerystore.DAO.IChiTietDonNhapHangDAO;
import com.grocerystore.DAO.IDonNhapHangDAO;
import com.grocerystore.DAO.INhaSanXuat;
import com.grocerystore.DAO.ISanPhamDao;
import com.grocerystore.DAO.NhaSanXuatDAOImpl;
import com.grocerystore.DAO.SanPhamDAOImpl;
import com.grocerystore.model.ChiTietDonNhapHang;
import com.grocerystore.model.DonNhapHang;
import com.grocerystore.model.NhaSanXuat;
import com.grocerystore.model.SanPham;
import com.grocerystore.swing.scrollbar.ScrollBarCustom;
import com.raven.component.PanelSlide;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import connection.DatabaseConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author My PC
 */
public class Form_QLNhapHang extends javax.swing.JPanel {
    
    private ISanPhamDao sanPhamDAO = new SanPhamDAOImpl();
    private IDonNhapHangDAO donNhapDAO = new DonNhapHangDAOImpl();
    private INhaSanXuat nhasanxuatDAO = new NhaSanXuatDAOImpl();
    private IChiTietDonNhapHangDAO chiTietDonNhapDAO = new ChiTietDonNhapHangDAOImpl();
    
    private DonNhapHang donNhap ;
    private int SoLuong;
    private String MaDNH;
    
    /**
     * Creates new form Form_QLNhanVien
     */
    public Form_QLNhapHang() {
        connect_DB();
        initComponents();
        setOpaque(false);
        tb_DonNhapHang.fixTable(jScrollPane1);
        tb_ChiTietDonHang.fixTable(jScrollPane2);
        tb_SanPham.fixTable(jScrollPane3);
        setEditTextField();
        loadSanPham();
        loadDonNhapHang();
        loadChiTiet();
        turnOffButton();

    }
    
    private void connect_DB(){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private void loadSanPham() {
        List<SanPham> listSanPham = sanPhamDAO.getAll();

        String[] columnNames = {"Mã SP", "Tên Sản Phẩm", "Mã Loại SP", "Mã NSX","ĐVT","Giá Tiền","Giá Nhập","Số Lượng","Lợi Nhuận"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (SanPham sp : listSanPham) {
            Object[] row = new Object[9];
            row[0] = sp.getMaSP();
            row[1] = sp.getTenSP();
            row[2] = sp.getMaLoaiSP();
            row[3] = sp.getMaNSX();
            row[4] = sp.getDonViTinh();
            row[5] = sp.getGiaTien();
            row[6] = sp.getGiaNhap();
            row[7] = sp.getSoLuong();
            row[8] = sp.getLoiNhuan();
            
            model.addRow(row);
    }

    tb_SanPham.setModel(model);
    tb_SanPham.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

    for (int column = 0; column < tb_SanPham.getColumnCount(); column++) {
        TableColumn tableColumn = tb_SanPham.getColumnModel().getColumn(column);
        int preferredWidth = tableColumn.getMinWidth();
        int maxWidth = tableColumn.getMaxWidth();

        // Consider the header's width
        TableCellRenderer headerRenderer = tb_SanPham.getTableHeader().getDefaultRenderer();
        Object headerValue = tableColumn.getHeaderValue();
        Component headerComp = headerRenderer.getTableCellRendererComponent(tb_SanPham, headerValue, false, false, 0, column);
        preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + tb_SanPham.getIntercellSpacing().width);

        for (int row = 0; row < tb_SanPham.getRowCount(); row++) {
            TableCellRenderer cellRenderer = tb_SanPham.getCellRenderer(row, column);
            Component c = tb_SanPham.prepareRenderer(cellRenderer, row, column);
            int width = c.getPreferredSize().width + tb_SanPham.getIntercellSpacing().width;
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
    
    private void loadDonNhapHang(){
        List<DonNhapHang> listDonNhap = donNhapDAO.GetDSDNH();

        String[] columnNames = {"Mã Phiếu", "Ngày lập", "Nhà Sản Xuất", "Nhân Viên","Trạng Thái"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (DonNhapHang donnhap : listDonNhap) {
            Object[] row = new Object[5];
            row[0] = donnhap.getMaDNH();
            row[1] = donnhap.getNgayLap();
            row[2] = donnhap.getMaNSX();
            row[3] = donnhap.getMaNV();
            row[4] = donnhap.getTrangThai();
            model.addRow(row);
        }

        tb_DonNhapHang.setModel(model);
        tb_DonNhapHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

        for (int column = 0; column < tb_DonNhapHang.getColumnCount(); column++) {
            TableColumn tableColumn = tb_DonNhapHang.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            // Consider the header's width
            TableCellRenderer headerRenderer = tb_DonNhapHang.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(tb_DonNhapHang, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + tb_DonNhapHang.getIntercellSpacing().width);

            for (int row = 0; row < tb_DonNhapHang.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tb_DonNhapHang.getCellRenderer(row, column);
                Component c = tb_DonNhapHang.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tb_DonNhapHang.getIntercellSpacing().width;
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
    
    private void loadChiTiet(){
       

        String[] columnNames = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá Nhập", "Số lượng"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        tb_ChiTietDonHang.setModel(model);
        tb_ChiTietDonHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

        for (int column = 0; column < tb_ChiTietDonHang.getColumnCount(); column++) {
            TableColumn tableColumn = tb_ChiTietDonHang.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            // Consider the header's width
            TableCellRenderer headerRenderer = tb_ChiTietDonHang.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(tb_ChiTietDonHang, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + tb_ChiTietDonHang.getIntercellSpacing().width);

            for (int row = 0; row < tb_ChiTietDonHang.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tb_ChiTietDonHang.getCellRenderer(row, column);
                Component c = tb_ChiTietDonHang.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tb_ChiTietDonHang.getIntercellSpacing().width;
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
    
    private void loadSanPhamByNSX(String maNSX){
        List<SanPham> listSanPham = sanPhamDAO.findByMaNSX(maNSX);

        String[] columnNames = {"Mã SP", "Tên Sản Phẩm", "Mã Loại SP", "Mã NSX","ĐVT","Giá Tiền","Giá Nhập","Số Lượng","Lợi Nhuận"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (SanPham sp : listSanPham) {
            Object[] row = new Object[9];
            row[0] = sp.getMaSP();
            row[1] = sp.getTenSP();
            row[2] = sp.getMaLoaiSP();
            row[3] = sp.getMaNSX();
            row[4] = sp.getDonViTinh();
            row[5] = sp.getGiaTien();
            row[6] = sp.getGiaNhap();
            row[7] = sp.getSoLuong();
            row[8] = sp.getLoiNhuan();
            
            model.addRow(row);
        }

        tb_SanPham.setModel(model);
        tb_SanPham.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

        for (int column = 0; column < tb_SanPham.getColumnCount(); column++) {
            TableColumn tableColumn = tb_SanPham.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            // Consider the header's width
            TableCellRenderer headerRenderer = tb_SanPham.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(tb_SanPham, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + tb_SanPham.getIntercellSpacing().width);

            for (int row = 0; row < tb_SanPham.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tb_SanPham.getCellRenderer(row, column);
                Component c = tb_SanPham.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tb_SanPham.getIntercellSpacing().width;
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
    
    private void setEditTextField(){
        tf_MaSP.setEditable(false);
        tf_GiaNhap.setEditable(false);
        tf_TenSP.setEditable(false);
        tf_SoLuong.setEditable(false);
    }
    
    private void clearText(){
        tf_MaSP.setText("");
        tf_TenSP.setText("");
        tf_SoLuong.setText("");
        tf_GiaNhap.setText("");
    }
    
    private void turnOffButton(){
        btn_Them.setEnabled(false);
        btn_Them.setBackground(Color.gray);
        btn_HuyPhieu.setEnabled(false);
        btn_HuyPhieu.setBackground(Color.gray);
        btn_Luu.setEnabled(false);
        btn_Luu.setBackground(Color.gray);
        btn_Huy.setEnabled(false);
        btn_Huy.setBackground(Color.gray);
        btn_XacNhan.setEnabled(false);
        btn_XacNhan.setBackground(Color.gray);
        btn_Xoa.setEnabled(false);
        btn_Xoa.setBackground(Color.gray);
        tb_SanPham.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_ChiTietDonHang = new com.grocerystore.swing.table.Table();
        btn_TaoPhieu = new com.raven.swing.Button();
        jLabel1 = new javax.swing.JLabel();
        tf_MaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_TenSP = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_GiaNhap = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_SoLuong = new javax.swing.JTextField();
        btn_Them = new com.raven.swing.Button();
        btn_HuyPhieu = new com.raven.swing.Button();
        btn_Luu = new com.raven.swing.Button();
        btn_Huy = new com.raven.swing.Button();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_DonNhapHang = new com.grocerystore.swing.table.Table();
        btn_XacNhan = new com.raven.swing.Button();
        btn_Xoa = new com.raven.swing.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_SanPham = new com.grocerystore.swing.table.Table();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(102, 153, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Nhập Sản Phẩm");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        tb_ChiTietDonHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá Nhập", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_ChiTietDonHang);
        if (tb_ChiTietDonHang.getColumnModel().getColumnCount() > 0) {
            tb_ChiTietDonHang.getColumnModel().getColumn(0).setPreferredWidth(200);
            tb_ChiTietDonHang.getColumnModel().getColumn(1).setPreferredWidth(200);
            tb_ChiTietDonHang.getColumnModel().getColumn(2).setMinWidth(130);
            tb_ChiTietDonHang.getColumnModel().getColumn(2).setPreferredWidth(130);
            tb_ChiTietDonHang.getColumnModel().getColumn(2).setMaxWidth(130);
            tb_ChiTietDonHang.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_TaoPhieu.setBackground(new java.awt.Color(51, 102, 255));
        btn_TaoPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btn_TaoPhieu.setText("Tạo phiếu   ");
        btn_TaoPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TaoPhieu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_TaoPhieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_TaoPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoPhieuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Mã sản phẩm");

        tf_MaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_MaSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Tên sản phẩm");

        tf_TenSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_TenSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Đơn giá nhập");

        tf_GiaNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_GiaNhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Số lượng");

        tf_SoLuong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_SoLuong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tf_SoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_SoLuongKeyTyped(evt);
            }
        });

        btn_Them.setBackground(new java.awt.Color(51, 102, 255));
        btn_Them.setForeground(new java.awt.Color(255, 255, 255));
        btn_Them.setText("Thêm   ");
        btn_Them.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Them.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_Them.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_HuyPhieu.setBackground(new java.awt.Color(204, 0, 51));
        btn_HuyPhieu.setForeground(new java.awt.Color(255, 255, 255));
        btn_HuyPhieu.setText("Hủy   ");
        btn_HuyPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_HuyPhieu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_HuyPhieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_HuyPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyPhieuActionPerformed(evt);
            }
        });

        btn_Luu.setBackground(new java.awt.Color(51, 102, 255));
        btn_Luu.setForeground(new java.awt.Color(255, 255, 255));
        btn_Luu.setText("Lưu   ");
        btn_Luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Luu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_Luu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        btn_Huy.setBackground(new java.awt.Color(204, 0, 51));
        btn_Huy.setForeground(new java.awt.Color(255, 255, 255));
        btn_Huy.setText("Hủy   ");
        btn_Huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Huy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_Huy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tf_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(tf_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(tf_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(tf_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btn_TaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_HuyPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_TaoPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_GiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_SoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HuyPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(102, 153, 255));
        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh Sách Đơn Nhập Chưa Nhập Về Kho");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel6.setOpaque(true);

        tb_DonNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu", "Ngày Lập", "Nhà Sản Xuất", "Nhân Viên", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_DonNhapHang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tb_DonNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_DonNhapHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_DonNhapHang);
        if (tb_DonNhapHang.getColumnModel().getColumnCount() > 0) {
            tb_DonNhapHang.getColumnModel().getColumn(0).setResizable(false);
            tb_DonNhapHang.getColumnModel().getColumn(1).setResizable(false);
            tb_DonNhapHang.getColumnModel().getColumn(2).setResizable(false);
            tb_DonNhapHang.getColumnModel().getColumn(3).setResizable(false);
            tb_DonNhapHang.getColumnModel().getColumn(4).setResizable(false);
        }

        btn_XacNhan.setBackground(new java.awt.Color(51, 102, 255));
        btn_XacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btn_XacNhan.setText("Xác nhận nhập hàng về kho   ");
        btn_XacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_XacNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_XacNhan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanActionPerformed(evt);
            }
        });

        btn_Xoa.setBackground(new java.awt.Color(204, 0, 51));
        btn_Xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_Xoa.setText("Xóa   ");
        btn_Xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Xoa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_Xoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(102, 153, 255));
        jLabel7.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(76, 76, 76));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Danh Sách Sản Phẩm");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel8.setOpaque(true);

        tb_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Mã Loại SP", "Mã NSX", "ĐVT", "Giá Tiền", "Giá Nhập", "Số Lượng", "Lợi Nhuận"
            }
        ));
        tb_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tb_SanPham);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TaoPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoPhieuActionPerformed
        
        clearText();
        
        List<NhaSanXuat> listNhaSanXuat = nhasanxuatDAO.getAll();
        
        Form_ChonNCC newForm = new Form_ChonNCC();
       
        newForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newForm.setVisible(true);
        
        newForm.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                String MaNSX = newForm.getMaNSX();
                if(MaNSX != null){
                    loadSanPhamByNSX(MaNSX);

                    donNhap = new DonNhapHang();

                    donNhap.setMaNV(PanelSlide.IDCurUser);
                    donNhap.setMaNSX(MaNSX);

                    btn_Them.setEnabled(true);
                    btn_Them.setBackground(new Color(51,102,255));

                    btn_HuyPhieu.setEnabled(true);
                    btn_HuyPhieu.setBackground(new Color(204,0,51));

                    tb_SanPham.setEnabled(true);
                }
            }
        });

    }//GEN-LAST:event_btn_TaoPhieuActionPerformed

    private void tb_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SanPhamMouseClicked

        int row = tb_SanPham.rowAtPoint(evt.getPoint());
        tf_MaSP.setText(tb_SanPham.getValueAt(row, 0).toString());
        tf_TenSP.setText(tb_SanPham.getValueAt(row, 1).toString());
        tf_GiaNhap.setText(tb_SanPham.getValueAt(row, 6).toString());
        tf_SoLuong.setEditable(true);
        SoLuong = Integer.parseInt(tb_SanPham.getValueAt(row, 7).toString());
        
    }//GEN-LAST:event_tb_SanPhamMouseClicked

    private void tb_DonNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_DonNhapHangMouseClicked
        int row = tb_DonNhapHang.rowAtPoint(evt.getPoint());
        MaDNH = tb_DonNhapHang.getValueAt(row, 0).toString();
        
        btn_XacNhan.setEnabled(true);
        btn_XacNhan.setBackground(new Color(51,102,255));
        
        btn_Xoa.setEnabled(true);
        btn_Xoa.setBackground(new Color(204,0,51));
    }//GEN-LAST:event_tb_DonNhapHangMouseClicked

    private void btn_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanActionPerformed
  
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn Có Muốn Xác Nhận Đơn Hàng Này Không ?",
                "Xác Nhận Đơn Hàng",JOptionPane.YES_NO_OPTION);

        if(JOptionPane.YES_OPTION == result){
            if(donNhapDAO.XacNhanDNH(MaDNH)){
                List<ChiTietDonNhapHang> listSanPham =  chiTietDonNhapDAO.listSanPham(MaDNH);
                for(ChiTietDonNhapHang sanpham : listSanPham){
                    sanPhamDAO.update_soluongByNhapHang(sanpham.getMaSP(), sanpham.getSoLuong());
                }
                loadSanPham();
                loadDonNhapHang();
                JOptionPane.showMessageDialog(this, "Xác nhận đơn nhập hàng thành công");  
            }else{
                JOptionPane.showMessageDialog(this, "Xác nhận thất bại","Hệ thống thông báo",JOptionPane.ERROR_MESSAGE);
            }
            
            
 
        }
       
    }//GEN-LAST:event_btn_XacNhanActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
   
        int result = JOptionPane.showConfirmDialog(this,
                "Bạn Có Muốn Xóa Đơn Hàng Này Không ?",
                "Xác Nhận Xóa",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
        if(JOptionPane.YES_OPTION == result){
            if(donNhapDAO.XoaDNH(MaDNH)){
                loadDonNhapHang();
                JOptionPane.showMessageDialog(this, "Xóa thành công");

                btn_XacNhan.setEnabled(false);
                btn_XacNhan.setBackground(Color.gray);
                btn_Xoa.setEnabled(false);
                btn_Xoa.setBackground(Color.gray);  
            }else{
                JOptionPane.showMessageDialog(this, "Xóa thất bại","Hệ thống thông báo",JOptionPane.ERROR_MESSAGE);
            }
        
        }
        
        
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
        
        try{
            int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn lưu đơn nhập hàng này?","Hệ thống",JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(JOptionPane.YES_NO_OPTION == result){
                if(donNhapDAO.ThemDNH(donNhap)){

                        chiTietDonNhapDAO = new ChiTietDonNhapHangDAOImpl();
                        String maDNH = donNhapDAO.GetMaxDNH();

                        DefaultTableModel model = new DefaultTableModel();
                        model = (DefaultTableModel) tb_ChiTietDonHang.getModel();

                        for(int i = 0 ; i < model.getRowCount(); i++){
                            ChiTietDonNhapHang chiTiet = new ChiTietDonNhapHang();
                            chiTiet.setMaDNH(maDNH);
                            chiTiet.setMaSP(tb_ChiTietDonHang.getValueAt(i, 0).toString());
                            chiTiet.setSoLuong(Integer.parseInt(tb_ChiTietDonHang.getValueAt(i, 3).toString()));
                            chiTiet.setDonGia(Double.parseDouble(tb_ChiTietDonHang.getValueAt(i, 2).toString()));
                            chiTietDonNhapDAO.ThemCTDNH(chiTiet);
                        }

                        JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công");
                        tb_ChiTietDonHang.removeAll();
                        loadDonNhapHang();
                        loadChiTiet();
                        loadSanPham();

                }else{
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình tạo đơn","Hệ thống thông báo",JOptionPane.ERROR_MESSAGE);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void tf_SoLuongKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_SoLuongKeyTyped
        char c = evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_tf_SoLuongKeyTyped

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        
        btn_Luu.setEnabled(true);
        btn_Luu.setBackground(new Color(51,102,255));
        btn_Huy.setEnabled(true);
        btn_Huy.setBackground(new Color(204,0,51));

        if(tf_MaSP.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm thêm vào đơn hàng");
        }
        else{
            if(tf_SoLuong.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sản phẩm");
                tf_SoLuong.requestFocus();
            }
            else if(Integer.parseInt(tf_SoLuong.getText()) > SoLuong){
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm còn lại không đủ");
            }
            else{
                
                DefaultTableModel model = new DefaultTableModel();
                model = (DefaultTableModel) tb_ChiTietDonHang.getModel();


                Object[] row = new Object[4];
                row[0] = tf_MaSP.getText();
                row[1] = tf_TenSP.getText();
                row[2] = tf_GiaNhap.getText();
                row[3] = tf_SoLuong.getText();
                model.addRow(row);

                clearText();
                setEditTextField();
            } 
        }

    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_HuyPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyPhieuActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy phiếu này không","Thông báo",JOptionPane.YES_NO_CANCEL_OPTION);
        if(JOptionPane.YES_OPTION == result){
            loadSanPham();
            loadChiTiet();
            loadDonNhapHang();

            turnOffButton();
            clearText();
            setEditTextField();
            JOptionPane.showMessageDialog(this, "Đã hủy tạo phiếu thành công");
        }
    }//GEN-LAST:event_btn_HuyPhieuActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy phiếu này không","Thông báo",JOptionPane.YES_NO_CANCEL_OPTION);
        if(JOptionPane.YES_OPTION == result){
            loadSanPham();
            loadChiTiet();
            loadDonNhapHang();

            turnOffButton();
            clearText();
            setEditTextField();
            JOptionPane.showMessageDialog(this, "Đã hủy tạo phiếu thành công");
        }
    }//GEN-LAST:event_btn_HuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_Huy;
    private com.raven.swing.Button btn_HuyPhieu;
    private com.raven.swing.Button btn_Luu;
    private com.raven.swing.Button btn_TaoPhieu;
    private com.raven.swing.Button btn_Them;
    private com.raven.swing.Button btn_XacNhan;
    private com.raven.swing.Button btn_Xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.grocerystore.swing.table.Table tb_ChiTietDonHang;
    private com.grocerystore.swing.table.Table tb_DonNhapHang;
    private com.grocerystore.swing.table.Table tb_SanPham;
    private javax.swing.JTextField tf_GiaNhap;
    private javax.swing.JTextField tf_MaSP;
    private javax.swing.JTextField tf_SoLuong;
    private javax.swing.JTextField tf_TenSP;
    // End of variables declaration//GEN-END:variables
}
