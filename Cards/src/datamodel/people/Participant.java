/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.people;

import datamodel.interfaces.IPerson;
import com.gmail.qkitty6.patterns.observer.IObserver;
import com.gmail.qkitty6.patterns.observer.ISubject;
import com.gmail.qkitty6.patterns.observer.ISubjectImpl;
import datamodel.interfaces.IValidateable;
import java.util.Collection;
import java.util.Set;

public class Participant implements IPerson, IValidateable {

//<editor-fold defaultstate="collapsed" desc="Attributes">
    private int id;
    private String idString;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String DEFAULTVALUE;
    private final ISubject observers;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Participant() {
        this.observers = new ISubjectImpl();
        this.DEFAULTVALUE = "UNKNOWN";
        this.id = 0;
        this.idString = Integer.toString(this.id);
        this.firstName = this.DEFAULTVALUE;
        this.lastName = this.DEFAULTVALUE;
        this.phoneNumber = this.DEFAULTVALUE;
        this.eMail = this.DEFAULTVALUE;
    }

    public Participant(int newId, String newFName, String newLName, String newPhoneNumber, String newEMail) {
        this();
        this.setIdNo(newId);
        this.firstName = newFName;
        this.lastName = newLName;
        this.phoneNumber = newPhoneNumber;
        this.eMail = newEMail;
    }

    public Participant(String newId, String newFName, String newLName, String newPhoneNumber, String newEMail) {
        this();
        this.setIdString(newId);
        this.idString = Integer.toString(this.id);
        this.firstName = newFName;
        this.lastName = newLName;
        this.phoneNumber = newPhoneNumber;
        this.eMail = newEMail;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IPerson implementation">
    @Override
    public int getIdNo() {
        return this.id;
    }

    @Override
    public final void setIdNo(int newId) {
        if (0 <= newId) {
            this.id = newId;
            this.idString = Integer.toString(this.id);
            this.notifyObservers();
        } else {
            throw new IllegalArgumentException("A new participant ID number must be greater than or equal to 0");
        }
    }

    @Override
    public String getIdString() {
        return this.idString;
    }

    @Override
    public final void setIdString(String newId) {
        if (!newId.isEmpty()) {
            this.idString = newId;
            try {
                this.id = Integer.parseInt(newId);
            } catch (NumberFormatException ex) {
                this.id = 0;
            }
        } else {
            this.id = 0;
            this.idString = Integer.toString(0);
        }
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String newFName) {
        this.firstName = newFName;
        this.notifyObservers();
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String newLName) {
        this.lastName = newLName;
        this.notifyObservers();
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNumber(String contactNo) {
        this.phoneNumber = contactNo;
        this.notifyObservers();
    }

    @Override
    public String getEMail() {
        return this.eMail;
    }

    @Override
    public void setEMail(String newEMail) {
        this.eMail = newEMail;
        this.notifyObservers();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IXMLPersistable implementation">
    @Override
    public Class<?> getFactoryClass() {
        return PersonFactory.class;
    }

    @Override
    public String getFactoryMethodName() {
        return "createParticipant";
    }

    @Override
    public Object[] getFactoryArgs() {
        Object[] result = new Object[5];
        result[0] = this.id;
        result[1] = this.firstName;
        result[2] = this.lastName;
        result[3] = this.phoneNumber;
        result[4] = this.eMail;
        return result;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Observer Pattern Implementation">
    @Override
    public boolean registerObserver(IObserver o) {
        return observers.registerObserver(o);
    }

    @Override
    public boolean removeObserver(IObserver o) {
        return observers.removeObserver(o);
    }

    @Override
    public void notifyObservers() {
        observers.notifyObservers();
    }

    @Override
    public <T> void notifyObservers(T data) {
        observers.notifyObservers(data);
    }

    @Override
    public Set<IObserver> removeAllObservers() {
        return observers.removeAllObservers();
    }

    @Override
    public boolean registerObserver(Collection<? extends IObserver> observerCollection) {
        return observers.registerObserver(observerCollection);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Overridden Object methods">
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getLastName() + ", ");
        builder.append(this.getFirstName() + ", ");
        if (0 != this.id) {
            builder.append(this.id + ", ");
        } else {
            builder.append(this.getIdString());
        }
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="IValidateable interface implementation">
    @Override
    public boolean isInValidState() {
        boolean result = false;
        if(!this.idString.isEmpty() && !this.firstName.isEmpty() && !this.lastName.isEmpty()){
            if(!this.eMail.isEmpty() || !this.phoneNumber.isEmpty()){
                if(!this.idString.equals(DEFAULTVALUE) 
                        && !this.firstName.equals(DEFAULTVALUE)
                        && !this.lastName.equals(DEFAULTVALUE)
                        && !this.eMail.equals(DEFAULTVALUE)
                        && !this.phoneNumber.equals(DEFAULTVALUE)){
                    result = true;
                }
            }
        }
        return result;
    }
//</editor-fold>

}
