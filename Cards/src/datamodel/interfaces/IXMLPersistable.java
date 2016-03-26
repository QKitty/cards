/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.interfaces;

import datamodel.persistance.IXMLPersistablePersistanceDelegate;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interface to be implemented by any object that is to be persisted in XML form
 *
 * @author qkitt
 */
public interface IXMLPersistable {

    /**
     * Accessor to retrieve the factory to be used to recreate the object
     *
     * @return An object being the instantiated factory to use to create objects
     * of the implementing class
     */
    Class<?> getFactoryClass();

    /**
     * Accessor to retrieve the name of the factory method to invoke to create
     * objects of the implementing class
     *
     * @return A String being the name of the factory method that creates
     * objects of this class
     */
    String getFactoryMethodName();

    /**
     * Accessor to retrieve the in order arguments to be passed to the factory
     * method. Return null to invoke the factory method with an empty list of
     * arguments
     *
     * @return An Object array holding the factory method arguments (in order)
     * or NULL if the factory method has no arguments.
     */
    Object[] getFactoryArgs();

    /**
     * Creates a XMLEncoder that is ready to write this object to a file with
     * provided name and path.
     *
     * @param fileName - String being the name of the file to write (may include
     * path)
     * @return An XMLEncoder configured to write this object to an XML file.
     */
    default XMLEncoder createXMLEncoder(String fileName) {
        File aFile = new File(fileName);
        return this.createXMLEncoder(aFile);
    }

    /**
     * Creates a XMLEncoder that is ready to write this object to the provided
     * file. If the file does not exist it will be created. If it does exist it
     * will be replaced.
     * 
     * DEFAULT implementation will serialise the object successfully ONLY if
     * built in JAVA classes are used for all attributes. If custom classes are
     * used override this method to add a persistence delegate for all custom
     * classes
     *
     * @param aFile - The file to create or replace with the objects XML
     * @return An XMLEncoder configured to write this object to an XML file.
     */
    default XMLEncoder createXMLEncoder(File aFile) {
        XMLEncoder enc = null;
        try {
            enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(aFile)));
            enc.setPersistenceDelegate(this.getClass(), this.getPersistanceDelegate());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IXMLPersistable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enc;
    }

    /**
     * Accessor to retrieve the Expression object used to serialise instances of
     * this object to XML.
     *
     * @return The Expression object used to serialise this object to XML.
     */
    default Expression getCreationExpression() {
        return new Expression(this, getFactoryClass(), getFactoryMethodName(), getFactoryArgs());
    }

    /**
     * Retrieves an instance of the IXMLPersistablePersistanceDelegate which can
     * be used to implement XML serialisation of any object that implements the
     * IXMLPersistable interface
     *
     * @return An instance of the IXMLPersistablePersistanceDelegate class.
     */
    default PersistenceDelegate getPersistanceDelegate() {
        return new IXMLPersistablePersistanceDelegate();
    }

}
