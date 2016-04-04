/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import datamodel.enums.DrawnCardsDisplayType;
import datamodel.interfaces.IDeck;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import views.BaseCardWindow;
import views.components.BaseCardPanel;

/**
 *
 * @author qkitt
 */
public class BaseCardPanelTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame test = new BaseCardWindow();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        IDeck createFixedDeck = TestHarness.createFixedDeck();
        BaseCardPanel panel = new BaseCardPanel();
        panel.setCardDeck(createFixedDeck);
        panel.setSubPanelDisplayType(DrawnCardsDisplayType.CARD_HISTORY_DISPLAY);
        test.add(panel, BorderLayout.CENTER);
        test.pack();
        test.setVisible(true);
//        Dimension preferredSize = test.getPreferredSize();
//        System.out.println(preferredSize);
    }
    
}
