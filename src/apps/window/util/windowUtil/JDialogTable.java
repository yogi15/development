package apps.window.util.windowUtil;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import beans.LegalEntity;

public class JDialogTable extends javax.swing.JDialog {

	  public void clearRolesTables() {
		jTable1.removeAll();
		   DefaultTableModel mo = (DefaultTableModel) jTable1.getModel();
		   for(int i=0;i<mo.getRowCount();i++) {
			   mo.removeRow(i);
				
			}
	   }
	
	public JDialogTable(DefaultTableModel model) {
		// TODO Auto-generated constructor stub
		initComponents(model);
	}
	
	public void setJTableModel(DefaultTableModel model) {
		  jTable1.removeAll();
          jTable1.setModel(model);
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(DefaultTableModel model) {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        
     //   jTable1.setc

     //   setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       // javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(o,s);
        jTable1.removeAll();
            jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        getContentPane().setLayout(new BorderLayout()); 
        getContentPane().add(jPanel1, BorderLayout.CENTER);
        add(jPanel1);
        
     

        pack();
    }// </editor-fold>

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
            java.util.logging.Logger.getLogger(JDialogTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDialogTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDialogTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDialogTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            	
            	//test.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    // End of variables declaration
}


