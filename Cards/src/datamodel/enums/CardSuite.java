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
public enum CardSuite {

    HEARTS,
    DIAMONDS,
    SPADES,
    CLUBS,
    NONE;

    public String getSuiteString() {
        String result;
        switch (this) {
            case CLUBS:
                result = "clubs";
                break;
            case DIAMONDS:
                result = "diamonds";
                break;
            case HEARTS:
                result = "hearts";
                break;
            case SPADES:
                result = "spades";
                break;
            default:
                result = "";
        }
        return result;
    }

    public static CardSuite getSuiteFromInt(int aValue) {
        CardSuite result;
        switch (aValue) {
            case 2:
                result = DIAMONDS;
                break;
            case 3:
                result = HEARTS;
                break;
            case 4:
                result = SPADES;
                break;
            default:
                result = CLUBS;
        }
        return result;
    }

}
