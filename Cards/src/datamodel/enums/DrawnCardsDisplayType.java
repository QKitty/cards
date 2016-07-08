/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.enums;

import datamodel.interfaces.IDeck;
import javax.swing.JPanel;
import views.components.DrawnCardsHistoryDisplayPanel;
import views.components.DrawnCardsLastCardDisplayPanel;
import views.components.DrawnCardsLastCardPlusTextDisplayPanel;
import views.components.DrawnCardsTextDisplayPanel;

/**
 *
 * @author qkitt
 */
public enum DrawnCardsDisplayType {
    
    CARD_HISTORY_DISPLAY,
    LAST_CARD_DRAWN_DISPLAY,
    TEXT_DISPLAY;
    
    public JPanel createDisplayPanel(IDeck aDeck){
        JPanel result;
        switch(this){
            case CARD_HISTORY_DISPLAY:
                result = new DrawnCardsHistoryDisplayPanel(aDeck);
                break;
            case LAST_CARD_DRAWN_DISPLAY:
                result = new DrawnCardsLastCardDisplayPanel(aDeck);
                break;
            case TEXT_DISPLAY:
                //result = new DrawnCardsTextDisplayPanel(aDeck);
                result = new DrawnCardsLastCardPlusTextDisplayPanel(aDeck);
                break;
            default:
                result = new JPanel();
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "UNKNOWN";
        switch(this){
            case CARD_HISTORY_DISPLAY:
                result = "History of drawn cards.";
                break;
            case LAST_CARD_DRAWN_DISPLAY:
                result = "Show last card drawn.";
                break;
            case TEXT_DISPLAY:
                result = "Show count of drawn cards.";
                break;
            default:
                result = "UNKNOWN";
        }
        return result;
    }
    
}
