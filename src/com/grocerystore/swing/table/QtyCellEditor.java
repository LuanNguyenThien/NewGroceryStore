package com.grocerystore.swing.table;

import com.grocerystore.form.FormPopupNotification;
import com.grocerystore.model.SanPham;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatter;

public class QtyCellEditor extends DefaultCellEditor {

    private EventCellInputChange event;
    private JSpinner input;

    private JTable table;
    private int row;
    private ModelItemSell item;
    private SanPham sanpham;

    public QtyCellEditor(EventCellInputChange event) {
        super(new JCheckBox());
        this.event = event;
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
        // Lấy JFormattedTextField từ JSpinner
        JFormattedTextField textField = ((JSpinner.DefaultEditor) input.getEditor()).getTextField();
        
        // Thêm InputVerifier vào JFormattedTextField
        textField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JFormattedTextField) input).getText();
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    FormPopupNotification popup = new FormPopupNotification("Giá trị nhập vào không hợp lệ!!!", FormPopupNotification.Type.WARNING);
                    popup.setAlwaysOnTop(true);
                    popup.setVisible(true);
                    return false;
                }
            }
        });

        // Thêm DocumentListener vào JFormattedTextField
        textField.getDocument().addDocumentListener(new DocumentListener() {
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
                // Gọi phương thức inputChange() mỗi khi text field thay đổi
                System.out.println("test");
                inputChange();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;
        int qty = Integer.parseInt(value.toString());
        input.setValue(qty);
        input.setEnabled(false);
        enable();
        return input;
    }

    private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {

                }
            }
        }).start();
    }

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }

    private void inputChange() {
        int qty = Integer.parseInt(input.getValue().toString());
        int oldQty = (Integer) table.getValueAt(row, 3); // Lấy số lượng cũ từ bảng
        if (qty != oldQty) {
            DecimalFormat df = new DecimalFormat("#,##0.##");
            BigDecimal price = (BigDecimal) table.getValueAt(row, 2); // Lấy giá từ bảng
            BigDecimal total = price.multiply(new BigDecimal(qty)); // Tính toán tổng giá
            table.setValueAt(total, row, 4); // Cập nhật tổng giá trong bảng
            event.inputChanged();
        }
    }
}
