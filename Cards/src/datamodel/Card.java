/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.SuiteColour;
import datamodel.interfaces.ICard;
import datamodel.interfaces.ICardIconGenerator;
import datamodel.interfaces.SVGIconGenerator;
import javax.swing.Icon;

/**
 * Concrete implementation of the ICard interface. Represents a playing card
 * in the system
 * @author rtucker
 */
public class Card implements ICard {
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    private final CardSuite suite;
    private final CardValue value;
    private final ICardIconGenerator iconCreator;
    private boolean showingFace;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Card(CardSuite newSuite, CardValue newValue){
        this.suite = newSuite;
        this.value = newValue;
        this.iconCreator = new SVGIconGenerator();
        this.showingFace = false;
    }
    
    public Card(CardSuite newSuite, CardValue newValue, boolean showFace){
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
        switch(this.value){
            case JOKER_BLACK:
                result = SuiteColour.BLACK;
                break;
            default:
                switch(this.suite){
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
        if(0 < (width * height)){
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
    // </editor-fold>
    
}
