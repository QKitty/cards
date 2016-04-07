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
    
    public boolean validateForCategory(double kingProb, double queenProb){
        boolean result = false;
        if(kingProb >= 0 && kingProb <= 1){
            if(queenProb>= 0 && queenProb <= 1){
                switch(this){
                    case KING_HI_INFORMATIVE:
                    case KING_LOW_INFORMATIVE:
                        if(kingProb > queenProb){
                            result = true;
                        }
                        break;
                    case QUEEN_HI_INFORMATIVE:
                    case QUEEN_LOW_INFORMATIVE:
                        if(kingProb < queenProb){
                            result = true;
                        }
                        break;
                    default:
                        result = false;
                }
            }
        }
        return result;
    }

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
