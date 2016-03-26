/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.deckalgorithms;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.enums.DeckType;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IKnownFiniteDeckAlgorithm;
import datamodel.persistance.DeckAlgorithmFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author qkitt
 */
public class FiniteKnownDeckAlgorithm extends BaseDeckAlgorithm implements IKnownFiniteDeckAlgorithm {
    
    private final List<ICard> cardsInDeck;
    
    public FiniteKnownDeckAlgorithm(){
        this.cardsInDeck = new ArrayList<>();
    }
    
    public FiniteKnownDeckAlgorithm(Collection<? extends ICard> c){
        this.cardsInDeck = new ArrayList<>(c);
    }

    @Override
    public ICard drawCard() {
        ICard result = null;
        if(this.hasCardsRemaining()){
            result = this.cardsInDeck.remove(0);
        }
        return result;
    }

    @Override
    public boolean hasCardsRemaining() {
        boolean result = false;
        if(0 < this.cardsInDeck.size()){
            result = true;
        }
        return result;
    }

    @Override
    public int getNoOfRemainingCards() {
        return this.cardsInDeck.size();
    }

    @Override
    public DeckType getDeckType() {
        return DeckType.FIXED_DECK;
    }

    @Override
    public CardAlgorithmCategory getAlgorithmCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ICard createSpecialCard() {
        //Fixed Deck creating cards is not supported
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ICard createNormalCard() {
        //Fixed Deck creating cards is not supported
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        this.cardsInDeck.clear();
    }

    @Override
    public boolean isAlgorithmic() {
        return false;
    }

    @Override
    public boolean add(ICard aCard) {
        return this.cardsInDeck.add(aCard);
    }

    @Override
    public void add(int index, ICard element) {
        this.cardsInDeck.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends ICard> c) {
        return this.cardsInDeck.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends ICard> c) {
        return this.cardsInDeck.addAll(index, c);
    }

    @Override
    public void clear() {
        this.cardsInDeck.clear();
    }

    @Override
    public ICard remove(int index) {
        return this.cardsInDeck.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        boolean result = false;
        if(o instanceof ICard){
            result = this.cardsInDeck.remove((ICard)o);
        }
        return result;
    }

    @Override
    public Class<?> getFactoryClass() {
        return DeckAlgorithmFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createFiniteKnownDeckAlgorithm";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[1];
        result[0] = this.cardsInDeck;
        return result;
    }
    
}
