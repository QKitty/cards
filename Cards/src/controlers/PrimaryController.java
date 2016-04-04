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
import testing.TestHarness;
import views.CardsMainForm;
import views.components.ExperimentDisplayPanel;

/**
 * 
 * @author qkitt
 */
public class PrimaryController implements IController {
    
//<editor-fold defaultstate="collapsed" desc="Attributes">
    private final IExperimentModel model;
    private boolean blnExpRunning;
    private DrawnCardsDisplayType displayType;
    private final CardsMainForm mainWin;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public PrimaryController(CardsMainForm newMainWindow){
        this.mainWin = newMainWindow;
        this.model = new ExperimentModelImpl();
        this.blnExpRunning = false;
        this.displayType = DrawnCardsDisplayType.CARD_HISTORY_DISPLAY;
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
        if(null != newParticipant){
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
    public void startExperiment(){
        this.createDebugDecks();
        //Ask for trial ID
        //Create display
        ExperimentDisplayPanel expDisplay = new ExperimentDisplayPanel(this);
        //Create "Trial"
        //Update main window
        this.mainWin.setView(expDisplay);
        this.setExperimentRunning(true);
    }


    @Override
    public DrawnCardsDisplayType getDrawnCardsDisplayType() {
        return this.displayType;
    }

    @Override
    public void setDrawnCardsDisplayType(DrawnCardsDisplayType aType) {
        if(null != aType && aType != this.displayType){
            this.displayType = aType;
            this.model.update();
        }
    }
    
    @Override
    public void createDebugDecks(){
        this.model.addDeck(TestHarness.createFixedDeck());
        this.model.addDeck(TestHarness.createFixedDeck());
        this.model.addDeck(TestHarness.createFixedDeck());
        this.model.addDeck(TestHarness.createFixedDeck());
    }
    //</editor-fold>
}