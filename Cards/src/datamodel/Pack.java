/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.interfaces.ICard;
import datamodel.interfaces.IPack;
import java.util.Iterator;

/**
 * Concrete implementation of the IPack interface. Represents a pack of playing
 * cards in the system.
 * @author rtucker
 */
public class Pack implements IPack {

    @Override
    public Iterator<ICard> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
