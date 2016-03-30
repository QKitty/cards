/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.IObserver;

/**
 * This interface represents the controller for the application
 * @author qkitt
 */
public interface IController extends IObserver<Void> {
    
    IExperimentModel getExpModel();
    
    IPerson getParticipant();
    
    boolean setParticipant(IPerson newParticipant);
    
}
