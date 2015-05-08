/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import enums.UserConstraintType;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bernhard1
 */
@Table(name = "user_constraints")
@Entity
public class UserConstraint implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String key;
    @Column(nullable = false)
    private String value;
    @Enumerated(EnumType.STRING)
    private UserConstraintType type;

    public UserConstraint() {
    }

    public UserConstraint(String key, String value, UserConstraintType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        if(type == UserConstraintType.DATE){
            int year = 0;
            int day = 0;
            int month = 0;
            
            if(value.contains("-")){
                String parts[] = value.split("-");
                day = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                year = Integer.parseInt(parts[2]);
                
                Date date = new Date(year-1900, month-1, day);
                
                return date;
            }
        }else if(type == UserConstraintType.FLOAT){
            value = value.replaceAll(",", ".");
            float floatValue = Float.parseFloat(value);
            return floatValue;
        }else if(type == UserConstraintType.NUMERIC){
            int intValue = Integer.parseInt(value);
            return intValue;
        }else{
            return value;
        }
        return null;
        
    }
    

    public void setValue(String value) {
        this.value = value;
    }

    public UserConstraintType getType() {
        return type;
    }

    public void setType(UserConstraintType type) {
        this.type = type;
    }
    
     

    @Override
    public int hashCode() {
        int hash = 3;
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
        final UserConstraint other = (UserConstraint) obj;
        if (this.key != other.key) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return key + ": " + value;
    }
    
    
    
}
