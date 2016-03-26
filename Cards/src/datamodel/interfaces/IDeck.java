/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.ISubject;
import java.io.Serializable;
import java.util.List;

/**
 * This interface represents the abstract concept of a pack of playing cards
 * @author rtucker
 */
public interface IDeck extends Serializable, IDeckAlgorithm, ISubject {
    
    /**
     * Retrieves the current algorithm in use by the deck to create its cards
     * @return - An IDeckAlgorithm interface to the algorithm invoked when a 
     * card is drawn from the deck.
     */
    IDeckAlgorithm getDeckAlgorithm();
    
    /**
     * Accessor method to set the algorithm currently in use by the deck for 
     * generating cards when drawCard() is invoked.
     * @param alg - The new algorithm object to use
     * @return boolean True if the provided algorithm was accepted and will be
     * used in future to create cards when drawCard() is invoked.
     */
    boolean setDeckAlgorithm(IDeckAlgorithm alg);
    
    /**
     * Retrieves a list of all the cards that have been drawn from this deck so far.
     * @return A list of drawn cards
     */
    List<ICard> getDrawnCardList();
    
}
