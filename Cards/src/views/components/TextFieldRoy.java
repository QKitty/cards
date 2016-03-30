/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author qkitt
 */
public class TextFieldRoy extends JTextField {

    private static final String MESSAGE = "Enter data.";
    private static final Color PENDINGCOLOUR = new Color(150, 150, 150);

    public TextFieldRoy() {
        super();
        this.setText(MESSAGE);
        this.setForeground(PENDINGCOLOUR);
        this.addFocusListener(new DisplayManager());
    }

    @Override
    public String getText() {
        String result = super.getText();
        if(MESSAGE.equals(result)){
            result = "";
        }
        return result;
    }
    
    

    private class DisplayManager implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            setText("");
            setForeground(Color.BLACK);
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
                setForeground(PENDINGCOLOUR);
                setText(MESSAGE);
            }
        }
    }
}
