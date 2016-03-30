/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.ISubject;

/**
 * This interface represents the abstract concept of a person in the system
 * @author qkitt
 */
public interface IPerson extends IXMLPersistable, ISubject {
    
    /**
     * Retrieves the ID number for this person. If a string is used as an ID and
     * that string can be converted to an integer value this method will retrieve it.
     * If conversion is not possible a 0 value will be returned.
     * @return - An integer being the persons ID number
     */
    int getIdNo();
    
    /**
     * Accessor to set the persons ID number.
     * @param newId - Integer being the ID number to store
     */
    void setIdNo(int newId);
    
    /**
     * Retrieves the current persons ID string.
     * @return - A String being the persons ID
     */
    String getIdString();
    
    /**
     * Accessor to set the persons ID string. If the string set can be parsed as 
     * an integer this value will be returned by future calls to getIdNo()
     * @param newId - The ID string to store.
     */
    void setIdString(String newId);
    
    /**
     * Accessor to retrieve the persons first name
     * @return - String being the persons first name.
     */
    String getFirstName();
    
    /**
     * Accessor to set the persons first name
     * @param newFName - A non-empty string containing the persons first name
     */
    void setFirstName(String newFName);
    
    /**
     * Accessor to retrieve the persons last name
     * @return - String containing the persons last name
     */
    String getLastName();
    
    /**
     * Accessor to set the persons last name
     * @param newLName - A string being the persons last name to store
     */
    void setLastName(String newLName);
    
    /**
     * Accessor to retrieve the persons phone number
     * @return - String being this persons phone number.
     */
    String getPhoneNumber();
    
    /**
     * Accessor to set this persons phone number.
     * @param contactNo - String being the phone number to store.
     */
    void setPhoneNumber(String contactNo);
    
    /**
     * Accessor to retrieve this persons e-mail address.
     * @return - String containing this persons e-mail address
     */
    String getEMail();
    
    /**
     * Accessor to set this persons e-mail address.
     * @param newEMail - String containing this persons e-mail address
     */
    void setEMail(String newEMail);
}
