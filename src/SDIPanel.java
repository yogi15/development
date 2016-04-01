

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.commonUTIL;
import apps.window.tradewindow.BackOfficePanel;
import beans.LegalEntity;
import beans.Sdi;
import beans.Task;
import beans.Trade;
import beans.TransferRule;
import bo.transfer.rule.ProductTransferRule;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class SDIPanel  extends BackOfficePanel  {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JTextField jTextField1;
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
	private JLabel jLabel11;
	private JTextField jTextField8;
	private JTextField jTextField9;
	private JTextField jTextField10;
	private JTextField jTextField11;
	private JButton jButton0;

	public SDIPanel() {
		initComponents();
	//	init();
	}

	private void initComponents() {
		setFocusable(true);
		setEnabled(true);
		setVisible(true);
		setDoubleBuffered(true);
		setVerifyInputWhenFocusTarget(true);
		setRequestFocusEnabled(true);
		setOpaque(true);
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(8, 8, 1129), new Bilateral(7, 323, 10, 282)));
		add(getJPanel0(), new Constraints(new Bilateral(8, 9, 0), new Trailing(8, 309, 329, 329)));
		setSize(1134, 526);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane0(), new Constraints(new Bilateral(7, 10, 1089), new Bilateral(7, 7, 10, 254)));
		}
		return jPanel1;
	}

	private JTextField getJTextField13() {
		if (jTextField13 == null) {
			jTextField13 = new JTextField();
			///jTextField13.setText("jTextField13");
		}
		return jTextField13;
	}

	private JLabel getJLabel13() {
		if (jLabel13 == null) {
			jLabel13 = new JLabel();
			jLabel13.setText("SettlementDate");
		}
		return jLabel13;
	}

	private JTextField getJTextField12() {
		if (jTextField12 == null) {
			jTextField12 = new JTextField();
			//jTextField12.setText("jTextField12");
		}
		return jTextField12;
	}

	private JLabel getJLabel12() {
		if (jLabel12 == null) {
			jLabel12 = new JLabel();
			jLabel12.setText("Security");
		}
		return jLabel12;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setAutoCreateRowSorter(true);
			//
			 String col [] = { "Pay/Rec", "TransferType", "Curr", "ProductType","PayerName", "Payer Role", "Payer Agent", "Receiver","ReceiverRole", "ReceiverAgent", "Method","TradeID","Security","SettleDate" };
		        tmodel = new DefaultTableModel(col,0);
		        jTable0.setModel(tmodel);
		}jTable0.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = jTable0.getSelectedRow();
				jTextField1.setText((String) jTable0.getValueAt(selectRow, 0));
				jTextField4.setText((String) jTable0.getValueAt(selectRow, 1));
				jTextField8.setText((String) jTable0.getValueAt(selectRow, 2));
				jTextField0.setText((String) jTable0.getValueAt(selectRow, 3));
				jTextField5.setText((String) jTable0.getValueAt(selectRow, 4));
				jTextField9.setText((String) jTable0.getValueAt(selectRow, 5));
				jTextField2.setText((String) jTable0.getValueAt(selectRow, 6));
				jTextField7.setText((String) jTable0.getValueAt(selectRow, 7));
				jTextField10.setText((String) jTable0.getValueAt(selectRow, 8));
				jTextField3.setText((String) jTable0.getValueAt(selectRow, 9));
				jTextField6.setText((String) jTable0.getValueAt(selectRow, 10));
				jTextField11.setText(new Integer((Integer) jTable0.getValueAt(selectRow, 11)).toString());
				jTextField12.setText((String) jTable0.getValueAt(selectRow, 12));
				jTextField13.setText((String) jTable0.getValueAt(selectRow, 13));
				
				
				
			
				
			}
        
        	
        });
		return jTable0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Route");
		}
		return jButton0;
	}

	private JTextField getJTextField11() {
		if (jTextField11 == null) {
			jTextField11 = new JTextField();
			//jTextField11.setText("jTextField11");
		}
		return jTextField11;
	}

	private JTextField getJTextField10() {
		if (jTextField10 == null) {
			jTextField10 = new JTextField();
			//jTextField10.setText("jTextField10");
		}
		return jTextField10;
	}

	private JTextField getJTextField9() {
		if (jTextField9 == null) {
			jTextField9 = new JTextField();
			//jTextField9.setText("jTextField9");
		}
		return jTextField9;
	}

	private JTextField getJTextField8() {
		if (jTextField8 == null) {
			jTextField8 = new JTextField();
		//	jTextField8.setText("jTextField8");
		}
		return jTextField8;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
			jLabel11.setText("TradeID");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
			jLabel10.setText("ReceiverRole");
		}
		return jLabel10;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("PayerRole");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Currency");
		}
		return jLabel8;
	}

	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
		//	jTextField5.setText("jTextField5");
		}
		return jTextField5;
	}

	private JTextField getJTextField6() {
		if (jTextField6 == null) {
			jTextField6 = new JTextField();
		//	jTextField6.setText("jTextField6");
		}
		return jTextField6;
	}

	private JTextField getJTextField7() {
		if (jTextField7 == null) {
			jTextField7 = new JTextField();
		//	jTextField7.setText("jTextField7");
		}
		return jTextField7;
	}

	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
		//	jTextField4.setText("jTextField4");
		}
		return jTextField4;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("Method");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("Receiver");
		}
		return jLabel6;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("PayerName");
		}
		return jLabel5;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("TransferType");
		}
		return jLabel4;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(9, 10, 10), new Leading(16, 10, 10)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(95, 71, 10, 10), new Leading(42, 12, 12)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(95, 71, 12, 12), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(93, 71, 12, 12), new Leading(68, 12, 12)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(93, 71, 12, 12), new Leading(94, 12, 12)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(181, 93, 10, 10), new Leading(16, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(178, 93, 12, 12), new Leading(44, 12, 12)));
			jPanel0.add(getJLabel6(), new Constraints(new Leading(176, 93, 12, 12), new Leading(68, 12, 12)));
			jPanel0.add(getJLabel7(), new Constraints(new Leading(176, 81, 10, 10), new Leading(94, 12, 12)));
			jPanel0.add(getJTextField4(), new Constraints(new Leading(276, 137, 10, 10), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField7(), new Constraints(new Leading(276, 137, 12, 12), new Leading(68, 12, 12)));
			jPanel0.add(getJTextField5(), new Constraints(new Leading(276, 137, 12, 12), new Leading(42, 12, 12)));
			jPanel0.add(getJTextField6(), new Constraints(new Leading(276, 137, 12, 12), new Leading(96, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(435, 93, 10, 10), new Leading(14, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(435, 93, 12, 12), new Leading(40, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(435, 93, 12, 12), new Leading(68, 14, 12, 12)));
			jPanel0.add(getJLabel11(), new Constraints(new Leading(435, 93, 12, 12), new Leading(96, 14, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(683, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 74, 12, 12), new Leading(42, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(7, 78, 12, 12), new Leading(72, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(9, 74, 12, 12), new Leading(98, 12, 12)));
			jPanel0.add(getJLabel12(), new Constraints(new Leading(11, 74, 46, 477), new Leading(124, 12, 12)));
			jPanel0.add(getJLabel13(), new Constraints(new Leading(438, 93, 10, 10), new Leading(122, 14, 12, 12)));
			jPanel0.add(getJTextField13(), new Constraints(new Leading(537, 137, 10, 10), new Leading(122, 12, 12)));
			jPanel0.add(getJTextField8(), new Constraints(new Leading(540, 137, 12, 12), new Leading(14, 12, 12)));
			jPanel0.add(getJTextField9(), new Constraints(new Leading(540, 137, 12, 12), new Leading(38, 12, 12)));
			jPanel0.add(getJTextField10(), new Constraints(new Leading(540, 137, 12, 12), new Leading(66, 12, 12)));
			jPanel0.add(getJTextField11(), new Constraints(new Leading(540, 137, 12, 12), new Leading(94, 12, 12)));
			jPanel0.add(getJTextField12(), new Constraints(new Leading(93, 320, 12, 12), new Leading(124, 12, 12)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("PayerAgent");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("ProductType");
		}
		return jLabel1;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
		//	jTextField3.setText("jTextField3");
		}
		return jTextField3;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("ReceiverRole");
		}
		return jLabel3;
	}

	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
		//	jTextField2.setText("jTextField2");
		}
		return jTextField2;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			//jTextField1.setText("jTextField1");
		}
		return jTextField1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("PayRec");
		}
		return jLabel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
			//jScrollPane0.setVerticalScrollBarPolicy(JScrollBar.VERTICAL);
		//	jScrollPane0.setHorizontalScrollBarPolicy(JScrollBar.HORIZONTAL);
		}
		return jScrollPane0;
	}

	@Override
	public void fillJTabel(Vector data) {
		if(data != null && (!data.isEmpty())) {
			Hashtable<String, TransferRule> checkDuplicate = new Hashtable<String, TransferRule>();
			setSdis(data);
			rules =	rule.generateRules(trade);
		
			Iterator it = rules.iterator();
			int r = tmodel.getRowCount();
			for(int rows =r;rows > 0;rows--)  {
					tmodel.removeRow(rows-1);
			}
			jTable0.repaint();
			
			int i =0;
			while(it.hasNext()) {
				TransferRule trule = (TransferRule) it.next();
				Sdi sdi = trule.get__sMethod();
				String messageType = sdi.getMessageType();
				String Format = sdi.getsdiformat();
				String agentName =  getLEName(sdi.getAgentId());
				int account = sdi.getAccountID();
				String key = trule.get_payReceive()+trule.get_transferType()+trule.get_settlementCurrency()+trule.get_productType()+trule.get_payerLegalEntityId()+trule.get_payerLegalEntityRole()+agentName+trule.get_receiverLegalEntityId()+trule.get_receiverLegalEntityRole()+sdi.getkey();
				if(!checkDuplicate.containsKey(key)) {
					checkDuplicate.put(key, trule);
					tmodel.insertRow(i, new Object[]{ trule.get_payReceive(),trule.get_transferType(),trule.get_settlementCurrency(),trule.get_productType(),getLEName(trule.get_payerLegalEntityId()),trule.get_payerLegalEntityRole(),agentName, getLEName(trule.get_receiverLegalEntityId()),trule.get_receiverLegalEntityRole(),agentName,messageType,trade.getId(),getProductName(trule.get_productId()),commonUTIL.dateToString(trule.get_settleDate().getDate())});
					i++;
				}
				
			}
		}  else {
			commonUTIL.showAlertMessage(" SDI missing for " + trade.getId());
		}
		
	}
	
	public void setSdis(Vector sdis) {
		this.sdis = sdis;
	}
	 public static  ServerConnectionUtil de = null;
	 DefaultTableModel tmodel; 
	    RemoteReferenceData referenceData;
	    public ProductTransferRule getRule() {
			return rule;
		}

		public void setRule(ProductTransferRule rule) {
			this.rule = rule;
		}
		ProductTransferRule rule;
		Trade trade= null;
		Vector<TransferRule> rules = new Vector();  
		Vector sdis = new Vector();
		private JLabel jLabel12;
		private JTextField jTextField12;
		private JLabel jLabel13;
		private JTextField jTextField13;
		private JPanel jPanel1; 
		 public Vector getSdis() {
			return sdis;
		}
		 
		 public Trade getTrade() {
				return trade;
			}

			public void setTrade(Trade trade) {
				this.trade = trade;
			}
			
			 public String getProductName(int id) {
				 String productName = "";
				 if(id > 0) {
					 productName = trade.getTradedesc();
				 }
				 return productName;
			 }
			public boolean checkAlreadyExists(Task task) {
				 boolean flag = true;
				 for(int i = 1;i < tmodel.getRowCount();i++) {
					 String s = (String) tmodel.getValueAt(i, 0);
					 int ss = new Integer(s).intValue();
					 if(task.getId() == ss) {
						
						  flag = false;
					 }
					 
					
				 }
				 return flag;
			 }	
			
			private Sdi getSdi(int sdid) {
				 Sdi sd = null;
				 if((sdis != null) && (!sdis.isEmpty())) {
					 for(int i=0;i<sdis.size();i++) {
						 Sdi s = (Sdi) sdis.elementAt(i);
						 if(s.getId() == sdid) {
							 sd =s;
						     break;
						 }
					 }
					 
					 
					 
				 }
				 return sd;
			 }
			 
			 
			 public String getLEName(int id) {
				   String name = "";
				   try {
					   if(id ==  0) 
						   return name;
					LegalEntity le = (LegalEntity )referenceData.selectLE(id);
					name = le.getName();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   return name;
			   }
		 public void init() {
  		 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
		   	 try {
		   	 referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
		   	
		        jTable0.setModel(tmodel);
		   	 } catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }

}
