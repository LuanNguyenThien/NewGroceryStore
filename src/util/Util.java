/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

/**
 *
 * @author My PC
 */
public class Util {
    public static byte[] imageIconToByteArray(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
            icon.getIconWidth(),
            icon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    
    public static ImageIcon byteArrayToImageIcon(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            BufferedImage bi = ImageIO.read(bais);
            return new ImageIcon(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
