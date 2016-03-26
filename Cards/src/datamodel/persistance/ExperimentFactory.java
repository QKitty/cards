/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.ExperimentModelImpl;
import datamodel.interfaces.IDeck;
import datamodel.interfaces.IExperimentModel;
import java.util.List;

/**
 * Factory class to construct an experiment model with a give list of decks
 * @author qkitt
 */
public class ExperimentFactory {
    
    public IExperimentModel createExperimentModel(List<IDeck> decks){
        return new ExperimentModelImpl(decks);
    }
    
}
