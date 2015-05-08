/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bernhard1
 */

@Entity
@Table
public class Income implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int incomeID;
    @Column
    private BigDecimal incomeValue;
    @Column
    private int validFromMonth;
    @Column
    private int validFromYear;

    public Income() {
    }

    public Income(int incomeID, BigDecimal incomeValue, int validFromMonth, int validFromYear) {
        this.incomeID = incomeID;
        this.incomeValue = incomeValue;
        this.validFromMonth = validFromMonth;
        this.validFromYear = validFromYear;
    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public BigDecimal getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(BigDecimal incomeValue) {
        this.incomeValue = incomeValue;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.incomeID;
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
        final Income other = (Income) obj;
        if (this.incomeID != other.incomeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Income{" + "incomeID=" + incomeID + ", incomeValue=" + incomeValue + ", validFromMonth=" + validFromMonth + ", validFromYear=" + validFromYear + '}';
    }
    
    
}
