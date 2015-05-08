/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import annotations.GUIEditable;
import enums.CategoryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.transaction.Transactional;

/**
 *
 * @author Bernhard
 */


@Table
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public  class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;
    @Column(nullable = false)
    @GUIEditable(name = "Bezeichnung")
    private String name;

    @ManyToOne()
    private Category priorCategory;

    @OneToMany(mappedBy = "priorCategory", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Category> subCategories;
    @Column(nullable = false)
    private int level;
    
    @Enumerated(EnumType.STRING)
    @GUIEditable(name = "Type")
    private CategoryType type;

    public Category(String name, Category priorCategory, int level, CategoryType type) {
        this.name = name;
        this.priorCategory = priorCategory;
        this.level = level;
        subCategories = new ArrayList<>();
        this.type = type;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getPriorCategory() {
        return priorCategory;
    }

    public void setPriorCategory(Category priorCategory) {
        this.priorCategory = priorCategory;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.categoryID;
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
        final Category other = (Category) obj;
        if (this.categoryID != other.categoryID) {
            return false;
        }
        return true;
    }
    


}
