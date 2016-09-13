/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.gmail.qkitty6.patterns.observer.SubjectSetBaseClass;
import datamodel.BaseDeckImpl;
import datamodel.deckalgorithms.FiniteRandomlyGeneratedDeckAlgorithm;
import datamodel.enums.CardAlgorithmCategory;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IValidateable;
import datamodel.persistance.IXMLPersistablePersistanceDelegate;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeListenerProxy;
import java.beans.VetoableChangeSupport;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.IOException;

/**
 * This class is a data model for the experiment creation dialog
 *
 * @author qkitt
 */
public class DeckCreationDialogModel extends SubjectSetBaseClass implements IValidateable {

//<editor-fold defaultstate="collapsed" desc="Property Setup">
    private double PValue;

    public static final String PROP_PVALUE = "PValue";

    /**
     * Get the value of PValue
     *
     * @return the value of PValue
     */
    public double getPValue() {
        return PValue;
    }

    /**
     * Set the value of PValue
     *
     * @param PValue new value of PValue
     * @throws java.beans.PropertyVetoException
     */
    public void setPValue(double PValue) throws PropertyVetoException {
        double oldPValue = this.PValue;
        vetoableChangeSupport.fireVetoableChange(PROP_PVALUE, oldPValue, PValue);
        this.PValue = PValue;
        propertyChangeSupport.firePropertyChange(PROP_PVALUE, oldPValue, PValue);
        this.notifyObservers();
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public final void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private transient final VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    /**
     * Add VetoableChangeListener.
     *
     * @param listener
     */
    public final void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    /**
     * Remove VetoableChangeListener.
     *
     * @param listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    private double RValue;

    public static final String PROP_RVALUE = "RValue";

    /**
     * Get the value of RValue
     *
     * @return the value of RValue
     */
    public double getRValue() {
        return RValue;
    }

    /**
     * Set the value of RValue
     *
     * @param RValue new value of RValue
     * @throws java.beans.PropertyVetoException
     */
    public void setRValue(double RValue) throws PropertyVetoException {
        double oldRValue = this.RValue;
        vetoableChangeSupport.fireVetoableChange(PROP_RVALUE, oldRValue, RValue);
        this.RValue = RValue;
        propertyChangeSupport.firePropertyChange(PROP_RVALUE, oldRValue, RValue);
        this.notifyObservers();
    }

    private double ratio;

    public static final String PROP_RATIO = "ratio";

    /**
     * Get the value of ratio
     *
     * @return the value of ratio
     */
    public double getRatio() {
        return ratio;
    }

    /**
     * Set the value of ratio
     *
     * @param ratio new value of ratio
     * @throws java.beans.PropertyVetoException
     */
    public void setRatio(double ratio) throws PropertyVetoException {
        double oldRatio = this.ratio;
        vetoableChangeSupport.fireVetoableChange(PROP_RATIO, oldRatio, ratio);
        this.ratio = ratio;
        propertyChangeSupport.firePropertyChange(PROP_RATIO, oldRatio, ratio);
        this.notifyObservers();
    }

    private boolean usingRatio;

    public static final String PROP_USINGRATIO = "usingRatio";

    /**
     * Get the value of usingRatio
     *
     * @return the value of usingRatio
     */
    public boolean isUsingRatio() {
        return usingRatio;
    }

    /**
     * Set the value of usingRatio
     *
     * @param usingRatio new value of usingRatio
     * @throws java.beans.PropertyVetoException
     */
    public void setUsingRatio(boolean usingRatio) throws PropertyVetoException {
        boolean oldUsingRatio = this.usingRatio;
        vetoableChangeSupport.fireVetoableChange(PROP_USINGRATIO, oldUsingRatio, usingRatio);
        this.usingRatio = usingRatio;
        propertyChangeSupport.firePropertyChange(PROP_USINGRATIO, oldUsingRatio, usingRatio);
        this.notifyObservers();
    }

    private File destinationFolder;

    public static final String PROP_DESTINATIONFOLDER = "destinationFolder";

    /**
     * Get the value of destinationFolder
     *
     * @return the value of destinationFolder
     */
    public File getDestinationFolder() {
        return destinationFolder;
    }

    /**
     * Set the value of destinationFolder
     *
     * @param destinationFolder new value of destinationFolder
     * @throws java.beans.PropertyVetoException
     */
    public void setDestinationFolder(File destinationFolder) throws PropertyVetoException {
        File oldDestinationFolder = this.destinationFolder;
        vetoableChangeSupport.fireVetoableChange(PROP_DESTINATIONFOLDER, oldDestinationFolder, destinationFolder);
        this.destinationFolder = destinationFolder;
        propertyChangeSupport.firePropertyChange(PROP_DESTINATIONFOLDER, oldDestinationFolder, destinationFolder);
        this.notifyObservers();
    }

    private int numberOfCards;

    public static final String PROP_NUMBEROFCARDS = "numberOfCards";

    /**
     * Get the value of numberOfCards
     *
     * @return the value of numberOfCards
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Set the value of numberOfCards
     *
     * @param numberOfCards new value of numberOfCards
     * @throws java.beans.PropertyVetoException
     */
    public void setNumberOfCards(int numberOfCards) throws PropertyVetoException {
        int oldNumberOfCards = this.numberOfCards;
        vetoableChangeSupport.fireVetoableChange(PROP_NUMBEROFCARDS, oldNumberOfCards, numberOfCards);
        this.numberOfCards = numberOfCards;
        propertyChangeSupport.firePropertyChange(PROP_NUMBEROFCARDS, oldNumberOfCards, numberOfCards);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Constructor">
    public DeckCreationDialogModel() {
        this.PValue = 0.4d;
        this.RValue = 0.2d;
        this.ratio = this.RValue / this.PValue;
        this.usingRatio = true;
        this.numberOfCards = 500;
        String destFolder = DeckCreationDialogModel.getDefaultDeckDirectory().getAbsolutePath();
        this.destinationFolder = new File(destFolder);
        VetoableChangeListenerProxy vetoProxy = new VetoableChangeListenerProxy(PROP_RVALUE, new UsingRatioVeto());
        this.addVetoableChangeListener(vetoProxy);
        VetoableChangeListener constraint = new ZeroToOneConstraint();
        vetoProxy = new VetoableChangeListenerProxy(PROP_PVALUE, constraint);
        this.addVetoableChangeListener(vetoProxy);
        vetoProxy = new VetoableChangeListenerProxy(PROP_RVALUE, constraint);
        this.addVetoableChangeListener(vetoProxy);
        constraint = new PositiveNonZeroIntegerConstraint();
        vetoProxy = new VetoableChangeListenerProxy(PROP_NUMBEROFCARDS, constraint);
        this.addVetoableChangeListener(vetoProxy);
        this.addPropertyChangeListener(new PValueChanged());
    }
//</editor-fold>

    public boolean createCardDecks(File dest) throws IOException {
        boolean result = false;
        if (null != dest && this.isInValidState()) {
            if (!dest.exists()) {
                if (!dest.mkdirs() || !dest.exists()) {
                    throw new IOException("Cannot create destination directory for create card decks!");
                }
            }
            IDeck kingHighDec, kingLowDeck, queenHighDeck, queenLowDeck, currDeck;
            kingHighDec = new BaseDeckImpl(new FiniteRandomlyGeneratedDeckAlgorithm(this.PValue, this.RValue, CardAlgorithmCategory.KING_HI_INFORMATIVE, this.numberOfCards));
            kingLowDeck = new BaseDeckImpl(new FiniteRandomlyGeneratedDeckAlgorithm(this.RValue, this.RValue * this.ratio, CardAlgorithmCategory.KING_LOW_INFORMATIVE, this.numberOfCards));
            queenHighDeck = new BaseDeckImpl(new FiniteRandomlyGeneratedDeckAlgorithm(this.RValue, this.PValue, CardAlgorithmCategory.QUEEN_HI_INFORMATIVE, this.numberOfCards));
            queenLowDeck = new BaseDeckImpl(new FiniteRandomlyGeneratedDeckAlgorithm(this.RValue * this.ratio, this.RValue, CardAlgorithmCategory.QUEEN_LOW_INFORMATIVE, this.numberOfCards));

            //Persist the generated decks to XML files
            String fileName, filePath;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        currDeck = kingHighDec;
                        fileName = "KingHiDeck";
                        filePath = dest.getAbsolutePath() + File.separator + fileName + ".xml";
                        break;
                    case 1:
                        currDeck = kingLowDeck;
                        fileName = "KingLowDeck";
                        filePath = dest.getAbsolutePath() + File.separator + fileName + ".xml";
                        break;
                    case 2:
                        currDeck = queenHighDeck;
                        fileName = "QueenHiDeck";
                        filePath = dest.getAbsolutePath() + File.separator + fileName + ".xml";
                        break;
                    case 3:
                        currDeck = queenLowDeck;
                        fileName = "QueenLowDeck";
                        filePath = dest.getAbsolutePath() + File.separator + fileName + ".xml";
                        break;
                    default:
                        throw new IOException("Could not generate file path!");
                }
                File file = new File(filePath);
                try (XMLEncoder enc = currDeck.createXMLEncoder(file)) {
                    enc.setPersistenceDelegate(currDeck.getClass(), new IXMLPersistablePersistanceDelegate());
                    enc.writeObject(currDeck);
                }
            }
            result = true;
        }
        return result;
    }

//<editor-fold defaultstate="collapsed" desc="Private methods">
    private void recalculateRValue() {
        double oldRValue = this.RValue;
        this.RValue = this.PValue * this.ratio;
        propertyChangeSupport.firePropertyChange(PROP_RVALUE, oldRValue, this.RValue);
        this.notifyObservers();
    }

