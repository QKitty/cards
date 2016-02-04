/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.enums.CardSuite;
import datamodel.enums.CardValue;
import datamodel.enums.SuiteColour;
import javax.swing.Icon;

/**
 * This interface represents the abstract concept of a playing card
 * @author rtucker
 */
public interface ICard {
    
    /**
     * Tests to determine if this is a 'face' card. Face cards are:
     * King, Queen, Jack, Joker
     * @return boolean True if this is a face card, False otherwise
     */
    public boolean isFaceCard();
    
    /**
     * Accessor to retrieve the cards suite
     * @return A CardSuite object
     */
    public CardSuite getSuite();
    
    /**
     * Accessor to retrieve the cards suite
     * @return A CardValue object
     */
    public CardValue getValue();
    
    /**
     * Accessor to retrieve the colour of the card
     * @return A SuiteColour object
     */
    public SuiteColour getSuiteColour();
    
    /**
     * Creates an Icon object for this card. Will create icon based on the
     * current value of the isFaceShowing() method. If face is not showing
     * the icon will be the back of a playing card. If it is then it will
     * be the appropriate SVG graphic for this card
     * @param width - integer being the icons width
     * @param height - integer being the icons height
     * @return - An Icon that represents the playing card
     */
    public Icon getCardIcon(int width, int height);
    
    /**
     * Accessor to test if the cards face is visible when displaying the card
     * @return boolean True if the cards face should be shown, False if the 
     * back of the playing card should be shown
     */
    public boolean isShowingFace();
    
    /**
     * Accessor to set if the cards face is visible
     * @param flag - boolean True if card face is visible, false if card back
     * is visible
     */
    public void setShowingFace(boolean flag);
    
    /**
     * Tests if a card has been marked as selected
     * @return True if the card is to be treated as "selected" by the application,
     * False otherwise.
     */
    public boolean isSelected();
    
    /**
     * Marks this card as "selected" (or unselected) by the application
     * @param flag - boolean True if the application should treat the card as selected,
     * False otherwise.
     */
    public void setSelected(boolean flag);
    
}
