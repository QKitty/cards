/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import datamodel.ExperimentModelImpl;
import datamodel.enums.DrawnCardsDisplayType;
import datamodel.interfaces.IController;
import datamodel.interfaces.IExperimentModel;
import datamodel.interfaces.IPerson;
import datamodel.people.Participant;
import java.io.IOException;
import javax.swing.JOptionPane;
import testing.TestHarness;
import views.CardsMainForm;
import views.DeckCreationDialogModel;
import views.components.ExperimentDisplayPanel;

/**
 *
 * @author qkitt
 */
public class PrimaryController implements IController {

//<editor-fold defaultstate="collapsed" desc="Attributes">
    private final IExperimentModel model;
    private boolean blnExpRunning;
    //private DrawnCardsDisplayType displayType;
    private final CardsMainForm mainWin;
    private final DeckCreationDialogModel deckCreationModel;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public PrimaryController(CardsMainForm newMainWindow) {
        this.mainWin = newMainWindow;
        this.model = new ExperimentModelImpl();
        this.blnExpRunning = false;
        //this.displayType = DrawnCardsDisplayType.TEXT_DISPLAY;
        this.deckCreationModel = new DeckCreationDialogModel();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IController Interface implementation">
    @Override
    public IExperimentModel getExpModel() {
        return this.model;
    }

    @Override
    public IPerson getParticipant() {
        return this.model.getParticipant();
    }

    @Override
    public boolean setParticipant(IPerson newParticipant) {
        boolean result = false;
        if (null != newParticipant) {
            this.model.setParticipant(newParticipant);
        }
        return result;
    }

    @Override
    public boolean isExperimentRunning() {
        return this.blnExpRunning;
    }

    @Override
    public void setExperimentRunning(boolean flag) {
        this.blnExpRunning = flag;
        this.model.notifyObservers();
    }

    @Override
    public void startExperiment() {
        this.createDebugDecks();
        //Check that a valid participant has been setup
        if (this.model.hasValidParticipant()) {
            //Ask for trial ID
            //Create display
            this.model.scrambleDeckOrder();
            ExperimentDisplayPanel expDisplay = new ExperimentDisplayPanel(this);
            //Create "Trial"
            //Update main window
            this.mainWin.setView(expDisplay);
            this.setExperimentRunning(true);
        } else {
            this.mainWin.showInvalidParticipantWarning();
        }
    }
    
    @Override
    public void endExperiment() throws IllegalStateException, IOException {
        if(this.isExperimentRunning()){
            if(this.model.completeExperiment()){
                JOptionPane.showMessageDialog(mainWin, "Experiment result saved successfully!", "Experiment data saved", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(mainWin, "Error saving experiment data.", "Cannot save experiment result.", JOptionPane.ERROR_MESSAGE);
            }
            resetExperiment();
        } else {
            throw new IllegalStateException("No experiment is running.");
        }
    }

    @Override
    public DrawnCardsDisplayType getDrawnCardsDisplayType() {
        return this.model.getDrawnCardsDisplayType();
    }

    @Override
    public void setDrawnCardsDisplayType(DrawnCardsDisplayType aType) {
        this.model.setDrawnCardsDisplayType(aType);
    }

    @Override
    public void createDebugDecks() {
        if (this.model.getDecks().isEmpty()) {
            this.model.addDeck(TestHarness.createFixedDeck());
            this.model.addDeck(TestHarness.createFixedDeck());
            this.model.addDeck(TestHarness.createFixedDeck());
            this.model.addDeck(TestHarness.createFixedDeck());
        }
    }
    
    @Override
    public DeckCreationDialogModel getDeckCreationDialogModel() {
        return this.deckCreationModel;
    }
    
    @Override
    public void resetExperiment() {
        this.setExperimentRunning(false);
        this.model.reset();
        int resetParticipant = JOptionPane.showConfirmDialog(mainWin, "Do you wish to clear the participant data?", "Resetting experiment...", JOptionPane.YES_NO_OPTION);
        if(resetParticipant == JOptionPane.YES_OPTION){
            this.model.setParticipant(new Participant());
        }
        this.mainWin.setView(null);
    }
    //</editor-fold>
}
