/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import datamodel.interfaces.IDeck;
import datamodel.interfaces.IExperimentModel;
import java.util.List;

/**
 * A factory class to create pre-configured experiment models
 * @author qkitt
 */
public class ExperimentFactory {
    
    public static IExperimentModel createExperiment(int id, List<IDeck> decks){
        IExperimentModel result = null;
        return result;
    }
    
}
