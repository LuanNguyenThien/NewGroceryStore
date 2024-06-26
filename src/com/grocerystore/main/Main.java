package com.grocerystore.main;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.grocerystore.component.Header;
import com.grocerystore.component.Menu;
import com.grocerystore.event.EventMenuSelected;
import com.grocerystore.event.EventShowPopupMenu;
import com.grocerystore.form.Form1;
import com.grocerystore.form.Form_DoiMatKhau;
import com.grocerystore.form.Form_DoiMatKhau;
import com.grocerystore.form.Form_DoiMatKhau.UserUpdateListener;
import com.grocerystore.form.Form_Home;
import com.grocerystore.form.Form_Home1;
import com.grocerystore.form.Form_QLBanHang;
import com.grocerystore.form.Form_QLKhachHang;
import com.grocerystore.form.Form_QLNhanVien;
import com.grocerystore.form.Form_QLNhapHang;
import com.grocerystore.form.Form_QLSanPham;
import com.grocerystore.form.MainForm;
import com.grocerystore.model.NhanVien;
import com.grocerystore.swing.MenuItem;
import com.grocerystore.swing.PopupMenu;
import com.grocerystore.swing.icon.GoogleMaterialDesignIcons;
import com.grocerystore.swing.icon.IconFontSwing;
import com.raven.model.ModelUser;
import connection.DatabaseConnection;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private NhanVien nv_data;

    public Main() {
        initComponents();
        init();
    }

    public void setData(NhanVien nv_data) {
        this.nv_data = nv_data;
        header.setData(nv_data);
    }
    
    public NhanVien getData(){
        return nv_data;
    }
    
    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                if("Quản lý".equals(DataInitializer.curUser.getQuyen())){
                    if (menuIndex == 0) 
                        main.showForm(new Form_Home1());
                    if (menuIndex == 1)
                        main.showForm(new Form_QLBanHang());
                    if (menuIndex == 2)
                        main.showForm(new Form_QLNhapHang());
                    if (menuIndex == 3)
                        main.showForm(new Form_QLSanPham());
                    if (menuIndex == 4)
                        main.showForm(new Form_QLNhanVien());
                    if (menuIndex == 5)
                        main.showForm(new Form_QLKhachHang());
                    if (menuIndex == 6){
                        Form_DoiMatKhau formDoiMatKhau = new Form_DoiMatKhau();
                        formDoiMatKhau.setUserUpdateListener(new UserUpdateListener() {
                            @Override
                            public void onUserUpdated(NhanVien updatedUser) {
                                // Update the header
                                header.updateHeader();
                            }
                        });
                        main.showForm(formDoiMatKhau);
                    }
                }else{
                    if (menuIndex == 0) 
                        main.showForm(new Form_QLBanHang());
                    if (menuIndex == 1)
                        main.showForm(new Form_QLKhachHang());
                    if (menuIndex == 2)
                        main.showForm(new Form_DoiMatKhau());
                }
                
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        if("Quản lý".equals(DataInitializer.curUser.getQuyen()))
            main.showForm(new Form_Home1());
        else
            main.showForm(new Form_QLBanHang());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                FlatLaf.registerCustomDefaultsSource("style");
//                FlatDarculaLaf.setup();
                FlatIntelliJLaf.setup();
                try {
                    DatabaseConnection.getInstance().connectToDatabase();
                } catch (SQLException e) {
                    System.err.println(e);
                }
//                Main main = new Main();
//                main.setData(DataInitializer.nhanVien1);
//                main.setVisible(true);
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
