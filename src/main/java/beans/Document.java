/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bernhard
 */
@Table
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Document implements Serializable{
    @OneToMany(mappedBy = "document", fetch = FetchType.EAGER)
    protected List<DocumentPage> documentPages;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int documentID;
    
    @OneToOne(optional = false)
    protected Category category;
    
    @Column
    protected Timestamp dateAdded;

    public Document() {
    }

    public Document(List<DocumentPage> documentPages, Category category) {
        this.documentPages = documentPages;
        this.category = category;
    }

    public Document(List<DocumentPage> documentPages, Category category, Timestamp dateAdded) {
        this.documentPages = documentPages;
        this.category = category;
        this.dateAdded = dateAdded;
    }
    
    public Document(Category category) {
        this.category = category;
    }

    public List<DocumentPage> getDocumentPages() {
        return documentPages;
    }

    public void setDocumentPages(List<DocumentPage> documentPages) {
        this.documentPages = documentPages;
    }

    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    
    

}
