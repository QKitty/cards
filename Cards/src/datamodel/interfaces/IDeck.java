/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.ISubject;
import datamodel.exceptions.NotAnAlgorithmicDeckException;
import java.util.List;

/**
 * This interface represents the abstract concept of a pack of playing cards
 * @author rtucker
 */
public interface IDeck extends IDeckAlgorithm, ISubject {
    
    /**
     * Accessor to test if this deck uses an algorithm to generate its cards
     * @return boolean True if this deck accepts IDeckAlgorithm objects as a
     * means of generating cards. False otherwise.
     */
    boolean isAlgorithmic();
    
    /**
     * Retrieves the current algorithm in use by the deck to create its cards
     * @return - An IDeckAlgorithm interface to the algorithm invoked when a 
     * card is drawn from the deck.
     * @throws NotAnAlgorithmicDeckException - Thrown if this deck does not 
     * support the use of algorithms to generate cards.
     */
    IDeckAlgorithm getDeckAlgorithm() throws NotAnAlgorithmicDeckException;
    
    /**
     * Accessor method to set the algorithm currently in use by the deck for 
     * generating cards when drawCard() is invoked.
     * @param alg - The new algorithm object to use
     * @return boolean True if the provided algorithm was accepted and will be
     * used in future to create cards when drawCard() is invoked.
     * @throws NotAnAlgorithmicDeckException - Thrown if this deck does not 
     * support the use of algorithms to generate cards.
     */
    boolean setDeckAlgorithm(IDeckAlgorithm alg) throws NotAnAlgorithmicDeckException;
    
    /**
     * Retrieves a list of all the cards that have been drawn from this deck so far.
     * @return A list of drawn cards
     */
    List<ICard> getDrawnCardList();
    
}
