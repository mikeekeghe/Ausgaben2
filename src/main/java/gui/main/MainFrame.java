/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import beans.Account;
import beans.Article;
import beans.CashFlow;
import enums.CategoryType;
import beans.DocumentPage;
import beans.Payment;
import beans.PaymentPosition;
import beans.Transfer;
import bl.AusgabenBl;
import db.Database;
import gui.Search.SearchDlg;
import gui.accounts.AccountManager;
import gui.category.CategoryManagementDlg;
import gui.check.CheckDlg;
import gui.document.AddEditDocumentDlg;
import gui.document.ManageDocumentsDlg;
import gui.payee.ManagePayeesDlg;
import gui.payment.AddEditPaymentDlg;
import gui.statistics.PieChartTest;
import gui.transfer.AddEditTransferDlg;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableColumn;

/**
 *
 * @author Bernhard
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private AusgabenBl bl = AusgabenBl.getInstance();
    private ArrayList<CashFlow> cashFlow = new ArrayList<>();
    private CashFlowTableModel cftm = cftm = new CashFlowTableModel(cashFlow);
    private ArrayList<Account> accounts;

    public MainFrame() {
        initComponents();

        accounts = bl.getAccounts();

        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        int lastDay = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int currentMonth = gc.get(GregorianCalendar.MONTH);
        int currentYear = gc.get(GregorianCalendar.YEAR);
        Date begin = new java.sql.Date(currentYear - 1900, currentMonth, 1);
        dpBegin.setDate(begin);
        Date end = new Date(currentYear - 1900, currentMonth, lastDay);
        dpEnd.setDate(end);
        updateAccounts();

        tbCashFlow.setModel(cftm);
        for (int x = 0; x < tbCashFlow.getColumnCount(); ++x) {
            TableColumn col = tbCashFlow.getColumnModel().getColumn(x);
            col.setCellRenderer(new CashFlowCellRenderer());
        }

//        tbCashFlow.setAutoCreateRowSorter(true);
        updateTable();
        updateAccountBalances();
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private void updateAccounts() {
        cbAccounts.removeAllItems();
        cbAccounts.addItem("Alle");
        for (Account acc : bl.getAccounts()) {
            cbAccounts.addItem(acc.getName());
        }
    }

    private void updateTable() {
        String accountStr = (String) cbAccounts.getSelectedItem();
        java.sql.Date from = new java.sql.Date(dpBegin.getDate().getTime());
        java.sql.Date to = new java.sql.Date(dpEnd.getDate().getTime());
        cashFlow.clear();
        if (accountStr.equals("Alle")) {
            cashFlow.addAll(bl.getCashFlow(from, to));
        } else {
            Account account = bl.getAccountByName(accountStr);
            cashFlow.addAll(bl.getCashFlow(from, to, account));
        }
        onSort(null);
        tbCashFlow.setModel(cftm);
        tbCashFlow.packAll();
        tbCashFlow.updateUI();
    }

    private void updateAccountBalances() {
        Color green = new Color(51, 153, 0);
        pnAccountBalance.removeAll();
        pnAccountBalance.setLayout(new GridLayout(accounts.size() + 1, 2));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        BigDecimal total = new BigDecimal(0);
        for (Account acc : accounts) {
            JLabel name = new JLabel(acc.getName() + ": ");
            BigDecimal amount = bl.getAccountBalance(acc);
            JLabel balance = new JLabel(formatter.format(amount.doubleValue()));
            total = total.add(amount);
            balance.setOpaque(true);
            name.setOpaque(true);
            balance.setFont(balance.getFont().deriveFont(Font.BOLD));
            if (amount.doubleValue() < 0) {
                balance.setForeground(Color.red);
            } else {
                balance.setForeground(green);
            }
            pnAccountBalance.add(name);
            pnAccountBalance.add(balance);
        }

        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setBorder(new MatteBorder(2, 0, 0, 0, Color.black));
        JLabel totalBalanceLabel = new JLabel(formatter.format(total.doubleValue()));
        totalBalanceLabel.setBorder(new MatteBorder(2, 0, 0, 0, Color.black));

        totalBalanceLabel.setFont(totalBalanceLabel.getFont().deriveFont(Font.BOLD));
        if (total.doubleValue() < 0) {
            totalBalanceLabel.setForeground(Color.red);
        } else {
            totalBalanceLabel.setForeground(green);
        }
        pnAccountBalance.add(totalLabel);
        pnAccountBalance.add(totalBalanceLabel);
        pnAccountBalance.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        popup = new javax.swing.JPopupMenu();
        miIC = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        mEdit = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dpBegin = new org.jdesktop.swingx.JXDatePicker();
        dpEnd = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbAccounts = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbSort = new javax.swing.JComboBox();
        pnAccountBalance = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbCashFlow = new org.jdesktop.swingx.JXTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        miIC.setText("Rechnungskopie anzeigen");
        miIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onShowInvvoiceCopy(evt);
            }
        });
        popup.add(miIC);

        jMenuItem2.setText("Ansehen");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onView(evt);
            }
        });
        popup.add(jMenuItem2);

        mEdit.setText("Bearbeiten");
        mEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEdit(evt);
            }
        });
        popup.add(mEdit);

        jMenuItem3.setText("Löschen");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDelete(evt);
            }
        });
        popup.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Von:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel1, gridBagConstraints);

        dpBegin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                onPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(dpBegin, gridBagConstraints);

        dpEnd.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                onPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(dpEnd, gridBagConstraints);

        jLabel2.setText("Bis:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Konto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel3, gridBagConstraints);

        cbAccounts.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbAccounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onValueChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cbAccounts, gridBagConstraints);

        jLabel4.setText("Sortieren nach:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jLabel4, gridBagConstraints);

        cbSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Sender/Empfänger", "Konto", "Datum", "Betrag" }));
        cbSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSort(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(cbSort, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pnAccountBalance.setBorder(javax.swing.BorderFactory.createTitledBorder("Kontostände"));
        getContentPane().add(pnAccountBalance, java.awt.BorderLayout.SOUTH);

        tbCashFlow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCashFlow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbCashFlow);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Hinzufügen");

        jMenuItem4.setText("Zahlung");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddPayment(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Umbuchung");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddTransfer(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Administrieren");

        jMenuItem1.setText("Kategorien");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1onAdministrateCategories(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem6.setText("Konten");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6onAdminAccounts(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem8.setText("Sender/Empfänger");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onManagePayees(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem10.setText("Dokumente");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onManageDocuments(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Ausgleich durchführen");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCheckCashFlow(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Suchen");

        jMenuItem7.setText("Zahlung");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSearchPayment(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Statistik");

        jMenuItem9.setText("Twst");
        jMenuItem9.setToolTipText("");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onTest(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onDelete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDelete
        int row = tbCashFlow.getSelectedRow();
        if (row >= 0) {
            int sel = JOptionPane.showConfirmDialog(this, "Wirklich löschen?");

            if (sel == 0) {
                //really delete
                CashFlow selected = cashFlow.get(row);
                if (selected instanceof Transfer) {
                    bl.removeObj(selected);

                } else if (selected instanceof Payment) {
                    Payment payment = (Payment) selected;
                    //delete invoicecopy
                    if (payment.getInvoiceCopy() != null) {
                        //delete pages
                        for (DocumentPage dp : payment.getInvoiceCopy().getDocumentPages()) {
                            bl.removeObj(dp);
                        }

                    }
                    //delete paymentpostions
                    for (PaymentPosition pp : payment.getPaymentPositions()) {
                        bl.removeObj(pp);
                    }
                    //delete payment itself
                    bl.removeObj(payment);
                    if (payment.getInvoiceCopy() != null) {
                        bl.removeObj(payment.getInvoiceCopy());
                    }
                }

                cashFlow.remove(row);
                updateTable();
                updateAccountBalances();
            }
        }
    }//GEN-LAST:event_onDelete

    private void onAddTransfer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddTransfer
        AddEditTransferDlg aetdlg = new AddEditTransferDlg(this, true, null);
        aetdlg.setVisible(true);
        if (aetdlg.isOk()) {
            Transfer t = aetdlg.getTransfer();
            bl.addObj(t);

            updateAccountBalances();
            updateTable();
        }
    }//GEN-LAST:event_onAddTransfer

    private void onAddPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddPayment
        AddEditPaymentDlg aepdlg = new AddEditPaymentDlg(this, true, null, false);
        aepdlg.setVisible(true);
        if (aepdlg.isOk()) {
            Payment payment = aepdlg.getPayment();
            if (payment.getInvoiceCopy() != null) {
                bl.addObj(payment.getInvoiceCopy());
                for (DocumentPage dp : payment.getInvoiceCopy().getDocumentPages()) {
                    dp.setDocument(payment.getInvoiceCopy());
                    bl.addObj(dp);
                }

            }

            bl.addObj(payment);

            for (PaymentPosition pp : payment.getPaymentPositions()) {
                pp.setPayment(payment);
                bl.addObj(pp.getArticle());
                bl.addObj(pp);
            }

            if (payment.getInvoiceCopy() != null) {
//                    JOptionPane.showMessageDialog(this, "ID der Zahlung: " + payment.getId());
            }
            JOptionPane.showMessageDialog(this, "ID der eingefügten Zahlung: " + payment.getId());

        }
        updateTable();
        updateAccountBalances();
    }//GEN-LAST:event_onAddPayment

    private void onShowInvvoiceCopy(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onShowInvvoiceCopy
        int row = tbCashFlow.getSelectedRow();
        if (row >= 0) {
            CashFlow sel = cashFlow.get(row);
            if (sel instanceof Payment) {
                Payment payment = (Payment) sel;
                AddEditDocumentDlg icdlg = new AddEditDocumentDlg(this, true, true, payment.getInvoiceCopy(), true);
                icdlg.setVisible(true);
            }
        }
    }//GEN-LAST:event_onShowInvvoiceCopy

    private void onEdit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEdit
        int row = tbCashFlow.getSelectedRow();
        if (row >= 0) {
            CashFlow sel = cashFlow.get(row);
            if (sel instanceof Payment) {
                Payment payment = (Payment) sel;
                AddEditPaymentDlg aepdlg = new AddEditPaymentDlg(this, true, payment, false);
                aepdlg.setVisible(true);
                if (aepdlg.isOk()) {
                    //delete old paymentpositions and create new ones
                    payment = aepdlg.getPayment();
                    bl.deleteAllPaymentPositions(payment);
                    System.out.println(payment.getPaymentPositions());
                    for (PaymentPosition pp : payment.getPaymentPositions()) {
                        String articleName = pp.getArticle().getName();
                        BigDecimal price = pp.getArticle().getPrice();
                        Article article = bl.getArticle(articleName, price);
                        pp.setArticle(article);
                        if (pp.getPayment() == null) {
                            pp.setPayment(payment);
                        }
                        bl.addObj(pp);
                    }

                    //delete old invoicecopy and create new one
                    if (payment.getInvoiceCopy() != null) {
                        if (!payment.getInvoiceCopy().getDocumentPages().isEmpty()) {
                            bl.deleteDocumentPages(payment.getInvoiceCopy());
                            if (payment.getInvoiceCopy().getDocumentID() == 0) {
                                bl.addObj(payment.getInvoiceCopy());
                            }
                            for (DocumentPage dp : payment.getInvoiceCopy().getDocumentPages()) {
                                dp.setDocument(payment.getInvoiceCopy());
                                bl.addObj(dp);
                            }

                        } else {
                            //delete ic
                            bl.removeObj(payment.getInvoiceCopy());
                            payment.setInvoiceCopy(null);
                        }
                    }
                    bl.updateObj(payment);

                }

            } else if (sel instanceof Transfer) {
                Transfer transfer = (Transfer) sel;
                AddEditTransferDlg aetdlg = new AddEditTransferDlg(this, true, transfer);
                aetdlg.setVisible(true);
                if (aetdlg.isOk()) {
                    bl.updateObj(transfer);

                }
            }
        }
        updateTable();
        updateAccountBalances();
    }//GEN-LAST:event_onEdit

    private void onValueChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onValueChanged
        if (cbAccounts.getModel().getSize() > 0) {
            updateTable();
        }
    }//GEN-LAST:event_onValueChanged

    private void jMenuItem1onAdministrateCategories(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1onAdministrateCategories
        CategoryManagementDlg cmdlg = new CategoryManagementDlg(null, true, false, CategoryType.BILL);
        cmdlg.setVisible(true);
    }//GEN-LAST:event_jMenuItem1onAdministrateCategories

    private void jMenuItem6onAdminAccounts(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6onAdminAccounts
        AccountManager am = new AccountManager(null, true);
        am.setVisible(true);
        updateAccounts();
        updateAccountBalances();
    }//GEN-LAST:event_jMenuItem6onAdminAccounts

    private void onPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_onPropertyChange
        if (dpBegin.getDate() != null && dpEnd.getDate() != null) {
            updateTable();
        }
    }//GEN-LAST:event_onPropertyChange

    private void onSearchPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSearchPayment
        SearchDlg sdlg = new SearchDlg(this, true);
        sdlg.setVisible(true);
    }//GEN-LAST:event_onSearchPayment

    private void onManagePayees(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onManagePayees
        ManagePayeesDlg mpdlg = new ManagePayeesDlg(this, true);
        mpdlg.setVisible(true);
    }//GEN-LAST:event_onManagePayees

    private void onMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onMouseClicked

        if (evt.getButton() == 3) {
            int row = tbCashFlow.getSelectedRow();
            if (row >= 0) {
                CashFlow selected = cashFlow.get(row);
                if(selected.isChecked()){
                    mEdit.setEnabled(false);
                }else{
                    mEdit.setEnabled(true);
                }
                popup.show(tbCashFlow, evt.getX(), evt.getY());
                if (selected instanceof Payment) {
                    Payment p = (Payment) selected;
                    miIC.setEnabled((p.getInvoiceCopy() != null));
                } else {
                    miIC.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_onMouseClicked

    private void onTest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onTest
        PieChartTest pct = new PieChartTest(this, true);
        pct.setVisible(true);
    }//GEN-LAST:event_onTest

    private void onSort(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSort
        String sort = (String) cbSort.getSelectedItem();

        
        
        if (sort.equals("Betrag")) {
            cashFlow.sort((CashFlow o1, CashFlow o2) -> {
                BigDecimal value1 = null;
                BigDecimal value2 = null;
                if (o1 instanceof Transfer) {
                    Transfer t1 = (Transfer) o1;
                    value1 = t1.getAbsolutValue();
                } else {
                    Payment p1 = (Payment) o1;
                    value1 = p1.getTotalAmount();
                }
                
                if (o2 instanceof Transfer) {
                    Transfer t2 = (Transfer) o2;
                    value2 = t2.getAbsolutValue();
                } else {
                    Payment p2 = (Payment) o2;
                    value2 = p2.getTotalAmount();
                    
                }
                return value1.compareTo(value2);
            });
            
        }else if(sort.equals("Sender/Empfänger")){
            cashFlow.sort((CashFlow o1, CashFlow o2) -> {
                String s1 = "";
                String s2 = "";
                if(o1 instanceof Payment){
                    Payment p1 = (Payment) o1;
                    s1 = p1.getPayee().getName();
                }else{
                    Transfer t1 = (Transfer) o1;
                    s1 = "nach " + t1.getTo().getName();
                }
                
                if(o2 instanceof Payment){
                    Payment p2 = (Payment) o2;
                    s2 = p2.getPayee().getName();
                }else{
                    Transfer t2 = (Transfer) o2;
                    s2 = "nach " + t2.getTo().getName();
                }
                
                
                return s1.compareTo(s2);
            });
        }else if(sort.equals("Datum")){
            cashFlow.sort((CashFlow o1, CashFlow o2) -> {
                return o1.getDate().compareTo(o2.getDate());
            });
        }else if(sort.equals("ID")){
            cashFlow.sort((CashFlow o1, CashFlow o2) -> {
                if(o1.getId() < o2.getId())
                    return -1;
                else if(o1.getId() > o2.getId())
                    return 11;
                else
                    return 0;
            });
        }else if(sort.equals("Konto")){
            cashFlow.sort((CashFlow o1, CashFlow o2) -> {
                String s1 = "";
                String s2 = "";
                if(o1 instanceof Payment){
                    Payment p1 = (Payment) o1;
                    s1 = p1.getAccount().getName();
                }else{
                    Transfer t1 = (Transfer) o1;
                    s1 = "Von " + t1.getFrom().getName();
                }
                
                if(o2 instanceof Payment){
                    Payment p2 = (Payment) o2;
                    s2 = p2.getAccount().getName();
                }else{
                    Transfer t2 = (Transfer) o2;
                    s2 = "Von " + t2.getFrom().getName();
                }
                
                
                return s1.compareTo(s2);
            
            });
        }
        
        tbCashFlow.updateUI();
        
        
        
    }//GEN-LAST:event_onSort

    private void onManageDocuments(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onManageDocuments
        ManageDocumentsDlg mddlg = new ManageDocumentsDlg(this, true);
        mddlg.setVisible(true);
    }//GEN-LAST:event_onManageDocuments

    private void onCheckCashFlow(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCheckCashFlow
        CheckDlg cdlg = new CheckDlg(this, true);
        cdlg.setVisible(true);
        updateTable();
    }//GEN-LAST:event_onCheckCashFlow

    private void onView(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onView
        int row = tbCashFlow.getSelectedRow();
        if (row >= 0) {
            CashFlow sel = cashFlow.get(row);
            if (sel instanceof Payment) {
                Payment payment = (Payment) sel;
                AddEditPaymentDlg aepdlg = new AddEditPaymentDlg(this, false, payment, true);
                aepdlg.setVisible(true);
            }else{
                
            }
        }
    }//GEN-LAST:event_onView

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String[] options = {"Test Datenbank", "Produktiv Datenbank"};
                int option = JOptionPane.showOptionDialog(null, "Welche DB verwenden?",
                        "Datenbank?", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if (option == 0) {
                    Database.configFile = "hibernate.cfg.xml";
                } else {
                    Database.configFile = "hibernate_prod.cfg.xml";
                }
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbAccounts;
    private javax.swing.JComboBox cbSort;
    private org.jdesktop.swingx.JXDatePicker dpBegin;
    private org.jdesktop.swingx.JXDatePicker dpEnd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mEdit;
    private javax.swing.JMenuItem miIC;
    private javax.swing.JPanel pnAccountBalance;
    private javax.swing.JPopupMenu popup;
    private org.jdesktop.swingx.JXTable tbCashFlow;
    // End of variables declaration//GEN-END:variables
}
