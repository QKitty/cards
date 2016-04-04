/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.enums;

/**
 * This enum allows card algorithms to be categorised into families for
 * reporting purposes.
 *
 * @author rtucker
 */
public enum CardAlgorithmCategory {

    KING_HI_INFORMATIVE,
    KING_LOW_INFORMATIVE,
    QUEEN_HI_INFORMATIVE,
    QUEEN_LOW_INFORMATIVE,
    UNKNOWN;

    @Override
    public String toString() {
        String result;
        switch (this) {
            case KING_HI_INFORMATIVE:
                result = "KING HI INFORMATIVE";
                break;
            case KING_LOW_INFORMATIVE:
                result = "KING LOW INFORMATIVE";
                break;
            case QUEEN_HI_INFORMATIVE:
                result = "QUEEN HI INFORMATIVE";
                break;
            case QUEEN_LOW_INFORMATIVE:
                result = "QUEEN LOW INFORMATIVE";
                break;
            default:
                result = "UNKNOWN";
                break;
        }
        return result;
    }

}
