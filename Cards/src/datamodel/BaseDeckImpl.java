/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.deckalgorithms.BaseDeckAlgorithm;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.CardValue;
import datamodel.enums.DeckType;
import datamodel.enums.ParticipantGuess;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;
import datamodel.interfaces.IXMLPersistable;
import datamodel.persistance.DeckFactory;
import datamodel.persistance.IXMLPersistablePersistanceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Concrete implementation of the IDeck interface. Represents a pack of playing
 * cards in the system.
 *
 * @author rtucker
 */
public class BaseDeckImpl implements IDeck, IXMLPersistable {

    // <editor-fold defaultstate="collapsed" desc="Class Attributes">
    protected String id;
    private DeckStatistics stats;
    protected IDeckAlgorithm cardAlgorithm;
    protected List<ICard> drawnCards;
    protected ParticipantGuess currGuess;
    protected ISubject observers;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public BaseDeckImpl() {
        this.drawnCards = new ArrayList<>();
        this.currGuess = ParticipantGuess.UNKNOWN;
        observers = new ISubjectImpl();
        this.stats = new DeckStatistics();
        this.id = "UNKNOWN";
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
        this.notifyObservers();
    }

    @Override
    public ICard drawCard() {
        ICard drawnCard = this.cardAlgorithm.drawCard();
        drawnCard.setShowingFace(true);
        drawnCards.add(drawnCard);
        drawnCard.registerObserver(this);
        this.stats.refresh();
        this.notifyObservers();
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
        this.notifyObservers();
    }

    @Override
    public void reset() {
        this.cardAlgorithm.reset();
        this.drawnCards.stream().forEach((currCard) -> {
            currCard.removeObserver(this);
        });
        this.drawnCards.clear();
        this.notifyObservers();
    }

    @Override
    public final boolean isAlgorithmic() {
        boolean result = false;
        if (null != this.cardAlgorithm) {
            result = this.cardAlgorithm.isAlgorithmic();
        }
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IDeck interface implementation">
    @Override
    public final IDeckAlgorithm getDeckAlgorithm() {
        return this.cardAlgorithm;
    }

    @Override
    public final boolean setDeckAlgorithm(IDeckAlgorithm alg) {
        boolean result = false;
        if (null != alg) {
            this.cardAlgorithm = alg;
            result = true;
            this.notifyObservers();
        }
        return result;
    }

    @Override
    public List<ICard> getDrawnCardList() {
        return new ArrayList<>(drawnCards);
    }
    
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String newId) {
        if(null != newId){
            if(!newId.isEmpty()){
                this.id = newId;
            }else{
                this.id = "UNKNOWN";
            }
            this.notifyObservers();
        } else {
            throw new NullPointerException("Cannot set a NULL value as a deck Id.");
        }
    }

    @Override
    public int getNoOfKingsDrawn() {
        int result = 0;
        if(!drawnCards.isEmpty()){
            result = stats.kingCount;
        }
        return result;
    }

    @Override
    public int getNoOfQueensDrawn() {
        int result = 0;
        if(!drawnCards.isEmpty()){
            result = stats.queenCount;
        }
        return result;
    }

    @Override
    public int getNoOfOtherCardsDrawn() {
        int result = 0;
        if(!drawnCards.isEmpty()){
            result = stats.otherCount;
        }
        return result;
    }

    @Override
    public double getProportionOfKingsVKingsAndQueensDrawn() {
        double result = 0.0;
        if(!drawnCards.isEmpty()){
            result = stats.kingCount / (double)(stats.kingCount + stats.queenCount);
        }
        return result;
    }

    @Override
    public double getProportionOfKingsAndQueensDrawn() {
        double result = 0.0;
        if(!drawnCards.isEmpty()){
            result = (stats.kingCount + stats.queenCount) / (double)(stats.kingCount + stats.queenCount + stats.otherCount);
        }
        return result;
    }
    
    @Override
    public ParticipantGuess getParticipantsGuess() {
        return this.currGuess;
    }

    @Override
    public void setParticipantsGuess(ParticipantGuess aGuess) {
        if(null != aGuess){
            this.currGuess = aGuess;
            this.notifyObservers();
        } else {
            throw new NullPointerException("Cannot set a deck type guess of NULL.");
        }
    }

    @Override
    public boolean isParticipantGuessCorrect() {
        return this.cardAlgorithm.getAlgorithmCategory().isGuessCorrect(this.currGuess);
    }
    
    @Override
    public boolean hasParticipantGuessSet(){
        boolean result = false;
        if(this.currGuess != ParticipantGuess.UNKNOWN){
            result = true;
        }
        return result;
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

    @Override
    public void update() {
        this.notifyObservers();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IXMLPersistable implementation">
    @Override
    public Class<?> getFactoryClass() {
        return DeckFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createDeck";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[1];
        result[0] = this.cardAlgorithm;
        return result;
    }

    @Override
    public XMLEncoder createXMLEncoder(File aFile) {
        XMLEncoder enc = null;
        try {
            enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(aFile)));
            enc.setPersistenceDelegate(this.getClass(), this.getPersistanceDelegate());
            enc.setPersistenceDelegate(this.cardAlgorithm.getClass(), ((BaseDeckAlgorithm)this.cardAlgorithm).getPersistanceDelegate());
            enc.setPersistenceDelegate(Card.class, new IXMLPersistablePersistanceDelegate());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BaseDeckImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enc;
    }
    //</editor-fold>

    

//<editor-fold defaultstate="collapsed" desc="Inner Classes - Yuck">
    private class DeckStatistics {
        
        public int kingCount;
        public int queenCount;
        public int otherCount;
        
        public DeckStatistics(){
            this.refresh();
        }
        
        public final void refresh(){
            kingCount = 0;
            queenCount = 0;
            otherCount = 0;
            drawnCards.stream().forEach((currCard) -> {
                if(currCard.getValue() == CardValue.KING){
                    kingCount++;
                } else if(currCard.getValue() == CardValue.QUEEN){
                    queenCount++;
                } else {
                    otherCount++;
                }
            });
        }
    }
//</editor-fold>
}
