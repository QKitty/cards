/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;

/**
 * Classes that provide an algorithm for generating or removing a card from a
 * deck should implement this interface
 * @author rtucker
 */
public interface IDeckAlgorithm {
    
    /**
     * Accessor to retrieve the number of cards drawn from the deck since the
     * last face card was drawn
     * @return int being the number of non face cards drawn since the last
     * face card (0 if the last card drawn was a face card)
     */
    public int timeSinceLastFaceCard();
    
    /**
     * Draws a card from the deck of cards.
     * @return The ICard interface to the card object drawn from the deck or
     * NULL if the deck has no further cards in it
     */
    public ICard drawCard();
    
    /**
     * Accessor to test if it is possible to draw another card from this deck
     * @return boolean True if a call to drawCard() will return a card object.
     * False if drawCard() will return NULL
     */
    public boolean hasCardsRemaining();
    
    /**
     * Retrieves the number of cards left in this deck. In the case of a deck
     * with an infinite supply of cards this method should return -1.
     * @return
     */
    public int getNoOfRemainingCards();
    
    /**
     * Accessor to retrieve the type of this deck
     * @return A DeckType enumeration
     */
    DeckType getDeckType();
    
    /**
     * Accessor to retrieve the algorithms category enumeration
     * @return A CardAlgorithmCategory that describes the algorithm
     */
    CardAlgorithmCategory getAlgorithmCategory();
    
}
