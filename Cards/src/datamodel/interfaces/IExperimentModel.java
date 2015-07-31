/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

/**
 * This interface is implemented by any class that can serve as the experiment
 * when running a trial
 * @author rtucker
 */
public interface IExperimentModel {
    
    /**
     * Accessor to test if experiment has been marked as complete
     * @return boolean True if experiment has been completed. False otherwise.
     */
    boolean isExperimentComplete();
    
    /**
     * Accessor to set the current status of the experiments complete / incomplete
     * flag.
     * @param flag
     */
    void setExperimentComplete(boolean flag);
    
    /**
     * Resets the experiment so it can be run again
     */
    void resetExperiment();
    
    /**
     * Starts running this experiment
     */
    void runExperiment();
    
    /**
     * Accessor to determine if the experiment is currently being run
     * @return boolean True if runExperiment() has been called and the 
     * experiment has not been either reset or completed. False otherwise.
     */
    boolean isRunning();
    
}
