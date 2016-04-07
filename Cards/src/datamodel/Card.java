/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.SuiteColour;
import datamodel.interfaces.ICard;
import datamodel.interfaces.ICardIconGenerator;
import datamodel.interfaces.SVGIconGenerator;
import datamodel.persistance.CardFactory;
import java.beans.XMLEncoder;
import java.io.File;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import javax.swing.Icon;

/**
 * Concrete implementation of the ICard interface. Represents a playing card in
 * the system
 *
 * @author rtucker
 */
public class Card implements ICard {

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final CardSuite suite;
    private final CardValue value;
    private final ICardIconGenerator iconCreator;
    private final ISubject observers;
    private boolean showingFace;
    private boolean selected;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Card(CardSuite newSuite, CardValue newValue) {
        this.suite = newSuite;
        this.value = newValue;
        this.iconCreator = new SVGIconGenerator();
        this.showingFace = false;
        this.selected = false;
        this.observers = new ISubjectImpl();
    }

    public Card(CardSuite newSuite, CardValue newValue, boolean showFace) {
        this(newSuite, newValue);
        this.showingFace = showFace;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ICard interface implementation">
    @Override
    public boolean isFaceCard() {
        return this.value.isFaceCard();
    }

    @Override
    public CardSuite getSuite() {
        return this.suite;
    }

    @Override
    public CardValue getValue() {
        return this.value;
    }

    @Override
    public SuiteColour getSuiteColour() {
        SuiteColour result;
        switch (this.value) {
            case JOKER_BLACK:
                result = SuiteColour.BLACK;
                break;
            default:
                switch (this.suite) {
                    case CLUBS:
                    case SPADES:
                        result = SuiteColour.BLACK;
                        break;
                    default:
                        result = SuiteColour.RED;
                }
        }
        return result;
    }

    @Override
    public Icon getCardIcon(int width, int height) {
        Icon result = null;
        if (0 < (width * height)) {
            result = this.iconCreator.generateCardIcon(this, width, height);
        }
        return result;
    }

    @Override
    public boolean isShowingFace() {
        return this.showingFace;
    }

    @Override
    public void setShowingFace(boolean flag) {
        this.showingFace = flag;
    }

    @Override
    public boolean isSelected() {
        return this.selected;
    }

    @Override
    public void setSelected(boolean flag) {
        this.selected = flag;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="IXMLPersistable Interface">
    @Override
    public String toString() {
        return "The " + this.value.toString() + " of " + this.suite.toString();
    }

    @Override
    public Class<?> getFactoryClass() {
        return CardFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createCard";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] args = new Object[3];
        args[0] = this.getSuite();
        args[1] = this.getValue();
        args[2] = this.isShowingFace();
        return args;
    }

//    @Override
//    public XMLEncoder createXMLEncoder(File aFile) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Observer Pattern Implementation">
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

//<editor-fold defaultstate="collapsed" desc="Overridden Object methods">
    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if(obj instanceof ICard){
            ICard otherCard = (ICard)obj;
            if(this.suite.equals(otherCard.getSuite()) && this.value.equals(otherCard.getValue())){
                result = true;
            }
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.suite);
        hash = 47 * hash + Objects.hashCode(this.value);
        return hash;
    }
//</editor-fold>
    
    
}
