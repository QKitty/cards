/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.deckalgorithms.CustomisableInfiniteDeckAlgorithm;
import datamodel.deckalgorithms.FiniteKnownDeckAlgorithm;
import datamodel.deckalgorithms.FrequencyInfiniteDeckAlgorithm;
import datamodel.deckalgorithms.KingHiInfoInfiniteDeckAlgorithm;
import datamodel.deckalgorithms.QueenHiInfoInfiniteDeckAlgorithm;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeckAlgorithm;
import java.util.Collection;

/**
 * A Factory to generate deck algorithms on request
 * @author qkitt
 */
public class DeckAlgorithmFactory {
    
    public static IDeckAlgorithm createCustomisableInfiniteDeckAlgorithm(double prefKing, double prefQueen, int noKQCards, int maxDrawsBetween){
        return new CustomisableInfiniteDeckAlgorithm(prefKing, prefQueen, noKQCards, maxDrawsBetween);
    }
    
    public static IDeckAlgorithm createFiniteKnownDeckAlgorithm(Collection<? extends ICard> c){
        return new FiniteKnownDeckAlgorithm(c);
    }
    
    public static IDeckAlgorithm createFrequencyInfiniteDeckAlgorithm(double fKing, double fQueen, double fOther, double deltaf){
        return new FrequencyInfiniteDeckAlgorithm(fKing, fQueen, fOther, deltaf);
    }
    
    public static IDeckAlgorithm createKingHiInfoInfiniteDeckAlgorithm(int maxCardsBetweenSpecialCard){
        return new KingHiInfoInfiniteDeckAlgorithm(maxCardsBetweenSpecialCard);
    }
    
    public static IDeckAlgorithm createQueenHiInfoInfiniteDeckAlgorithm(int maxCardsBetweenSpecialCard){
        return new QueenHiInfoInfiniteDeckAlgorithm(maxCardsBetweenSpecialCard);
    }
    
}
