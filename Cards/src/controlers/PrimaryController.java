/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import datamodel.ExperimentModelImpl;
import datamodel.interfaces.IController;
import datamodel.interfaces.IExperimentModel;
import datamodel.interfaces.IPerson;
import javax.swing.JFrame;

/**
 * 
 * @author qkitt
 */
public class PrimaryController implements IController {
    
//<editor-fold defaultstate="collapsed" desc="Attributes">
    private IExperimentModel model;
    private JFrame participantWindow;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public PrimaryController(){
        this.model = new ExperimentModelImpl();
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
            
        }
        return result;
    }
//</editor-fold>
    
}
