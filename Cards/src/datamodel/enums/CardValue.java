/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.enums;

/**
 *
 * @author rtucker
 */
public enum CardValue {

    JOKER_RED, JOKER_BLACK, ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
    JACK, QUEEN, KING;

    public boolean isFaceCard() {
        boolean result;
        switch (this) {
            case JOKER_RED:
            case JOKER_BLACK:
            case JACK:
            case QUEEN:
            case KING:
                result = true;
                break;
            default:
                result = false;
        }
        return result;
    }

    public String getCardValueString() {
        String result;
        switch (this) {
            case ACE:
                result = "ace";
                break;
            case TWO:
                result = "2";
                break;
            case THREE:
                result = "3";
                break;
            case FOUR:
                result = "4";
                break;
            case FIVE:
                result = "5";
                break;
            case SIX:
                result = "6";
                break;
            case SEVEN:
                result = "7";
                break;
            case EIGHT:
                result = "8";
                break;
            case NINE:
                result = "9";
                break;
            case TEN:
                result = "10";
                break;
            case JACK:
                result = "jack";
                break;
            case QUEEN:
                result = "queen";
                break;
            case KING:
                result = "king";
                break;
            case JOKER_RED:
                result = "red_joker";
                break;
            case JOKER_BLACK:
                result = "black_joker";
                break;
            default:
                result = "";
        }
        return result;
    }

}
