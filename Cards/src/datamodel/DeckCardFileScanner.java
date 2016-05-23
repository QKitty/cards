/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.exceptions.NotACardDeckFileException;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IDeckCardFileRecord;
import datamodel.interfaces.IDeckCardFileScanner;
import datamodel.interfaces.IExperimentModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeckCardFileScanner extends ISubjectImpl implements IDeckCardFileScanner {

    private File targetDirectory;
    private final List<IDeckCardFileRecord> loadedFileRecords;

    public DeckCardFileScanner() {
        this.targetDirectory = new File(System.getProperty("user.home"));
        this.loadedFileRecords = new ArrayList<>();
    }
    
    public DeckCardFileScanner(File target) {
        this();
        this.setTargetDirectory(target);
    }

    @Override
    public File getTargetDirectory() {
        return this.targetDirectory;
    }

    @Override
    public final boolean setTargetDirectory(File newTarget) {
        boolean result = false;
        if (null != newTarget) {
            if (newTarget.exists()) {
                if (newTarget.isDirectory()) {
                    if (newTarget.canRead()) {
                        this.targetDirectory = newTarget;
                        result = true;
                    } else {
                        throw new IllegalArgumentException("Cannot target the directory " + newTarget.getAbsolutePath() + " this directory cannot be read!");
                    }
                } else {
                    throw new IllegalArgumentException("File scanner must target a directory. The file " + newTarget.getAbsolutePath() + " is NOT a directory!");
                }
            } else {
                throw new IllegalArgumentException("Cannot set scanner target to a file that does not exist!");
            }
        } else {
            throw new NullPointerException("Cannot target file scanner at a NULL file object!");
        }
        return result;
    }

    @Override
    public boolean performDirectoryScan() {
        boolean result = false;
        if(this.isInValidState()){
            this.reset();
            List<File> xmlFiles = IDeckCardFileScanner.retrieveDirectoryXMLFiles(this.targetDirectory);
            xmlFiles.stream().forEach((currFile) -> {
                try {
                    IDeckCardFileRecord record = new DeckCardFileRecord(currFile);
                    this.loadedFileRecords.add(record);
                } catch (NotACardDeckFileException ex) {
                    Logger.getLogger(DeckCardFileScanner.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if(0 < this.loadedFileRecords.size()){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean hasCardDecks() {
        boolean result = false;
        if(0 < this.loadedFileRecords.size()){
            for(IDeckCardFileRecord currRec : this.loadedFileRecords){
                result = currRec.isToBeIncluded();
                if(result){
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean populateModel(IExperimentModel model) {
        boolean result = false;
        if(null != model){
            if(!this.hasCardDecks()){
                this.performDirectoryScan();
            }
            if(this.hasCardDecks()){
                model.reset();
                int deckId = 1;
                for(IDeckCardFileRecord currRecord : this.loadedFileRecords){
                    if(currRecord.isToBeIncluded()){
                        IDeck cardDeck = currRecord.getCardDeck();
                        cardDeck.setId("Deck " + deckId);
                        model.addDeck(currRecord.getCardDeck());
                        deckId++;
                    }
                }
                if(0 < model.getDecks().size()){
                    result = true;
                }
            } else {
                throw new IllegalStateException("The file scanner has no card decks to load!");
            }
        } else {
            throw new NullPointerException("Cannot populate a NULL experiment model with card decks!");
        }
        return result;
    }

    @Override
    public void reset() {
        this.loadedFileRecords.clear();
    }

    @Override
    public boolean isInValidState() {
        boolean result = false;
        if(null != targetDirectory){
            if(targetDirectory.exists() && targetDirectory.canRead()){
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<IDeckCardFileRecord> iterator() {
        List<IDeckCardFileRecord> result = new ArrayList<>(this.loadedFileRecords);
        return result.iterator();
    }

}
