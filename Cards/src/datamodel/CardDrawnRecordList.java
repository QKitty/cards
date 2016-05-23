/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.interfaces.ICardDrawnRecord;
import datamodel.interfaces.IDeck;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Concrete implementation of the ICardDrawnRecordList to track each card drawn
 * from each deck.
 * @author qkitt
 */
public class CardDrawnRecordList implements ICardDrawnRecordList{
    
    private final TreeSet<ICardDrawnRecord> cardRecords;
    
    public CardDrawnRecordList(){
        cardRecords = new TreeSet<>();
    }
    
    @Override
    public void reset(){
        cardRecords.clear();
    }
    
    @Override
    public void recordCardDrawn(IDeck deckFromWhichCardWasDrawn){
        if(null != deckFromWhichCardWasDrawn){
            int recordNo = cardRecords.size();
            ICardDrawnRecord newRecord = new CardDrawnRecord(deckFromWhichCardWasDrawn);
            newRecord.setRecordNo(recordNo);
            cardRecords.add(newRecord);
        } else {
            throw new NullPointerException("Cannot record a card being drawn from a NULL deck.");
        }
    }

    @Override
    public Iterator<ICardDrawnRecord> iterator() {
        TreeSet<ICardDrawnRecord> resultSet = new TreeSet<>(cardRecords);
        return resultSet.iterator();
    }
    
}
