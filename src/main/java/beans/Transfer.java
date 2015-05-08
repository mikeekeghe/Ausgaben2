/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name="TransferID")
public class Transfer extends CashFlow implements Serializable {
    

    @OneToOne(optional = false)
    private Account from;
    @OneToOne(optional = false)
    private Account to;
    @Column(nullable = false)
    private BigDecimal absolutValue;

    public Transfer() {
    }

    public Transfer(Account from, Account to, BigDecimal absolutValue, Date date) {
        this.from = from;
        this.to = to;
        this.absolutValue = absolutValue;
        this.date = date;
    }

    public int getTransferID() {
        return super.getId();
    }

    public void setTransferID(int transferID) {
        super.setId(transferID);
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public BigDecimal getAbsolutValue() {
        return absolutValue;
    }

    public void setAbsolutValue(BigDecimal absolutValue) {
        this.absolutValue = absolutValue;
    }

    @Override
    public Date getDate() {
        return super.getDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDate(Date date) {
        super.setDate(date); //To change body of generated methods, choose Tools | Templates.
    }

    public String getAbsolutValueAsString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(absolutValue.doubleValue());
    }

    public void setData(Account from, Account to, BigDecimal value, Date newDate) {
        this.from = from;
        this.to = to;
        this.absolutValue = value;
        this.date = newDate;
    }
    
    
}
