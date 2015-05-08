/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.payment;

import beans.Account;
import beans.Article;
import beans.Category;
import enums.CategoryType;
import beans.DocumentPage;
import beans.InvoiceCopy;
import beans.Payee;
import beans.Payment;
import beans.PaymentPosition;
import bl.AusgabenBl;
import gui.accounts.AccountManager;
import gui.category.CategoryManagementDlg;
import gui.document.AddEditDocumentDlg;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Bernhard
 */
public class AddEditPaymentDlg extends javax.swing.JDialog {

    /**
     * Creates new form AddEditPayment
     */
    private AusgabenBl bl = AusgabenBl.getInstance();
    private Vector listData = new Vector();
    private ArrayList<PaymentPosition> paymentPositions = new ArrayList<>();
    private PaymentPositionTableModel pptm = new PaymentPositionTableModel(paymentPositions);
    private int lastKeyCode;
    private String lastCategory;
    private PaymentPosition selected = null;
    private Payment payment = new Payment();
    private InvoiceCopy invoiceCopy;
    private boolean ok;
    private boolean viewMode;

    public AddEditPaymentDlg(java.awt.Frame parent, boolean modal, Payment payment, boolean viewMode) {
        super(parent, modal);
        initComponents();
        this.viewMode = viewMode;
        jlEntries.setListData(listData);
        tbPaymentPositions.setModel(pptm);
        updateAccounts();

        if (payment != null) {
            tfPayee.setText(payment.getPayee().getName());
            cbAccounts.setSelectedItem(payment.getAccount().getName());
            dpDate.setDate(payment.getDate());
            if (payment.getType() == Payment.IN) {
                rbIn.setSelected(true);
            } else {
                rbOut.setSelected(true);
            }

            invoiceCopy = payment.getInvoiceCopy();

            paymentPositions.addAll(payment.getPaymentPositions());
            this.payment = payment;
            lbID.setText("ID: " + payment.getId());
        }

        if (viewMode) {
            this.remove(pnManageArticle);
            btSave.setText("Schließen");
            tfPayee.setEditable(false);
            dpDate.setEditable(false);
            rbIn.setEnabled(false);
            rbOut.setEnabled(false);
            cbAccounts.setEditable(false);
        }

        updateTotalBalance();
        this.setLocationRelativeTo(parent);
        tfPayee.requestFocus();
    }

    private void updateTotalBalance() {
        double total = 0;
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        for (PaymentPosition pp : paymentPositions) {
            total += pp.getArticle().getPrice().multiply(new BigDecimal(pp.getQuantity())).doubleValue();
        }
        lbTotalAmount.setText("Gesamtbetrag: " + formatter.format(total));
    }

