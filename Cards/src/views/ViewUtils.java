/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * A collection of functions useful for managing views
 * @author rtucker
 */
public class ViewUtils {
    
    public static void centreFrame(JFrame aFrame){
        if(null != aFrame){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle area = new Rectangle(screenSize);
            ViewUtils.centreFrame(aFrame, area);
        }
    }
    
    public static void centreFrame(JFrame aFrame, Rectangle area){
        if(null != aFrame && null != area){
            if(0 < (area.width * area.height)){
                Rectangle frameBounds = aFrame.getBounds();
                Dimension frameDim = new Dimension(frameBounds.width, frameBounds.height);
                int spareHorizontal = area.width - frameDim.width;
                int spareVertical = area.height - frameDim.height;
                //if either spare is odd number then add 1 to make even (dividable by 2)
                if((spareHorizontal % 2) != 0){
                    spareHorizontal++;
                }
                if((spareVertical % 2) != 0){
                    spareVertical++;
                }
                int intHalfHorizontal = spareHorizontal / 2;
                int intHalfVertical = spareVertical / 2;
                intHalfHorizontal += area.x;
                intHalfVertical += area.y;
                aFrame.setLocation(intHalfHorizontal, intHalfVertical);
            }
        }
    }
    
    public static void centreDialog(JDialog aDialog){
        if(null != aDialog){
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle area = new Rectangle(screenSize);
            ViewUtils.centreDialog(aDialog, area);
        }
    }
    
    public static void centreDialog(JDialog aDialog, Rectangle area){
        if(null != aDialog && null != area){
            if(0 < (area.width * area.height)){
                Rectangle frameBounds = aDialog.getBounds();
                Dimension frameDim = new Dimension(frameBounds.width, frameBounds.height);
                int spareHorizontal = area.width - frameDim.width;
                int spareVertical = area.height - frameDim.height;
                //if either spare is odd number then add 1 to make even (dividable by 2)
                if((spareHorizontal % 2) != 0){
                    spareHorizontal++;
                }
                if((spareVertical % 2) != 0){
                    spareVertical++;
                }
                int intHalfHorizontal = spareHorizontal / 2;
                int intHalfVertical = spareVertical / 2;
                intHalfHorizontal += area.x;
                intHalfVertical += area.y;
                aDialog.setLocation(intHalfHorizontal, intHalfVertical);
            }
        }
    }
    
}
