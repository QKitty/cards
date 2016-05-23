/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.exceptions.NotACardDeckFileException;
import java.io.File;

/**
 * This interface represents the abstract concept of a record of a card deck
 * XML file on the computer
 * @author qkitt
 */
public interface IDeckCardFileRecord extends IValidateable {
    
    /**
     * Accessor to retrieve this card decks file
     * @return A File object representing the XML file for this card deck.
     */
    File getCardDeckFile();
    
    /**
     * Accessor to set the file from which the card deck should be loaded
     * @param newCardDeck The File object from which this card deck can be loaded.
     * @throws datamodel.exceptions.NotACardDeckFileException if the file provided
     * cannot be loaded a produce a valid card deck
     */
    void setCardDeckFile(File newCardDeck) throws NotACardDeckFileException;
    
    /**
     * Accessor that defines if this deck should be included in an experiment.
     * @return True if this card deck should be added to an experiment model.
     * False otherwise
     */
    boolean isToBeIncluded();
    
    /**
     * Accessor to set wheter or not this deck should be included in an experiment.
     * @param flag - Boolean True to include this deck or False to exclude it.
     */
    void setToBeIncluded(boolean flag);
    
    /**
     * Retrieves the number of cards in this card deck.
     * @return int being the number of cards in this card deck.
     */
    int getNoOfCardsInDeck();
    
    /**
     * The CardAlgorithmCategory for the card deck
     * @return A CardAlgorithmCategory enumeration
     */
    CardAlgorithmCategory getCardAlgorithmCategory();
    
    /**
     * Accessor to retrieve the IDeck interface to this loaded card deck.
     * @return
     */
    IDeck getCardDeck();
    
}