    private void savePostion() {
        String name = tfArticleName.getText();
        String priceStr = tfPrice.getText();
        String quantityStr = tfQuantity.getText();
        String category = tfCategory.getText();

        if (name.length() > 0
                && priceStr.length() > 0
                && quantityStr.length() > 0
                && category.length() > 0) {
            try {
                int quantity = Integer.valueOf(quantityStr);
                priceStr = priceStr.replaceAll(",", ".");
                BigDecimal price = new BigDecimal(priceStr);
                Article article = new Article(name, price);
                Category cat = bl.getBillCategoryByName(category);
                if (cat != null) {
                    if (selected == null) {
                        PaymentPosition pp = new PaymentPosition(article, null, quantity, cat);
                        paymentPositions.add(pp);
                    } else {
                        selected.setArticle(article);
                        selected.setCategory(cat);
                        selected.setQuantity(quantity);
                        selected = null;
                    }
                    tbPaymentPositions.updateUI();
                    lastCategory = category;
                    clearPPFiels();
                    tfArticleName.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(this, "Kategorie existiert nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Werte ungültig!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        updateTotalBalance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgType = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfPayee = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dpDate = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        rbIn = new javax.swing.JRadioButton();
        rbOut = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        cbAccounts = new javax.swing.JComboBox();
        lbID = new javax.swing.JLabel();
        pnManageArticle = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfArticleName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlEntries = new javax.swing.JList();
        tfPrice = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCategory = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPaymentPositions = new javax.swing.JTable();
        btSave = new javax.swing.JButton();
        lbTotalAmount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Allgemeine Daten"));

        jLabel1.setText("Sender/Empfänger:");

        tfPayee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPayeeFocusLost(evt);
            }
        });
        tfPayee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onPayeeKeyReleased(evt);
            }
        });

        jLabel2.setText("Datum:");

        jButton1.setText("Rechnungskopie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddInvoiceCopy(evt);
            }
        });

        jLabel7.setText("Art:");

        bgType.add(rbIn);
        rbIn.setText("Einnahme");

        bgType.add(rbOut);
        rbOut.setText("Ausgabe");
        rbOut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                onRbOutFocusLost(evt);
            }
        });

        jLabel8.setText("Konto:");

        lbID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel8)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPayee)
                            .addComponent(dpDate, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbIn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbOut))
                                    .addComponent(cbAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbID)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfPayee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbIn)
                    .addComponent(rbOut)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(lbID)
                .addContainerGap())
        );

        pnManageArticle.setBorder(javax.swing.BorderFactory.createTitledBorder("Artikel managen"));

        jLabel3.setText("Bezeichnung:");

        jLabel4.setText("Preis:");

        jlEntries.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlEntries);

        tfPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onPriceKeyReleased(evt);
            }
        });

        jLabel5.setText("Menge:");

        tfQuantity.setText("1");
        tfQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                onQuantityFocusGained(evt);
            }
        });

        jLabel6.setText("Kategorie:");

        tfCategory.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfCategoryKeyReleased(evt);
            }
        });

        jButton3.setText("Speichern");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSavePostion(evt);
            }
        });

        jButton4.setText("Ausgewählten Aritkel löschen");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeleteSelectedArticle(evt);
            }
        });

        jButton5.setText("Ausgwählten Artikel bearbeiten");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEditSelectedArticle(evt);
            }
        });

        javax.swing.GroupLayout pnManageArticleLayout = new javax.swing.GroupLayout(pnManageArticle);
        pnManageArticle.setLayout(pnManageArticleLayout);
        pnManageArticleLayout.setHorizontalGroup(
            pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnManageArticleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnManageArticleLayout.createSequentialGroup()
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfArticleName)
                            .addComponent(tfPrice)
                            .addComponent(tfQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(tfCategory)))
                    .addGroup(pnManageArticleLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton3)
                            .addComponent(jButton5))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnManageArticleLayout.setVerticalGroup(
            pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnManageArticleLayout.createSequentialGroup()
                .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnManageArticleLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfArticleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tfQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnManageArticleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        tbPaymentPositions.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPaymentPositions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onTbPaymentPostionsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbPaymentPositions);

        btSave.setText("Speichern");
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSave(evt);
            }
        });

        lbTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTotalAmount.setText("Gesamtbetrag: ");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Administrieren");

        jMenuItem1.setText("Kategorien");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAdministrateCategories(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Konten");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAdminAccounts(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btSave)
                            .addComponent(lbTotalAmount))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnManageArticle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTotalAmount)
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnManageArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onAdministrateCategories(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAdministrateCategories
        CategoryManagementDlg cmdlg = new CategoryManagementDlg(null, true, false, CategoryType.BILL);
        cmdlg.setVisible(true);
    }//GEN-LAST:event_onAdministrateCategories

    private void tfCategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCategoryKeyReleased

        if (evt.getKeyCode() == 38) {
            //up
            int index = jlEntries.getSelectedIndex();
            if (index >= 0) {
                if ((index - 1) >= 0) {
                    index--;
                }
            } else {
                index = listData.size() - 1;
            }

            jlEntries.setSelectedIndex(index);
        } else if (evt.getKeyCode() == 40) {
            int index = jlEntries.getSelectedIndex();
            if (index < listData.size()) {
                if ((index + 1) < listData.size()) {
                    index++;
                }
            } else {
                index = 0;
            }

            jlEntries.setSelectedIndex(index);
        } else if (evt.getKeyCode() == 10) {
            int index = jlEntries.getSelectedIndex();
            if (index >= 0) {
                Category selected = (Category) listData.get(index);
                tfCategory.setText(selected.getName());
                tfCategory.selectAll();
                jlEntries.clearSelection();
                listData.clear();
                jlEntries.updateUI();
            } else {
                //store position
                savePostion();
            }
        } else {
            //normal entries
//            System.out.println(evt.getKeyCode());
            String text = tfCategory.getText();
            if (text.length() > 0) {
                ArrayList<Category> categoryList = bl.getCategoriesByPattern(text, CategoryType.BILL);
                listData.clear();
                listData.addAll(categoryList);
                jlEntries.updateUI();
            }
        }
        updateTotalBalance();
    }//GEN-LAST:event_tfCategoryKeyReleased

    private void onQuantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_onQuantityFocusGained
        tfQuantity.setSelectionStart(0);
        tfQuantity.setSelectionEnd(tfQuantity.getText().length());
    }//GEN-LAST:event_onQuantityFocusGained

    private void onSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSave
        String payeeName = tfPayee.getText();
        String typeStr = null;
        int type = 0;
        if (rbIn.isSelected()) {
            typeStr = "in";
        } else if (rbOut.isSelected()) {
            typeStr = "out";
        } else {
            typeStr = null;
        }
        if (payeeName.length() > 0) {
            if (typeStr != null) {
                type = (typeStr.equals("in")) ? Payment.IN : Payment.OUT;
                if (!paymentPositions.isEmpty()) {
                    if (dpDate.getDate() != null) {
                        Date date = new java.sql.Date(dpDate.getDate().getTime());
                        if (date != null) {
                            String accountName = (String) cbAccounts.getSelectedItem();
                            if (!accountName.startsWith("-")) {
                                Account acc = bl.getAccountByName(accountName);
                                Payee payee = bl.getPayeeByName(payeeName);
                                if (payee == null) {
                                    payee = new Payee(payeeName);
                                    bl.addObj(payee);
                                }

                                payment.setData(acc, payee, date, type);
                                System.out.println(paymentPositions);
                                payment.setPaymentPositions(paymentPositions);

                                payment.setInvoiceCopy(invoiceCopy);
                                ok = true;
                                setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(this, "Konto auswählen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Datum auswählen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Datum auswählen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Es muss mind. eine Position eingegeben werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Art auswählen!", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sender/Empfänger eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_onSave

    private void onAdminAccounts(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAdminAccounts
        AccountManager am = new AccountManager(null, true);
        am.setVisible(true);
        updateAccounts();
    }//GEN-LAST:event_onAdminAccounts

    private void onDeleteSelectedArticle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDeleteSelectedArticle
        int selectedRow = tbPaymentPositions.getSelectedRow();
        if (selectedRow >= 0) {
            paymentPositions.remove(selectedRow);
            tbPaymentPositions.updateUI();
        }
        updateTotalBalance();
    }//GEN-LAST:event_onDeleteSelectedArticle

    private void onTbPaymentPostionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_onTbPaymentPostionsMouseClicked

    }//GEN-LAST:event_onTbPaymentPostionsMouseClicked

    private void onEditSelectedArticle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEditSelectedArticle

        int row = tbPaymentPositions.getSelectedRow();
        if (row >= 0) {
            selected = paymentPositions.get(row);
            tfArticleName.setText(selected.getArticle().getName());
            tfPrice.setText(selected.getArticle().getPrice().doubleValue() + "");
            tfQuantity.setText(selected.getQuantity() + "");
            tfCategory.setText(selected.getCategory().getName());
        }
        updateTotalBalance();
    }//GEN-LAST:event_onEditSelectedArticle

    private void onAddInvoiceCopy(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddInvoiceCopy
        if (viewMode) {
            AddEditDocumentDlg icd = new AddEditDocumentDlg(null, true, true, invoiceCopy, true);
            icd.setVisible(true);
        } else {
            AddEditDocumentDlg icd = new AddEditDocumentDlg(null, true, false, invoiceCopy, true);
            icd.setVisible(true);
            if (icd.isOk()) {
                invoiceCopy = (InvoiceCopy) icd.getInvoiceCopy();
            }
        }
    }//GEN-LAST:event_onAddInvoiceCopy

    private void onPayeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onPayeeKeyReleased

        if (evt.getKeyCode() == 38) {
            //up
            int index = jlEntries.getSelectedIndex();
            if (index >= 0) {
                if ((index - 1) >= 0) {
                    index--;
                }
            } else {
                index = listData.size() - 1;
            }

            jlEntries.setSelectedIndex(index);
        } else if (evt.getKeyCode() == 40) {
            int index = jlEntries.getSelectedIndex();
            if (index < listData.size()) {
                if ((index + 1) < listData.size()) {
                    index++;
                }
            } else {
                index = 0;
            }

            jlEntries.setSelectedIndex(index);
        } else if (evt.getKeyCode() == 10) {
            int index = jlEntries.getSelectedIndex();
            if (index >= 0) {
                Payee selected = (Payee) listData.get(index);
                tfPayee.setText(selected.getName());
                tfPayee.selectAll();
                jlEntries.clearSelection();
                listData.clear();
            }
        } else {
            //normal entries
            String text = tfPayee.getText();
            if (text.length() > 0) {
                ArrayList<Payee> list = bl.getPayeesByPattern(text);
                listData.clear();
                listData.addAll(list);
                jlEntries.updateUI();
            }
        }
    }//GEN-LAST:event_onPayeeKeyReleased

    private void tfPayeeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPayeeFocusLost
        listData.clear();
        jlEntries.updateUI();
    }//GEN-LAST:event_tfPayeeFocusLost

    private void onRbOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_onRbOutFocusLost
        tfArticleName.requestFocus();
    }//GEN-LAST:event_onRbOutFocusLost

    private void onSavePostion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSavePostion
        savePostion();
    }//GEN-LAST:event_onSavePostion

    private void onPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onPriceKeyReleased
        if (evt.getKeyCode() == 10) {
            savePostion();
        }
    }//GEN-LAST:event_onPriceKeyReleased

    private void clearPPFiels() {
        tfArticleName.setText(null);
        tfPrice.setText(null);
        tfQuantity.setText("1");
        tfCategory.setText(lastCategory);
        jlEntries.clearSelection();
        listData.clear();
    }

    private void updateAccounts() {
        cbAccounts.removeAllItems();
        cbAccounts.addItem("---");
        for (Account acc : bl.getAccounts()) {
            cbAccounts.addItem(acc.getName());
        }
    }

    public boolean isOk() {
        return ok;
    }

    public Payment getPayment() {
        return payment;
    }

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
            java.util.logging.Logger.getLogger(AddEditPaymentDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditPaymentDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditPaymentDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditPaymentDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddEditPaymentDlg dialog = new AddEditPaymentDlg(new javax.swing.JFrame(), true, null, false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgType;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox cbAccounts;
    private org.jdesktop.swingx.JXDatePicker dpDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList jlEntries;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbTotalAmount;
    private javax.swing.JPanel pnManageArticle;
    private javax.swing.JRadioButton rbIn;
    private javax.swing.JRadioButton rbOut;
    private javax.swing.JTable tbPaymentPositions;
    private javax.swing.JTextField tfArticleName;
    private javax.swing.JTextField tfCategory;
    private javax.swing.JTextField tfPayee;
    private javax.swing.JTextField tfPrice;
    private javax.swing.JTextField tfQuantity;
    // End of variables declaration//GEN-END:variables
}
