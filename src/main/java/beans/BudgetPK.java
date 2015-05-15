/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bernhard1
 */
public class BudgetPK implements Serializable{
    private Category category;
    private int month;
    private int year;

    public BudgetPK() {
    }

    public BudgetPK(Category category, int month, int year) {
        this.category = category;
        this.month = month;
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.category);
        hash = 29 * hash + this.month;
        hash = 29 * hash + this.year;
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
        final BudgetPK other = (BudgetPK) obj;
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (this.month != other.month) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        return true;
    }
    
    
}
