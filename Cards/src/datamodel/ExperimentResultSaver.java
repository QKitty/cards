/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.enums.DeckType;
import datamodel.interfaces.ICardDrawnRecord;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IExperimentModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible for saving a completed experiment to the specified
 * file location.
 *
 * @author qkitt
 */
public class ExperimentResultSaver {

    /**
     *
     * @param experimentModel - The completed experiment model to save
     * @param destFile - The file to save to. This may be NULL in which case the
     * data is saved to the users home folder with the name
     * "ExperimentResult<number>.csv"
     * @return
     * @throws IllegalStateException
     * @throws java.io.IOException
     */
    public static boolean saveExperimentResult(IExperimentModel experimentModel, File destFile) throws IllegalStateException, IOException {
        boolean result = false;
        if (null != experimentModel) {
            if (experimentModel.isExperimentComplete()) {
                if (null == destFile) {
                    int fileNo = 0;
                    boolean blnFreeFileName = false;
                    File tempFile = null;
                    while (!blnFreeFileName) {
                        StringBuilder path = new StringBuilder(System.getProperty("user.home"));
                        path.append("/ExperimentResult");
                        path.append(fileNo);
                        path.append(".csv");
                        tempFile = new File(path.toString());
                        if (!tempFile.exists()) {
                            blnFreeFileName = true;
                        } else {
                            fileNo++;
                        }
                    }
                    destFile = tempFile;
                    if (null == destFile) {
                        throw new IllegalStateException("Cannot save to a NULL results file!");
                    }
                }
                //At this point I have a file to save to!
                //Now build the files csv string
                String strHeaderStart = ",,,,,,";
                String strDataStart = ",,,,,";
                StringBuilder data = new StringBuilder("Initialising parameters:,,,,,Summary statistics:\n");
                //data.append("Deck Name,Deck Identity:,,,,").append(experimentModel.getTotalCardsDrawn()).append("\n");
                data.append("Deck Name,Deck Identity:,,,,Trials to decision (i.e. terminate sampling and guess at deck identities)\n");
                int count = 0;
                for (IDeck currDeck : experimentModel) {
                    count++;
                    if (count != 1) {
                        data.append(currDeck.getId()).append(",").append(currDeck.getAlgorithmCategory().toString()).append("\n");
                    } else {
                        data.append(currDeck.getId()).append(",").append(currDeck.getAlgorithmCategory().toString()).append(",,,,").append(experimentModel.getTotalCardsDrawn()).append("\n");
                    }
                }
                data.append("\n");
                data.append("Type of trial:,").append(experimentModel.getDeck(0).getDeckType().toString()).append("\n\n");
                data.append("Participant ID:\n");
                data.append(experimentModel.getParticipant().getIdNo());
                data.append("\n");
                data.append("\n");
                //Now output data tables
                data.append(strHeaderStart).append("Number of kings drawn,Number of queens drawn,Number of 'other' cards drawn,Proportion (kings:queens) drawn,Proportion ([kings+queens]:others) drawn\n");
                for (IDeck currDeck : experimentModel) {
                    Integer noOfKingsDrawn = currDeck.getNoOfKingsDrawn();
                    Integer noOfQueensDrawn = currDeck.getNoOfQueensDrawn();
                    Integer noOfOtherCardsDrawn = currDeck.getNoOfOtherCardsDrawn();
                    Double proportionKQ;
                    if (0 != (noOfKingsDrawn.doubleValue() + noOfQueensDrawn.doubleValue())) {
                        proportionKQ = noOfKingsDrawn.doubleValue() / (noOfKingsDrawn.doubleValue() + noOfQueensDrawn.doubleValue());
                    } else {
                        proportionKQ = 0d;
                    }
                    Double proportionKQO;
                    if (0 != (noOfKingsDrawn.doubleValue() + noOfQueensDrawn.doubleValue() + noOfOtherCardsDrawn.doubleValue())) {
                        proportionKQO = (noOfKingsDrawn.doubleValue() + noOfQueensDrawn.doubleValue()) / (noOfKingsDrawn.doubleValue() + noOfQueensDrawn.doubleValue() + noOfOtherCardsDrawn.doubleValue());
                    } else {
                        proportionKQO = 0d;
                    }
                    data.append(strDataStart).append(currDeck.getId()).append(",").append(noOfKingsDrawn).append(",").append(noOfQueensDrawn).append(",").append(noOfOtherCardsDrawn).append(",").append(proportionKQ).append(",").append(proportionKQO).append("\n");
                }
                data.append("\n");
                data.append(strHeaderStart).append("Sum of all cards turned\n");
                for (IDeck currDeck : experimentModel) {
                    data.append(strDataStart).append(currDeck.getId()).append(",").append(currDeck.getDrawnCardList().size()).append("\n");
                }
                data.append("\n");
                data.append(strHeaderStart).append("Guess at deck ID,Correctly identified\n");
                Double correctCount = 0d;
                for (IDeck currDeck : experimentModel) {
                    String guessCorrect = "NO";
                    if (currDeck.isParticipantGuessCorrect()) {
                        guessCorrect = "YES";
                        correctCount++;
                    }
                    data.append(strDataStart).append(currDeck.getId()).append(",").append(currDeck.getParticipantsGuess().toString()).append(",").append(guessCorrect).append("\n");
                }
                data.append("\n");
                Double deckCount = (double) experimentModel.getNoOfDecks();
                Double proportionCorrect = correctCount / deckCount;
                data.append(strDataStart).append("Proportion correct ID (out of 4 - constrained to >1),").append(proportionCorrect.toString()).append("\n");
                data.append("\n");
                data.append(strDataStart).append("Raw data:\n");
                data.append(strDataStart).append("Trial number,Time stamp,Deck choice,Card revealed\n");
                for (ICardDrawnRecord currRecord : experimentModel.getCardDrawnRecordList()) {
                    data.append(strDataStart).append(currRecord.toString());
                }
                //Now write the data into the file:
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(destFile))) {
                    bw.write(data.toString());
                }
                result = true;
            } else {
                throw new IllegalStateException("Cannot save experiment result before the experiment is complete.");
            }
        } else {
            throw new NullPointerException("Experiment object cannot be NULL when trying to save an experiment.");
        }
        return result;
    }

}
