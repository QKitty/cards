/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

/**
 * This interface represents the abstract concept of and object that can be
 * controlled by the applications controller class
 *
 * @author qkitt
 */
public interface IControllable {

    /**
     * Accessor to retrieve the controller for this object.
     * @return An IController interface to the controller object for this class
     */
    IController getController();

    /**
     * Accessor to set the controller for this object.
     * @param aController - The controller object for this object.
     * @return - Boolean true if the provided object now controls this object,
     * False otherwise.
     */
    boolean setController(IController aController);

}