    private void recalculateRatio() {
        double oldRatio = this.ratio;
        this.ratio = this.RValue / this.PValue;
        propertyChangeSupport.firePropertyChange(PROP_RATIO, oldRatio, this.ratio);
        this.notifyObservers();
    }
//</editor-fold>

    @Override
    public boolean isInValidState() {
        boolean result = false;
        if (this.PValue >= this.RValue) {
            if (this.ratio == (this.RValue / this.PValue)) {
                result = true;
            }
        }
        return result;
    }
    
    public static File getDefaultDeckDirectory(){
        File result = new File(System.getProperty("user.home") + File.separator + "GeneratedDecks");
        if(!result.exists()){
            result.mkdir();
        }
        return result;
    }

//<editor-fold defaultstate="collapsed" desc="Inner Classes YUK!">
    private class UsingRatioVeto implements VetoableChangeListener {

        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            if (isUsingRatio()) {
                String propertyName = evt.getPropertyName();
                String msg = "Cannot change the " + propertyName + " property directly when using a ratio between the high and low probability values.";
                throw new PropertyVetoException(msg, evt);
            }
        }
    }

    private class ZeroToOneConstraint implements VetoableChangeListener {

        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            double newValue = (double) evt.getNewValue();
            if (newValue < 0.0d || newValue > 1.0d) {
                String propertyName = evt.getPropertyName();
                String msg = "The property " + propertyName + " is constrained to the range 0.0 to 1.0. Cannot set a value of " + newValue + ".";
                throw new PropertyVetoException(msg, evt);
            }
        }
    }

    private class PositiveNonZeroIntegerConstraint implements VetoableChangeListener {

        @Override
        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
            int newValue = (int) evt.getNewValue();
            if (newValue <= 0) {
                String propertyName = evt.getPropertyName();
                String msg = "The property " + propertyName + " is constrained to the range 1 to infinity. Cannot set a value of " + newValue + ".";
                throw new PropertyVetoException(msg, evt);
            }
        }
    }

    private class PValueChanged implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (isUsingRatio()) {
                recalculateRValue();
            } else {
                recalculateRatio();
            }
        }
    }
//</editor-fold>

}
