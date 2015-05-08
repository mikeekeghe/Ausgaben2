/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author Bernhard1
 */
@Table
@Entity
public class PeriodicFund implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int perioidFundID;
    
    @Column(columnDefinition = "fundID")
    @OneToOne
    private Fund fund;
    @Column(columnDefinition = "periodicFundTypeID")
    @OneToOne
    private PeriodicFundType periodicFundType;
    @Column
    private Date lastTimeAsked;
    @Column
    private Date nextTimeAsked;
    @Column
    private long periodInSeconds;
    @Column
    @OneToOne
    private Fund fromFund;
    @Column
    @OneToOne
    private Fund toFund;
    @Column
    private BigDecimal absolutValue;

    public PeriodicFund(int perioidFundID, Fund fund, PeriodicFundType periodicFundType, Date lastTimeAsked, Date nextTimeAsked, long periodInSeconds, Fund fromFund, Fund toFund, BigDecimal absolutValue) {
        this.perioidFundID = perioidFundID;
        this.fund = fund;
        this.periodicFundType = periodicFundType;
        this.lastTimeAsked = lastTimeAsked;
        this.nextTimeAsked = nextTimeAsked;
        this.periodInSeconds = periodInSeconds;
        this.fromFund = fromFund;
        this.toFund = toFund;
        this.absolutValue = absolutValue;
    }

    public PeriodicFund() {
    }
    
    

    public int getPerioidFundID() {
        return perioidFundID;
    }

    public void setPerioidFundID(int perioidFundID) {
        this.perioidFundID = perioidFundID;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public PeriodicFundType getPeriodicFundType() {
        return periodicFundType;
    }

    public void setPeriodicFundType(PeriodicFundType periodicFundType) {
        this.periodicFundType = periodicFundType;
    }

    public Date getLastTimeAsked() {
        return lastTimeAsked;
    }

    public void setLastTimeAsked(Date lastTimeAsked) {
        this.lastTimeAsked = lastTimeAsked;
    }

    public Date getNextTimeAsked() {
        return nextTimeAsked;
    }

    public void setNextTimeAsked(Date nextTimeAsked) {
        this.nextTimeAsked = nextTimeAsked;
    }

    public long getPeriodInSeconds() {
        return periodInSeconds;
    }

    public void setPeriodInSeconds(long periodInSeconds) {
        this.periodInSeconds = periodInSeconds;
    }

    public Fund getFromFund() {
        return fromFund;
    }

    public void setFromFund(Fund fromFund) {
        this.fromFund = fromFund;
    }

    public Fund getToFund() {
        return toFund;
    }

    public void setToFund(Fund toFund) {
        this.toFund = toFund;
    }

    public BigDecimal getAbsolutValue() {
        return absolutValue;
    }

    public void setAbsolutValue(BigDecimal absolutValue) {
        this.absolutValue = absolutValue;
    }

    @Override
    public String toString() {
        return "PeriodicFund{" + "perioidFundID=" + perioidFundID + ", fund=" + fund + ", periodicFundType=" + periodicFundType + ", lastTimeAsked=" + lastTimeAsked + ", nextTimeAsked=" + nextTimeAsked + ", periodInSeconds=" + periodInSeconds + ", fromFund=" + fromFund + ", toFund=" + toFund + ", absolutValue=" + absolutValue + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.perioidFundID;
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
        final PeriodicFund other = (PeriodicFund) obj;
        if (this.perioidFundID != other.perioidFundID) {
            return false;
        }
        return true;
    }
    
    
}
