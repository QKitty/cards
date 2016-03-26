/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import java.util.List;

/**
 * This interface is implemented by any class that can serve as the experiment
 * when running a trial
 * @author rtucker
 */
public interface IExperimentModel extends Iterable<IDeck>, IObserver<Object>, ISubject, IXMLPersistable {
    
    /**
     * Accessor to test if experiment has been marked as complete
     * @return boolean True if experiment has been completed. False otherwise.
     */
    boolean isExperimentComplete();
    
    /**
     * Accessor to set the current status of the experiments complete / incomplete
     * flag.
     * @param flag
     */
    void setExperimentComplete(boolean flag);
    
    /**
     * Add a new deck to the experiment
     * @param aDeck - The IDeck object to add
     * @throws NullPointerException - Thrown if aDeck = null
     */
    void addDeck(IDeck aDeck) throws NullPointerException;
    
    /**
     * Add a list of decks to the experiment. Any null values in the list will
     * be ignored.
     * @param deckList
     * @throws NullPointerException - Thrown if deckList = null
     */
    void addDecks(List<IDeck> deckList) throws NullPointerException;
    
    /**
     * Removes the specified deck from the experiment
     * @param aDeck - The deck to remove from the experiment
     * @return The IDeck object that was removed.
     * @throws NullPointerException - Thrown if aDeck = null
     */
    IDeck removeDeck(IDeck aDeck) throws NullPointerException;
    
    /**
     * Removes the deck at the specified index from the experiment
     * @param index - index of the deck to remove
     * @return - The deck removed from the experiment
     * @throws IndexOutOfBoundsException - Thrown if index is negative or equal 
     * to or greater than the number of decks in the experiment
     */
    IDeck removeDeck(int index) throws IndexOutOfBoundsException;
    
    /**
     * Retrieves the IDeck object at the given index in the experiment
     * @param index - Index of the IDeck object to retrieve
     * @return - The deck at the specified index
     * @throws IndexOutOfBoundsException - Thrown if index is negative or equal 
     * to or greater than the number of decks in the experiment
     */
    IDeck getDeck(int index) throws IndexOutOfBoundsException;
    
    /**
     * Retrieves a list of the IDeck objects in this experiment
     * @return - A list of the IDeck objects in this experiment (may be empty)
     */
    List<IDeck> getDecks();
    
    /**
     * Removes all IDeck objects from this experiment
     */
    void clear();
    
    /**
     * Resets the experiment. All decks will no cards drawn after this method
     * completes.
     */
    void reset();
    
    /**
     * Retrieves the number of IDeck objects in this experiment.
     * @return - int being the number of IDeck objects in this experiment.
     */
    int getNoOfDecks();
}
