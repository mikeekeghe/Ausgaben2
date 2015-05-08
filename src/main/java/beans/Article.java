/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Entity
@Table
public class Article implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int articleID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;

    public Article(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Article() {
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public String getPriceAsString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(price.doubleValue());
    }
    
    
}
