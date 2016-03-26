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
 *
 * @author qkitt
 */
public class CustomisableInfiniteDeckAlgorithm extends BaseDeckAlgorithm {

    private final double prefForKing;
    private final double prefForQueen;
    private final int noOfKingQueenCards;             //Allowable range is 2 to 13
    private final Random rng;

    public CustomisableInfiniteDeckAlgorithm() {
        super();
        this.rng = new Random();
        this.prefForKing = 0.5d;
        this.prefForQueen = 0.5d;
        this.noOfKingQueenCards = 2;
    }

    public CustomisableInfiniteDeckAlgorithm(double prefKing, double prefQueen, int noKQCards, int maxDrawsBetween) {
        super(maxDrawsBetween);
        this.rng = new Random();
        this.prefForKing = prefKing;
        this.prefForQueen = prefQueen;
        this.noOfKingQueenCards = noKQCards;
        if (1.0d != (prefKing + prefQueen)) {
            throw new IllegalArgumentException("The preference for king or queen must sum to 1");
        }
        if (noKQCards > 13) {
            throw new IllegalArgumentException("Total number of cards in a deck cannot exceed 13");
        }
        if (noKQCards < 2) {
            throw new IllegalArgumentException("Must be at least two cards in a deck.");
        }
    }

    @Override
    public ICard drawCard() {
        ICard result;
        if (this.intDrawsSinceLastSpecialCard >= this.intMaxCardDrawsBetweenSpecialCard) {
            result = this.createSpecialCard();
        } else {
            int intCardPos = this.rng.nextInt(13) + 1;
            if (intCardPos < (13 - noOfKingQueenCards)) {
                result = this.createNormalCard();
            } else {
                result = this.createSpecialCard();
            }
        }
        if (this.checkIfKingOrQueenCard(result)) {
            this.resetTimeSinceLastSpecialCard();
        } else {
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
        CardAlgorithmCategory result;
        if (this.prefForKing >= this.prefForQueen) {
            result = CardAlgorithmCategory.KING_HI_INFORMATIVE;
        } else {
            result = CardAlgorithmCategory.QUEEN_HI_INFORMATIVE;
        }
        return result;
    }

    @Override
    protected ICard createSpecialCard() {
        ICard result;
        double randomDouble = this.rng.nextDouble();
        CardValue myValue;
        if (randomDouble <= this.prefForQueen) {
            myValue = CardValue.QUEEN;
        } else {
            myValue = CardValue.KING;
        }
        int intValue = this.rng.nextInt(4 - 1 + 1) + 1;
        CardSuite mySuite = CardSuite.getSuiteFromInt(intValue);
        result = new Card(mySuite, myValue, true);
        return result;
    }

    @Override
    protected ICard createNormalCard() {
        ICard result;
        if (13 != noOfKingQueenCards) {
            int intValue = this.rng.nextInt(13 - 2) + 1;
            CardValue myValue = CardValue.getCardValueFromInt(intValue);
            intValue = this.rng.nextInt(4 - 1 + 1) + 1;
            CardSuite mySuite = CardSuite.getSuiteFromInt(intValue);
            result = new Card(mySuite, myValue, true);
        } else {
            result = createSpecialCard();
        }
        return result;
    }

    @Override
    public void reset() {
        //No reset action needed
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
        return "createCustomisableInfiniteDeckAlgorithm";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[4];
        //double prefKing, double prefQueen, int noKQCards, int maxDrawsBetween
        result[0] = this.prefForKing;
        result[1] = this.prefForQueen;
        result[2] = this.noOfKingQueenCards;
        result[3] = this.intMaxCardDrawsBetweenSpecialCard;
        return result;
    }

}
