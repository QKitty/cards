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
    JACK, QUEEN, KING, NOCARD;

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
            case NOCARD:
                result = "No_Card";
                break;
            default:
                result = "";
        }
        return result;
    }

    public static CardValue getCardValueFromInt(int aValue) {
        CardValue result;
        switch (aValue) {
            case 2:
                result = TWO;
                break;
            case 3:
                result = THREE;
                break;
            case 4:
                result = FOUR;
                break;
            case 5:
                result = FIVE;
                break;
            case 6:
                result = SIX;
                break;
            case 7:
                result = SEVEN;
                break;
            case 8:
                result = EIGHT;
                break;
            case 9:
                result = NINE;
                break;
            case 10:
                result = TEN;
                break;
            case 11:
                result = JACK;
                break;
            case 12:
                result = QUEEN;
                break;
            case 13:
                result = KING;
                break;
            case 14:
                result = JOKER_BLACK;
                break;
            case 15:
                result = JOKER_RED;
                break;
            case -1:
                result = NOCARD;
                break;
            default:
                result = ACE;
        }
        return result;
    }

    @Override
    public String toString() {
        String result;
        switch (this) {
            case ACE:
                result = "Ace";
                break;
            case TWO:
                result = "Two";
                break;
            case THREE:
                result = "Three";
                break;
            case FOUR:
                result = "Four";
                break;
            case FIVE:
                result = "Five";
                break;
            case SIX:
                result = "Six";
                break;
            case SEVEN:
                result = "Seven";
                break;
            case EIGHT:
                result = "Eight";
                break;
            case NINE:
                result = "Nine";
                break;
            case TEN:
                result = "Ten";
                break;
            case JACK:
                result = "Jack";
                break;
            case QUEEN:
                result = "Queen";
                break;
            case KING:
                result = "King";
                break;
            case JOKER_RED:
                result = "Red Joker";
                break;
            case JOKER_BLACK:
                result = "Black Joker";
                break;
            default:
                result = "";
        }
        return result;
    }

}
