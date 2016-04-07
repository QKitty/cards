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
import java.util.List;
import java.util.Random;

/**
 * Randomly generates a deck of playing cards with the specified king and queen
 * probability, total number of cards and specified category. 
 * @author qkitt
 */
public class FiniteRandomlyGeneratedDeckAlgorithm extends BaseDeckAlgorithm {

    private List<ICard> cardsInDeck;
    private double kingProb;
    private double queenProb;
    private CardAlgorithmCategory category;
    private int nextCardIndex;
    private ICard noCard;
    private transient Random randGen;

    public FiniteRandomlyGeneratedDeckAlgorithm() {
        this.cardsInDeck = new ArrayList<>();
        this.nextCardIndex = 0;
        this.randGen = new Random();
    }

    public FiniteRandomlyGeneratedDeckAlgorithm(double kingProb, double queenProb, CardAlgorithmCategory cat, int cardCount) {
        this();
        if (cat.validateForCategory(kingProb, queenProb)) {
            if (0 < cardCount) {
                this.category = cat;
                this.kingProb = kingProb;
                this.queenProb = queenProb;
            } else {
                throw new IllegalArgumentException("No of cards in a fixed deck must be greater than zero (0).");
            }
        } else {
            throw new IllegalArgumentException("Invalid probabilities for king and queen cards.");
        }
        this.generateDeck(cardCount);
    }
    
    public FiniteRandomlyGeneratedDeckAlgorithm(double kingProb, double queenProb, CardAlgorithmCategory cat, List<ICard> cardsInDeck) {
        this();
        if (cat.validateForCategory(kingProb, queenProb)) {
            if (0 < cardsInDeck.size()) {
                this.category = cat;
                this.kingProb = kingProb;
                this.queenProb = queenProb;
            } else {
                throw new IllegalArgumentException("No of cards in a fixed deck must be greater than zero (0).");
            }
        } else {
            throw new IllegalArgumentException("Invalid probabilities for king and queen cards.");
        }
        this.cardsInDeck.addAll(cardsInDeck);
    }

    @Override
    public ICard drawCard() {
        ICard result;
        if(this.hasCardsRemaining()){
            result = this.cardsInDeck.get(this.nextCardIndex);
            this.nextCardIndex++;
        } else {
            if(null == this.noCard){
                this.noCard = new Card(CardSuite.HEARTS, CardValue.NOCARD);
            }
            result = this.noCard;
        }
        return result;
    }

    @Override
    public boolean hasCardsRemaining() {
        boolean result = false;
        if (this.nextCardIndex < this.cardsInDeck.size()) {
            result = true;
        }
        return result;
    }

    @Override
    public int getNoOfRemainingCards() {
        return this.cardsInDeck.size() - this.nextCardIndex;
    }

    @Override
    public DeckType getDeckType() {
        return DeckType.FIXED_DECK;
    }

    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        return this.category;
    }

    @Override
    protected ICard createSpecialCard() {
        ICard result;
        double random = Math.random();
        CardValue value = CardValue.KING;
        if (random < 0.5d) {
            value = CardValue.QUEEN;
        }
        random = Math.random();
        CardSuite suite = CardSuite.CLUBS;
        if (random < 0.25d) {
            suite = CardSuite.DIAMONDS;
        } else if (random < 0.5d) {
            suite = CardSuite.HEARTS;
        } else if (random < 0.75d) {
            suite = CardSuite.SPADES;
        }
        result = new Card(suite, value);
        return result;
    }

    @Override
    protected ICard createNormalCard() {
        ICard result;
        double random = Math.random();
        CardSuite suite = CardSuite.CLUBS;
        if (random < 0.25d) {
            suite = CardSuite.DIAMONDS;
        } else if (random < 0.5d) {
            suite = CardSuite.HEARTS;
        } else if (random < 0.75d) {
            suite = CardSuite.SPADES;
        }
        int intVal = randGen.nextInt(11) + 1;
        CardValue value = CardValue.getCardValueFromInt(intVal);
        result = new Card(suite, value);
        return result;
    }

    @Override
    public boolean isAlgorithmic() {
        return false;
    }

    @Override
    public Class<?> getFactoryClass() {
        return DeckAlgorithmFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createFiniteRandomlyGeneratedDeckAlgorithm";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[4];
        result[0] = this.kingProb;
        result[1] = this.queenProb;
        result[2] = this.category;
        result[3] = this.cardsInDeck;
        return result;
    }

    @Override
    public void reset() {
        this.nextCardIndex = 0;
    }

    private void generateDeck(int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            ICard currCard = null;
            double random = Math.random();
            if (random <= this.kingProb) {
                //Generate a King card
                currCard = this.createSpecialCard(CardValue.KING);
            } else if (random <= (this.kingProb + this.queenProb)) {
                //Generate a Queen card
                currCard = this.createSpecialCard(CardValue.QUEEN);
            } else {
                //Generate a normal card
                currCard = this.createNormalCard();
            }
            this.cardsInDeck.add(currCard);
        }
    }
    
    private ICard createSpecialCard(CardValue aValue){
        ICard result;
        if(aValue != CardValue.KING && aValue != CardValue.QUEEN){
            throw new IllegalArgumentException("A Card of value " + aValue.toString() + " is not a special (King / Queen) card.");
        }
        double random = Math.random();
        CardSuite suite = CardSuite.CLUBS;
        if (random < 0.25d) {
            suite = CardSuite.DIAMONDS;
        } else if (random < 0.5d) {
            suite = CardSuite.HEARTS;
        } else if (random < 0.75d) {
            suite = CardSuite.SPADES;
        }
        result = new Card(suite, aValue);
        return result;
    }

}
