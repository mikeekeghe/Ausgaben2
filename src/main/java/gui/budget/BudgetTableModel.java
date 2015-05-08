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
    private String[] headers = {"Kategorie", "Budget", "Budget mit Übertrag", "Verbrauch", "Differenz"};

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
        Budget budget = budgets.get(rowIndex);
        switch(columnIndex){
            case 0: return budget.getCategory().getName();
            case 1: return budget.getBudgetValueAsString();
            case 2: return budget.getBudgetWithSurplusAsString();
            case 3: return budget.getConsumptionAsString();
            case 4: return budget.getDifferenceAsString();
            default: return null;
        }
    }
    
}
