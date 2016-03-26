/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import java.io.Serializable;

/**
 * Classes that provide an algorithm for generating or removing a card from a
 * deck should implement this interface
 * @author rtucker
 */
public interface IDeckAlgorithm extends Serializable, IXMLPersistable {
    
    /**
     * Accessor to retrieve the number of cards drawn from the deck since the
     * last face card was drawn
     * @return int being the number of non face cards drawn since the last
     * face card (0 if the last card drawn was a face card)
     */
    public int cardsDrawnSinceLastSpecialCard();
    
    /**
     * Accessor to retrieve the maximum number of cards that can be drawn
     * randomly before a "special" card is drawn
     * @return - int being the maximum number of non-special cards that can 
     * be drawn before a special card appears. A zero (0) means that cards
     * are always drawn without ensuring that a special card appears
     */
    public int getMaxCardDrawsBetweenSpecialCards();
    
    /**
     * Accessor to set the maximum number of cards that can be drawn
     * randomly before a "special" card is drawn
     * @param max - being the maximum number of non-special cards that can 
     * be drawn before a special card appears.
     */
    public void setMaxCardDrawsBetweenSpecialCards(int max) throws IllegalArgumentException;
    
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
    
    /**
     * Accessor to retrieve the probability of seeing a "Special face card" in
     * normal use these are Kings or Queens only (not other face cards)
     * @return double being a value between 0.0 and 1.0 representing the 
     * probability that on the next random card draw one of the special
     * face cards will be produced
     */
    double getProbabilityOfSpecialCard();
    
    /**
     * Accessor method to set the probability of drawing a special card
     * @param probability - double between 0.0 and 1.0 being the probability
     * of drawing a special card when a random card is drawn
     */
    void setProbabilityOfSpecialCard(double probability) throws IllegalArgumentException;
    
    /**
     * Resets the card deck and card deck algorithm so no cards have been drawn
     * and the deck can be re-used.
     */
    void reset();
    
    /**
     * Accessor to test if this deck uses an algorithm to generate its cards
     * @return boolean True if this deck accepts IDeckAlgorithm objects as a
     * means of generating cards. False otherwise.
     */
    boolean isAlgorithmic();
    
}
