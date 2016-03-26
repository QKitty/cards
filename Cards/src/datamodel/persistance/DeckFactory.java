/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.BaseDeckImpl;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckAlgorithm;

/**
 * Factory to create a card deck using the provided algorithm
 * @author qkitt
 */
public class DeckFactory {
    
    public static IDeck createDeck(IDeckAlgorithm alg){
        return new BaseDeckImpl(alg);
    }
    
}
