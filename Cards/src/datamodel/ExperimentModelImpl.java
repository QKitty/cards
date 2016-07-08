/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectSetImpl;
import datamodel.interfaces.IController;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IExperimentModel;
import datamodel.interfaces.IPerson;
import datamodel.interfaces.IValidateable;
import datamodel.people.Participant;
import datamodel.persistance.ExperimentFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ExperimentModelImpl implements IExperimentModel {

//<editor-fold defaultstate="collapsed" desc="Attributes">
    private final List<IDeck> myDecks;
    private boolean experimentComplete;
    private final ISubject observers;
    private IPerson participant;
    private final ICardDrawnRecordList cardRecordList;
    private IController controller;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public ExperimentModelImpl() {
        myDecks = new ArrayList<>();
        this.experimentComplete = false;
        this.participant = new Participant();
        this.cardRecordList = new CardDrawnRecordList();
        this.observers = new ISubjectSetImpl();
    }

    public ExperimentModelImpl(IPerson aParticipant) {
        myDecks = new ArrayList<>();
        this.experimentComplete = false;
        this.participant = aParticipant;
        this.cardRecordList = new CardDrawnRecordList();
        this.observers = new ISubjectSetImpl();
    }

    public ExperimentModelImpl(List<IDeck> decks) {
        myDecks = new ArrayList<>(decks);
        this.experimentComplete = false;
        this.participant = new Participant();
        this.cardRecordList = new CardDrawnRecordList();
        this.observers = new ISubjectSetImpl();
    }

    public ExperimentModelImpl(IPerson aParticipant, List<IDeck> decks) {
        myDecks = new ArrayList<>(decks);
        this.experimentComplete = false;
        this.participant = aParticipant;
        this.cardRecordList = new CardDrawnRecordList();
        this.observers = new ISubjectSetImpl();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IExperimentModel implementation">
    @Override
    public boolean isExperimentComplete() {
        return this.experimentComplete;
    }

    @Override
    public boolean completeExperiment() throws IllegalStateException, IOException {
        boolean result = false;
        if (inValidStateToComplete()) {
            this.experimentComplete = true;
            result = saveExperiment();
        } else {
            throw new IllegalStateException("The experiment cannot be completed at this time. Make a guess for every deck.");
        }
        return result;
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
            if (null != result) {
                this.notifyObservers();
            }
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
        this.notifyObservers();
    }

    @Override
    public void reset() {
        myDecks.clear();
        cardRecordList.reset();
        this.experimentComplete = false;
        this.notifyObservers();
    }

    @Override
    public int getNoOfDecks() {
        return this.myDecks.size();
    }

    @Override
    public IPerson getParticipant() {
        return this.participant;
    }

    @Override
    public boolean setParticipant(IPerson newParticipant) {
        boolean result = false;
        if (null != newParticipant) {
            if (null != this.participant) {
                this.participant.removeObserver(this);
            }
            this.participant = newParticipant;
            this.participant.registerObserver(this);
            result = true;
            this.notifyObservers();
        }
        return result;
    }

    @Override
    public boolean hasValidParticipant() {
        boolean result = false;
        if (null != this.participant) {
            if (this.participant instanceof IValidateable) {
                IValidateable person = (IValidateable) this.participant;
                result = person.isInValidState();
            }
        }
        return result;
    }

    @Override
    public ICardDrawnRecordList getCardDrawnRecordList() {
        return this.cardRecordList;
    }

    @Override
    public IController getController() {
        return this.controller;
    }

    @Override
    public boolean setController(IController aController) {
        boolean result = false;
        if (null != aController) {
            this.controller = aController;
            result = true;
        }
        return result;
    }

    @Override
    public void scrambleDeckOrder() {
        if (this.myDecks.size() > 1) {
            int timesToRepeat = 2 * this.myDecks.size();
            if (timesToRepeat < 20) {
                timesToRepeat = 20;
            }
            Random randGen = new Random();
            IDeck deck1, deck2;
            int index1, index2;
            int count = this.myDecks.size();
            for (int i = 0; i < timesToRepeat; i++) {
                index1 = randGen.nextInt(count);
                index2 = randGen.nextInt(count);
                if(index1 != index2){
                    deck1 = this.myDecks.get(index1);
                    deck2 = this.myDecks.get(index2);
                    this.myDecks.set(index2, deck1);
                    this.myDecks.set(index1, deck2);
                }
            }
        }
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

//<editor-fold defaultstate="collapsed" desc="IObserver implementation">
    @Override
    public void update() {
        this.notifyObservers();
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

//<editor-fold defaultstate="collapsed" desc="Private helper methods">
    private boolean saveExperiment() throws IOException {
        boolean result = false;
        String fileName = "/ExperimentResults";
        File dest = new File(System.getProperty("user.dir") + fileName + ".csv");
        int count = 0;
        while (dest.exists()) {
            dest = new File(System.getProperty("user.dir") + fileName + count + ".csv");
            count++;
        }
        result = ExperimentResultSaver.saveExperimentResult(this, dest);
        return result;
    }

    private boolean inValidStateToComplete() {
        boolean result = false;
        List<Boolean> tests = new ArrayList<>();
        for (IDeck currDeck : this) {
            tests.add(currDeck.hasParticipantGuessSet());
        }
        int count = 0;
        count = tests.stream().filter((currVal) -> (currVal)).map((_item) -> 1).reduce(count, Integer::sum);
        if (count == tests.size()) {
            result = true;
        }
        return result;
    }
//</editor-fold>

}
