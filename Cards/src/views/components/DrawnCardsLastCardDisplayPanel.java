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
public class DrawnCardsLastCardDisplayPanel extends javax.swing.JPanel implements IObserver<Void>, IDeckManager {

    private IDeck myCardDeck;

    /**
     * Creates new form DrawnCardsLastCardDisplayPanel
     *
     * @param aDeck - IDeck interface to the card deck that will provide data to
     * the panel
     */
    public DrawnCardsLastCardDisplayPanel(IDeck aDeck) {
        initComponents();
        this.addComponentListener(new ResizeDetector());
        this.setCardDeck(aDeck);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLastCard = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lblLastCard.setToolTipText("Last card drawn from deck");
        lblLastCard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lblLastCard.setMaximumSize(new java.awt.Dimension(10000, 10000));
        lblLastCard.setPreferredSize(new java.awt.Dimension(167, 242));
        add(lblLastCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLastCard;
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
            List<ICard> drawnCardList = this.myCardDeck.getDrawnCardList();
            if (drawnCardList.size() > 0) {
                ICard lastCard = drawnCardList.get(drawnCardList.size() - 1);
                Border border = lblLastCard.getBorder();
                Insets borderInsets = border.getBorderInsets(lblLastCard);
                int w = lblLastCard.getWidth() - (borderInsets.left + borderInsets.right);
                int h = lblLastCard.getHeight() - (borderInsets.top + borderInsets.bottom);
                lblLastCard.setIcon(lastCard.getCardIcon(w, h));
            } else {
                SVGIconGenerator gen = new SVGIconGenerator();
                Border border = lblLastCard.getBorder();
                if (null != border) {
                    Insets borderInsets = border.getBorderInsets(lblLastCard);
                    int w = lblLastCard.getWidth() - (borderInsets.left + borderInsets.right);
                    int h = lblLastCard.getHeight() - (borderInsets.top + borderInsets.bottom);
                    if (w > 0 && h > 0) {
                        Icon generateNoCardIcon = gen.generateNoCardIcon(w, h);
                        lblLastCard.setIcon(generateNoCardIcon);
                    } else {
                        java.awt.EventQueue.invokeLater(() -> {
                            update();
                        });
                    }
                }
            }
        } else {
            lblLastCard.setIcon(null);
        }
        repaint();
    }
    
    
    private class ResizeDetector extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent e) {
            update();
        }
    }
}