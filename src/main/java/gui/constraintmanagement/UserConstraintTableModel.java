/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.constraintmanagement;

import beans.UserConstraint;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bernhard1
 */
public class UserConstraintTableModel extends AbstractTableModel{
    
    private ArrayList<UserConstraint> constraints;
    private String[] columns = {"Bezeichnung", "Typ", "Wert"};

    public UserConstraintTableModel(ArrayList<UserConstraint> constraints) {
        this.constraints = constraints;
    }
    
    

    @Override
    public int getRowCount() {
        return constraints.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            UserConstraint uc = constraints.get(rowIndex);
            switch(columnIndex){
                case 0: return uc.getKey();
                case 1: return uc.getType();
                case 2: return uc.getValue();
                default: return "";
            }
       }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    
    
}
