/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import datamodel.exceptions.NotAnAlgorithmicDeckException;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;

/**
 * Concrete implementation of the IDeck interface. Represents a pack of playing
 * cards in the system.
 *
 * @author rtucker
 */
public class BaseDeckImpl implements IDeck {

    // <editor-fold defaultstate="collapsed" desc="Class Attributes">
    protected IDeckAlgorithm cardAlgorithm;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    protected BaseDeckImpl() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IDeckAlgorithm interface implementation">
    @Override
    public int timeSinceLastFaceCard() {
        return this.cardAlgorithm.timeSinceLastFaceCard();
    }

    @Override
    public ICard drawCard(){
        return this.cardAlgorithm.drawCard();
    }

    @Override
    public boolean hasCardsRemaining(){
        return this.cardAlgorithm.hasCardsRemaining();
    }

    @Override
    public int getNoOfRemainingCards(){
        return this.cardAlgorithm.getNoOfRemainingCards();
    }
    
    @Override
    public DeckType getDeckType() {
        return this.cardAlgorithm.getDeckType();
    }

    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        return this.cardAlgorithm.getAlgorithmCategory();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IDeck interface implementation">
    @Override
    public final boolean isAlgorithmic() {
        boolean result = false;
        if (null != this.cardAlgorithm) {
            result = true;
        }
        return result;
    }

    @Override
    public final IDeckAlgorithm getDeckAlgorithm() throws NotAnAlgorithmicDeckException {
        IDeckAlgorithm result = null;
        if (this.isAlgorithmic()) {
            result = this.cardAlgorithm;
        } else {
            throw new NotAnAlgorithmicDeckException("Cannot call getDeckAlgorithm() on a deck that does not support algorithmic card generation");
        }
        return result;
    }

    @Override
    public final boolean setDeckAlgorithm(IDeckAlgorithm alg) throws NotAnAlgorithmicDeckException {
        boolean result = false;
        if (null != alg) {
            if (this.isAlgorithmic()) {
                this.cardAlgorithm = alg;
                result = true;
            } else {
                throw new NotAnAlgorithmicDeckException("Cannot call setDeckAlgorithm() on a deck that does not support algorithmic card generation");
            }
        }
        return result;
    }
    // </editor-fold>

    

}
