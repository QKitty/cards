/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import datamodel.ICardDrawnRecordList;
import java.io.IOException;
import java.util.List;

/**
 * This interface is implemented by any class that can serve as the experiment
 * when running a trial
 * @author rtucker
 */
public interface IExperimentModel extends Iterable<IDeck>, IObserver<Void>, ISubject, IXMLPersistable, IControllable {
    
    /**
     * Accessor to test if experiment has been marked as complete
     * @return boolean True if experiment has been completed. False otherwise.
     */
    boolean isExperimentComplete();
    
    /**
     * Marks the current experiment as complete and saves off the results file.
     * @return A boolean true if the data was save successfully, false otherwise.
     * @throws IllegalStateException - Thrown if the model has not stored a guess
     * for every deck in it.
     * @throws java.io.IOException - Thrown if the experiment data file cannot be saved.
     */
    boolean completeExperiment() throws IllegalStateException, IOException;
    
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
    
    /**
     * Accessor to retrieve the participant in the experiment
     * @return - An IPerson object representing the participant in the experiment
     */
    IPerson getParticipant();
    
    /**
     * Accessor to set the details of the person participating in the experiment
     * @param newParticipant - An IPerson object representing the experiments participant
     * @return - Boolean true if the details where accepted and stored, False otherwise.
     */
    boolean setParticipant(IPerson newParticipant);
    
    /**
     * Test to determine if the stored participant details are valid
     * @return - Boolean true if the current participant details are valid, 
     * False otherwise.
     */
    boolean hasValidParticipant();
    
    /**
     * Accessor to retrieve the ICardDrawnRecordList for this experiment model
     * @return - An ICardDrawnRecordList object containing a record of every card drawn from a deck in order.
     */
    ICardDrawnRecordList getCardDrawnRecordList();
    
    /**
     * Retrieves the total number of cards drawn from all decks in this experiment model
     * @return - integer being the sum of all cards drawn from all decks in the experiment model
     */
    default int getTotalCardsDrawn(){
        int count = 0;
        for(IDeck currDeck : this){
            int cardsDrawn = currDeck.getNoOfKingsDrawn() + currDeck.getNoOfQueensDrawn() + currDeck.getNoOfOtherCardsDrawn();
            count += cardsDrawn;
        }
        return count;
    }
}
