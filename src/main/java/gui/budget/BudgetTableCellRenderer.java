package gui.budget;

import beans.Budget;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Bernhard1 on 09.05.15.
 */
public class BudgetTableCellRenderer extends JLabel implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Budget budget = (Budget) value;
        JLabel label = new JLabel();
        label.setOpaque(true);
        switch(column){
            case 0: label.setText(budget.getCategory().getName());break;
            case 1: label.setText(budget.getBudgetValueAsString());break;
            case 2: label.setText(budget.getBudgetWithSurplusAsString());break;
            case 3: label.setText(budget.getConsumptionAsString());break;
            case 4: label.setText(budget.getDifferenceAsString());break;
            default: label.setText("");
        }

        if(budget.getDifference().doubleValue() < 0 && column == 4){
            label.setBackground(new Color(241, 110, 88));
            label.setForeground(new Color(196, 27, 22));
            label.setFont(label.getFont().deriveFont(Font.BOLD));

        }else if (budget.getDifference().doubleValue() > 0 && column == 4){
            label.setForeground(new Color(67, 128, 53));
            label.setBackground(new Color(113, 216, 89));
            label.setFont(label.getFont().deriveFont(Font.BOLD));
        }

        return label;
    }
}
