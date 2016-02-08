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
import java.util.Random;

/**
 *
 * @author rtucker
 */
public class QueenHiInfoInfiniteDeckAlgorithm extends BaseDeckAlgorithm {
    
    private Random rng;
    
    public QueenHiInfoInfiniteDeckAlgorithm(){
        super();
        this.rng = new Random();
    }
    
    public QueenHiInfoInfiniteDeckAlgorithm(int maxCardsBetweenSpecialCard){
        this();
        this.intMaxCardDrawsBetweenSpecialCard = maxCardsBetweenSpecialCard;
    }

    @Override
    public ICard drawCard() {
        ICard result;
        if(this.intDrawsSinceLastSpecialCard >= this.intMaxCardDrawsBetweenSpecialCard){
            result = this.createSpecialCard();
        }else{
            result = this.createNormalCard();
        }
        result = this.biasCardValueToQueen(result);
        if(this.checkIfKingOrQueenCard(result)){
            this.resetTimeSinceLastSpecialCard();
        }else{
            this.intDrawsSinceLastSpecialCard++;
        }
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
        return CardAlgorithmCategory.QUEEN_HI_INFORMATIVE;
    }

    @Override
    protected ICard createSpecialCard() {
        ICard result;
        int intValue = this.rng.nextInt(13 - 12 + 1) + 12;
        CardValue myValue = CardValue.getCardValueFromInt(intValue);
        intValue = this.rng.nextInt(4 - 1 + 1) + 1;
        CardSuite mySuite = CardSuite.getSuiteFromInt(intValue);
        result = new Card(mySuite, myValue, true);
        return result;
    }

    @Override
    protected ICard createNormalCard() {
        ICard result;
        int intValue = this.rng.nextInt(13) + 1;
        CardValue myValue = CardValue.getCardValueFromInt(intValue);
        intValue = this.rng.nextInt(4 - 1 + 1) + 1;
        CardSuite mySuite = CardSuite.getSuiteFromInt(intValue);
        result = new Card(mySuite, myValue, true);
        return result;
    }

    private ICard biasCardValueToQueen(ICard aCard) {
        ICard result = aCard;
        if(CardValue.KING == aCard.getValue()){
            int intValue = this.rng.nextInt(6) + 1;
            if(intValue >= 3){
                result = new Card(aCard.getSuite(), CardValue.QUEEN);
            }
        }
        return result;
    }
    
}
