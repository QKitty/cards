/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.exceptions;

/**
 * Any attempt to retrieve or set a decks card algorithm should throw this
 * exception if the deck does not support algorithms for generating its cards
 * @author rtucker
 */
public class NotAnAlgorithmicDeckException extends Exception {
    
    public NotAnAlgorithmicDeckException() {
        super();
    }

    public NotAnAlgorithmicDeckException(String string) {
        super(string);
    }

    public NotAnAlgorithmicDeckException(String string, Throwable thrwbl) {
        super(thrwbl);
    }

    public NotAnAlgorithmicDeckException(Throwable thrwbl) {
        super(null, thrwbl);
    }
    
}
