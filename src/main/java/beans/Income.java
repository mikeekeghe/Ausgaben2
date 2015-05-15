/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author Bernhard1
 */

@Entity
@Table
@IdClass(IncomePK.class)
public class Income implements Serializable {

    @Column
    private BigDecimal incomeValue;
    @Id
    @Column
    private int month;
    @Id
    @Column
    private int year;

    public Income() {
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        this.month = cal.get(Calendar.MONTH)+1;
        this.year = cal.get(Calendar.YEAR);
    }

    public Income(int incomeID, BigDecimal incomeValue) {
        this.incomeValue = incomeValue;
        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
        this.month = cal.get(Calendar.MONTH)+1;
        this.year = cal.get(Calendar.YEAR);
    }

    public BigDecimal getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(BigDecimal incomeValue) {
        this.incomeValue = incomeValue;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int validFromMonth) {
        this.month = validFromMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int validFromYear) {
        this.year = validFromYear;
    }

    public String getIncomeAsString(){
        NumberFormat numberFormat  = NumberFormat.getCurrencyInstance();
        return numberFormat.format(incomeValue.doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (month != income.month) return false;
        return year == income.year;

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "Income{" +
                "incomeValue=" + incomeValue +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
