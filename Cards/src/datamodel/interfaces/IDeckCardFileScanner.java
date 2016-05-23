/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import com.gmail.qkitty6.patterns.observer.ISubject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface represents the abstract concept of a model class that can scan
 * a directory for card deck XML files and load them
 * @author qkitt
 */
public interface IDeckCardFileScanner extends ISubject, IValidateable, Iterable<IDeckCardFileRecord> {
    
    /**
     * Accessor method to retrieve the target directory to scan.
     * @return - A File object holding the path to the target directory.
     */
    File getTargetDirectory();
    
    /**
     * Accessor method that resets the scanner and changes the target directory to scan
     * @param newTarget - File object representing the new target directory.
     * @return - Boolean True if the target directory exists, can be read and was stored
     * as the new target, false otherwise.
     */
    boolean setTargetDirectory(File newTarget);
    
    /**
     * Scans the current target directory for card deck XML files and loads any
     * that it finds. Currently loaded files will be discarded
     * @return Boolean True if the scan completed successfully (even if no files
     * where found and loaded), False otherwise.
     */
    boolean performDirectoryScan();
    
    /**
     * Tests if the scanner has loaded card decks available.
     * @return True if card deck files are available, False otherwise
     */
    boolean hasCardDecks();
    
    /**
     * Adds any loaded card decks to the provided experiment model. If card 
     * decks are already present in the model they will be discarded.
     * @param model - The experiment model to modify.
     * @return Boolean True if this scanner has loaded card decks and they have
     * replaced any card decks previously in the experiment model.
     */
    boolean populateModel(IExperimentModel model);
    
    /**
     * Resets this scanner so their are no loaded card decks. After this method 
     * completes a call to hasCardDecks() will return false. The target directory
     * will be unchanged
     */
    void reset();
    
    static List<File> retrieveDirectoryXMLFiles(File aDir){
        List<File> result = new ArrayList<>();
        if(null != aDir){
            if(aDir.exists()){
                if(aDir.isDirectory()){
                    if(aDir.canRead()){
                        File[] fileList = aDir.listFiles();
                        for (File currFile : fileList) {
                            String name = currFile.getName();
                            if (name.endsWith(".xml") || name.endsWith(".XML")) {
                                result.add(currFile);
                            }
                        }
                    } else {
                        throw new IllegalArgumentException("Cannot read the provided directory: " + aDir.getAbsolutePath());
                    }
                } else {
                    throw new IllegalArgumentException("To retrieve XML files from a directory you must provide a directory! File " + aDir.getAbsolutePath() + " is NOT a directory!");
                }
            } else {
                throw new IllegalArgumentException("Cannot retrieve XML files from a directory that does not exist! Directory: " + aDir.getAbsolutePath());
            }
        } else {
            throw new NullPointerException("Cannot retrieve XML files from a NULL directory!");
        }
        return result;
    }
    
}
