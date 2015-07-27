/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.svg;

import datamodel.Card;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.interfaces.ICard;

/**
 * This class of static methods will produce card objects to request
 * @author rtucker
 */
public class CardFactory {
    
    public static ICard createPlayingCard(CardSuite suite, CardValue value){
        ICard result = new Card(suite, value);
        return result;
    }
    
    public static ICard createPlayingCard(CardSuite suite, CardValue value, boolean faceFlag){
        ICard result = CardFactory.createPlayingCard(suite, value);
        result.setShowingFace(faceFlag);
        return result;
    }
    
}
