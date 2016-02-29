/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.deckalgorithms;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import datamodel.interfaces.ICard;

/**
 *
 * @author qkitt
 */
public class FrequencyInfiniteDeckAlgorithm extends BaseDeckAlgorithm {

    private double kingFreq;
    private double queenFreq;
    private double otherFreq;
    private double deltaF;
    private int intKingsDrawn;
    private int intQueensDrawn;
    private int intOthersDrawn;
    
    public FrequencyInfiniteDeckAlgorithm(double fKing, double fQueen, double fOther) {
        this(fKing, fQueen, fOther, 0.0d);
    }

    public FrequencyInfiniteDeckAlgorithm(double fKing, double fQueen, double fOther, double deltaf) {
        if (0 > fKing || 0 > fQueen || 0 > fOther) {
            throw new IllegalArgumentException("Frequencies cannot be negative");
        }
        if(0 > deltaf){
            throw new IllegalArgumentException("Frequencies delta value cannot be negative");
        }
        double dblTotal = fKing + fQueen + fOther;
        if (dblTotal <= 0.0d) {
            throw new IllegalArgumentException("Frequencies must sum to a positive number greater than 0");
        }
        kingFreq = fKing / dblTotal;
        queenFreq = fQueen / dblTotal;
        otherFreq = fOther / dblTotal;
    }

    @Override
    public ICard drawCard() {
        ICard result;
        if (this.intDrawsSinceLastSpecialCard >= this.intMaxCardDrawsBetweenSpecialCard) {
            result = this.createSpecialCard();
        } else {
            result = this.createNormalCard();
        }
        if (this.checkIfKingOrQueenCard(result)) {
            this.resetTimeSinceLastSpecialCard();
        } else {
            this.intDrawsSinceLastSpecialCard++;
        }
        incCounters(result);
        return result;
    }

    @Override
    public boolean hasCardsRemaining() {
        return true;
    }

    @Override
    public int getNoOfRemainingCards() {
        return -1;
    }

    @Override
    public DeckType getDeckType() {
        return DeckType.INFINITE_DECK;
    }

    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ICard createSpecialCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ICard createNormalCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        kingFreq = 0;
        queenFreq = 0;
        otherFreq = 0;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void incCounters(ICard aCard) {
        if (null != aCard) {
            switch (aCard.getValue()) {
                case KING:
                    intKingsDrawn++;
                    break;
                case QUEEN:
                    intQueensDrawn++;
                    break;
                default:
                    intOthersDrawn++;
            }
        } else {
            throw new NullPointerException("Cannot increment counters for a NULL card");
        }
    }
    
    private double calcCurrFreq(int value){
        double result = 0.0d;
        double dblTotal = intKingsDrawn + intQueensDrawn + intOthersDrawn;
        if(0 < dblTotal){
            result = value / dblTotal;
        }
        return result;
    }

}
