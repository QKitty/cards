/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

/**
 * This interface represents the abstract concept of a class that can store an
 * IDeck object.
 * @author qkitt
 */
public interface IDeckManager {
    
    /**
     * Accessor to retrieve the currently stored card deck.
     * @return - IDeck interface to the currently stored card deck.
     */
    IDeck getCardDeck();
    
    /**
     * Accessor to set the currently stored deck
     * @param aDeck - IDeck interface to the new card deck to store.
     */
    void setCardDeck(IDeck aDeck);
    
}
