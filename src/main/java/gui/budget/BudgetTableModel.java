/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.budget;

import beans.Budget;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bernhard1
 */
public class BudgetTableModel extends AbstractTableModel{
    
    private ArrayList<Budget> budgets;
    private String[] headers = {"Kategorie", "Budget", "Budget mit Übertrag", "Verbrauch", "Differenz aktueller Monat"};

    public BudgetTableModel(ArrayList<Budget> budgets) {
        this.budgets = budgets;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }


    @Override
    public int getRowCount() {
        return budgets.size();
    }

    @Override
    public int getColumnCount() {
       return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return budgets.get(rowIndex);
    }
    
}
