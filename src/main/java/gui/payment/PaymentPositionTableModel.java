/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.payment;

import beans.PaymentPosition;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bernhard
 */
public class PaymentPositionTableModel extends AbstractTableModel{
    
    private String[] colHeader = {"Bezeichnung", "Einzelpreis", "Anzahl", "Gesamtpreis", "Kategorie"};
    private ArrayList<PaymentPosition> paymentPositions;

    public PaymentPositionTableModel(ArrayList<PaymentPosition> paymentPositions) {
        this.paymentPositions = paymentPositions;
    }
    

    @Override
    public String getColumnName(int column) {
        return colHeader[column];
    }
    
    @Override
    public int getRowCount() {
        return paymentPositions.size();
    }

    @Override
    public int getColumnCount() {
        return colHeader.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PaymentPosition pp = paymentPositions.get(rowIndex);
        switch(columnIndex){
            case 0: return pp.getArticle().getName();
            case 1: return pp.getArticle().getPriceAsString();
            case 2: return pp.getQuantity()+"";
            case 3: return pp.getTotalPriceAsString();
            case 4: return pp.getCategory().getName();
            default: return "";
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    
    
}
