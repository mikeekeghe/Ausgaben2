/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import annotations.GUIEditable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Bernhard1
 */
@Table
@Entity
@IdClass(BudgetPK.class)
public class Budget implements Serializable {
    

    @OneToOne
    @Id
    @GUIEditable(name = "Kategorie", showType = GUIEditable.ShowType.COMBOBOX)
    private Category category;
    @Column
    @GUIEditable(name = "Budget pro Monat")
    private BigDecimal budgetValue;
    @Id
    @Column
    @GUIEditable(name = "Gültig ab Monat")
    private int validFromMonth;
    @Id
    @Column
    @GUIEditable(name = "Gültig ab Jahr")
    private int validFromYear;

    @Transient
    private BigDecimal consumption;
    
    @Transient 
            BigDecimal budgetWithSurplus;
    @Transient
    Date validFromAsDate;

    public Budget() {
    }

    public Budget(Category category, BigDecimal budgetValue, int validFromMonth, int validFromYear, int validTillMonth, int validTillYear) {
        this.category = category;
        this.budgetValue = budgetValue;
        this.validFromMonth = validFromMonth;
        this.validFromYear = validFromYear;
    }

    

    
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getBudgetValue() {
        return budgetValue;
    }
    
    public String getBudgetValueAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(budgetValue);
    }

    public void setBudgetValue(BigDecimal budgetValue) {
        this.budgetValue = budgetValue;
    }

    public int getValidFromMonth() {
        return validFromMonth;
    }

    public void setValidFromMonth(int validFromMonth) {
        this.validFromMonth = validFromMonth;
    }

    public int getValidFromYear() {
        return validFromYear;
    }

    public void setValidFromYear(int validFromYear) {
        this.validFromYear = validFromYear;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }
    
    public String getConsumptionAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(consumption);
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }
    
    public BigDecimal getDifference(){

        return budgetValue.subtract(consumption);
    }
    
    public String getDifferenceAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.getDifference());
    }

    public BigDecimal getBudgetWithSurplus() {
        return budgetWithSurplus;
    }
    
    public String getBudgetWithSurplusAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(budgetWithSurplus);
    }

    public void setBudgetWithSurplus(BigDecimal budgetWithSurplus) {
        this.budgetWithSurplus = budgetWithSurplus;
    }
    
    public Date getValidFromAsDate(){
        
        validFromAsDate = new Date(validFromYear-1900, validFromMonth-1, 1);
        
        return validFromAsDate;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Budget other = (Budget) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "category=" + category +
                ", budgetValue=" + budgetValue +
                ", validFromMonth=" + validFromMonth +
                ", validFromYear=" + validFromYear +
                ", consumption=" + consumption +
                ", budgetWithSurplus=" + budgetWithSurplus +
                ", validFromAsDate=" + validFromAsDate +
                '}';
    }
}
