/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.deckalgorithms;

import datamodel.Card;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.DeckType;
import datamodel.interfaces.ICard;
import datamodel.persistance.DeckAlgorithmFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        double currKingFreq, currQueenFreq, currOtherFreq;
        double deltaKingFreq, deltaQueenFreq, deltaOtherFreq;
        currKingFreq = calcCurrFreq(this.intKingsDrawn);
        currQueenFreq = calcCurrFreq(this.intQueensDrawn);
        currOtherFreq = calcCurrFreq(this.intOthersDrawn);
        deltaKingFreq = currKingFreq - this.kingFreq;
        deltaQueenFreq = currQueenFreq - this.queenFreq;
        deltaOtherFreq = currOtherFreq - this.otherFreq;
        if(Math.abs(deltaKingFreq) > deltaF || Math.abs(deltaQueenFreq) > deltaF || Math.abs(deltaOtherFreq) > deltaF){
            //We need to select a card based on frequency.
            //Find the type of card with the greatest delta frequency
            List<Double> deltaFs = new ArrayList();
            deltaFs.add(deltaKingFreq);
            deltaFs.add(deltaQueenFreq);
            deltaFs.add(deltaOtherFreq);
            Collections.sort(deltaFs);
            CardChoice reqCardType;
            if(deltaFs.get(deltaFs.size()-1).equals(deltaKingFreq)){
                reqCardType = CardChoice.KING;
            } else if(deltaFs.get(deltaFs.size()-1).equals(deltaQueenFreq)){
                reqCardType = CardChoice.QUEEN;
            } else {
                reqCardType = CardChoice.OTHER;
            }
            switch(reqCardType){
                case KING:
                    result = this.createSpecialCard();
                    if(result.getValue() != CardValue.KING){
                        result = new Card(result.getSuite(), CardValue.KING, true);
                    }
                    break;
                case QUEEN:
                    result = this.createSpecialCard();
                    if(result.getValue() != CardValue.QUEEN){
                        result = new Card(result.getSuite(), CardValue.QUEEN, true);
                    }
                    break;
                default:
                    result = this.createNormalCard();
            }
        } else {
            //We need to select a card randomly
            result = drawRandomCard();
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
        ICard result;
        CardValue cardValue;
        CardSuite suiteValue;
        Double randValue = Math.random();
        int intValue = Math.round(randValue.floatValue());
        if(intValue == 0){
            cardValue = CardValue.QUEEN;
        } else {
            cardValue = CardValue.KING;
        }
        randValue = Math.random() * 4;
        intValue = Math.round(randValue.floatValue());
        suiteValue = CardSuite.getSuiteFromInt(intValue);
        result = new Card(suiteValue, cardValue, true);
        return result;
    }

    @Override
    protected ICard createNormalCard() {
        ICard result;
        Double randValue = Math.random() * 11;
        int intValue = Math.round(randValue.floatValue());
        CardValue cardValue = CardValue.getCardValueFromInt(intValue);
        randValue = Math.random() * 4;
        intValue = Math.round(randValue.floatValue());
        CardSuite suiteValue = CardSuite.getSuiteFromInt(intValue);
        result = new Card(suiteValue, cardValue, true);
        return result;
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

    private ICard drawRandomCard() {
        ICard result;
        //Normalise frequency values
        double nmlKingFreq, nmlQueenFreq, nmlOtherFreq, totalFreq;
        totalFreq = this.kingFreq + this.queenFreq + this.otherFreq;
        nmlKingFreq = this.kingFreq / totalFreq;
        nmlQueenFreq = this.queenFreq / totalFreq;
        nmlOtherFreq = this.otherFreq / totalFreq;
        double rand = Math.random();
        if(rand <= nmlKingFreq) {
            result = createSpecialCard();
            if(result.getValue() != CardValue.KING){
                result = new Card(result.getSuite(), CardValue.KING, true);
            }
        }else if(rand <= (nmlKingFreq + nmlQueenFreq)){
            result = createSpecialCard();
            if(result.getValue() != CardValue.QUEEN){
                result = new Card(result.getSuite(), CardValue.QUEEN, true);
            }
        } else {
            result = createNormalCard();
        }
        return result;
    }

    @Override
    public boolean isAlgorithmic() {
        return true;
    }

    @Override
    public Class<?> getFactoryClass() {
        return DeckAlgorithmFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createFrequencyInfiniteDeckAlgorithm";
    }

    @Override
    public Object[] getFactoryArgs() {
        //double fKing, double fQueen, double fOther, double deltaf
        Object[] result = new Object[4];
        result[0] = this.kingFreq;
        result[0] = this.queenFreq;
        result[0] = this.otherFreq;
        result[0] = this.deltaF;
        return result;
    }
    
    private enum CardChoice {
        KING, QUEEN, OTHER;
    }

}
