/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Bernhard
 */
@Table
@Entity
@PrimaryKeyJoinColumn(name = "PaymentID")
public class Payment extends CashFlow implements Serializable {

    public static final int IN = 1;
    public static final int OUT = -1;

    @OneToOne
    private Account account;
    @OneToOne
    private Payee payee;

    @OneToMany(mappedBy = "payment", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PaymentPosition> paymentPositions;
    //1=in -1=out
    private int type;

    @OneToOne(optional = true)
    private InvoiceCopy invoiceCopy;

    public Payment() {
//        paymentPositions = new ArrayList<>();
//        paymentPositions.addAll(Database.getInstance().getAllPaymentPostions(id));
    }

    public Payment(Account account, Payee payee, Date date, int type) {
        this.account = account;
        this.payee = payee;
        this.date = date;
        this.type = type;

    }

    public List<PaymentPosition> getPaymentPositions() {
//        if(paymentPositions == null){
//            paymentPositions = new ArrayList<>();
//            paymentPositions.addAll(Database.getInstance().getAllPaymentPostions(id));
//        }
        return paymentPositions;
    }

    public InvoiceCopy getInvoiceCopy() {
        return invoiceCopy;
    }

    public void setInvoiceCopy(InvoiceCopy invoiceCopy) {
        this.invoiceCopy = invoiceCopy;
    }

    public void setPaymentPositions(ArrayList<PaymentPosition> paymentPositions) {
        if (this.paymentPositions == null) {
            this.paymentPositions = new ArrayList<>();
        }

        this.paymentPositions.clear();
        this.paymentPositions.addAll(paymentPositions);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPaymentID() {
        return super.getId();
    }

    public void setPaymentID(int transactionID) {
        super.setId(transactionID);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @Override
//    public String toString() {
//        return "Transaction{" + "transactionID=" + paymentID + ", account=" + account + ", payee=" + payee + '}';
//    }
    public String getTotalAmountAsString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(getTotalAmountAsDouble());
    }

    public double getTotalAmountAsDouble() {
        BigDecimal total = getTotalAmount();

        return total.doubleValue();
    }

    public BigDecimal getTotalAmount() {
        BigDecimal total = new BigDecimal(0);
        for (PaymentPosition pp : paymentPositions) {
            BigDecimal price = pp.getArticle().getPrice();
            BigDecimal quantity = new BigDecimal(pp.getQuantity());
            BigDecimal amount = price.multiply(quantity);
            total = total.add(amount);
        }
        
        return total;
    }

    public void setData(Account acc, Payee payee, Date date, int type) {
        this.account = acc;
        this.payee = payee;
        this.date = date;
        this.type = type;
    }

}
