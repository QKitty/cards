/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.interfaces.ICardDrawnRecord;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import java.time.LocalDateTime;

/**
 * This class serves as a record of each card drawn as the experiment proceeds
 * @author qkitt
 */
public class CardDrawnRecord implements ICardDrawnRecord {
    
    private int recordNo;
    private IDeck theDeck;
    private ICard theCardRevealed;
    private LocalDateTime time;
    
    public CardDrawnRecord(IDeck deckDrawnFrom){
        if(null != deckDrawnFrom){
            recordNo = -1;
            theDeck = deckDrawnFrom;
            int index = deckDrawnFrom.getDrawnCardList().size() - 1;
            theCardRevealed = deckDrawnFrom.getDrawnCardList().get(index);
            time = LocalDateTime.now();
        } else {
            throw new NullPointerException("Cannot record a card being drawn from a NULL deck object.");
        }
    }

    /**
     * @return the recordNo
     */
    @Override
    public int getRecordNo() {
        return recordNo;
    }

    /**
     * @param recordNo the recordNo to set
     */
    @Override
    public void setRecordNo(int recordNo) {
        this.recordNo = recordNo;
    }

    /**
     * @return the theDeck
     */
    @Override
    public IDeck getTheDeck() {
        return theDeck;
    }

    /**
     * @return the theCardRevealed
     */
    @Override
    public ICard getTheCardRevealed() {
        return theCardRevealed;
    }

    /**
     * @return the time
     */
    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public int compareTo(CardDrawnRecord o) {
        int result = 0;
        if(null != o){
            LocalDateTime oTime = o.getTime();
            if(!this.time.isEqual(oTime)){
                result = this.time.compareTo(oTime);
            } else {
                int oRecordNo = o.getRecordNo();
                if(this.recordNo != oRecordNo){
                    if(this.recordNo < oRecordNo){
                        result = -1;
                    }else{
                        result = 1;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.recordNo).append(",").append(this.time.toString()).append(",").append(this.theDeck.getId()).append(",").append(this.getCardValueCode()).append("\n");
        return sb.toString();
    }
    
    private String getCardValueCode(){
        String result;
        switch(this.theCardRevealed.getValue()){
            case KING:
                result = "K";
                break;
            case QUEEN:
                result = "Q";
                break;
            default:
                result = "N";
        }
        return result;
    }
    
}
