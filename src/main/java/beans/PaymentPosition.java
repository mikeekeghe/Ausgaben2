/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
@IdClass(PaymentPositionPK.class)
public class PaymentPosition implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    private Article article;
    @Id
    @OneToOne
    private Payment payment;

    @Column(nullable = false)
    private int quantity;
    @OneToOne
    private Category category;

    public PaymentPosition() {
        //primKey = new PaymentPositionPK();
    }

    public PaymentPosition(Article article, Payment payment, int quantity, Category category) {
        this.quantity = quantity;
        // primKey = new PaymentPositionPK(article, payment);
        this.category = category;
        this.article = article;
        this.payment = payment;
    }

//    public PaymentPositionPK getPrimKey() {
//        return primKey;
//    }
//
//    public void setPrimKey(PaymentPositionPK primKey) {
//        this.primKey = primKey;
//    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Article getArticle() {
        return article;
    }

    public Payment getPayment() {
        return payment;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
//    

    public String getTotalPriceAsString() {
        BigDecimal totalPrice = article.getPrice().multiply(new BigDecimal(quantity));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(totalPrice.doubleValue());
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = article.getPrice().multiply(new BigDecimal(quantity));
        return totalPrice;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.article);
        hash = 13 * hash + Objects.hashCode(this.payment);
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
        final PaymentPosition other = (PaymentPosition) obj;
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        if (!Objects.equals(this.payment, other.payment)) {
            return false;
        }
        return true;
    }

}
