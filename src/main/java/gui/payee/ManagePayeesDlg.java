/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.payee;

import beans.Payee;
import bl.AusgabenBl;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author bwaidach
 */
public class ManagePayeesDlg extends javax.swing.JDialog {

    /**
     * Creates new form ManagePayeesDlg
     */
    private Vector<Payee> payees = new Vector<>();
    private AusgabenBl bl = AusgabenBl.getInstance();

    public ManagePayeesDlg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jlPayees.setListData(payees);
        payees.clear();
        payees.addAll(bl.getPayeesByPattern(""));
        jlPayees.updateUI();
        setSize(400, 400);
        setLocationRelativeTo(parent);
        setTitle("Sender/Empfänger bearbeiten");
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

        tfName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlPayees = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tfName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onKeyReleased(evt);
            }
        });
        getContentPane().add(tfName, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Hinzufügen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAdd(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setText("Bearbeiten");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onEdit(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jButton2, gridBagConstraints);

        jButton3.setText("Löschen");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDelete(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(jButton3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        jlPayees.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jlPayees);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyReleased
        String text = tfName.getText();
        if (text.length() > 0) {
            payees.clear();
            payees.addAll(bl.getPayeesByPattern(text));
            jlPayees.updateUI();
        } else {
            payees.clear();
            payees.addAll(bl.getPayeesByPattern(""));
            jlPayees.updateUI();
        }
    }//GEN-LAST:event_onKeyReleased

    private void onEdit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onEdit
        Payee payee = (Payee) jlPayees.getSelectedValue();
        if (payee != null) {
            String newName = JOptionPane.showInputDialog("Bitte einen neuen Namen eingeben: ", payee.getName());
            if (newName.length() > 0) {
                payee.setName(newName);
                bl.updateObj(payee);
                jlPayees.updateUI();
            }
        }
    }//GEN-LAST:event_onEdit

    private void onDelete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDelete
        Payee payee = (Payee) jlPayees.getSelectedValue();
        if (payee != null) {
            if (!bl.isPayeeInUse(payee)) {
                bl.removeObj(payee);
                payees.remove(jlPayees.getSelectedIndex());
                jlPayees.updateUI();
            } else {
                JOptionPane.showMessageDialog(this, "Zu diesem Eintrag sind Zahlungen vorhanden!\n"
                        + "Löschung nicht möglich", "Fehler!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_onDelete

    private void onAdd(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAdd

        String name = JOptionPane.showInputDialog("Bitte einen Namen eingeben: ", "");
        if (name.length() > 0) {
            Payee payee = new Payee(name);
            if (bl.getPayeeByName(name) == null) {
                bl.addObj(payee);
                payees.add(payee);
                jlPayees.updateUI();
            } else {
                JOptionPane.showMessageDialog(this, "Dieser Sender/Empfänger exisitiert bereits!", "Fehler!", JOptionPane.ERROR_MESSAGE);
            }

        }

    }//GEN-LAST:event_onAdd

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
            java.util.logging.Logger.getLogger(ManagePayeesDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePayeesDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePayeesDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePayeesDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManagePayeesDlg dialog = new ManagePayeesDlg(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList jlPayees;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables
}