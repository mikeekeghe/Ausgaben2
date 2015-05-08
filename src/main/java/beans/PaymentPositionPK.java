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
 * @author Bernhard
 */

public class PaymentPositionPK implements Serializable{
     Article article;
     Payment payment;

    

    public PaymentPositionPK() {
    }

    public PaymentPositionPK(Article article, Payment payment) {
        this.article = article;
        this.payment = payment;

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.article);
        hash = 23 * hash + Objects.hashCode(this.payment);
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
        final PaymentPositionPK other = (PaymentPositionPK) obj;
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
