/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.tabelrendering;

import datamodel.DeckCardFileRecord;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * A table cell renderer to display the card deck data and allow it to be
 * selected for inclusion into the experiment.
 *
 * @author qkitt
 */
public class DeckCardFileRecordCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component result;
        if(null != value){
            result = new JLabel(value.toString());
        } else {
            result = new JLabel();
        }
        if (value instanceof DeckCardFileRecord) {
            DeckCardFileRecord item = (DeckCardFileRecord) value;
            switch (column) {
                case 0:
                    ((JLabel) result).setText(item.getCardDeckFile().getName());
                    break;
                case 1:
                    ((JLabel) result).setText(item.getCardDeckFile().getPath());
                    break;
                case 2:
                    result = new JCheckBox();
                    ((JCheckBox) result).setSelected(item.isToBeIncluded());
                    break;
            }
        }
        return result;
    }

}
