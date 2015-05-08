/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Account;
import beans.Budget;
import beans.CashFlow;
import beans.Category;
import enums.CategoryType;
import beans.InvoiceCopy;
import beans.Payee;
import beans.Payment;
import beans.PaymentPosition;
import beans.Record;
import beans.Transfer;
import beans.UserConstraint;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Bernhard
 */
public class Database {

    private static Database instance;
    private Session session;
    private SessionFactory sessionFactory;
    public static String configFile;

    private Database() {
        if (configFile == null) {
//            configFile = Database.class.getResource("/hibernate.cfg.xml").toString();/
            configFile = "hibernate.cfg.xml";
        }
        File file = new File(configFile);
        System.out.println(file.getAbsolutePath());
        Configuration configuration = new Configuration();
        configuration.configure(configFile);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void close() {
        session.close();
    }

    public ArrayList getAll(Class c) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from " + c.getName());
        ArrayList data = new ArrayList();
        data.addAll(query.list());
        session.close();
        return data;
    }

    public ArrayList getAllCategories(CategoryType type) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Category where type = '" + type.name() + "'");
        ArrayList data = new ArrayList();
        data.addAll(query.list());
        session.close();
        return data;
    }

    public void addObj(Object obj) {
        session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();
        try {
            session.save(obj);
            ta.commit();
            session.close();
        } catch (Exception e) {
            ta.rollback();
            session.close();
            throw e;
        }

    }

    public void deleteObj(Object obj) {
        session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();
        session.delete(obj);
        ta.commit();
        session.close();
    }


    public void updateObj(Object obj) {
        session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();
        session.update(obj);
        ta.commit();
        session.close();
    }

    public ArrayList<Category> getCategoriesByPattern(String pattern, CategoryType type) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Categoy where name like :search AND type = :t");
        query.setString("search", "%" + pattern + "%");
        query.setString("t", type.name());
        ArrayList data = new ArrayList();
        data.addAll(query.list());
        session.close();
        return data;
    }

    public ArrayList<Payee> getPayeesByPattern(String pattern) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from Payee where name like :search");
        query.setString("search", "%" + pattern + "%");
        ArrayList<Payee> data = new ArrayList();
        data.addAll(query.list());
        session.close();
        return data;
    }

    public boolean hasAccountPayments(Account acc) {
        session = sessionFactory.openSession();

        Number count = (Number) session.createCriteria(Payment.class)
                .add(Restrictions.eq("account", acc))
                .setProjection(Projections.rowCount()).uniqueResult();
        if (count.intValue() == 0) {
            return false;
        }

        count = (Number) session.createCriteria(Transfer.class)
                .add(Restrictions.eq("to", acc))
                .add(Restrictions.eq("from", acc))
                .setProjection(Projections.rowCount()).uniqueResult();
        if (count.intValue() == 0) {
            return false;
        }

        session.close();
        return true;
    }

    public ArrayList<CashFlow> getCashFlow(Date begin, Date end, Account acc) {
        session = sessionFactory.openSession();
        ArrayList<CashFlow> cashFlow = new ArrayList<>();
        List transfers = session.createCriteria(Transfer.class)
                .add(Restrictions.disjunction()
                        .add(Restrictions.eq("from", acc))
                        .add(Restrictions.eq("to", acc))
                )
                .add(Restrictions.between("date", begin, end)).list();

        List payments = session.createCriteria(Payment.class)
                .add(Restrictions.eq("account", acc))
                .add(Restrictions.between("date", begin, end)).list();

        int lastid = 0;
        for (int i = payments.size() - 1; i >= 0; i--) {
            Payment p = (Payment) payments.get(i);
            if (p.getId() != lastid) {
                lastid = p.getId();
            } else {
                payments.remove(i);
            }
        }

        cashFlow.addAll(transfers);
        cashFlow.addAll(payments);
        session.close();
        return cashFlow;
    }

    public ArrayList<CashFlow> getCashFlow(Date begin, Date end) {
        session = sessionFactory.openSession();
        ArrayList<CashFlow> cashFlow = new ArrayList<>();
        List transfers = session.createCriteria(Transfer.class)
                .add(Restrictions.between("date", begin, end)).list();

        List payments = session.createCriteria(Payment.class)
                .add(Restrictions.between("date", begin, end))
                .addOrder(Order.asc("id"))
                .list();

        int lastid = 0;
        for (int i = payments.size() - 1; i >= 0; i--) {
            Payment p = (Payment) payments.get(i);
            if (p.getId() != lastid) {
                lastid = p.getId();
            } else {
                payments.remove(i);
            }
        }

        cashFlow.addAll(transfers);
        cashFlow.addAll(payments);
        session.close();
        return cashFlow;
    }

    public void savePayment(Payment p) {

    }

    public ArrayList<PaymentPosition> getAllPaymentPostions(int id) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from PaymentPostion where payment_paymentid = " + id);
        ArrayList<PaymentPosition> data = new ArrayList();
        data.addAll(query.list());
        session.close();
        return data;
    }

    public void deleteAllPaymentPositions(Payment p) {
        session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM PaymentPosition where payment_paymentid = " + p.getId());
        query.executeUpdate();
        ta.commit();
        session.close();
    }

    public void deleteDocumentPages(InvoiceCopy ic) {
        session = sessionFactory.openSession();
        Transaction ta = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM DocumentPage WHERE document_documentid = " + ic.getDocumentID());
        query.executeUpdate();
        ta.commit();
        session.close();
    }

    public BigDecimal getAccountBalance(Account acc) {
        session = sessionFactory.openSession();
        BigDecimal balance = new BigDecimal(0);
        String sql = "select SUM(a.price * pp.quantity * p.type) "
                + "from payment p\n "
                + "inner join paymentposition pp ON (p.PaymentID = pp.payment_PaymentID)\n "
                + "inner join article a ON(a.articleID = pp.article_articleID)\n "
                + "where p.account_accountID = " + acc.getAccountID();

        Query query = session.createSQLQuery(sql);
        if (query.uniqueResult() != null) {
            balance = balance.add((BigDecimal) query.uniqueResult());
        }

        sql = "select sum(t.absolutValue*-1)\n"
                + "from transfer t\n"
                + "where t.from_accountID = " + acc.getAccountID();

        Query query2 = session.createSQLQuery(sql);
        if (query2.uniqueResult() != null) {
            balance = balance.add((BigDecimal) query2.uniqueResult());
        }

        sql = "select sum(t.absolutValue)\n"
                + "from transfer t\n"
                + "where t.to_accountID = " + acc.getAccountID();

        Query query3 = session.createSQLQuery(sql);
        if (query3.uniqueResult() != null) {
            balance = balance.add((BigDecimal) query3.uniqueResult());
        }
        session.close();
        return balance;
    }

    public boolean isBillCategoryInUse(Category cat) {
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select count(*)\n"
                + "from paymentposition pp \n"
                + "where category_categoryID IN (\n"
                + "SELECT distinct categoryID\n"
                + "FROM category\n"
                + "where priorCategory_categoryID = :id\n"
                + ") \n"
                + "OR category_categoryID = :id");
        query.setInteger("id", cat.getCategoryID());

        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        }

        session.close();
        return false;
    }

    public boolean isRecordCategoryInUse(Category cat) {
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select count(*)\n"
                + "from document d \n"
                + "where category_categoryID IN (\n"
                + "SELECT distinct categoryID\n"
                + "FROM category\n"
                + "where priorCategory_categoryID = :id\n"
                + ") \n"
                + "OR category_categoryID = :id");
        query.setInteger("id", cat.getCategoryID());

        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        }

        session.close();
        return false;
    }

    public ArrayList<Payment> getPayments(String payee, Account account, BigDecimal exactAmount,
            BigDecimal fromAmount, BigDecimal toAmount,
            Date exactDate, Date fromDate, Date toDate,
            boolean withCopy, boolean withoutCopy, int type) {
        session = sessionFactory.openSession();
        ArrayList<Payment> payments = new ArrayList<>();

        String sql = "select * from payment paym inner join cashflow cf on(paym.paymentid = cf.id)";

        //bild joins
        if (payee != null) {
            sql += "inner join payee pay ON(paym.payee_payeeid = pay.payeeid) ";
        }

        //bild where clause
        sql += " WHERE paym.paymentid > 0 "; //make dummy clause, to easily add new clauses

        if (payee != null) {
            sql += " AND pay.name LIKE '" + payee + "' ";
        }
        if (account != null) {
            sql += " AND account_accountid = " + account.getAccountID();
        }
        if (withCopy && withoutCopy) {
            //do nothing
        } else if (withCopy) {
            sql += " AND invoicecopy_invoicecopy_id IS NOT NULL ";
        } else if (withoutCopy) {
            sql += " AND  invoicecopy_invoicecopy_id IS NULL ";
        }
        if (exactAmount != null || (fromAmount != null && toAmount != null)) {
            sql += " AND ( select SUM(a.price * pp.quantity)\n"
                    + "from  paymentposition pp \n"
                    + "inner join article a ON(a.articleID = pp.article_articleID) \n"
                    + "where pp.payment_PaymentID = paym.paymentid ) ";

            if (exactAmount != null) {
                sql += " = " + exactAmount.doubleValue();
            } else if (fromAmount != null && toAmount != null) {
                sql += " BETWEEN " + fromAmount.doubleValue() + " AND " + toAmount.doubleValue() + " ";
            }

        }
        if (type != 0) {
            sql += " AND paym.type = " + type + " ";
        }
        if (exactDate != null) {
            sql += " AND cf.date = '" + exactDate.toString() + "' ";
        }
        if (fromDate != null && toDate != null) {
            sql += " AND cf.date BETWEEN '" + fromDate.toString() + "' AND '" + toDate.toString() + "' ";
        }

        Query query = session.createSQLQuery(sql).addEntity(Payment.class);

        List<Payment> list = query.list();

//        List list = criteria.list();
        payments.addAll(list);
//        int lastid = 0;
//        for (int i = payments.size() - 1; i >= 0; i--) {
//            Payment p = (Payment) payments.get(i);
//            if (p.getId() != lastid) {
//                lastid = p.getId();
//            } else {
//                payments.remove(i);
//            }
//        }

        session.close();
        return payments;
    }

    public void bla() {
        String[] s = sessionFactory.getClassMetadata(Payment.class).getPropertyNames();
        for (String ss : s) {
            System.out.println(ss);
        }
    }

    public static void main(String[] args) {
//        Account acc = new Account();
//        acc.setAccountID(2);
//        getInstance().bla();
//        Date a = new Date(2014 - 1900, 11, 1);
//        Date b = new Date(2014 - 1900, 11, 31);
//        getInstance().getTotalExpensesByCategory(a, b);

    }

    public boolean isPayeeInUse(Payee payee) {
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery("select count(*)\n"
                + "from payment where payee_payeeid = :id");
        query.setInteger("id", payee.getPayeeID());

        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        }

        session.close();
        return false;
    }

    public HashMap<String, BigDecimal> getTotalExpensesByCategory(Date from, Date to) {
        session = sessionFactory.openSession();
        HashMap<String, BigDecimal> expenses = new HashMap<>();
        String sql = "select c.name, sum(pp.quantity * a.price)\n"
                + "from payment p\n"
                + "inner join paymentposition pp ON(p.PaymentID = pp.payment_paymentid)\n"
                + "inner join article a ON(a.articleID =pp.article_articleID)\n"
                + "inner join category c ON(c.categoryID = pp.category_categoryID)\n"
                + "inner join cashflow cf ON(cf.id = p.paymentid) "
                + "where p.type = -1\n"
                + "AND cf.date BETWEEN '" + from.toString() + "' AND '" + to.toString() + "' \n"
                + "GROUP BY pp.category_categoryID";

        Query query = session.createSQLQuery(sql);
        List<Object[]> rows = query.list();
        for (Object[] row : rows) {
            expenses.put((String) row[0], (BigDecimal) row[1]);
        }
        session.close();

        return expenses;
    }

    public ArrayList<PaymentPosition> getPaymentPositionsByCategory(Category cat, Date from, Date to) {
        ArrayList<PaymentPosition> positions = new ArrayList<>();
        String sql = "select *\n"
                + "from paymentposition pp\n"
                + "inner join payment p on(pp.payment_paymentid = p.paymentid)\n"
                + "inner join cashflow cf on(p.PaymentID = cf.id)\n"
                + "WHERE pp.category_categoryID = :id AND cf.date between :from AND :to "
                + "order by p.paymentid";
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(PaymentPosition.class);
        query.setInteger("id", cat.getCategoryID());
        query.setDate("from", from);
        query.setDate("to", to);

        positions.addAll(query.list());

        session.close();

        return positions;
    }

    public ArrayList<Record> getReocrdsByCategory(Category cat) {
        ArrayList<Record> records = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM record r "
                + "inner join document d on(d.documentid = r.recordid) "
                + "where d.category_categoryid = " + cat.getCategoryID();
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery(sql).addEntity(Record.class);
        records.addAll(query.list());
        session.close();
        return records;
    }

    public  ArrayList<CashFlow> getUncheckedCashFlow(Account acc) {
    
        session = sessionFactory.openSession();
        ArrayList<CashFlow> cashFlow = new ArrayList<>();
        List transfers = session.createCriteria(Transfer.class)
                .add(Restrictions.disjunction()
                        .add(Restrictions.eq("from", acc))
                        .add(Restrictions.eq("to", acc))
                )
                .add(Restrictions.eq("checked", false)).list();

        List payments = session.createCriteria(Payment.class)
                .add(Restrictions.eq("account", acc))
                .add(Restrictions.eq("checked", false)).list();

        int lastid = 0;
        for (int i = payments.size() - 1; i >= 0; i--) {
            Payment p = (Payment) payments.get(i);
            if (p.getId() != lastid) {
                lastid = p.getId();
            } else {
                payments.remove(i);
            }
        }

        cashFlow.addAll(transfers);
        cashFlow.addAll(payments);
        session.close();
        return cashFlow;

    }
    
    public HashMap<String, UserConstraint> getUserConstraints(){
        HashMap<String, UserConstraint> constraints = new HashMap<>();
        
        ArrayList<UserConstraint> constraintList = getAll(UserConstraint.class);
        for(UserConstraint constraint : constraintList){
            constraints.put(constraint.getKey(), constraint);
            System.out.println(constraint.getValue());
        }
        
        
        return constraints;
    }
    
    public ArrayList<Budget> getAllBudgets(){
        ArrayList<Budget> budgets = new ArrayList<>();
        session = sessionFactory.openSession();
        List list = session.createCriteria(Budget.class)
                       .addOrder(Order.asc("validFromYear"))
                       .addOrder(Order.asc("validFromMonth"))
                       .list();
        
        budgets.addAll(list);
        session.close();
        return budgets;
    }
    
    public void backup(){
        
    }
}
