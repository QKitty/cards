/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.deckalgorithms;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeckAlgorithm;

/**
 * Abstract base class from which deck algorithms should inherit. Provides
 * utility methods to assist algorithm implementation
 * @author rtucker
 */
public abstract class BaseDeckAlgorithm implements IDeckAlgorithm {
    
    protected int intDrawsSinceLastSpecialCard;
    protected int intMaxCardDrawsBetweenSpecialCard;
    protected double dblProbOfSpecialCard;
    
    public BaseDeckAlgorithm(){
        this.intDrawsSinceLastSpecialCard = 0;
        this.intMaxCardDrawsBetweenSpecialCard = 5;
    }
    
    public BaseDeckAlgorithm(int maxCardsBetweenSpecialCard){
        this.intDrawsSinceLastSpecialCard = 0;
        this.intMaxCardDrawsBetweenSpecialCard = maxCardsBetweenSpecialCard;
    }

    @Override
    public int cardsDrawnSinceLastSpecialCard() {
        return this.intDrawsSinceLastSpecialCard;
    }
    
    @Override
    public int getMaxCardDrawsBetweenSpecialCards() {
        return this.intMaxCardDrawsBetweenSpecialCard;
    }

    @Override
    public void setMaxCardDrawsBetweenSpecialCards(int max)  throws IllegalArgumentException {
        if(0 > max){
            throw new IllegalArgumentException("Maximum number of cards drawn between special cards must be positive");
        }
        this.intMaxCardDrawsBetweenSpecialCard = max;
    }

    @Override
    public abstract ICard drawCard();

    @Override
    public abstract boolean hasCardsRemaining();

    @Override
    public abstract int getNoOfRemainingCards();

    @Override
    public abstract DeckType getDeckType();

    @Override
    public abstract CardAlgorithmCategory getAlgorithmCategory();
    
    @Override
    public double getProbabilityOfSpecialCard() {
        return this.dblProbOfSpecialCard;
    }

    @Override
    public void setProbabilityOfSpecialCard(double probability) throws IllegalArgumentException {
        if(0.0d <= probability && 1.0d >= probability){
            throw new IllegalArgumentException("Probability of drawing a special card must be between 0 and 1");
        }
        this.dblProbOfSpecialCard = probability;
    }
    
    protected boolean checkIfKingOrQueenCard(ICard aCard){
        boolean result = false;
        if(null != aCard){
            if(aCard.isFaceCard()){
                switch(aCard.getValue()){
                    case KING:
                        result = true;
                        break;
                    case QUEEN:
                        result = true;
                }
            }
        }
        return result;
    }
    
    protected void updateTimeSinceLastSpecialCard(){
        this.intDrawsSinceLastSpecialCard = 0;
    }
    
    protected abstract ICard createSpecialCard();
    
    protected abstract ICard createNormalCard();
    
}
