import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import util.NumericTextField;


public class ReportDatepanel extends javax.swing.JFrame {
	
	
	public ReportDatepanel() {
        initComponents();
    }
	
	public static void main(String args[]) {
		ReportDatepanel rr = new ReportDatepanel();
		rr.setVisible(true);
		rr.setSize(300,300);
	}
	 private void initComponents() {
		 
		 
		 JPanel pp = new JPanel();
		 DecimalFormat format = new DecimalFormat("#####,####");
		 NumericTextField tt = new NumericTextField(10,format);
		 pp.add(tt,new BorderLayout().CENTER);
		 add(pp);
		// tt.setValue(d)
	        
	 }
	 private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JPanel jPanel2;
	    private NumericTextField  jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private javax.swing.JTextField jTextField3;
	    private javax.swing.JTextField jTextField4;
	    private javax.swing.JTextField jTextField5;
	    private javax.swing.JTextField jTextField6;
}
