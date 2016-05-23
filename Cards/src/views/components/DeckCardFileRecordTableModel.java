/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import datamodel.interfaces.IDeckCardFileRecord;
import datamodel.interfaces.IDeckCardFileScanner;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * This class represents a table model that allows the display of DeckCardFileRecord
 * objects in a JTable
 * @author qkitt
 */
public class DeckCardFileRecordTableModel extends DefaultTableModel {
    
    public DeckCardFileRecordTableModel(IDeckCardFileScanner scanner){
        super();
        int cols = 3;
        Vector data = new Vector();
        for(IDeckCardFileRecord currRecord : scanner){
            Vector row = new Vector();
            for(int i = 0; i < cols; i++){
                row.add(currRecord);
            }
            data.add(row);
        }
        Vector<String> colNames = new Vector<>();
        colNames.add("File Name");
        colNames.add("Folder");
        colNames.add("Inc / Exc Deck");
        this.setDataVector(data, colNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        boolean result = false;
        if(2 == column){
            result = true;
        }
        return result;
    }
    
    
    
}
