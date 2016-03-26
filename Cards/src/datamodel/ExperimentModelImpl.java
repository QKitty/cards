/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectSetImpl;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IExperimentModel;
import datamodel.persistance.ExperimentFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExperimentModelImpl implements IExperimentModel {

//<editor-fold defaultstate="collapsed" desc="Attributes">
    private final List<IDeck> myDecks;
    private boolean experimentComplete;
    private final ISubject observers;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public ExperimentModelImpl() {
        myDecks = new ArrayList<>();
        this.experimentComplete = false;
        this.observers = new ISubjectSetImpl();
    }
    
    public ExperimentModelImpl(List<IDeck> decks) {
        myDecks = new ArrayList<>(decks);
        this.experimentComplete = false;
        this.observers = new ISubjectSetImpl();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IExperimentModel implementation">
    @Override
    public boolean isExperimentComplete() {
        return this.experimentComplete;
    }
    
    @Override
    public void setExperimentComplete(boolean flag) {
        this.experimentComplete = flag;
        this.notifyObservers();
    }
    
    @Override
    public void addDeck(IDeck aDeck) throws NullPointerException {
        if (null != aDeck) {
            if (!this.myDecks.contains(aDeck)) {
                if (this.myDecks.add(aDeck)) {
                    aDeck.registerObserver(this);
                }
            }
            this.notifyObservers();
        } else {
            throw new NullPointerException("Cannot add a NULL deck to the experiment");
        }
    }
    
    @Override
    public void addDecks(List<IDeck> deckList) throws NullPointerException {
        if (null != deckList) {
            for (IDeck currDeck : deckList) {
                if (!this.myDecks.contains(currDeck)) {
                    if (this.myDecks.add(currDeck)) {
                        currDeck.registerObserver(this);
                    }
                }
            }
            this.notifyObservers();
        } else {
            throw new NullPointerException("Cannot add a NULL list of decks to the experiment");
        }
    }
    
    @Override
    public IDeck removeDeck(IDeck aDeck) throws NullPointerException {
        IDeck result = null;
        if (null != aDeck) {
            if (this.myDecks.contains(aDeck)) {
                if (this.myDecks.remove(aDeck)) {
                    result = aDeck;
                    this.notifyObservers();
                }
            }
        } else {
            throw new NullPointerException("Cannot remove a NULL deck from the experiment");
        }
        return result;
    }
    
    @Override
    public IDeck removeDeck(int index) throws IndexOutOfBoundsException {
        IDeck result = null;
        if (index >= 0 && index < this.myDecks.size()) {
            result = this.myDecks.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
        return result;
    }
    
    @Override
    public IDeck getDeck(int index) throws IndexOutOfBoundsException {
        IDeck result = null;
        if (index >= 0 && index < this.myDecks.size()) {
            result = this.myDecks.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
        return result;
    }
    
    @Override
    public List<IDeck> getDecks() {
        return new ArrayList<>(this.myDecks);
    }
    
    @Override
    public void clear() {
        this.myDecks.clear();
    }
    
    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getNoOfDecks() {
        return this.myDecks.size();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Iterable<IDeck> implementation">
    @Override
    public Iterator<IDeck> iterator() {
        return new ArrayList<>(this.myDecks).iterator();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ISubject implementation">
    @Override
    public boolean registerObserver(IObserver io) {
        return this.observers.registerObserver(io);
    }
    
    @Override
    public boolean removeObserver(IObserver io) {
        return this.observers.removeObserver(io);
    }
    
    @Override
    public void notifyObservers() {
        this.observers.notifyObservers();
    }
    
    @Override
    public <T> void notifyObservers(T t) {
        this.observers.notifyObservers(t);
    }
    
    @Override
    public Set<IObserver> removeAllObservers() {
        return this.observers.removeAllObservers();
    }
    
    @Override
    public boolean registerObserver(Collection<? extends IObserver> clctn) {
        return this.observers.registerObserver(clctn);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IXMLPersistable implementation">
    @Override
    public Class<?> getFactoryClass() {
        return ExperimentFactory.class;
    }
    
    @Override
    public String getFactoryMethodName() {
        return "createExperimentModel";
    }
    
    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[1];
        result[0] = this.myDecks;
        return result;
    }
//</editor-fold>

}
