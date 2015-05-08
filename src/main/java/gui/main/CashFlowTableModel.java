/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import beans.CashFlow;
import beans.Payment;
import beans.Transfer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bernhard
 */
public class CashFlowTableModel extends AbstractTableModel {

    private ArrayList<CashFlow> cashFlow;
    private String[] headers = {"ID", "Konto", "Sender/Empfänger", "Datum", "Gesamtbetrag", "Abgeschlossen"};

    public CashFlowTableModel(ArrayList<CashFlow> cashFlow) {
        this.cashFlow = cashFlow;
    }

    @Override
    public int getRowCount() {
        return cashFlow.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CashFlow cf = cashFlow.get(rowIndex);

        
        return cf;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }



    
    
    

}
