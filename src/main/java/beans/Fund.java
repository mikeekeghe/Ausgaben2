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
public class Fund implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fundID;
    @Column(unique = true)
    private String name;
    @Column
    private BigDecimal soll;
    @Column
    private Account physicalAccount;

    public int getFundID() {
        return fundID;
    }

    public void setFundID(int fundID) {
        this.fundID = fundID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSoll() {
        return soll;
    }

    public void setSoll(BigDecimal soll) {
        this.soll = soll;
    }

    public Account getPhysicalAccount() {
        return physicalAccount;
    }

    public void setPhysicalAccount(Account physicalAccount) {
        this.physicalAccount = physicalAccount;
    }

    @Override
    public String toString() {
        return "Fund{" + "fundID=" + fundID + ", name=" + name + ", soll=" + soll + ", physicalAccount=" + physicalAccount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.fundID;
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
        final Fund other = (Fund) obj;
        if (this.fundID != other.fundID) {
            return false;
        }
        return true;
    }
    
    
    
}
