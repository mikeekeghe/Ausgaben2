/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "PeriodicFundType")
public class PeriodicFundType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "periodicFundTypeID")
    private Integer periodicFundTypeID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public PeriodicFundType() {
    }

    public PeriodicFundType(Integer periodicFundTypeID) {
        this.periodicFundTypeID = periodicFundTypeID;
    }

    public PeriodicFundType(Integer periodicFundTypeID, String name) {
        this.periodicFundTypeID = periodicFundTypeID;
        this.name = name;
    }

    public Integer getPeriodicFundTypeID() {
        return periodicFundTypeID;
    }

    public void setPeriodicFundTypeID(Integer periodicFundTypeID) {
        this.periodicFundTypeID = periodicFundTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (periodicFundTypeID != null ? periodicFundTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodicFundType)) {
            return false;
        }
        PeriodicFundType other = (PeriodicFundType) object;
        if ((this.periodicFundTypeID == null && other.periodicFundTypeID != null) || (this.periodicFundTypeID != null && !this.periodicFundTypeID.equals(other.periodicFundTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.PeriodicFundType[ periodicFundTypeID=" + periodicFundTypeID + " ]";
    }
    
}
