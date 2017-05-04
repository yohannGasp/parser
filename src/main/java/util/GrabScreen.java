/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author evgeniy
 */
public class GrabScreen {

    public static BufferedImage grabScreen() {
        try {
            return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (SecurityException e) {
        } catch (AWTException e) {
        }
        return null;
    }

    public static File getHomeDir() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getHomeDirectory();
    }

    public static InputStream screenCom(String command, String link, long pause) {

        BufferedImage buffer = null;
        InputStream is = null;

        try {
            ProcessBuilder pb = new ProcessBuilder(command, link);
            pb.start();
            sleep(pause);
            buffer = grabScreen();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffer, "jpeg", os);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (InterruptedException | IOException e) {
            System.out.println("IO exception" + e);
        }
        
        return is;

    }

}
