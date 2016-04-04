/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.IObserver;
import datamodel.enums.DrawnCardsDisplayType;

/**
 * This interface represents the controller for the application
 * @author qkitt
 */
public interface IController extends IObserver<Void> {
    
    /**
     * Accessor to retrieve the current experiment model in use
     * @return The current Experiment model
     */
    IExperimentModel getExpModel();
    
    /**
     * Accessor to retrieve the current participant details
     * @return The IPerson interface to the current participant details
     */
    IPerson getParticipant();
    
    /**
     * Accessor to set the current participants details
     * @param newParticipant - The IPerson interface to the new participant details
     * @return - Boolean True if the details where accepted and stored, False otherwise.
     */
    boolean setParticipant(IPerson newParticipant);
    
    /**
     * Accessor to test if the experiment is in progress
     * @return - Boolean true if an experiment is in progress, False otherwise
     */
    boolean isExperimentRunning();
    
    /**
     * Accessor to set the experiment to a running or stopped state
     * @param flag
     */
    void setExperimentRunning(boolean flag);
    
    /**
     * Start an experiment trial using the current experiment model
     */
    void startExperiment();
    
    /**
     * Accessor to retrieve the type of display to use for the current experiment
     * @return - A DrawnCardsDisplayType defining how a decks drawn card list should
     * be displayed.
     */
    DrawnCardsDisplayType getDrawnCardsDisplayType();
    
    /**
     * Accessor to set the type of display to use for the current experiment
     * @param aType - A DrawnCardsDisplayType defining how a decks drawn card list should
     * be displayed.
     */
    void setDrawnCardsDisplayType(DrawnCardsDisplayType aType);
    
    /**
     * Create debugging decks - DELETE LATER
     */
    void createDebugDecks();
    
}
