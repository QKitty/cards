/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.CardDrawnRecord;
import java.time.LocalDateTime;

/**
 * This interface represents the abstract concept of a record of a card being drawn.
 * @author qkitt
 */
public interface ICardDrawnRecord extends Comparable<CardDrawnRecord> {

    @Override
    int compareTo(CardDrawnRecord o);

    /**
     * @return the recordNo
     */
    int getRecordNo();

    /**
     * @return the theCardRevealed
     */
    ICard getTheCardRevealed();

    /**
     * @return the theDeck
     */
    IDeck getTheDeck();

    /**
     * @return the time
     */
    LocalDateTime getTime();

    /**
     * @param recordNo the recordNo to set
     */
    void setRecordNo(int recordNo);
    
}
