package com.raven.component;

import com.grocerystore.main.Main;
//import com.raven.main.Main;
import com.raven.model.ModelUser;
import connection.DatabaseConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import com.grocerystore.DAO.NhanVienDAOImpl;
import com.grocerystore.main.DataInitializer;
import com.grocerystore.model.NhanVien;
import java.util.concurrent.CountDownLatch;
import javax.swing.SwingWorker;
import util.Util;

public class PanelSlide extends javax.swing.JLayeredPane {

    public void setFram(JFrame fram) {
        this.fram = fram;
    }

    private final Animator animator;
    private float animate = 1f;
    private boolean slideLeft;
    private final PanelLogin login;
    private final PanelLoading loading;
    private Thread th;
    private MigLayout layout;
    private JFrame fram;
    public static String IDCurUser;

    public PanelSlide() {
        initComponents();
        setPreferredSize(new Dimension(350, 450));
        layout = new MigLayout("inset 0", "[fill]", "[fill]");
        setLayout(layout);
        login = new PanelLogin();
        loading = new PanelLoading();
        loading.setVisible(false);
        Color color = new Color(67, 99, 132);
        setBackground(color);
        loading.setBackground(color);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (slideLeft) {
                    loading.setVisible(true);
                } else {
                    login.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                double width = getWidth();
                animate = fraction;
                float a = easeOutQuint(fraction);
                int x = (int) (a * width);
                loading.setAnimate(slideLeft, fraction);
                layout.setComponentConstraints(loading, "pos " + x + " 0 100% 100%");
                revalidate();
                repaint();
            }

            @Override
            public void end() {
                if (slideLeft) {
                    login.setVisible(false);
                } else {
                    loading.setVisible(false);
                    loading.reset();
                }
            }
        };
        animator = new Animator(1000, target);
        animator.setResolution(0);
        add(loading, " pos 0 0 0 0, w 0!");
        add(login);
        login.addEventLogin(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!animator.isRunning()) {
                if (login.checkUser()) {
                    showSlide(true);
                    new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            CountDownLatch latch = new CountDownLatch(1);
                            login(login.getUserName(), login.getPassword(), latch);
                            latch.await(); // Wait for the login to finish
                            return null;
                        }

                        @Override
                        protected void done() {
                            // No need to get data here because loading is updated directly in login
                        }
                    }.execute();
                }
            }
        }
    });

    loading.addEventContinue(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            Main main = new Main();
            main.setData(loading.getData());
            main.setVisible(true);
            //  Close login form
            fram.dispose();
        }
    });
        loading.addEventBack(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    th.interrupt();
                    showSlide(false);
                }
            }
        });
    }

    public void showSlide(boolean show) {
        slideLeft = show;
        animator.start();
    }

    private void login(String userName, String password, CountDownLatch latch) {
        th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    NhanVien nv = new NhanVien();
                    NhanVienDAOImpl nvdao = new NhanVienDAOImpl();
                    nv = nvdao.checkLogin(userName, password);
                    
                    if(nv!=null){
                        if("Đã nghỉ".equals(nv.getTrangThai())){
                            loading.showError("Nhân viên đã bị sa thải");
                            return;
                        }
                        DataInitializer.curUser = nv;
                        IDCurUser = nv.getMaNV();
                        String quyen = nv.getQuyen();
                        String hoten = nv.getHoTen();
                        byte[] blobimg = nv.getHinhAnh();
                        if(blobimg==null){
                            ImageIcon profile;
                            profile = new ImageIcon(getClass().getResource("/com/raven/icon/user.png"));
                            blobimg = Util.imageIconToByteArray(profile);
                        }
                        NhanVien nv_data = new NhanVien(hoten, blobimg, quyen);
                        loading.doneLogin(nv_data);
                    }
                    else{
                        loading.showError("Tên tài khoản hoặc mật khẩu không đúng");
                    }
                } catch (InterruptedException e) {
                    System.err.println(e);
                } catch (Exception e) {
                    System.err.println(e);
                    loading.showError("Lỗi kết nối database");
                } finally {
                    latch.countDown(); // Signal that the login is done
                }
            }
        });
        th.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        if (slideLeft == false) {
            Graphics2D g2 = (Graphics2D) grphcs.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();
            float x = easeOutQuint(animate) * width;
            float y = 0;
            int centerY = height / 2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate) * width, centerY, x, y);
            g2.setColor(getBackground());
            g2.fill(p);
            g2.dispose();
        }
    }

    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        double v;
        if (x < 1 / d1) {
            v = n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    private float easeOutQuint(float x) {
        double v = 1 - Math.pow(1 - x, 5);
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
