/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Account;
import beans.Article;
import beans.Budget;
import beans.CashFlow;
import beans.Category;
import enums.CategoryType;
import beans.InvoiceCopy;
import beans.Payee;
import beans.Payment;
import beans.PaymentPosition;
import beans.PeriodicFundType;
import beans.Record;
import beans.UserConstraint;
import db.Database;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bernhard
 */
public class AusgabenBl {

    private ArrayList<Account> accounts;
    private ArrayList<Category> billCategories;
    private ArrayList<Category> documentCategories;
    private ArrayList<Article> articles;
    private ArrayList<Payee> payees;
    private ArrayList<PeriodicFundType> periodicFundTypes;
    private static AusgabenBl instance;
    private Database db;

    private Category rootBillCategory;
    private Category rootDocumentCategory;

    private HashMap<String, UserConstraint> userConstraints;

    private AusgabenBl() {
        db = Database.getInstance();
        accounts = db.getAll(Account.class);
        articles = db.getAll(Article.class);
        payees = db.getAll(Payee.class);

        billCategories = db.getAllCategories(CategoryType.BILL);
        documentCategories = db.getAllCategories(CategoryType.DOCUMENT);

        rootBillCategory = getBillCategoryByName("Rechnungskategorien");
        rootDocumentCategory = getDocumentCategoryByName("Dokumentenkategorien");
        if (rootBillCategory == null || rootDocumentCategory == null) {
            try {
                throw new Exception("Category roots not set!");
            } catch (Exception ex) {
                Logger.getLogger(AusgabenBl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        userConstraints = db.getUserConstraints();
        periodicFundTypes = db.getAll(PeriodicFundType.class);

    }

    public static AusgabenBl getInstance() {
        if (instance == null) {
            instance = new AusgabenBl();
        }

        return instance;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Category> getBillCategories() {
        return billCategories;
    }

    public void addObj(Object obj) {
        db.addObj(obj);
        if (obj instanceof Account) {
            accounts.add((Account) obj);
        } else if (obj instanceof Category) {
            Category cat = (Category) obj;
            if (cat.getType().equals(CategoryType.BILL)) {
                billCategories.add(cat);
            } else {
                documentCategories.add(cat);
            }
        } else if (obj instanceof Article) {
            articles.add((Article) obj);
        } else if (obj instanceof Payee) {
            payees.add((Payee) obj);
        }
    }

    public void removeObj(Object obj) {
        db.deleteObj(obj);
        if (obj instanceof Account) {
            accounts.remove(obj);
        } else if (obj instanceof Category) {
            Category cat = (Category) obj;
            if (cat.getType().equals(CategoryType.BILL)) {
                billCategories.remove(obj);
            } else if (cat.getType().equals(CategoryType.DOCUMENT)) {
                documentCategories.remove(obj);
            }
        }
    }

    public void updateObj(Object obj) {
        db.updateObj(obj);
    }

    public ArrayList<Category> getCategoriesByPattern(String pattern, CategoryType type) {
        return db.getCategoriesByPattern(pattern, type);
    }

    public ArrayList<Payee> getPayeesByPattern(String pattern) {
        return db.getPayeesByPattern(pattern);
    }

    public Category getBillCategoryByName(String name) {
        Category cat = null;
        for (Category c : billCategories) {
            if (c.getName().equalsIgnoreCase(name)) {
                cat = c;
            }

        }
        return cat;
    }

    public Category getDocumentCategoryByName(String name) {
        Category cat = null;
        for (Category c : documentCategories) {
            if (c.getName().equalsIgnoreCase(name)) {
                cat = c;
            }

        }
        return cat;
    }

    public boolean hasAccountPayments(Account acc) {
        return db.hasAccountPayments(acc);
    }

    public Account getAccountByName(String name) {
        for (Account acc : accounts) {
            if (acc.getName().equalsIgnoreCase(name)) {
                return acc;
            }
        }

        return null;
    }

    public Payee getPayeeByName(String payeeName) {
        for (Payee p : payees) {
            if (p.getName().equalsIgnoreCase(payeeName)) {
                return p;
            }
        }

        return null;
    }

    public ArrayList<Category> getDocumentCategories() {
        return documentCategories;
    }

    public ArrayList<CashFlow> getCashFlow(java.sql.Date from, java.sql.Date to) {
        return db.getCashFlow(from, to);
    }

    public Category getRootBillCategory() {
        return rootBillCategory;
    }

    public Article getArticle(String name, BigDecimal price) {
        Article article = null;
        for (Article a : articles) {
            if (a.getName().equalsIgnoreCase(name) && a.getPrice().doubleValue() == price.doubleValue()) {
                article = a;
            }
        }

        if (article == null) {
            //it wasn't found, so create a new one
            article = new Article(name, price);
            addObj(article);
        }

        return article;
    }

    public void deleteAllPaymentPositions(Payment p) {
        db.deleteAllPaymentPositions(p);
    }

    public void deleteDocumentPages(InvoiceCopy ic) {
        db.deleteDocumentPages(ic);
    }

    public BigDecimal getAccountBalance(Account acc) {
        return db.getAccountBalance(acc);
    }

    public ArrayList<CashFlow> getCashFlow(java.sql.Date from, java.sql.Date to, Account account) {
        return db.getCashFlow(from, to, account);
    }

    public boolean isBillCategoryInUse(Category selected) {
        return db.isBillCategoryInUse(selected);
    }

    public boolean isRecordCategoryInUse(Category selected) {
        return db.isRecordCategoryInUse(selected);
    }

    public boolean doesSubCategoryExist(Category selected, String name) {
        for (Category cat : selected.getSubCategories()) {
            if (cat.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Payment> getPayments(String payee, Account account, BigDecimal exactAmount,
            BigDecimal fromAmount, BigDecimal toAmount,
            java.sql.Date exactDate, java.sql.Date fromDate, java.sql.Date toDate,
            boolean withCopy, boolean withoutCopy, int type) {
        return db.getPayments(payee, account, exactAmount, fromAmount, toAmount,
                exactDate, fromDate, toDate,
                withCopy, withoutCopy, type);
    }

    public boolean isPayeeInUse(Payee payee) {
        return db.isPayeeInUse(payee);
    }

    public ArrayList<Record> getReocrdsByCategory(Category cat) {
        return db.getReocrdsByCategory(cat);
    }

    public ArrayList<PaymentPosition> getPaymentPositionsByCategory(Category cat, java.sql.Date from, java.sql.Date to) {
        return db.getPaymentPositionsByCategory(cat, from, to);
    }

    public ArrayList<CashFlow> getUncheckedCashFlow(Account account) {
        return db.getUncheckedCashFlow(account);
    }

    public HashMap<String, UserConstraint> getAllUserConstraints() {
        return db.getUserConstraints();
    }

    public ArrayList<Object> getAll(Class<?> type) {
        return db.getAll(type);
    }
    
    public ArrayList<Budget> getAllBudgets(){
        return db.getAllBudgets();
    }
}
