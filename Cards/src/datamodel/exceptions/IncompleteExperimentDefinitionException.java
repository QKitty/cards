/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.exceptions;

/**
 * A checked exception thrown by an experiment generator that cannot create a
 * valid experiment.
 * @author rtucker
 */
public class IncompleteExperimentDefinitionException extends Exception {
    
    public IncompleteExperimentDefinitionException() {
        super();
    }

    public IncompleteExperimentDefinitionException(String string) {
        super(string);
    }

    public IncompleteExperimentDefinitionException(String string, Throwable thrwbl) {
        super(thrwbl);
    }

    public IncompleteExperimentDefinitionException(Throwable thrwbl) {
        super(null, thrwbl);
    }
    
}
