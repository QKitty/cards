/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.enums.ParticipantGuess;
import java.io.Serializable;
import java.util.List;

/**
 * This interface represents the abstract concept of a pack of playing cards
 * @author rtucker
 */
public interface IDeck extends Serializable, IDeckAlgorithm {
    
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
    
    /**
     * Accessor to retrieve the ID String for this deck
     * @return A String holding the Id that identifies this deck.
     */
    String getId();
    
    /**
     * Accessor to set the Id string for this deck
     * @param newId - String being the new Id string for this deck.
     */
    void setId(String newId);
    
    /**
     * Accessor to retrieve the number of King cards drawn
     * @return - An integer being the number of king cards that where drawn
     */
    int getNoOfKingsDrawn();
    
    /**
     * Accessor to retrieve the number of Queen cards drawn
     * @return - An integer being the number of queen cards that where drawn
     */
    int getNoOfQueensDrawn();
    
    /**
     * Accessor to retrieve the number of non King or Queen cards drawn
     * @return - An integer being the number of non king or queen cards that where drawn
     */
    int getNoOfOtherCardsDrawn();
    
    /**
     * Retrieves the proportion of Kings vs Queens drawn per the formula
     * K / (K + Q) where K = no of Kings drawn and Q = no of Queens drawn
     * @return double being the result of the formula K / (K + Q)
     */
    double getProportionOfKingsVKingsAndQueensDrawn();
    
    /**
     * Retrieves the proportion of king + queen cards drawn as a fraction of all cards drawn
     * per the formula (K + Q) / (K + Q + O) where K = no of Kings drawn, Q = no of Queens drawn
     * and O = no of non king or queen cards
     * @return double being the result of the formula (K + Q) / (K + Q + O)
     */
    double getProportionOfKingsAndQueensDrawn();
    
    /**
     * Accessor to retrieve the participants guess for this decks type.
     * @return - The CardAlgorithmCategory that the participant has set for this deck
     */
    ParticipantGuess getParticipantsGuess();
    
    /**
     * Accessor to set the participants guess for this decks type.
     * @param aGuess - The ParticipantGuess type the participant guesses is in use.
     */
    void setParticipantsGuess(ParticipantGuess aGuess);
    
    /**
     * Tests if the currently stored participants guess for the algorithm type
     * matches the algorithm in use.
     * @return boolean True if the participant has set a guess value that matches
     * the type of algorithm in use by this deck, False otherwise.
     */
    boolean isParticipantGuessCorrect();
    
    /**
     * Tests if the experiments participant has made a guess as to this decks
     * king / queen preference.
     * @return False if the participants guess for this deck is UNKNOWN, True otherwise
     */
    boolean hasParticipantGuessSet();
    
}
