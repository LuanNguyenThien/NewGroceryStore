/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.grocerystore.form;

import com.grocerystore.model.NhanVien;
import com.grocerystore.DAO.INhanVienDAO;
import com.grocerystore.DAO.LoaiSanPhamDAOImpl;
import com.grocerystore.DAO.NhaSanXuatDAOImpl;
import com.grocerystore.DAO.NhanVienDAOImpl;
import com.grocerystore.DAO.SanPhamDAOImpl;
import com.grocerystore.model.LoaiSanPham;
import com.grocerystore.model.ModelCard;
import com.grocerystore.model.NhaSanXuat;
import com.grocerystore.model.SanPham;
import com.grocerystore.swing.icon.GoogleMaterialDesignIcons;
import com.grocerystore.swing.icon.IconFontSwing;
import com.grocerystore.swing.scrollbar.ScrollBarCustom;
import com.raven.model.Model_Product;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import connection.DatabaseConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;
import util.Util;

/**
 *
 * @author My PC
 */
public class Form_QLSanPham extends javax.swing.JPanel {

    private LoaiSanPhamDAOImpl loaiSanPhamDao;
    private NhaSanXuatDAOImpl nhaSanXuatDao; 
    private SanPhamDAOImpl sanPhamDao;
    /**
     * Creates new form Form_QLNhanVien
     */
    public Form_QLSanPham() {
        loaiSanPhamDao = new LoaiSanPhamDAOImpl();
        nhaSanXuatDao = new NhaSanXuatDAOImpl();
        sanPhamDao = new SanPhamDAOImpl();
        initComponents();
        setOpaque(false); 
        init();
    }
    
    private void init(){
        lbl_errorInput.setText("");
        lbl_errorInput.setVisible(false);
        tf_giaban.setEnabled(false);
        table_listSP.fixTable(jScrollPane3);
        loadData_SP();
        card_init();
        load_cbbselect();
        format_gianhap();
        format_loinhuan();
    }
    
    private void format_gianhap(){
        // Add DocumentListener to tf_gianhap
        ((AbstractDocument) tf_gianhap.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                    formatText();
                }
            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                    formatText();
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                formatText();
            }

