/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.Card;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.interfaces.ICard;

/**
 * A factory class to create ICard objects. To be used in serialisation.
 * @author qkitt
 */
public class CardFactory {
    
    public static ICard createCard(CardSuite newSuite, CardValue newValue, boolean showFace){
        ICard result = new Card(newSuite, newValue);
        result.setShowingFace(showFace);
        return result;
    }
    
}
