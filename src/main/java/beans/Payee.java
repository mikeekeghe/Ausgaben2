/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
public class Payee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int payeeID;
    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "payee")
    private List<Payment> payments;

    public Payee() {
    }
    
    

    public Payee(String name) {
        this.name = name;
    }

    public int getPayeeID() {
        return payeeID;
    }

    public void setPayeeID(int payeeID) {
        this.payeeID = payeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Payment> getTransactions() {
        return payments;
    }

    public void setTransactions(ArrayList<Payment> transactions) {
        this.payments = transactions;
    }

    @Override
    public String toString() {
        return name;
    }
    
    

}
