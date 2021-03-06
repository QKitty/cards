/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import com.gmail.qkitty6.patterns.observer.IObserver;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckManager;
import datamodel.interfaces.SVGIconGenerator;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.border.Border;

/**
 *
 * @author qkitt
 */
public class DrawnCardsLastCardPlusTextDisplayPanel extends javax.swing.JPanel implements IObserver<Void>, IDeckManager {

    private IDeck myCardDeck;

    /**
     * Creates new form DrawnCardsLastCardPlusTextDisplayPanel
     * @param aDeck - An IDeck interface to the deck of cards used by this panel
     */
    public DrawnCardsLastCardPlusTextDisplayPanel(IDeck aDeck) {
        initComponents();
        this.addComponentListener(new ResizeHandler());
        setCardDeck(aDeck);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblKQValue = new javax.swing.JLabel();
        lblNonKQValue = new javax.swing.JLabel();
        lblCardsDrawn = new javax.swing.JLabel();
        lblLastCardHeading = new javax.swing.JLabel();
        lblLastCard = new javax.swing.JLabel();

        lblKQValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblKQValue.setText("Total Kings & Queens = 0");
        lblKQValue.setToolTipText("");

        lblNonKQValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNonKQValue.setText("Total other cards = 0");
        lblNonKQValue.setToolTipText("");

        lblCardsDrawn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCardsDrawn.setText("Total cards drawn = 0");
        lblCardsDrawn.setToolTipText("");

        lblLastCardHeading.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLastCardHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLastCardHeading.setText("Last Card Drawn was:");

        lblLastCard.setToolTipText("");
        lblLastCard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKQValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(lblNonKQValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCardsDrawn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLastCardHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLastCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblKQValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNonKQValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCardsDrawn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLastCardHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLastCard, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCardsDrawn;
    private javax.swing.JLabel lblKQValue;
    private javax.swing.JLabel lblLastCard;
    private javax.swing.JLabel lblLastCardHeading;
    private javax.swing.JLabel lblNonKQValue;
    // End of variables declaration//GEN-END:variables

    @Override
    public IDeck getCardDeck() {
        return this.myCardDeck;
    }

    @Override
    public final void setCardDeck(IDeck aDeck) {
        if (null != aDeck) {
            if (null != this.myCardDeck) {
                this.myCardDeck.removeObserver(this);
            }
            this.myCardDeck = aDeck;
            this.myCardDeck.registerObserver(this);
            this.update();
        }
    }

    @Override
    public void update() {
        if (null != this.myCardDeck) {
            int intK = this.myCardDeck.getNoOfKingsDrawn();
            int intQ = this.myCardDeck.getNoOfQueensDrawn();
            int intKQ = intK + intQ;
            int intOther = this.myCardDeck.getNoOfOtherCardsDrawn();
            int intTotal = intKQ + intOther;
            lblKQValue.setText("Total Kings & Queens = " + intKQ);
            lblNonKQValue.setText("Total other cards = " + intOther);
            lblCardsDrawn.setText("Total cards drawn = " + intTotal);
            //Create Icon for last card drawn panel
            Icon icnLastCard;
            Border border = lblLastCard.getBorder();
            if (null != border) {
                Insets borderInsets = border.getBorderInsets(lblLastCard);
                int w = lblLastCard.getWidth() - (borderInsets.left + borderInsets.right);
                int h = lblLastCard.getHeight() - (borderInsets.top + borderInsets.bottom);
                List<ICard> drawnCardList = this.myCardDeck.getDrawnCardList();
                if (h > 0 && w > 0) {
                    if (drawnCardList.size() > 0) {
                        //Get Icon for last card drawn
                        ICard lastCard = drawnCardList.get(drawnCardList.size() - 1);
                        icnLastCard = lastCard.getCardIcon(w, h);
                    } else {
                        //Create No Card Drawn Icon
                        SVGIconGenerator gen = new SVGIconGenerator();
                        icnLastCard = gen.generateNoCardIcon(w, h);
                    }
                    lblLastCard.setIcon(icnLastCard);
                }
            }
        } else {
            lblKQValue.setText("Total Kings & Queens = " + 0);
            lblNonKQValue.setText("Total other cards = " + 0);
            lblCardsDrawn.setText("Total cards drawn = " + 0);
            Icon icnLastCard = null;
            Border border = lblLastCard.getBorder();
            if (null != border) {
                Insets borderInsets = border.getBorderInsets(lblLastCard);
                int w = lblLastCard.getWidth() - (borderInsets.left + borderInsets.right);
                int h = lblLastCard.getHeight() - (borderInsets.top + borderInsets.bottom);
                if (h > 0 && w > 0) {
                    //Create No Card Drawn Icon
                    SVGIconGenerator gen = new SVGIconGenerator();
                    icnLastCard = gen.generateNoCardIcon(w, h);
                }
            }
            lblLastCard.setIcon(icnLastCard);
        }
        repaint();
    }
    
    private class ResizeHandler extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent e) {
            update();
        }
        
    }

}
