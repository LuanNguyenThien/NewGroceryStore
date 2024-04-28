package com.raven.component;

import com.grocerystore.model.SanPham;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Product extends javax.swing.JPanel {

    public interface ProductClickListener {
        void onProductClick(SanPham sanpham);
    }
    
    private final SanPham sanpham;
    private final Timer timer;
    private final Timer timerStop;
    private final ProductDescription cardDescription;
    private int y = 140;
    private int speed = 10;
    private boolean showing = false;
    private ProductClickListener listener;
    NumberFormat format;

    public void setProductClickListener(ProductClickListener listener) {
        this.listener = listener;
    }

    public Product(SanPham sanpham) {
        format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        this.sanpham = sanpham;
        initComponents();
        setOpaque(false);
        String description = "Tên SP: "+sanpham.getTenSP()+"\nNSX: "+sanpham.getTenNSX()+"\nSố lượng: "+(sanpham.getSoLuong() == 0 ? "Hết hàng" : sanpham.getSoLuong());
        cardDescription = new ProductDescription(format.format(sanpham.getGiaTien()), description);
        cardDescription.setLocation(0, y);
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (listener != null) {
                    listener.onProductClick(sanpham);
                }
            }
        });
        
        setPreferredSize(new Dimension(150, 200));
        cardDescription.setSize(new Dimension(150, 150));
        add(cardDescription);
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (showing) {
                    y -= speed;
                    if (y <= 50) {
                        y = 50;
                        cardDescription.setLocation(0, y);
                        timer.stop();
                    } else {
                        cardDescription.setLocation(0, y);
                    }
                } else {
                    y += speed;
                    if (y >= 140) {
                        y = 140;
                        cardDescription.setLocation(0, y);
                        timer.stop();
                    } else {
                        cardDescription.setLocation(0, y);
                    }
                }
            }
        });
        //  500 for delay hide description
        timerStop = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showing = false;
                timerStop.stop();
                timer.start();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                showing = true;
                timerStop.stop();
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                timerStop.start();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 72, 238), 2, true));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        ImageIcon icsanpham = util.Util.byteArrayToImageIcon(sanpham.getHinhAnh());
        Rectangle size = getAutoSize(icsanpham);
        g2.drawImage(toImage(icsanpham), size.x, size.y, size.width, size.height, null);
        super.paintComponent(grphcs);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // GradientPaint g = new GradientPaint(0, getHeight(), new Color(64, 67, 186, 200), 0, getHeight() - 50, new Color(0, 0, 0, 0));
        GradientPaint g = new GradientPaint(0, getHeight(), new Color(93, 58, 196), 0, getHeight()-50, new Color(33, 105, 249,0));
        g2.setPaint(g);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }

    private Rectangle getAutoSize(Icon image) {
        int w = 150;
        int h = 200;
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
        int width = (int) (scale * iw * 0.8);
        int height = (int) (scale * ih);
        int x = (w - width) / 2;
        int y = (h - height) / 2;
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
