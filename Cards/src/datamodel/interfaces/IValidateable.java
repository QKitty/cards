/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

/**
 * This interface represents the abstract concept of an object that can be tested
 * to determine if it is in a valid state
 * @author qkitt
 */
public interface IValidateable {
    
    boolean isInValidState();
    
}
