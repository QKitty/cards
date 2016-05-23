/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.enums.CardAlgorithmCategory;
import datamodel.exceptions.NotACardDeckFileException;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckCardFileRecord;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeckCardFileRecord implements IDeckCardFileRecord {

    private File cardDeckFile;
    private boolean toBeInculded;
    private IDeck cardDeck;

    public DeckCardFileRecord(File aFile) throws NotACardDeckFileException {
        if (null == aFile) {
            throw new IllegalArgumentException("Cannot create a record for a null file.");
        }
        if (!aFile.exists()) {
            throw new IllegalArgumentException("Cannot create a record for a file that does not exist.");
        }
        if (!aFile.canRead()) {
            throw new IllegalArgumentException("Cannot create a record for a file that cannot be read.");
        }
        this.cardDeck = this.loadDeckFromFile(aFile);
        this.cardDeckFile = aFile;
        this.toBeInculded = true;
    }

    @Override
    public File getCardDeckFile() {
        return this.cardDeckFile;
    }

    @Override
    public void setCardDeckFile(File newCardDeck) throws NotACardDeckFileException {
        if (null == newCardDeck) {
            throw new IllegalArgumentException("Cannot create a record for a null file.");
        }
        if (!newCardDeck.exists()) {
            throw new IllegalArgumentException("Cannot create a record for a file that does not exist.");
        }
        if (!newCardDeck.canRead()) {
            throw new IllegalArgumentException("Cannot create a record for a file that cannot be read.");
        }
        this.cardDeck = this.loadDeckFromFile(newCardDeck);
        this.cardDeckFile = newCardDeck;
    }

    @Override
    public boolean isToBeIncluded() {
        return this.toBeInculded;
    }

    @Override
    public void setToBeIncluded(boolean flag) {
        if (!this.getCardAlgorithmCategory().equals(CardAlgorithmCategory.UNKNOWN)) {
            this.toBeInculded = flag;
        }
    }

    @Override
    public int getNoOfCardsInDeck() {
        int result = 0;
        if (null != this.cardDeck) {
            if (!this.getCardAlgorithmCategory().equals(CardAlgorithmCategory.UNKNOWN)) {
                result = this.cardDeck.getNoOfRemainingCards();
            }
        }
        return result;
    }

    @Override
    public CardAlgorithmCategory getCardAlgorithmCategory() {
        CardAlgorithmCategory result = CardAlgorithmCategory.UNKNOWN;
        if (null != this.cardDeck) {
            result = this.cardDeck.getAlgorithmCategory();
        }
        return result;
    }

    @Override
    public IDeck getCardDeck() {
        IDeck result = null;
        if (this.isInValidState()) {
            result = this.cardDeck;
        }
        return result;
    }

    @Override
    public boolean isInValidState() {
        boolean result = false;
        if (null != this.cardDeck) {
            if (!this.getCardAlgorithmCategory().equals(CardAlgorithmCategory.UNKNOWN)) {
                if(0 < this.getNoOfCardsInDeck()){
                    result = true;
                }
            }
        }
        return result;
    }

    private IDeck loadDeckFromFile(File aFile) throws NotACardDeckFileException {
        IDeck result = null;
        if (null != aFile) {
            try (XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(aFile)))) {
                Object readObject = dec.readObject();
                result = (IDeck) readObject;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeckCardFileRecord.class.getName()).log(Level.SEVERE, null, ex);
                throw new NotACardDeckFileException("Unable to find file: " + aFile.getAbsolutePath(), ex);
            } catch (ClassCastException ex) {
                Logger.getLogger(DeckCardFileRecord.class.getName()).log(Level.SEVERE, null, ex);
                throw new NotACardDeckFileException("Unable to load file: " + aFile.getAbsolutePath(), ex);
            }
        } else {
            throw new NullPointerException("Cannot load a card deck from a NULL file object.");
        }
        if (null == result) {
            throw new NotACardDeckFileException("Failed to load card deck from file: " + aFile.getAbsolutePath());
        }
        return result;
    }

}
