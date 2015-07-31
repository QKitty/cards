/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.exceptions.IncompleteExperimentDefinitionException;

/**
 * This interface should be implemented by and class that can create an
 * experiment model.
 * @author rtucker
 */
public interface IExperimentGenerator {
    
    /**
     * Creates an experiment model that can be used to run an experiment
     * @return - The IExperimentModel interface to a concrete experiment
     * implementation
     * @throws datamodel.exceptions.IncompleteExperimentDefinitionException if
     * a valid experiment cannot be created by the generator.
     */
    IExperimentModel createExperimentModel() throws IncompleteExperimentDefinitionException;
    
}
