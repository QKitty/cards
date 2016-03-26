/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;

/**
 *
 * @author qkitt
 */
public class DeckPersistanceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        IDeck deckToSerialise = (IDeck)oldInstance;
        IDeckAlgorithm deckAlgorithm = deckToSerialise.getDeckAlgorithm();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
