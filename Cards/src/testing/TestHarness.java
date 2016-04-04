/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import datamodel.BaseDeckImpl;
import datamodel.Card;
import datamodel.deckalgorithms.FiniteKnownDeckAlgorithm;
import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.interfaces.ICard;
import datamodel.interfaces.IDeck;
import datamodel.persistance.IXMLPersistablePersistanceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qkitt
 */
public class TestHarness {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //testCard("ACard.xml");
        testDeck("ADeck.xml");

    }

    public static IDeck createFixedDeck() {
        IDeck result;
        List<ICard> pack = new ArrayList<>();
        for (CardSuite currSuite : CardSuite.values()) {
            for (CardValue currValue : CardValue.values()) {
                Card currCard = new Card(currSuite, currValue, true);
                pack.add(currCard);
            }
        }
        FiniteKnownDeckAlgorithm alg = new FiniteKnownDeckAlgorithm(pack);
        result = new BaseDeckImpl(alg);
        return result;
    }

    private static void testDeck(String fName) {
        IDeck myDeck = TestHarness.createFixedDeck();
//        try (XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)))) {
//            IDeckAlgorithm deckAlgorithm = myDeck.getDeckAlgorithm();
//            enc.setPersistenceDelegate(deckAlgorithm.getClass(), new IXMLPersistablePersistanceDelegate());
//            enc.setPersistenceDelegate(myDeck.getClass(), new IXMLPersistablePersistanceDelegate());
//            enc.setPersistenceDelegate(Card.class, new IXMLPersistablePersistanceDelegate());
//            enc.writeObject(myDeck);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(TestHarness.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error: " + ex.getMessage());
//        } finally {
//            System.out.println("Operation complete; File stored at: " + f.getAbsolutePath());
//        }
        try (XMLEncoder enc = myDeck.createXMLEncoder(fName)) {
            enc.writeObject(myDeck);
        } finally {
            System.out.println("Deck test write completed");
        }
    }

    private static void testCard(String fName) {
        File f = new File(fName);
        ICard testCard = new Card(CardSuite.DIAMONDS, CardValue.SEVEN, true);
        try (XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)))) {
            //enc.setPersistenceDelegate(testCard.getClass(), new CardPersistanceDelegate());
            enc.setPersistenceDelegate(testCard.getClass(), new IXMLPersistablePersistanceDelegate());
            enc.writeObject(testCard);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestHarness.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Operation complete; File stored at: " + f.getAbsolutePath());
        }

        testCard = null;

        //Load it back up
        try (XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)))) {
            Object readObject = dec.readObject();
            testCard = (ICard) readObject;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestHarness.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != testCard) {
                System.out.println(testCard.toString());
            } else {
                System.err.println("Failed to reload object");
            }
        }
    }
}
