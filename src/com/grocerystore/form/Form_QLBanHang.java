/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.grocerystore.form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.grocerystore.DAO.ChiTietHoaDonDAOImpl;
import com.grocerystore.DAO.HoaDonDAOImpl;
import com.grocerystore.DAO.IChiTietHoaDonDAO;
import com.grocerystore.DAO.IHoaDonDAO;
import com.grocerystore.model.NhanVien;
import com.grocerystore.DAO.INhanVienDAO;
import com.grocerystore.DAO.NhanVienDAOImpl;
import com.grocerystore.main.DataInitializer;
import com.grocerystore.model.CTHD;
import com.grocerystore.model.HoaDon;
import com.grocerystore.model.ModelStudent;
import com.grocerystore.model.SanPham;
import com.grocerystore.model.XuatHoaDon;
import com.grocerystore.swing.scrollbar.ScrollBarCustom;
import com.grocerystore.swing.table.EventAction;
import com.grocerystore.swing.table.EventCellInputChange;
import com.grocerystore.swing.table.ModelAction;
import com.grocerystore.swing.table.QtyCellEditor;
import com.grocerystore.swing.table.TableCellAction;
import com.raven.model.Model_Product;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import connection.DatabaseConnection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import print.ReportManager;
import print.model.FieldReportPayment;
import print.model.ParameterReportPayment;

/**
 *
 * @author My PC
 */
public class Form_QLBanHang extends javax.swing.JPanel {
    
    private IHoaDonDAO hoaDonDao;
    private IChiTietHoaDonDAO chitietHDDao;
    private HoaDon hd;
    private boolean themhd = false;
    NumberFormat format;
    /**
     * Creates new form Form_QLNhanVien
     */
    public Form_QLBanHang() {
        format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        hoaDonDao = new HoaDonDAOImpl();
        chitietHDDao = new ChiTietHoaDonDAOImpl();
        connect_DB();
        initComponents();
        setOpaque(false);
        init();
    }
    
    private void init(){
        tf_tienthua.setEnabled(false);
        tf_tienKH.setText("");
        tf_SDT.setEnabled(true);
        tf_tenKH.setEnabled(false);
        btn_thanhtoan.setEnabled(false);
        btnHuy.setEnabled(false);
        loadData();
        setWidget();
    }
    
