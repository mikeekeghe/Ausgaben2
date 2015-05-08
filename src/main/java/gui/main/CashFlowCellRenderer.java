/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import beans.Payment;
import beans.Transfer;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bernhard
 */
public class CashFlowCellRenderer extends JLabel implements TableCellRenderer {

    private static final String iconFolder = System.getProperty("user.dir") + "\\src\\gui\\icons\\";
    private static final ImageIcon checked = new ImageIcon(iconFolder + "locked.png");
    private static final ImageIcon unChecked = new ImageIcon(iconFolder + "unlocked.png");

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        if (value instanceof Transfer) {
            Transfer t = (Transfer) value;
            setBackground(new Color(204, 204, 204));
            switch (column) {
                case 0:
                    setText(t.getId() + "");
                    break;
                case 1:
                    setText("Von " + t.getFrom().getName());
                    break;
                case 2:
                    setText("nach " + t.getTo().getName());
                    break;
                case 3:
                    setText(sdf.format(t.getDate()));
                    break;
                case 4:
                    setText(t.getAbsolutValueAsString());
                    break;
                case 5:
                    if (t.isChecked()) {
                        setIcon(checked);
                    } else {
                        setIcon(unChecked);
                    }
                    setHorizontalAlignment(JLabel.CENTER);
                    break;
            }
        } else if (value instanceof Payment) {
            Payment p = (Payment) value;
            if (p.getType() == Payment.IN) {
                setBackground(new Color(102, 255, 102));
            } else {
                setBackground(new Color(255, 51, 51));
            }
            switch (column) {
                case 0:
                    setText(p.getId() + "");
                    break;
                case 1:
                    setText(p.getAccount().getName());
                    break;
                case 2:
                    setText(p.getPayee().getName());
                    break;
                case 3:
                    setText(sdf.format(p.getDate()));
                    break;
                case 4:
                    setText(p.getTotalAmountAsString());
                    break;
                case 5:
                    if (p.isChecked()) {
                        setIcon(checked);
                        System.out.println(checked);
                    } else {
                        setIcon(unChecked);
                    }
                    break;
            }
        }

        if (isSelected) {
            setBackground(new Color(51, 153, 255));
        }
        setOpaque(true);
        return this;
    }

}
