/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import beans.Budget;
import bl.AusgabenBl;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Bernhard
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        GUIEditableTestBean  test = new GUIEditableTestBean();
//        AddEditBeanDlg aebdlg = new AddEditBeanDlg(null, true, test);
//        aebdlg.setVisible(true);
//        if(aebdlg.isOk()){
//            System.out.println(test.getName());
//            System.out.println(test.getDate());
//            System.out.println(test.getCategory());
//            System.out.println(test.getAccount());
//            System.out.println(test.getOptional());
//        }
        
        AusgabenBl bl = AusgabenBl.getInstance();
        ArrayList<Budget> budgets = bl.getAllBudgets();
        Budget lastBudget = null;
        double sum = 0;
        for(Budget budget : budgets){
            if(budget != null){
                int diffYear = lastBudget.getValidFromYear() - budget.getValidFromYear();
                int diffMonth = diffYear * 12 + lastBudget.getValidFromMonth() - budget.getValidFromMonth();
                sum += diffMonth * lastBudget.getBudgetValue().doubleValue();
            }
            
            lastBudget = budget;
        }
        
        Date currentDate = new Date(System.currentTimeMillis());
        System.out.println(currentDate.getMonth());
        
        
        sum += lastBudget.getBudgetValue().doubleValue();
        
//        System.out.println(sum);

    }

}
