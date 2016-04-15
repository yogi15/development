package apps.window.operationwindow.jobpanl;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.Transfer;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TransferPanel extends JInternalFrame {
   Transfer transfer = null;
	private static final long serialVersionUID = 1L;
//	private JPanel jPanel0;
	private JTextField jTextField2;
	private JLabel jLabel3;
	private JTextField jTextField3;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel0;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JTextField jTextField4;
	private JTextField jTextField7;
	private JTextField jTextField6;
	private JTextField jTextField5;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JTextField jTextField8;
	private JTextField jTextField9;
	private JTextField jTextField10;
	private JLabel jLabel18;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	public TransferPanel(Transfer transfer) {
		this.transfer = transfer;
		initComponents();
		
	}

	private void initComponents() {
		setVisible(true);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(4, 963, 10, 10), new Leading(6, 279, 10, 10)));
		setSize(980, 325);
		if(transfer == null )
			 return;
		
		jTextField1.setText(((Integer)transfer.getTradeId()).toString());
		jTextField4.setText(transfer.getEventType());
		jTextField8.setText((String) transfer.getTransferType());
		jTextField0.setText(((Double) transfer.getAmount()).toString());
		jTextField5.setText(transfer.getProductType());
		jTextField9.setText((String) transfer.getStatus());
	    jTextField2.setText((String) transfer.getDeliveryDate());
		jTextField7.setText((String) transfer.getSettlecurrency());
		jTextField10.setText((String) transfer.getValueDate());
		jTextField3.setText((String) transfer.getPayerCode());
		jTextField6.setText((String)transfer.getReceiverCode()); 
	}
	JTextField jTextField11;
	JTextField jTextField1;
	private JTextField getJTextField1() {
		
		
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
		//	jTextField1.setText("CANCELLED");
			jTextField1.setEditable(false);
		}
		return jTextField1;
	}
	
	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Amt");
		}
		return jLabel1;
	}
	
	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
		
	
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTextField1(), new Constraints(new Leading(95, 71, 12, 12), new Leading(14, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 49, 12, 12), new Leading(42, 12, 12)));
			jPanel0.add(getJTextField4(), new Constraints(new Leading(276, 137, 10, 10), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField7(), new Constraints(new Leading(276, 137, 12, 12), new Leading(68, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(276, 137, 12, 12), new Leading(96, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(435, 93, 10, 10), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField8(), new Constraints(new Leading(524, 137, 10, 10), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField10(), new Constraints(new Leading(524, 137, 12, 12), new Leading(66, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(7, 66, 10, 10), new Leading(72, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(9, 66, 12, 12), new Leading(98, 12, 12)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(9, 62, 12, 12), new Leading(16, 10, 10)));
			jPanel0.add(getJLabel18(), new Constraints(new Leading(357, 46, 477), new Leading(126, 27, 12, 12)));
			jPanel0.add(getJButton1(), new Constraints(new Leading(9, 46, 12, 12), new Leading(134, 12, 12)));
			jPanel0.add(getJTextField11(), new Constraints(new Leading(91, 118, 10, 10), new Leading(140, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(435, 93, 12, 12), new Leading(68, 14, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(805, 10, 10), new Leading(16, 10, 10)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(208, 65, 10, 10), new Leading(20, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(206, 52, 12, 12), new Leading(46, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(93, 105, 10, 10), new Leading(68, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(95, 106, 10, 10), new Leading(42, 12, 12)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(93, 104, 12, 12), new Leading(94, 12, 12)));
			jPanel0.add(getJTextField9(), new Constraints(new Leading(598, 137, 10, 10), new Leading(38, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(516, 48, 10, 10), new Leading(42, 12, 12)));
			jPanel0.add(getJTextField5(), new Constraints(new Leading(276, 233, 10, 10), new Leading(42, 12, 12)));
			jPanel0.add(getJLabel6(), new Constraints(new Leading(208, 56, 12, 12), new Leading(70, 12, 12)));
			jPanel0.add(getJLabel7(), new Constraints(new Leading(206, 56, 12, 12), new Leading(94, 20, 12, 12)));
			jPanel0.add(getJScrollPane1(), new Constraints(new Leading(431, 477, 12, 12), new Leading(120, 72, 12, 12)));
			
		}
		return jPanel0;
	}
	
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
//			jTextField3.setText("jTextField3");
			jTextField1.setEditable(false);
		}
		return jTextField3;
	}
	
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		//	jTextField2.setText("jTextField2");
			jTextField1.setEditable(false);
		}
		return jTextField2;
	}

	
	JTextField jTextField0;
	private JTextField getJTextField0() {
		
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
			jTextField1.setEditable(false);
		}
		return jTextField0;
	}
	JButton jButton0;
	private JButton getJButton0() {
		
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Settlement");
		}
		return jButton0;
	}

	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setText("CANCELLED");
			jTextField1.setEditable(false);
		}
		return jTextField11;
	}
	JButton jButton1;
	private JButton getJButton1() {
	
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
		}
		return jButton1;
	}
	JLabel jLabel0;
	private JLabel getJLabel0() {
		
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("TradeID");
		}
		return jLabel0;
	}
	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("SettleDate");
		}
		return jLabel2;
	}
	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Payer");
		}
		return jLabel3;
	}
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setAutoCreateRowSorter(true);
			
		}
		return jTable1;
	}

	private JLabel getJLabel18() {
		if (jLabel18 == null) {
			jLabel18 = new JLabel();
			jLabel18.setText("Attributes");
		}
		return jLabel18;
	}

	private JTextField getJTextField10() {
		if (jTextField10 == null) {
			jTextField10 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField10.setText("jTextField10");
		}
		return jTextField10;
	}

	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField9.setText("jTextField9");
		}
		return jTextField9;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
		///	jTextField8.setText("jTextField8");
		}
		return jTextField8;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("ValueDate");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Status");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("TransferType");
		}
		return jLabel8;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField5.setText("jTextField5");
		}
		return jTextField5;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField6.setText("jTextField6");
		}
		return jTextField6;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField7.setText("jTextField7");
		}
		return jTextField7;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField1.setEditable(false);
		//	jTextField4.setText("jTextField4");
		}
		return jTextField4;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Receiver");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Currency");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Product");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("EventType");
		}
		return jLabel4;
	}


}
