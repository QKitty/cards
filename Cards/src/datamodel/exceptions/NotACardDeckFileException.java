/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.exceptions;

/**
 * Any attempt to load a card deck from a file that does not contain card deck
 * information should throw this exception
 * @author qkitt
 */
public class NotACardDeckFileException extends Exception {
    
    public NotACardDeckFileException() {
        super();
    }

    public NotACardDeckFileException(String string) {
        super(string);
    }

    public NotACardDeckFileException(String string, Throwable thrwbl) {
        super(thrwbl);
    }

    public NotACardDeckFileException(Throwable thrwbl) {
        super(null, thrwbl);
    }
    
}
