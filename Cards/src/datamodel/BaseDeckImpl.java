/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import datamodel.exceptions.NotAnAlgorithmicDeckException;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Concrete implementation of the IDeck interface. Represents a pack of playing
 * cards in the system.
 *
 * @author rtucker
 */
public class BaseDeckImpl implements IDeck {

    // <editor-fold defaultstate="collapsed" desc="Class Attributes">
    protected IDeckAlgorithm cardAlgorithm;
    protected List<ICard> drawnCards;
    protected ISubject observers;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public BaseDeckImpl() {
        this.drawnCards = new ArrayList<>();
        observers = new ISubjectImpl();
    }
    
    public BaseDeckImpl(IDeckAlgorithm algorithm) {
        this();
        this.cardAlgorithm = algorithm;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IDeckAlgorithm interface implementation">
    @Override
    public int cardsDrawnSinceLastSpecialCard() {
        return this.cardAlgorithm.cardsDrawnSinceLastSpecialCard();
    }
    
    @Override
    public int getMaxCardDrawsBetweenSpecialCards() {
        return this.cardAlgorithm.getMaxCardDrawsBetweenSpecialCards();
    }
    
    @Override
    public void setMaxCardDrawsBetweenSpecialCards(int max) throws IllegalArgumentException {
        this.cardAlgorithm.setMaxCardDrawsBetweenSpecialCards(max);
    }
    
    @Override
    public ICard drawCard() {
        ICard drawnCard = this.cardAlgorithm.drawCard();
        drawnCards.add(drawnCard);
        return drawnCard;
    }
    
    @Override
    public boolean hasCardsRemaining() {
        return this.cardAlgorithm.hasCardsRemaining();
    }
    
    @Override
    public int getNoOfRemainingCards() {
        return this.cardAlgorithm.getNoOfRemainingCards();
    }
    
    @Override
    public DeckType getDeckType() {
        return this.cardAlgorithm.getDeckType();
    }
    
    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        return this.cardAlgorithm.getAlgorithmCategory();
    }
    
    @Override
    public double getProbabilityOfSpecialCard() {
        return this.cardAlgorithm.getProbabilityOfSpecialCard();
    }
    
    @Override
    public void setProbabilityOfSpecialCard(double probability) throws IllegalArgumentException {
        this.cardAlgorithm.setProbabilityOfSpecialCard(probability);
    }
    
    @Override
    public void reset(){
        this.cardAlgorithm.reset();
        this.drawnCards.clear();
        this.observers.notifyObservers();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IDeck interface implementation">
    @Override
    public final boolean isAlgorithmic() {
        boolean result = false;
        if (null != this.cardAlgorithm) {
            result = true;
        }
        return result;
    }
    
    @Override
    public final IDeckAlgorithm getDeckAlgorithm() throws NotAnAlgorithmicDeckException {
        IDeckAlgorithm result = null;
        if (this.isAlgorithmic()) {
            result = this.cardAlgorithm;
        } else {
            throw new NotAnAlgorithmicDeckException("Cannot call getDeckAlgorithm() on a deck that does not support algorithmic card generation");
        }
        return result;
    }
    
    @Override
    public final boolean setDeckAlgorithm(IDeckAlgorithm alg) throws NotAnAlgorithmicDeckException {
        boolean result = false;
        if (null != alg) {
            if (this.isAlgorithmic()) {
                this.cardAlgorithm = alg;
                result = true;
            } else {
                throw new NotAnAlgorithmicDeckException("Cannot call setDeckAlgorithm() on a deck that does not support algorithmic card generation");
            }
        }
        return result;
    }
    
    @Override
    public List<ICard> getDrawnCardList() {
        return new ArrayList<>(drawnCards);
    }
    // </editor-fold>

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
    //</editor-fold>
}
