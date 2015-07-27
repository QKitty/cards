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
import javax.swing.Icon;

/**
 * Concrete implementation of the ICard interface. Represents a playing card
 * in the system
 * @author rtucker
 */
public class Card implements ICard {
    
    private final CardSuite suite;
    private final CardValue value;
    private boolean showingFace;
    
    public Card(CardSuite newSuite, CardValue newValue){
        this.suite = newSuite;
        this.value = newValue;
        this.showingFace = false;
    }
    
    public Card(CardSuite newSuite, CardValue newValue, boolean showFace){
        this(newSuite, newValue);
        this.showingFace = showFace;
    }

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
            if(showingFace){
                //Create Icon showing face
            }else{
                //Create Icon showing back of card
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isShowingFace() {
        return this.showingFace;
    }

    @Override
    public void setShowingFace(boolean flag) {
        this.showingFace = flag;
    }
    
}
