/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.payment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Bernhard
 */
public class CellEditorTest extends DefaultCellEditor{

    public CellEditorTest(JTextField textField) {
        super(textField);
        textField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("tabed");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
               // System.out.println(e.getKeyCode());
            }
        });
    }
    
}
