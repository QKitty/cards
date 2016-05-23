/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import com.gmail.qkitty6.patterns.observer.IObserver;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.DeckType;
import datamodel.enums.ParticipantGuess;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.swing.Icon;
import views.svg.CardFactory;

/**
 *
 * @author qkitt
 */
public class TotalsCardPanel extends javax.swing.JPanel implements IDeck, IObserver<Void> {

    private ICard dumbyBackCard;
    private IDeck myCardDeck;
    private int intKQDrawn;
    private int intOtherCardsDrawn;

    /**
     * Creates new form TotalsCardPanel
     */
    public TotalsCardPanel() {
        initComponents();
        intKQDrawn = 0;
        intOtherCardsDrawn = 0;
        java.awt.EventQueue.invokeLater(() -> {
            setIcon();
            refresh();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCardBack = new javax.swing.JLabel();
        lblKQValue = new javax.swing.JLabel();
        lblNonKQValue = new javax.swing.JLabel();
        lblCardsDrawn = new javax.swing.JLabel();

        lblCardBack.setToolTipText("");
        dumbyBackCard = CardFactory.createPlayingCard(CardSuite.CLUBS, CardValue.ACE, false);
        Icon cardIcon = dumbyBackCard.getCardIcon(lblCardBack.getWidth(), lblCardBack.getHeight());
        lblCardBack.setIcon(cardIcon);
        lblCardBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCardBackMouseClicked(evt);
            }
        });

        lblKQValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblKQValue.setText("Total Kings & Queens = 0");
        lblKQValue.setToolTipText("");

        lblNonKQValue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNonKQValue.setText("Total other cards = 0");
        lblNonKQValue.setToolTipText("");

        lblCardsDrawn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCardsDrawn.setText("Total cards drawn = 0");
        lblCardsDrawn.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblKQValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(lblNonKQValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCardsDrawn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(lblCardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKQValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNonKQValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCardsDrawn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblCardBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCardBackMouseClicked
        if (evt.getClickCount() == 2) {
            if (null != myCardDeck) {
                //Draw Card
                ICard nextCard = myCardDeck.drawCard();
                nextCard.setShowingFace(true);
                if (nextCard.getValue() == CardValue.KING || nextCard.getValue() == CardValue.QUEEN) {
                    intKQDrawn++;
                } else {
                    intOtherCardsDrawn++;
                }
                refresh();
            }
        }
    }//GEN-LAST:event_lblCardBackMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCardBack;
    private javax.swing.JLabel lblCardsDrawn;
    private javax.swing.JLabel lblKQValue;
    private javax.swing.JLabel lblNonKQValue;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        dumbyBackCard = CardFactory.createPlayingCard(CardSuite.CLUBS, CardValue.ACE, false);
        Icon cardIcon = dumbyBackCard.getCardIcon(lblCardBack.getWidth(), lblCardBack.getHeight());
        lblCardBack.setIcon(cardIcon);
        repaint();
    }

    public void setCardDeck(IDeck aDeck) {
        if (null != aDeck) {
            myCardDeck = aDeck;
        }
    }

    @Override
    public void update() {
        refresh();
        revalidate();
        repaint();
    }

    private void refresh() {
        lblKQValue.setText("Total Kings & Queens = " + intKQDrawn);
        lblNonKQValue.setText("Total other cards = " + intOtherCardsDrawn);
        int intTotal = intKQDrawn + intOtherCardsDrawn;
        lblCardsDrawn.setText("Total cards drawn = " + intTotal);
        repaint();
    }

//<editor-fold defaultstate="collapsed" desc="IDeck interface implementation">
    @Override
    public boolean isAlgorithmic() {
        return myCardDeck.isAlgorithmic();
    }

    @Override
    public IDeckAlgorithm getDeckAlgorithm() {
        return myCardDeck.getDeckAlgorithm();
    }

    @Override
    public boolean setDeckAlgorithm(IDeckAlgorithm alg) {
        return myCardDeck.setDeckAlgorithm(alg);
    }

    @Override
    public List<ICard> getDrawnCardList() {
        return myCardDeck.getDrawnCardList();
    }

    @Override
    public int cardsDrawnSinceLastSpecialCard() {
        return myCardDeck.cardsDrawnSinceLastSpecialCard();
    }

    @Override
    public int getMaxCardDrawsBetweenSpecialCards() {
        return myCardDeck.getMaxCardDrawsBetweenSpecialCards();
    }

    @Override
    public void setMaxCardDrawsBetweenSpecialCards(int max) throws IllegalArgumentException {
        myCardDeck.setMaxCardDrawsBetweenSpecialCards(max);
    }

    @Override
    public ICard drawCard() {
        return myCardDeck.drawCard();
    }

    @Override
    public boolean hasCardsRemaining() {
        return myCardDeck.hasCardsRemaining();
    }

    @Override
    public int getNoOfRemainingCards() {
        return myCardDeck.getNoOfRemainingCards();
    }

    @Override
    public DeckType getDeckType() {
        return myCardDeck.getDeckType();
    }

    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        return myCardDeck.getAlgorithmCategory();
    }

    @Override
    public double getProbabilityOfSpecialCard() {
        return myCardDeck.getProbabilityOfSpecialCard();
    }

    @Override
    public void setProbabilityOfSpecialCard(double probability) throws IllegalArgumentException {
        myCardDeck.setProbabilityOfSpecialCard(probability);
    }

    @Override
    public void reset() {
        this.myCardDeck.reset();
    }
    
    @Override
    public String getId() {
        return this.myCardDeck.getId();
    }

    @Override
    public void setId(String newId) {
        this.myCardDeck.setId(newId);
    }

    @Override
    public int getNoOfKingsDrawn() {
        return this.myCardDeck.getNoOfKingsDrawn();
    }

    @Override
    public int getNoOfQueensDrawn() {
        return this.myCardDeck.getNoOfQueensDrawn();
    }

    @Override
    public int getNoOfOtherCardsDrawn() {
        return this.myCardDeck.getNoOfOtherCardsDrawn();
    }

    @Override
    public double getProportionOfKingsVKingsAndQueensDrawn() {
        return this.myCardDeck.getProportionOfKingsVKingsAndQueensDrawn();
    }

    @Override
    public double getProportionOfKingsAndQueensDrawn() {
        return this.myCardDeck.getProportionOfKingsAndQueensDrawn();
    }
    
    @Override
    public ParticipantGuess getParticipantsGuess() {
        return this.myCardDeck.getParticipantsGuess();
    }

    @Override
    public void setParticipantsGuess(ParticipantGuess aGuess) {
        this.myCardDeck.setParticipantsGuess(aGuess);
    }

    @Override
    public boolean isParticipantGuessCorrect() {
        return this.myCardDeck.isParticipantGuessCorrect();
    }
    
    @Override
    public boolean hasParticipantGuessSet() {
        return this.myCardDeck.hasParticipantGuessSet();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ISubject interface for IDeck">
    @Override
    public boolean registerObserver(IObserver io) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeObserver(IObserver io) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> void notifyObservers(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<IObserver> removeAllObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerObserver(Collection<? extends IObserver> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IXMLPersistable is not supported by GUI components">
    @Override
    public Class<?> getFactoryClass() {
        throw new UnsupportedOperationException("Not supported by GUI components."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getFactoryMethodName() {
        throw new UnsupportedOperationException("Not supported GUI components."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object[] getFactoryArgs() {
        throw new UnsupportedOperationException("Not supported GUI components."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

}
