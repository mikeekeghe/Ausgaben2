/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author bwaidach
 */
@Entity
@Table
@PrimaryKeyJoinColumn(name="RecordID")
public class Record extends Document{
    String title;
    String description;

    public Record() {
    }

    public Record(String title, String description, List<DocumentPage> documentPages, Category category) {
        super(documentPages, category);
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        return title + ", " +sdf.format(dateAdded);
    }
    
    
    
    
}
