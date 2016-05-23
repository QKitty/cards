/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.interfaces.ICardDrawnRecord;
import datamodel.interfaces.IDeck;
import java.util.Iterator;

/**
 * This interface represents the abstract concept of a list of card draws from 
 * the decks in an experiment
 * @author qkitt
 */
public interface ICardDrawnRecordList extends Iterable<ICardDrawnRecord> {

    @Override
    Iterator<ICardDrawnRecord> iterator();

    /**
     * Records that the last card was drawn from the given card deck
     * @param deckFromWhichCardWasDrawn - The card deck from which a card has 
     * just been drawn by the user.
     */
    void recordCardDrawn(IDeck deckFromWhichCardWasDrawn);

    /**
     * Empties the list of card drawn records and resets the record count to zero
     */
    void reset();
    
}