            private void formatText() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            int caretPosition = tf_gianhap.getCaretPosition();
                            String text = tf_gianhap.getText().replaceAll(",", "");
                            if (!text.isEmpty()) {
                                if (text.matches("0*")) {
                                    tf_gianhap.setText("0");
                                } else {
                                    long number = Long.parseLong(text);
                                    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                                    tf_gianhap.setText(numberFormat.format(number));
                                    tf_gianhap.setCaretPosition(Math.min(caretPosition, tf_gianhap.getText().length()));

                                    String loiNhuanText = tf_loinhuan.getText();
                                    if (loiNhuanText != null && !loiNhuanText.isEmpty()) {
                                        double loiNhuan = Double.parseDouble(loiNhuanText);
                                        if (loiNhuan >= 0 && loiNhuan <= 100) {
                                            double giaBan = number + (number * loiNhuan / 100);
                                            long giaBanRounded = Math.round(giaBan);
                                            tf_giaban.setText(numberFormat.format(giaBanRounded));
                                        }
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            // Handle exception
                        }
                    }
                });
            }
        });
    }
    
    private void format_loinhuan(){
        // Add DocumentListener to tf_loinhuan
        ((AbstractDocument) tf_loinhuan.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                    formatText();
                }
            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                    formatText();
                }
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                formatText();
            }

            private void formatText() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            String number = tf_loinhuan.getText().replaceAll(",", "");
                            if (!number.isEmpty()) {
                                double loinhuan = Double.parseDouble(number);
                                if (loinhuan < 0 || loinhuan > 100) {
                                    lbl_errorInput.setText("Lợi nhuận chỉ nằm trong phạm vi 0 đến 100%");
                                    lbl_errorInput.setVisible(true);
                                } else {
                                    String giaNhapText = tf_gianhap.getText();
                                    if (giaNhapText != null && !giaNhapText.isEmpty()) {
                                        double giaNhap = Long.parseLong(giaNhapText);
                                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                                        double giaBan = giaNhap + (giaNhap * loinhuan / 100);
                                        long giaBanRounded = Math.round(giaBan);
                                        tf_giaban.setText(numberFormat.format(giaBanRounded)); 
                                    }
                                    lbl_errorInput.setVisible(false);
                                }
                            } else {
                                lbl_errorInput.setVisible(false);
                            }
                        } catch (NumberFormatException e) {
                            // Handle exception
                        }
                    }
                });
            }
        });
    }
    
    private void connect_DB(){
        try {
            DatabaseConnection.getInstance().connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    private void loadData_SP() {
       connect_DB();
       List<SanPham> SanPhamList = sanPhamDao.getAll_viewSP();

        String[] columnNames = {"Mã SP", "Tên Sản Phẩm", "Tên Loại Sản Phẩm", "Tên Nhà Sản Xuất", "Đơn vị tính", "Giá tiền", "Giá nhập", "Số lượng", "Lợi nhuận", "Tình trạng", "Hình ảnh", "Mã NSX", "Mã LSP"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 10:
                        return ImageIcon.class;
                    default:
                        return Object.class;
                }
            }
        };

        for (SanPham sanPham : SanPhamList) {
            Object[] row = new Object[13];
            row[0] = sanPham.getMaSP();
            row[1] = sanPham.getTenSP();
            row[2] = sanPham.getTenLoaiSP();
            row[3] = sanPham.getTenNSX();
            row[4] = sanPham.getDonViTinh();
            row[5] = sanPham.getGiaTien();
            row[6] = sanPham.getGiaNhap();
            row[7] = sanPham.getSoLuong();
            row[8] = sanPham.getLoiNhuan();
            row[9] = sanPham.getTinhTrang();
            if (sanPham.getHinhAnh() != null) {
                row[10] = Util.byteArrayToImageIcon(sanPham.getHinhAnh()); // Convert byte[] to ImageIcon
            } else {
                row[10] = new ImageIcon(getClass().getResource("/com/grocerystore/icon/none_SP.jpg"));
            }
            row[11] = sanPham.getMaNSX();
            row[12] = sanPham.getMaLoaiSP();
            model.addRow(row);
        }

        table_listSP.setModel(model);
        table_listSP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing

        // Hide MaNSX and MaLSP columns
        table_listSP.removeColumn(table_listSP.getColumnModel().getColumn(12));
        table_listSP.removeColumn(table_listSP.getColumnModel().getColumn(11));


        for (int column = 0; column < table_listSP.getColumnCount(); column++) {
            TableColumn tableColumn = table_listSP.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            // Consider the header's width
            TableCellRenderer headerRenderer = table_listSP.getTableHeader().getDefaultRenderer();
            Object headerValue = tableColumn.getHeaderValue();
            Component headerComp = headerRenderer.getTableCellRendererComponent(table_listSP, headerValue, false, false, 0, column);
            preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + table_listSP.getIntercellSpacing().width);

            for (int row = 0; row < table_listSP.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table_listSP.getCellRenderer(row, column);
                Component c = table_listSP.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + table_listSP.getIntercellSpacing().width;
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
    
    private void card_init(){
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_CART, 60, new Color(255, 255, 255, 255), new Color(255, 255, 255, 255));
        card_NSX.setData(new ModelCard("Nhà sản xuất", 5100, 20, icon1));
        card_NSX.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card_NSXMouseClicked(evt);
            }
        });
        
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOP, 60, new Color(255, 255, 255, 255), new Color(255, 255, 255, 255));
        card_LSP.setData(new ModelCard("Loại sản phẩm", 0, 0, icon2));
        card_LSP.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card_LSPMouseClicked(evt);
            }
        });
    }
    
    private void card_NSXMouseClicked(java.awt.event.MouseEvent evt) {
        // Open Form_LSP here
        Form_NSX form_NSX = new Form_NSX();
        form_NSX.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                load_cbbselect();
            }
        });
        form_NSX.setVisible(true);
    }
    
    private void card_LSPMouseClicked(java.awt.event.MouseEvent evt) {
        // Open Form_LSP here
        Form_LSP form_LSP = new Form_LSP();
        form_LSP.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                load_cbbselect();
            }
        });
        form_LSP.setVisible(true);
    }
    
    
    
    private void load_cbbselect(){
        connect_DB();
        cbb_LSPselect.removeAllItems();
        List<LoaiSanPham> loaiSanPhamList = loaiSanPhamDao.getAll();
        for (LoaiSanPham loaiSanPham : loaiSanPhamList) {
            cbb_LSPselect.addItem(loaiSanPham.getMaLoaiSP(), loaiSanPham.getTenLoaiSP());
        }
        
        cbb_NSXselect.removeAllItems();
        List<NhaSanXuat> nhaSanXuatList = nhaSanXuatDao.getAll();
        for (NhaSanXuat nhaSanXuat : nhaSanXuatList) {
            cbb_NSXselect.addItem(nhaSanXuat.getMaNSX(), nhaSanXuat.getTenNSX());
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_tensp = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_picSP = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tf_gianhap = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_loinhuan = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tf_giaban = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_soluong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btn_reload = new com.raven.swing.Button();
        btn_stopSP = new com.raven.swing.Button();
        btn_capnhatSP = new com.raven.swing.Button();
        btn_themSP = new com.raven.swing.Button();
        cbb_LSPselect = new com.raven.swing.CustomCombobox();
        cbb_NSXselect = new com.raven.swing.CustomCombobox();
        cbb_DVT = new com.raven.swing.CustomCombobox();
        lbl_errorInput = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_listSP = new com.grocerystore.swing.table.Table();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        button1 = new com.raven.swing.Button();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        button2 = new com.raven.swing.Button();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        customCombobox1 = new com.raven.swing.CustomCombobox();
        customCombobox2 = new com.raven.swing.CustomCombobox();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        card_NSX = new com.grocerystore.component.Card_custom();
        card_LSP = new com.grocerystore.component.Card_custom();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setBackground(new java.awt.Color(102, 153, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Sản Phẩm");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        tf_tensp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_tensp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Tên sản phẩm");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Hình ảnh");

        lbl_picSP.setBackground(new java.awt.Color(255, 255, 255));
        lbl_picSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_picSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_picSPMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Giá nhập");

        tf_gianhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_gianhap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Lợi nhuận (%)");

        tf_loinhuan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_loinhuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Giá bán");

        tf_giaban.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_giaban.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Số lượng");

        tf_soluong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_soluong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Loại sản phẩm");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Nhà sản xuất");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Đơn vị tính");

        btn_reload.setBackground(new java.awt.Color(51, 102, 255));
        btn_reload.setForeground(new java.awt.Color(255, 255, 255));
        btn_reload.setText("Làm mới   ");
        btn_reload.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_reload.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_reload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_stopSP.setBackground(new java.awt.Color(51, 102, 255));
        btn_stopSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_stopSP.setText("Ngừng kinh doanh   ");
        btn_stopSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_stopSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_stopSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_capnhatSP.setBackground(new java.awt.Color(51, 102, 255));
        btn_capnhatSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_capnhatSP.setText("Cập nhật   ");
        btn_capnhatSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capnhatSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_capnhatSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btn_themSP.setBackground(new java.awt.Color(51, 102, 255));
        btn_themSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_themSP.setText("Thêm   ");
        btn_themSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_themSP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_themSP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_themSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themSPActionPerformed(evt);
            }
        });

        cbb_LSPselect.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbb_NSXselect.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbb_DVT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cái", "Tờ", "Hộp", "Chai", "Lốc", "Thùng" }));
        cbb_DVT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbb_DVT.setOpaque(false);

        lbl_errorInput.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_errorInput.setForeground(new java.awt.Color(255, 51, 51));
        lbl_errorInput.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_errorInput.setText("Thông tin không được bỏ trống");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_loinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_LSPselect, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_picSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbb_NSXselect, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(cbb_DVT, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbl_errorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_stopSP, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btn_capnhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btn_themSP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel17)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel18)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_tensp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_gianhap, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_loinhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_giaban, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_soluong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbl_picSP, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel20))
                            .addComponent(cbb_LSPselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel15)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel21))
                    .addComponent(cbb_NSXselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel22))
                    .addComponent(cbb_DVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lbl_errorInput)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_stopSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_capnhatSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(102, 153, 255));
        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh Sách Sản Phẩm");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel6.setOpaque(true);

        table_listSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Mã Loại SP", "Mã NSX", "ĐVT", "Giá Tiền", "Giá Nhập", "Số Lượng", "Lợi Nhuận"
            }
        ));
        table_listSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_listSPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_listSP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(102, 153, 255));
        jLabel7.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(76, 76, 76));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Bộ Lọc Sản Phẩm");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel8.setOpaque(true);

        button1.setBackground(new java.awt.Color(51, 102, 255));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Áp dụng   ");
        button1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tên sản phẩm");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        button2.setBackground(new java.awt.Color(51, 102, 255));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Làm mới   ");
        button2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        button2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Loại sản phẩm");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Nhà cung cấp");

        customCombobox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        customCombobox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(108, 108, 108))
                                    .addComponent(jTextField3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(52, 52, 52))
                                    .addComponent(customCombobox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(62, 62, 62))
                                    .addComponent(customCombobox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customCombobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customCombobox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(102, 153, 255));
        jLabel9.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(76, 76, 76));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Thành Phần");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel10.setOpaque(true);

        card_NSX.setBackground(new java.awt.Color(255, 153, 102));
        card_NSX.setColorGradient(new java.awt.Color(255, 102, 102));

        card_LSP.setColorGradient(new java.awt.Color(153, 102, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(card_LSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(card_NSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card_NSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card_LSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_picSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_picSPMouseClicked
        // TODO add your handling code here:
        Form_LoadAnh formLoadAnh = new Form_LoadAnh();
        formLoadAnh.setVisible(true);
        formLoadAnh.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ImageIcon image = formLoadAnh.getSelectedImage();
                if (image != null) {
                    lbl_picSP.setIcon(Util.resizeImage(image, lbl_picSP.getWidth(), lbl_picSP.getHeight()));
                }
            }
        });
        
    }//GEN-LAST:event_lbl_picSPMouseClicked

    private void btn_themSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themSPActionPerformed
        // Create a new SanPham object
        SanPham sp = new SanPham();

        // Set the properties of the SanPham object based on the input fields
        sp.setTenSP(tf_tensp.getText());
        sp.setMaLoaiSP(cbb_LSPselect.getSelectedValue());
        sp.setMaNSX(cbb_NSXselect.getSelectedValue());
        sp.setDonViTinh(cbb_DVT.getSelectedItem().toString());
        System.out.println(sp.getDonViTinh());
        String giatien = tf_giaban.getText().replaceAll(",", "");
        sp.setGiaTien(new BigDecimal(giatien));
        sp.setGiaNhap(new BigDecimal(tf_gianhap.getText()));
        sp.setSoLuong(Integer.parseInt(tf_soluong.getText()));
        sp.setLoiNhuan(Integer.parseInt(tf_loinhuan.getText()));
        sp.setTinhTrang("Đang bán");

        // Convert the image in lbl_picSP to a byte array
        ImageIcon icon = (ImageIcon) lbl_picSP.getIcon();
        byte[] imageInByte = Util.imageIconToByteArray(icon);
        sp.setHinhAnh(imageInByte);

        // Use SanPhamDao to add the new SanPham to the database
        if (sanPhamDao.add(sp)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
        }
        init();
    }//GEN-LAST:event_btn_themSPActionPerformed

    private void table_listSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_listSPMouseClicked
        // TODO add your handling code here:
        int selectedRow = table_listSP.getSelectedRow();
        TableModel model = table_listSP.getModel();

        // Lấy dữ liệu từ hàng được chọn và đặt giá trị cho các textfield
        String tenSP = model.getValueAt(selectedRow, 1).toString();
        tf_tensp.setText(tenSP);
        
        String donViTinh = model.getValueAt(selectedRow, 4).toString();
        cbb_DVT.setSelectedItem(donViTinh);

        BigDecimal giaTien = (BigDecimal) model.getValueAt(selectedRow, 5);
        tf_giaban.setText(giaTien.stripTrailingZeros().toPlainString());

        BigDecimal giaNhap = (BigDecimal) model.getValueAt(selectedRow, 6);
        tf_gianhap.setText(giaNhap.stripTrailingZeros().toPlainString());

        String soLuong = model.getValueAt(selectedRow, 7).toString();
        tf_soluong.setText(soLuong);

        String loiNhuan = model.getValueAt(selectedRow, 8).toString();
        tf_loinhuan.setText(loiNhuan);
        
        String MaNSX = model.getValueAt(selectedRow, 11).toString();
        cbb_NSXselect.setSelectedValue(MaNSX);
        
        String MaLSP = model.getValueAt(selectedRow, 12).toString();
        cbb_LSPselect.setSelectedValue(MaLSP);
        
        String DVT = model.getValueAt(selectedRow, 4).toString();
        cbb_DVT.setSelectedItem(DVT);

        // Đặt hình ảnh cho lbl_picSP từ dữ liệu trong bảng
        lbl_picSP.setIcon((ImageIcon) table_listSP.getValueAt(selectedRow, 10));
    }//GEN-LAST:event_table_listSPMouseClicked

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button btn_capnhatSP;
    private com.raven.swing.Button btn_reload;
    private com.raven.swing.Button btn_stopSP;
    private com.raven.swing.Button btn_themSP;
    private com.raven.swing.Button button1;
    private com.raven.swing.Button button2;
    private com.grocerystore.component.Card_custom card_LSP;
    private com.grocerystore.component.Card_custom card_NSX;
    private com.raven.swing.CustomCombobox cbb_DVT;
    private com.raven.swing.CustomCombobox cbb_LSPselect;
    private com.raven.swing.CustomCombobox cbb_NSXselect;
    private com.raven.swing.CustomCombobox customCombobox1;
    private com.raven.swing.CustomCombobox customCombobox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbl_errorInput;
    private javax.swing.JLabel lbl_picSP;
    private com.grocerystore.swing.table.Table table_listSP;
    private javax.swing.JTextField tf_giaban;
    private javax.swing.JTextField tf_gianhap;
    private javax.swing.JTextField tf_loinhuan;
    private javax.swing.JTextField tf_soluong;
    private javax.swing.JTextField tf_tensp;
    // End of variables declaration//GEN-END:variables
}
