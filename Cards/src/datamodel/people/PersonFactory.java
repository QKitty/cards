/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.people;

import datamodel.interfaces.IPerson;

/**
 * A factory class to create people objects.
 * @author qkitt
 */
public class PersonFactory {
    
    public static IPerson createParticipant(int newId, String newFName, String newLName, String newPhoneNumber, String newEMail){
        return new Participant(newId, newFName, newLName, newPhoneNumber, newEMail);
    }
    
}
