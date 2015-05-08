/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import annotations.GUIEditable;
import beans.Account;
import beans.Category;
import java.sql.Date;

/**
 *
 * @author Bernhard1
 */
public class GUIEditableTestBean {
    @GUIEditable(name = "Bezeichnung")
    private String name;
    @GUIEditable(name = "Datum")
    private Date date;
    @GUIEditable(name = "Kategorie", showType = GUIEditable.ShowType.LIST)
    private Category category;
    @GUIEditable(name = "Konto", showType = GUIEditable.ShowType.COMBOBOX)
    private Account account;
    @GUIEditable(name = "Optionale Zahl", optional = true)
    private float optional;

    public GUIEditableTestBean(String name, Date date, Category category, Account account, float optional) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.account = account;
        this.optional = optional;
    }

    public GUIEditableTestBean() {
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public float getOptional() {
        return optional;
    }

    public void setOptional(float optional) {
        this.optional = optional;
    }
    
    
    
}
