/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.RectangleZ;
import datamodel.interfaces.ICard;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import javax.swing.Icon;

/**
 *
 * @author rtucker
 */
public class CardListPanel extends javax.swing.JPanel implements Iterable<ICard>, ISubject {

    // <editor-fold defaultstate="collapsed" desc="Class Attributes">
    private final List<ICard> cardList;

    private final static double CARD_OVERLAP_PERC = 0.2d;
    private final static double BASE_CARD_WIDTH = 100d;
    private final HashMap<ICard, RectangleZ> renderArea;
    private final ISubject observers;

    public final static int Y_ERROR = 27;
    public final static int HEIGHT_ERROR = 55;
    // </editor-fold>

    /**
     * Creates new form CardListViewer
     */
    public CardListPanel() {
        initComponents();
        this.cardList = new ArrayList<>();
        this.renderArea = new HashMap<>();
        this.observers = new ISubjectImpl();
        this.resizeBasedOnListContents();
        CardListPanelMouseActions mouseActions = new CardListPanelMouseActions();
        this.addMouseListener(mouseActions);
        this.addMouseMotionListener(mouseActions);
        this.addMouseWheelListener(mouseActions);
    }

    @Override
    public void setPreferredSize(Dimension dmnsn) {
        super.setPreferredSize(dmnsn); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Setting prefered size of: " + dmnsn.toString());
    }

    @Override
    public Dimension getPreferredSize() {
        System.out.println("Getting prefered size of: " + super.getPreferredSize().toString());
        return super.getPreferredSize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    // <editor-fold defaultstate="collapsed" desc="List manipulation methods exposed">
    public boolean replaceList(List<ICard> aCardList) {
        boolean result;
        List<ICard> oldList = new ArrayList<>(cardList);
        cardList.clear();
        result = cardList.addAll(aCardList);
        if (!result) {
            cardList.clear();
            cardList.addAll(oldList);
        }
        this.resizeBasedOnListContents();
        this.notifyObservers();
        return result;
    }

    public boolean addCard(ICard aCard) {
        boolean result = false;
        if (null != aCard) {
            result = cardList.add(aCard);
        }
        this.resizeBasedOnListContents();
        this.notifyObservers();
        return result;
    }

    public boolean removeCard(ICard aCard) {
        boolean result = false;
        if (null != aCard) {
            if (cardList.contains(aCard)) {
                result = cardList.remove(aCard);
            }
        }
        this.resizeBasedOnListContents();
        this.notifyObservers();
        return result;
    }

    public int cardListSize() {
        return cardList.size();
    }

    public ListIterator<ICard> listIterator() {
        return cardList.listIterator();
    }

    public void clearCardList() {
        cardList.clear();
        this.resizeBasedOnListContents();
        this.notifyObservers();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Iterable interface methods">
    @Override
    public Iterator<ICard> iterator() {
        return cardList.iterator();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Overridden methods">
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); //To change body of generated methods, choose Tools | Templates.

        Dimension size = this.getSize();
        grphcs.clearRect(0, 0, size.width, size.height);
        Double dblCardWidth = BASE_CARD_WIDTH;

        Double dblStep = CARD_OVERLAP_PERC * dblCardWidth;
        int cardWidth = dblCardWidth.intValue();
        int cardHeight = cardWidth * 2;
        int x = 0;
        int z = 0;
        this.renderArea.clear();
        for (ICard currCard : cardList) {
            Icon cardIcon = currCard.getCardIcon(cardWidth, cardHeight);
            if (null != cardIcon) {
                int y;
                if (currCard.isSelected()) {
                    y = 0;
                } else {
                    y = dblStep.intValue();
                }
                cardIcon.paintIcon(this, grphcs, x, y - Y_ERROR);
                RectangleZ area = new RectangleZ(x, y, z, cardIcon.getIconWidth(), cardIcon.getIconHeight() - HEIGHT_ERROR);
                this.renderArea.put(currCard, area);
            }
            x += dblStep.intValue();
            z++;
        }
        //grphcs.drawRect(0, 47, 100, 145);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ISubject interface methods">
    @Override
    public boolean registerObserver(IObserver o) {
        return this.observers.registerObserver(o);
    }

    @Override
    public boolean removeObserver(IObserver o) {
        return this.observers.removeObserver(o);
    }

    @Override
    public void notifyObservers() {
        this.observers.notifyObservers();
    }

    @Override
    public <T> void notifyObservers(T data) {
        this.observers.notifyObservers(data);
    }

    @Override
    public Set<IObserver> removeAllObservers() {
        return this.observers.removeAllObservers();
    }

    @Override
    public boolean registerObserver(Collection<? extends IObserver> observerCollection) {
        return this.observers.registerObserver(observerCollection);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private helper methods">
    private void resizeBasedOnListContents() {
        int noOfCards = this.cardList.size();
        Double dblReqWidth = BASE_CARD_WIDTH + ((noOfCards - 1) * (BASE_CARD_WIDTH * CARD_OVERLAP_PERC));
        int newWidth = 1 + dblReqWidth.intValue();
        int newHeight = 1 + (Double.valueOf(BASE_CARD_WIDTH).intValue() * 2);
        Dimension newDim = new Dimension(newWidth, newHeight);
        this.setMinimumSize(newDim);
        this.setMaximumSize(newDim);
        this.setPreferredSize(newDim);
        this.revalidate();
        this.repaint();
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Card List Mouse Listener">
    private class CardListPanelMouseActions extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent me) {
            if (0 < cardListSize()) {
                Point clickPoint = me.getPoint();
                HashMap<RectangleZ, ICard> selectable = new HashMap<>();
                for (ICard currCard : cardList) {
                    currCard.setSelected(false);
                    RectangleZ area = renderArea.get(currCard);
                    if (null != area) {
                        if (area.contains(clickPoint)) {
                            selectable.put(area, currCard);
                        }
                    }
                }
                if (0 < selectable.size()) {
                    List<RectangleZ> toSort = new ArrayList<>(selectable.keySet());
                    Collections.sort(toSort);
                    selectable.get(toSort.get(toSort.size() - 1)).setSelected(true);
                }
                repaint();
            }
        }
    }
    //</editor-fold>
}
