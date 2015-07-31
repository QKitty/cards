/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.enums;

/**
 * Enumeration of available deck types
 * @author rtucker
 */
public enum DeckType {
    
    /**
     * This type of deck has an infinite number of playing cards generated using
     * an algorithm. It is always possible to draw another card from this deck
     * but their is no guarantee that the algorithm creating the cards is 'fair'
     */
    INFINITE_DECK, 

    /**
     * This type of deck has a fixed number of playing cards. After they have 
     * all been drawn no more cards can be generated. Their is no guarantee that
     * the deck is 'fair' (aka a normal deck of 52 cards)
     */
    FIXED_DECK;
    
}
