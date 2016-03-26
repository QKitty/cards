/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.persistance;

import datamodel.interfaces.IXMLPersistable;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;

/**
 * A PersistenceDelegate to use for any class implementing IXMLPersistable
 * @author qkitt
 */
public class IXMLPersistablePersistanceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        IXMLPersistable item = (IXMLPersistable)oldInstance;
        //out.setPersistenceDelegate(item.getClass(), item.getPersistanceDelegate());
        return item.getCreationExpression();
    }
    
}
