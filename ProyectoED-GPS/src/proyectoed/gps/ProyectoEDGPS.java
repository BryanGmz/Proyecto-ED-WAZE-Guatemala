/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoed.gps;

import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import proyectoed.gps.gui.*;


/**
 *
 * @author bryan
 */
public class ProyectoEDGPS {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // TODO code application logic here
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        Principal fr = new Principal();
        fr.setVisible(true);
    }
}
