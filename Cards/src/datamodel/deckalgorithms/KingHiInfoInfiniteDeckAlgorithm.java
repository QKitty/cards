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
import java.util.Random;

/**
 * This is the concrete implementation for a deck of playing cards that
 * favours producing King cards with a high frequency
 * @author rtucker
 */
public class KingHiInfoInfiniteDeckAlgorithm extends BaseDeckAlgorithm {
    
    private Random rng;
    
    public KingHiInfoInfiniteDeckAlgorithm(){
        super();
        this.rng = new Random();
    }
    
    public KingHiInfoInfiniteDeckAlgorithm(int maxCardsBetweenSpecialCard){
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
        result = this.biasCardValueToKing(result);
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
        return CardAlgorithmCategory.KING_HI_INFORMATIVE;
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
    
    //Bias gives a 2/3 chance that a queen card becomes a king
    private ICard biasCardValueToKing(ICard aCard){
        ICard result = aCard;
        if(CardValue.QUEEN == aCard.getValue()){
            int intValue = this.rng.nextInt(6) + 1;
            if(intValue >= 3){
                result = new Card(aCard.getSuite(), CardValue.KING);
            }
        }
        return result;
    }

    @Override
    public void reset() {
        //No reset action needed.
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
        return "createKingHiInfoInfiniteDeckAlgorithm";
    }

    @Override
    public Object[] getFactoryArgs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
