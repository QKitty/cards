/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import javax.swing.Icon;
import org.apache.batik.transcoder.TranscoderException;

/**
 * Interface to be implemented by any class that maps an ICard object to an
 * icon that represents the object.
 * @author rtucker
 */
public interface ICardIconGenerator {
    
    /**
     * Creates an Icon object that represents the given playing card with the
     * specified width and height.
     * @param aCard - ICard interface to a playing card object
     * @param width - int being the width of the created icon
     * @param height - int being the height of the created icon
     * @return - An Icon object that represents the playing card at the 
     * requested size
     */
    public Icon generateCardIcon(ICard aCard, int width, int height);
    
}