    private void setWidget(){
        table_hoadon.fixTable(jScrollPane1);
        table_hoadon.getColumnModel().getColumn(5).setCellEditor(new TableCellAction());
        table_hoadon.getColumnModel().getColumn(3).setCellEditor(new QtyCellEditor(new EventCellInputChange(){
            @Override
            public void inputChanged(){
                int selectedRow = table_hoadon.getSelectedRow();
                if (selectedRow != -1) {
                    SanPham product = (SanPham) table_hoadon.getValueAt(selectedRow, 6);
                    int newQuantity;
                    // Kiểm tra xem có cell nào đang được chỉnh sửa hay không
                    if (table_hoadon.isEditing()) {
                        // Nếu có, lấy giá trị đang được chỉnh sửa từ cell editor
                        newQuantity = (Integer) table_hoadon.getCellEditor().getCellEditorValue();
                    } else {
                        // Nếu không, lấy giá trị hiện tại của cell
                        newQuantity = (Integer) table_hoadon.getValueAt(selectedRow, 3);
                    }
                    if (newQuantity > product.getSoLuong()) {
                        // Hiển thị thông báo nếu số lượng sản phẩm không đủ
                        FormPopupNotification popup = new FormPopupNotification("Số lượng sản phẩm không đủ", FormPopupNotification.Type.WARNING);
                        popup.setAlwaysOnTop(true);
                        popup.setVisible(true);
                        // Đặt lại số lượng sản phẩm về giá trị cũ
                        table_hoadon.getCellEditor().stopCellEditing();
                        table_hoadon.setValueAt(product.getSoLuong(), selectedRow, 3);
                    }
                }
            }
        }));
        table_hoadon.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                return this;
            }
        });
        
        form_Product2.setProductClickListener(new Form_Product.ProductClickListener() {
            @Override
            public void onProductClick(SanPham product) {
                // Ví dụ: thêm sản phẩm vào bảng
                addtoTable(product);
                System.out.println(product.getGiaTien());

                //Kiểm tra đây có phải là hóa đơn mới không
                if(!themhd){
                    ThemHD();
                    themhd = true;
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            FormPopupNotification popup = new FormPopupNotification("Hóa đơn mới đã được thêm", FormPopupNotification.Type.SUCCESS);
                            popup.setAlwaysOnTop(true);
                            popup.setVisible(true);
                            popup.toFront();
                        }
                    });
                    
                    tf_SDT.setEnabled(false);
                    btnHuy.setEnabled(true);
                }
            }
        });
        
        DocumentListener dl = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                update();
            }
            public void removeUpdate(DocumentEvent e) {
                update();
            }
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                try {
                    // Parse the text of tf_tienKH and lbl_tongtien to BigDecimal
                    BigDecimal tienKH = new BigDecimal(tf_tienKH.getText().replaceAll("[^0-9,]", "").replace(",", "."));
                    BigDecimal tongTien = new BigDecimal(lbl_tongtien.getText().replaceAll("[^0-9,]", "").replace(",", "."));

                    // Compare tienKH with tongTien and update tf_tienthua
                    if (tienKH.compareTo(tongTien) < 0) {
                        tf_tienthua.setText("Không đủ");
                        btn_thanhtoan.setEnabled(false);
                    } else {
                        BigDecimal tienThua = tienKH.subtract(tongTien);
                        tf_tienthua.setText(format.format(tienThua));
                        btn_thanhtoan.setEnabled(true);
                    }
                } catch (NumberFormatException e) {
                    // Handle the exception
                    tf_tienthua.setText("Không hợp lệ");
                    btn_thanhtoan.setEnabled(false);
                }
            }
        };
        tf_tienKH.getDocument().addDocumentListener(dl);
        tf_tienKH.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                     (c == KeyEvent.VK_BACK_SPACE) ||
                     (c == KeyEvent.VK_DELETE) || 
                     (c == ','))) {
                    e.consume();  // ignore event
                }
            }
        });

        lbl_tongtien.addPropertyChangeListener("text", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // Trigger the update method of tf_tienKH's DocumentListener
                dl.changedUpdate(null);
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
    
    private void addtoTable(SanPham product){
        DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
        model.addTableModelListener((TableModelEvent e) -> {
            sumAmount();
        });

        // Kiểm tra xem MaSP đã tồn tại trong bảng chưa
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(product.getMaSP())) {
                // Nếu MaSP đã tồn tại, cập nhật số lượng và tổng giá tiền
                int oldQuantity = (Integer) model.getValueAt(i, 3);
                if (oldQuantity + 1 > product.getSoLuong()) {
                    // Hiển thị thông báo nếu số lượng sản phẩm không đủ
                    FormPopupNotification popup = new FormPopupNotification("Số lượng sản phẩm không đủ", FormPopupNotification.Type.WARNING);
                    popup.setAlwaysOnTop(true);
                    popup.setVisible(true);
                    return;
                }
                model.setValueAt(oldQuantity + 1, i, 3);
                BigDecimal price = (BigDecimal) model.getValueAt(i, 2);
                BigDecimal quantity = new BigDecimal(model.getValueAt(i, 3).toString());
                model.setValueAt(price.multiply(quantity), i, 4);
                return;
            }
        }

        // Nếu MaSP chưa tồn tại, thêm hàng mới
        Object[] row = new Object[7];
        row[0] = product.getMaSP();
        row[1] = product.getTenSP();
        row[2] = product.getGiaTien();
        row[3] = 1; // Số lượng sản phẩm được thêm vào bảng là 1
        row[4] = product.getGiaTien(); // Tổng giá tiền của sản phẩm được thêm vào bảng
        row[6] = product;
        
        ModelStudent student = new ModelStudent();
        // Set student properties here
        EventAction event = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                // Your delete logic here
                DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
                int selectedRow = table_hoadon.getSelectedRow();
                if (selectedRow != -1 && selectedRow < model.getRowCount()) {
                    // Stop cell editing before removing the row
                    if (table_hoadon.getCellEditor() != null) {
                        table_hoadon.getCellEditor().stopCellEditing();
                    }
                    model.removeRow(selectedRow);
                    // Clear selection in the JTable
                    table_hoadon.clearSelection();
                    // Clear column selection in the JTable
                    if (table_hoadon.getSelectedColumn() != -1) {
                        table_hoadon.removeColumnSelectionInterval(0, table_hoadon.getColumnCount() - 1);
                    }
                }
            }
            @Override
            public void update(ModelStudent student) {
                // Your update logic here
            }
            
        };

        ModelAction modelAction = new ModelAction(student, event);
        row[5] = modelAction;
        model.addRow(row);
    }
    
    private void ThemHD(){
        connect_DB();
        hd = new HoaDon();
        hd.setMaNV(DataInitializer.nhanVien1.getMaNV());
        if(tf_tenKH==null||tf_tenKH.getText().contains(""))
            hd.setMaKH("KH0000");
        else
            hd.setMaKH(tf_tenKH.getText());
        try {
            hoaDonDao.ThemHD(hd);
        } catch (Exception e) {
            // Handle the exception
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại");
        }
    }
    
    private void HuyHD(){
        connect_DB();
        try {
            String maHD = hoaDonDao.LayMaHDMoiNhat();
            if(maHD != null && hoaDonDao.XoaHD(maHD)) {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn thành công");
                themhd = false;
                init();
                
                // Clear the table
                DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
                model.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(this, "Hủy hóa đơn thất bại");
            }
        } catch (Exception e) {
            // Handle the exception
            JOptionPane.showMessageDialog(this, "Hủy hóa đơn thất bại");
        }
    }
    
    private void sumAmount() {
        BigDecimal total = BigDecimal.ZERO;
        DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            BigDecimal itemTotal = (BigDecimal) model.getValueAt(i, 4);
            total = total.add(itemTotal);
        }
        lbl_tongtien.setText(format.format(total));
    }
    
    private void themCTHD(){
        connect_DB();
        try {
            String maHD = hoaDonDao.LayMaHDMoiNhat();

            for (int i = 0; i < table_hoadon.getRowCount(); i++) {
                String maSP = table_hoadon.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(table_hoadon.getValueAt(i, 3).toString());
                double tongTien = Double.parseDouble(table_hoadon.getValueAt(i, 4).toString());

                CTHD cthd = new CTHD(maHD, maSP, soLuong, tongTien);
                if (!chitietHDDao.ThemCTHD(cthd)) {
                    // Handle the case when ThemCTHD(cthd) returns false
                    FormPopupNotification popup = new FormPopupNotification("Thanh toán thất bại!!!", FormPopupNotification.Type.ERROR);
                    popup.setAlwaysOnTop(true);
                    popup.setVisible(true);
                }
            }
        } catch (Exception e) {
            // Handle the exception
            JOptionPane.showMessageDialog(this, "Thêm CTHD thất bại");
        }
    }
    
    private void loadData() {
//        INhanVienDAO nhanVienDao = new NhanVienDAOImpl();
//        List<NhanVien> nhanVienList = nhanVienDao.getAll();
//
//        String[] columnNames = {"Mã NV", "Họ tên", "Ngày sinh", "SĐT", "Giới tính", "Địa chỉ", "Ngày tuyển dụng", "Trạng thái", "Tên TK", "Hình ảnh", "Mật khẩu", "Quyền"};
//        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//
//        for (NhanVien nhanVien : nhanVienList) {
//            Object[] row = new Object[12];
//            row[0] = nhanVien.getMaNV();
//            row[1] = nhanVien.getHoTen();
//            row[2] = nhanVien.getNgaySinh();
//            row[3] = nhanVien.getSdt();
//            row[4] = nhanVien.getGioiTinh();
//            row[5] = nhanVien.getDiaChi();
//            row[6] = nhanVien.getNgayTuyenDung();
//            row[7] = nhanVien.getTrangThai();
//            row[8] = nhanVien.getTenTK();
//            row[9] = nhanVien.getHinhAnh();
//            row[10] = nhanVien.getMatKhau();
//            row[11] = nhanVien.getQuyen();
//            model.addRow(row);
//    }

//    table1.setModel(model);
//    table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Disable auto resizing
//
//    for (int column = 0; column < table1.getColumnCount(); column++) {
//        TableColumn tableColumn = table1.getColumnModel().getColumn(column);
//        int preferredWidth = tableColumn.getMinWidth();
//        int maxWidth = tableColumn.getMaxWidth();
//
//        // Consider the header's width
//        TableCellRenderer headerRenderer = table1.getTableHeader().getDefaultRenderer();
//        Object headerValue = tableColumn.getHeaderValue();
//        Component headerComp = headerRenderer.getTableCellRendererComponent(table1, headerValue, false, false, 0, column);
//        preferredWidth = Math.max(preferredWidth, headerComp.getPreferredSize().width + table1.getIntercellSpacing().width);
//
//        for (int row = 0; row < table1.getRowCount(); row++) {
//            TableCellRenderer cellRenderer = table1.getCellRenderer(row, column);
//            Component c = table1.prepareRenderer(cellRenderer, row, column);
//            int width = c.getPreferredSize().width + table1.getIntercellSpacing().width;
//            preferredWidth = Math.max(preferredWidth, width);
//
//            // We've exceeded the maximum width, no need to check other rows
//            if (preferredWidth >= maxWidth) {
//                preferredWidth = maxWidth;
//                break;
//            }
//        }
//        
//        tableColumn.setMinWidth(preferredWidth+5); // Set minimum width instead of preferred width
//    }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table_hoadon = new com.grocerystore.swing.table.Table();
        btn_thanhtoan = new com.raven.swing.EditButton();
        tf_tienthua = new javax.swing.JTextField();
        btnHuy = new com.raven.swing.EditButton();
        tf_tienKH = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_tongtien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        form_Product2 = new com.grocerystore.form.Form_Product();
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
        tf_SDT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tf_tenKH = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(102, 153, 255));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(76, 76, 76));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Hóa Đơn");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel4.setOpaque(true);

        table_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên Sản Phẩm", "Đơn giá", "Số lượng", "Thành tiền", "", "SanPham"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_hoadon);
        if (table_hoadon.getColumnModel().getColumnCount() > 0) {
            table_hoadon.getColumnModel().getColumn(0).setMinWidth(0);
            table_hoadon.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_hoadon.getColumnModel().getColumn(0).setMaxWidth(0);
            table_hoadon.getColumnModel().getColumn(1).setResizable(false);
            table_hoadon.getColumnModel().getColumn(1).setPreferredWidth(120);
            table_hoadon.getColumnModel().getColumn(2).setResizable(false);
            table_hoadon.getColumnModel().getColumn(2).setPreferredWidth(50);
            table_hoadon.getColumnModel().getColumn(3).setResizable(false);
            table_hoadon.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_hoadon.getColumnModel().getColumn(4).setResizable(false);
            table_hoadon.getColumnModel().getColumn(4).setPreferredWidth(50);
            table_hoadon.getColumnModel().getColumn(5).setMinWidth(50);
            table_hoadon.getColumnModel().getColumn(5).setPreferredWidth(50);
            table_hoadon.getColumnModel().getColumn(5).setMaxWidth(50);
            table_hoadon.getColumnModel().getColumn(6).setMinWidth(0);
            table_hoadon.getColumnModel().getColumn(6).setPreferredWidth(0);
            table_hoadon.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        btn_thanhtoan.setBackground(new java.awt.Color(51, 102, 255));
        btn_thanhtoan.setForeground(new java.awt.Color(255, 255, 255));
        btn_thanhtoan.setText("Thanh toán   ");
        btn_thanhtoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_thanhtoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btn_thanhtoan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_thanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhtoanActionPerformed(evt);
            }
        });

        tf_tienthua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_tienthua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnHuy.setBackground(new java.awt.Color(204, 0, 51));
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy   ");
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnHuy.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        tf_tienKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_tienKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Tiền khách hàng trả");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tiền thừa");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Tổng hóa đơn");

        lbl_tongtien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_tongtien.setForeground(new java.awt.Color(255, 51, 51));
        lbl_tongtien.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_tienthua)
                            .addComponent(lbl_tongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tf_tienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_thanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbl_tongtien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tienKH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tienthua, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_thanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(102, 153, 255));
        jLabel5.setFont(new java.awt.Font("sansserif", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(76, 76, 76));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh Sách Sản Phẩm");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel6.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form_Product2, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(form_Product2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        jLabel9.setText("Thông Tin Khách Hàng");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));

        jLabel10.setOpaque(true);

        tf_SDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_SDT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Số điện thoại");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tên khách hàng");

        tf_tenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tf_tenKH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_thanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhtoanActionPerformed
        // TODO add your handling code here:
        themCTHD();
        connect_DB();
        List<XuatHoaDon> listxuathoadon = hoaDonDao.getXuatHoaDon();
        try {
            List<FieldReportPayment> fields = new ArrayList<>();
            for (XuatHoaDon xhd : listxuathoadon) {
//                fieldReportPayment.set
//                ModelItemSell data = (ModelItemSell) table.getValueAt(i, 0);
                fields.add(new FieldReportPayment(xhd.getTenSP(), xhd.getSoLuong(), xhd.getGiaTien(), xhd.getTongTien()));
            }
            XuatHoaDon xhd = new XuatHoaDon();
            if(listxuathoadon != null)
                xhd = listxuathoadon.get(0); // Get the first XuatHoaDon

            String tenNhanVien = xhd.getTenNhanVien();
            String tenKhachHang = xhd.getTenKhachHang();
            String trigia = xhd.getTriGiaHoaDon().toString();
            ParameterReportPayment dataprint = new ParameterReportPayment(tenNhanVien, tenKhachHang, trigia, generateQrcode(), fields);
            ReportManager.getInstance().compileReport();
            ReportManager.getInstance().printReportPayment(dataprint);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            init();
            themhd = false;
            // Clear the table
            DefaultTableModel model = (DefaultTableModel) table_hoadon.getModel();
            model.setRowCount(0);
        }
    }//GEN-LAST:event_btn_thanhtoanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        HuyHD();
    }//GEN-LAST:event_btnHuyActionPerformed

    
    
    private InputStream generateQrcode() throws WriterException, IOException {
        NumberFormat nf = new DecimalFormat("0000000");
        Random ran = new Random();
        String invoice = nf.format(ran.nextInt(9999999) + 1);
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(invoice, BarcodeFormat.QR_CODE, 100, 100, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.EditButton btnHuy;
    private com.raven.swing.EditButton btn_thanhtoan;
    private com.raven.swing.Button button1;
    private com.raven.swing.Button button2;
    private com.raven.swing.CustomCombobox customCombobox1;
    private com.raven.swing.CustomCombobox customCombobox2;
    private com.grocerystore.form.Form_Product form_Product2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbl_tongtien;
    private com.grocerystore.swing.table.Table table_hoadon;
    private javax.swing.JTextField tf_SDT;
    private javax.swing.JTextField tf_tenKH;
    private javax.swing.JTextField tf_tienKH;
    private javax.swing.JTextField tf_tienthua;
    // End of variables declaration//GEN-END:variables
}
