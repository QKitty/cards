/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.ICardDrawnRecordList;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.DeckType;
import datamodel.enums.DrawnCardsDisplayType;
import datamodel.enums.ParticipantGuess;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JPanel;
import views.svg.CardFactory;

/**
 *
 * @author qkitt
 */
public class BaseCardPanel extends javax.swing.JPanel implements IDeck, IObserver<Void> {
    
    protected ICard dumbyBackCard;
    protected IDeck myCardDeck;
    protected DrawnCardsDisplayType subPanelType;
    private ICardDrawnRecordList cardsDrawnRecordList;
    private final ISubject observers;

    /**
     * Creates new form BaseCardPanel
     */
    public BaseCardPanel() {
        initComponents();
        this.observers = new ISubjectImpl();
        this.setSubPanelDisplayType(DrawnCardsDisplayType.CARD_HISTORY_DISPLAY);
        java.awt.EventQueue.invokeLater(() -> {
            setIcon();
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

        dumbyBackCard = CardFactory.createPlayingCard(CardSuite.CLUBS, CardValue.ACE, false);
        lblCardBack = new javax.swing.JLabel();
        cbxGuess = new javax.swing.JComboBox();
        lblGuess = new javax.swing.JLabel();
        scrSubPanel = new javax.swing.JScrollPane();

        lblCardBack.setToolTipText("");
        lblCardBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        lblCardBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCardBackMouseClicked(evt);
            }
        });

        cbxGuess.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxGuessItemStateChanged(evt);
            }
        });

        lblGuess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGuess.setText("Your guess:");
        lblGuess.setToolTipText("Pick your guess for the card deck's type");

        scrSubPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrSubPanel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxGuess, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 100, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCardBack, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxGuess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGuess))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        DefaultComboBoxModel model = new DefaultComboBoxModel(ParticipantGuess.values());
        model.setSelectedItem(ParticipantGuess.UNKNOWN);
        cbxGuess.setModel(model);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCardBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCardBackMouseClicked
        if (evt.getClickCount() == 2) {
            if (null != myCardDeck) {
                //Draw Card
                ICard nextCard = myCardDeck.drawCard();
                nextCard.setShowingFace(true);
                if (null != this.cardsDrawnRecordList) {
                    this.cardsDrawnRecordList.recordCardDrawn(myCardDeck);
                }
                this.update();
                //Move scroll bar (if shown) to the right as far as it can go
            }
        }
    }//GEN-LAST:event_lblCardBackMouseClicked

    private void cbxGuessItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxGuessItemStateChanged
        if (null != this.myCardDeck) {
            ParticipantGuess currModel = this.myCardDeck.getParticipantsGuess();
            ParticipantGuess currSelection = (ParticipantGuess) cbxGuess.getSelectedItem();
            if (!currSelection.equals(currModel)) {
                this.myCardDeck.setParticipantsGuess((ParticipantGuess) cbxGuess.getSelectedItem());
            }
        }
    }//GEN-LAST:event_cbxGuessItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbxGuess;
    private javax.swing.JLabel lblCardBack;
    private javax.swing.JLabel lblGuess;
    private javax.swing.JScrollPane scrSubPanel;
    // End of variables declaration//GEN-END:variables

    public void setCardDeck(IDeck aDeck) {
        if (null != aDeck) {
            myCardDeck = aDeck;
            this.update();
        } else {
            throw new NullPointerException("Cannot set a NULL deck object into a BaseCardPanel.");
        }
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

    //<editor-fold defaultstate="collapsed" desc="Observer implementation">
    @Override
    public boolean registerObserver(IObserver o) {
        return observers.registerObserver(o);
    }
    
    @Override
    public boolean removeObserver(IObserver o) {
        return observers.removeObserver(o);
    }
    
    @Override
    public void notifyObservers() {
        observers.notifyObservers();
    }
    
    @Override
    public <T> void notifyObservers(T data) {
        observers.notifyObservers(data);
    }
    
    @Override
    public Set<IObserver> removeAllObservers() {
        return observers.removeAllObservers();
    }
    
    @Override
    public boolean registerObserver(Collection<? extends IObserver> observerCollection) {
        return observers.registerObserver(observerCollection);
    }
    
    @Override
    public void update() {
        if (null != this.myCardDeck) {
            this.cbxGuess.setSelectedItem(this.myCardDeck.getParticipantsGuess());
        }
        revalidate();
        repaint();
    }
    //</editor-fold>

    public final void setSubPanelDisplayType(DrawnCardsDisplayType aType) {
        if (null != aType) {
            this.subPanelType = aType;
            this.setSubPanel(this.subPanelType.createDisplayPanel(myCardDeck));
        } else {
            throw new NullPointerException("Cannot set sub panel display type to NULL.");
        }
    }
    
    public boolean hasGuess() {
        boolean result = false;
        if (null != this.myCardDeck && this.cbxGuess.getSelectedItem() != ParticipantGuess.UNKNOWN) {
            result = true;
        }
        return result;
    }
    
    public void setCardsDrawnRecordList(ICardDrawnRecordList recordList) {
        this.cardsDrawnRecordList = recordList;
    }
    
    private void setIcon() {
        int width = lblCardBack.getWidth();
        int height = lblCardBack.getHeight();
        if (width > 0 && height > 0) {
            Icon cardIcon = dumbyBackCard.getCardIcon(width, height);
            lblCardBack.setIcon(cardIcon);
            revalidate();
            repaint();
        } else {
            java.awt.EventQueue.invokeLater(() -> {
                setIcon();
            });
        }
    }
    
    private void setSubPanel(JPanel aPanel) {
        scrSubPanel.setViewportView(aPanel);
        invalidate();
        repaint();
    }
}
