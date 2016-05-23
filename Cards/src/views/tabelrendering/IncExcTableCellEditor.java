/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.tabelrendering;

import datamodel.DeckCardFileRecord;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author qkitt
 */
public class IncExcTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    
    private JCheckBox checkBox;

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        checkBox = new JCheckBox();
        if(value instanceof DeckCardFileRecord){
            DeckCardFileRecord item = (DeckCardFileRecord)value;
            checkBox.setSelected(item.isToBeIncluded());
            checkBox.addActionListener((ActionEvent e) -> {
                item.setToBeIncluded(!item.isToBeIncluded());
                checkBox.setSelected(item.isToBeIncluded());
            });
        }
        return checkBox;
    }
    
}
