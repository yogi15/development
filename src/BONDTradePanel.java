

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import productPricing.BONDPricing;
import productPricing.MMPricing;
import productPricing.Pricer;
import util.NumericTextField;
import util.commonUTIL;
import dsEventProcessor.TaskEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import beans.Trade;
import beans.Users;
import apps.window.tradewindow.BackOfficePanel;
import apps.window.tradewindow.CommonPanel;
import apps.window.tradewindow.TradePanel;
import apps.window.tradewindow.panelWindow.FeesPanel;
import apps.window.tradewindow.panelWindow.PostingPanel;
import apps.window.tradewindow.panelWindow.SDIPanel;
import apps.window.tradewindow.panelWindow.TaskPanel;
import apps.window.tradewindow.panelWindow.TransferPanel;
import beans.Book;
import beans.LegalEntity;
import beans.StartUPData;


//VS4E -- DO NOT REMOVE THIS LINE!
public class BONDTradePanel extends  TradePanel  {

	private static final long serialVersionUID = 1L;
	private JTable JTable1;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JComboBox jcp;
	private JComboBox book;
	private JComboBox ttradername;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JTextField tradestatus;
	private JComboBox caction;
	//private JTextField tradeamount;
	public JLabel jLabel6;
	public JLabel jLabel7;
	public JTextField tradeid;
	public JLabel jLabel8;
	public JComboBox tcurrency;
	private JLabel jLabel9;
	//private JTextField tradedate;
	public JTextField tuse;
	public JLabel jLabel10;
	private JLabel jLabel11;
	public NumericTextField tradeamount;
	public JTextField tradeDate;
	public NumericTextField tprice;
	public NumericTextField tradeyield;
	public JTextField jTextField0;
	public JPanel jPanel1;
	 public javax.swing.JTextField tsettlement;
	 public NumericTextField tquantity;
	
	 TaskPanel taskPanel = null; 
	 PostingPanel postingPanel = null;
	 TransferPanel transferPanel = null;
	 SDIPanel sdiPanel = null;
	 FeesPanel feesPanel = null;
	 Trade trade = null;
	 BONDPanel productWindowpanel = null;
	 
	 
	
	 Users user = null;
 	 String productType = "";
 	 
 	 public static  ServerConnectionUtil de = null;
 	 RemoteReferenceData referenceData; 	
 	 RemoteTrade rtradeservices;
 	 RemoteBOProcess remoteBO;
	 RemoteTask remoteTask;
	 
	 
	 javax.swing.DefaultComboBoxModel bookCombo = new javax.swing.DefaultComboBoxModel();
 	 Hashtable bookData = new Hashtable();
 	 javax.swing.DefaultComboBoxModel cpCombo = new javax.swing.DefaultComboBoxModel();
 	 Hashtable cpData = new Hashtable();
 	 javax.swing.DefaultComboBoxModel traderCombo = new javax.swing.DefaultComboBoxModel();
 	 Hashtable trader = new Hashtable();
 	 javax.swing.DefaultComboBoxModel actionstatus = new javax.swing.DefaultComboBoxModel();
 	 javax.swing.DefaultComboBoxModel currencyDataModel = new javax.swing.DefaultComboBoxModel();
 	 Hashtable  attributeValue = new Hashtable();
 	 DefaultTableModel attributeModel = null;
 	 DecimalFormat format = new DecimalFormat("##,###,#######");
 	 
 	 
 	 
 	 
 	 
 	 
 	 
	
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public BONDTradePanel() {
		 init();
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		
		tsettlement = new JTextField();
		tquantity = new NumericTextField(10,format);
		tquantity.setText("0");
		
		add(getJPanel0(), new Constraints(new Bilateral(8, 10, 1146), new Bilateral(6, 20, 10)));
		setSize(1164, 230);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane0(), new Constraints(new Bilateral(10, 12, 25), new Leading(10, 164, 10, 10)));
		}
		return jPanel1;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setBackground(Color.white);
		//	jTextField0.setFont(new Font("Dialog", Font.PLAIN, 13));
			jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JTextField getTprice() {
		if (tprice == null) {
			tprice = new NumericTextField(10,format);
			tprice.setBackground(Color.white);
		//	tprice.setFont(new Font("Dialog", Font.PLAIN, 13));
			tprice.setText("0"); // NOI18N
	        tprice.setVisible(true);
		}tprice.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) { 
                	if(trade != null) {
                	
                	if(trade.getProductType().equalsIgnoreCase("Bond")) {
                		BONDPricing pricing = (BONDPricing) productWindowpanel.getPricer();
                	pricing.calculateYield(new Double(tprice.getText()).doubleValue());
				    tradeyield.setText(commonUTIL.doubleFormat(pricing.getYield()));
                	}
                }
                
                }
            }
        	
        });
		return tprice;
	} 

	private JTextField getTradeyield() {
		if (tradeyield == null) {
			tradeyield = new NumericTextField(10,format);
			tradeyield.setBackground(Color.white);
		//	tradeyield.setFont(new Font("Dialog", Font.PLAIN, 13));
			tradeyield.setText("0");
		}
		return tradeyield;
	}

	private JTextField getTradedate() {
		if (tradeDate == null) {
			tradeDate = new JTextField();
			tradeDate.setBackground(Color.white);
		//	tradeDate.setFont(new Font("Dialog", Font.PLAIN, 13));
			 tradeDate.setText((commonUTIL.getDateFormat(commonUTIL.getCurrentDate())));
		        tradeDate.setEditable(true);
		}
		return tradeDate;
	}

	private NumericTextField getTradeamount() {
		if (tradeamount == null) {
			tradeamount = new NumericTextField(10,format);
			tradeamount.setBackground(Color.white);
		//	tradeamount.setFont(new Font("Dialog", Font.PLAIN, 13));
			 tradeamount.setText("0");
		}
		tradeamount.addKeyListener(new KeyAdapter() {

            
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                //	trade.getTradeAmount() / product.getFaceValue();
                  try {
					double amt  = tradeamount.getDoubleValue();
					if(productWindowpanel.product == null) {
						commonUTIL.showAlertMessage("Select Product");
						return;
					}
					String value = new Double(amt/   productWindowpanel.product.getFaceValue()).toString();
					productWindowpanel.nominal.setText(value);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                  
                
                }
            }
            });
		tradeamount.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				try {
					double amt  = tradeamount.getDoubleValue();
					if(productWindowpanel.product != null) {
						String value = new Double(amt/   productWindowpanel.product.getFaceValue()).toString();
						productWindowpanel.nominal.setText(value);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		return tradeamount;
	}

	private JComboBox getTcurrency() {													
		if (tcurrency == null) {
			tcurrency = new JComboBox();
		//	tcurrency.setFont(new Font("SansSerif", Font.PLAIN, 13));
			tcurrency.setModel(new DefaultComboBoxModel(new Object[] {"INR", "item0", "item1", "item2", "item3" }));
			tcurrency.setSelectedItem(new String("INR"));
		}
		return tcurrency;
	}

	private JLabel getJLabel11() {
		if (jLabel11 == null) {
			jLabel11 = new JLabel();
		//	jLabel11.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel11.setText("Market Price");
		}
		return jLabel11;
	}

	private JLabel getJLabel10() {
		if (jLabel10 == null) {
			jLabel10 = new JLabel();
		//	jLabel10.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel10.setText("Trade Yield");
		}
		return jLabel10;
	}

	private JTextField getTuse() {
		if (tuse == null) {
			tuse = new JTextField();
			tuse.setBackground(Color.white);
			tuse.setEditable(false);
	//		tuse.setFont(new Font("Dialog", Font.PLAIN, 13));
		}
		return tuse;
	}

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
	//		jLabel9.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel9.setText("Settle Date");
		}
		return jLabel9;
	}

	private JComboBox getJComboBox4() {
		if (tcurrency == null) {
			tcurrency = new JComboBox();
		//	tcurrency.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 tcurrency.setSelectedItem(new String("INR"));
			tcurrency.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
		}
		return tcurrency;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
		//	jLabel8.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel8.setText("Currency");
		}
		return jLabel8;
	}

	private JTextField getTradeid() {
		if (tradeid == null) {
			tradeid = new JTextField();
			tradeid.setBackground(Color.white);
			tradeid.setEditable(true);
			tradeid.setText("0");
		//	tradeid.setFont(new Font("Dialog", Font.PLAIN, 13));
		}tradeid.addKeyListener(new KeyAdapter() {

            
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                 try {	
                   String number = tradeid.getText();
                   int tradeId = Integer.parseInt(number); 
                   trade = (Trade) rtradeservices.selectTrade(tradeId);
                   
                   if(trade != null) {
                	   if(! trade.getProductType().equalsIgnoreCase(productType)) {
                     	  commonUTIL.showAlertMessage("This Trade Will not open in current Window");
                       }
                	   setTrade(trade);
					openTrade(trade);
					productWindowpanel.openTrade(trade);
					//if(e.getClickCount() == 2) 
					 getTradeTask(taskPanel);
					 getTradeSDI(sdiPanel);
                   } else {
                	   commonUTIL.showAlertMessage("Trade does not Exists with id " );
                   }
                   
                 } catch(NumberFormatException n) {
                	 tradeid.setText("");
                	 commonUTIL.showAlertMessage("Enter Number only ");
                	 
                 } catch(RemoteException r) {
                	 System.out.println("JFrameTradeWindowApplication  " +  e);
                 }
                }
            }
        });
        
		return tradeid;
	}

	private JLabel getJLabel7() {
		if (jLabel7 == null) {
			jLabel7 = new JLabel();
		//	jLabel7.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel7.setText("ID");
		}
		return jLabel7;
	}

	private JLabel getJLabel6() {
		if (jLabel6 == null) {
			jLabel6 = new JLabel();
		//	jLabel6.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel6.setText("User");
		}
		return jLabel6;
	}

	

	private JComboBox getCaction() {
		if (caction == null) {
			caction = new JComboBox();
			actionstatus.insertElementAt("NEW", 0);
	        caction.setModel(actionstatus);
	        caction.setSelectedIndex(0);
		}
		return caction;
	}

	private JTextField getTradestatus() {
		if (tradestatus == null) {
			tradestatus = new JTextField();
			tradestatus.setBackground(new Color(128, 255, 255));
			tradestatus.setEditable(false);
		//	tradestatus.setFont(new Font("Dialog", Font.PLAIN, 13));
			 tradestatus.setText("NONE");
		}
		return tradestatus;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
		//	jLabel5.setFont(new Font("Dialog",Font.PLAIN, 13));
			jLabel5.setText("Trade Amount");
		}
		return jLabel5;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
		//	jLabel3.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel3.setText("Status");
		}
		return jLabel3;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
		//	jLabel4.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel4.setText("Action");
		}
		return jLabel4;
	}

	private JComboBox getTtradername() {
		if (ttradername == null) {
			ttradername = new JComboBox();
			 ttradername.setModel(traderCombo);
		}
		return ttradername;
	}

	private JComboBox getBook() {
		if (book == null) {
			book = new JComboBox();
			 book.setModel(bookCombo);
		}
		return book;
	}

	private JComboBox getJcp() {
		if (jcp == null) {
			jcp = new JComboBox();
			 jcp.setModel(cpCombo);
		}
		return jcp;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
		//	commonUTIL.setLabelFont(jLabel2);
			//jLabel2.setFont(new Font("SansSerif-12", Font.PLAIN, 12));
			jLabel2.setText("Trader Name");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
		//.setLabelFont(jLabel2);
		//	jLabel1.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel1.setText("Book");
		}
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			//commonUTIL.setLabelFont(jLabel2);
	//		jLabel0.setFont(new Font("Dialog", Font.PLAIN, 13));
			jLabel0.setText("Legal Entity");
		}
		return jLabel0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setFont(new Font("SansSerif-12", Font.PLAIN, 12));
		//	jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(12, 95, 10, 10), new Leading(22, 21, 12, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(12, 95, 40, 469), new Leading(70, 21, 12, 12)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(12, 95, 40, 469), new Leading(114, 21, 10, 10)));
			jPanel0.add(getJcp(), new Constraints(new Leading(131, 160, 10, 10), new Leading(16, 27, 12, 12)));
			jPanel0.add(getBook(), new Constraints(new Leading(131, 160, 46, 314), new Leading(64, 27, 12, 12)));
			jPanel0.add(getTtradername(), new Constraints(new Leading(131, 160, 46, 314), new Leading(108, 27, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(309, 82, 10, 10), new Leading(162, 21, 12, 12)));
			jPanel0.add(getJLabel6(), new Constraints(new Leading(573, 34, 10, 10), new Leading(22, 21, 12, 12)));
			jPanel0.add(getJLabel7(), new Constraints(new Leading(573, 25, 10, 10), new Leading(70, 21, 12, 12)));
			jPanel0.add(getTradeamount(), new Constraints(new Leading(406, 127, 46, 314), new Leading(111, 27, 12, 12)));
			jPanel0.add(getJLabel5(), new Constraints(new Leading(309, 92, 10, 10), new Leading(114, 21, 12, 12)));
			jPanel0.add(getTradedate(), new Constraints(new Leading(406, 127, 46, 314), new Leading(159, 27, 12, 12)));
			jPanel0.add(getTcurrency(), new Constraints(new Leading(131, 160, 332, 503), new Leading(156, 27, 12, 12)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(12, 82, 10, 10), new Leading(162, 21, 12, 12)));
			jPanel0.add(getJLabel10(), new Constraints(new Leading(309, 76, 10, 10), new Leading(25, 21, 12, 12)));
			jPanel0.add(getTradeyield(), new Constraints(new Leading(406, 127, 46, 314), new Leading(16, 27, 12, 12)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(577, 49, 10, 10), new Leading(117, 21, 12, 12)));
			jPanel0.add(getJLabel11(), new Constraints(new Leading(309, 82, 46, 314), new Leading(70, 21, 12, 12)));
			jPanel0.add(getTprice(), new Constraints(new Leading(406, 127, 46, 314), new Leading(64, 27, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(577, 48, 46, 314), new Leading(159, 21, 12, 12)));
			jPanel0.add(getTradeid(), new Constraints(new Leading(625, 140, 10, 10), new Leading(64, 27, 12, 12)));
			jPanel0.add(getTuse(), new Constraints(new Leading(625, 140, 46, 314), new Leading(16, 27, 12, 12)));
			jPanel0.add(getCaction(), new Constraints(new Leading(629, 140, 10, 10), new Leading(115, 27, 12, 12)));
			jPanel0.add(getTradestatus(), new Constraints(new Leading(633, 136, 46, 314), new Leading(162, 27, 12, 12)));
			jPanel0.add(getJPanel1(), new Constraints(new Bilateral(780, 14, 350), new Leading(14, 179, 10, 10)));
			
			commonUTIL.setLabelFont(jLabel0);
			commonUTIL.setLabelFont(jLabel1);
			commonUTIL.setLabelFont(jLabel2);
			commonUTIL.setLabelFont(jLabel9);
			commonUTIL.setLabelFont(jLabel6);
			commonUTIL.setLabelFont(jLabel7);
			commonUTIL.setLabelFont(jLabel5);
			commonUTIL.setLabelFont(jLabel4);
			commonUTIL.setLabelFont(jLabel3);
			commonUTIL.setLabelFont(jLabel11);
			commonUTIL.setLabelFont(jLabel8);
			commonUTIL.setLabelFont(jLabel10);
		
	        //  commonUTIL.setLabelFont(jCom);
			
			
			
            processDataCombo1(bookCombo,bookData,"Book",null); 
			
			processDataCombo1(cpCombo,cpData,"cp","CounterParty"); 
			processDataCombo1(traderCombo, trader, "cp", "Trader");
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (JTable1 == null) {
			JTable1 = new JTable();
			 String attributeColumnName [] =    {"Attribute Name ", "Attribute  Value "};
             
		        attributeModel = new DefaultTableModel(attributeColumnName,0);
		        processTableData(attributeModel);
		        JTable1.setModel(attributeModel);
			
		}
		return JTable1;
	}
	
	
	 private void setAttribute(String attributes) {
	    	String attributeColumnName [] =    {"Attribute Name ", "Attribute  Value "};
	        
	        DefaultTableModel attributeModel = new DefaultTableModel(attributeColumnName,0);
	        
	        
	    		if(attributes != null && attributes.length() > 0) {
	    		String atttoken [] = attributes.trim().split(";"); 
	    		
	    		
	    		for(int i =0;i<atttoken.length;i++) {
	    			String att = (String) atttoken[i];
	    			
	    			if(att.contains("=")) {
	    			String attvalue = att.substring(att.indexOf('=')+1, att.length());
	    			String attnameName = att.substring(0, att.indexOf('='));
	    			
	    			attributeModel.insertRow(i, new Object[]{attnameName.trim(),attvalue.trim()});
	    			}
	    		}
	    		JTable1.removeAll();
	            JTable1.setModel(attributeModel);
	    		}
	    	
	        
			
		}
	 public void getTradeSDI(BackOfficePanel panel) {
			try {
				
				sdiPanel.setTrade(trade);
				panel.fillJTabel((Vector) rtradeservices.getSDisOnTrade(trade));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	 
	 public int getBookKey(Hashtable t , int id) {
			int i = 0;
			Set set = t.entrySet();
		    Iterator it = set.iterator();
		    while (it.hasNext()) {
		      Map.Entry entry = (Map.Entry) it.next();
		     Book book =  ((Book) entry.getValue());
		     if(id == book.getBookno())
		    	 i = ((Integer) entry.getKey()).intValue();
		    }
	       return i;	    

		
			
		}
		public int getLEKey(Hashtable t , int id) {
			int i = 0;
			Set set = t.entrySet();
		    Iterator it = set.iterator();
		    while (it.hasNext()) {
		      Map.Entry entry = (Map.Entry) it.next();
		      LegalEntity le =  ((LegalEntity) entry.getValue());
		     if(id == le.getId())
		    	 i = ((Integer) entry.getKey()).intValue();
		    }
	       return i;	    

		
			
		}
	 
		
	
	private void processTableData(DefaultTableModel model) {
		// TODO Auto-generated method stub
   	Vector vector;
		try {
			vector = (Vector) referenceData.getStartUPData("TradeAttribute");
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		StartUPData tradeAttributes = (StartUPData) it.next();
	    		if(tradeAttributes.getName().equalsIgnoreCase("Trade Date")) {
	    	    	model.insertRow(i, new Object[]{tradeAttributes.getName(),commonUTIL.dateToString(commonUTIL.getCurrentDate())});
	    	    } else {
	    		   model.insertRow(i, new Object[]{tradeAttributes.getName(),"0"});
	    	    }
	    		
	    		i++;
	    		}
	    		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	
	public void processDataCombo1( javax.swing.DefaultComboBoxModel combodata,Hashtable ids,String name,String leRole) {
		Vector vector;
		try {
			if(name.equalsIgnoreCase("Book"))
			vector = (Vector) referenceData.selectALLBooks();
			else 
				vector = (Vector) referenceData.selectLEonWhereClause(" role = '"+leRole + "'");
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    	Object obj =	 it.next();
	    	
    		
	    	if(name.equalsIgnoreCase("Book"))	
    		combodata.insertElementAt(((Book)obj).getBook_name(), i);
	    	else 
	    		combodata.insertElementAt(((LegalEntity)obj).getName(), i);
    		ids.put(i, obj);
    		i++;
    	}	
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    	
    	
    }
	
	private void init() {
		this.productType = "BOND";
			de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
		   	 try {
		   	 referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
		   	rtradeservices = (RemoteTrade)	de.getRMIService("Trade");
		   	remoteTask = (RemoteTask) de.getRMIService("Task");
		   	remoteBO = (RemoteBOProcess) de.getRMIService("BOProcess");
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Override
	public void setTaskPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		taskPanel = (TaskPanel) panel;
	}

	@Override
	public void setTrade(Trade trade) {
		// TODO Auto-generated method stub
		 this.trade = trade;
	}

	@Override
	public Trade getTrade() {
		// TODO Auto-generated method stub
		return trade;
	}

	@Override
	public void saveASNew(Trade trade) {
		// TODO Auto-generated method stub
		tradeid.setText(new Integer(trade.getId()).toString());
		tradestatus.setText(trade.getStatus());
		actionstatus.removeAllElements();
		
	//	processActionData(actionstatus,trade.getTradedesc1()); 
		/// imp note : wf understand CASH productType as MM so passing MM as hardcoded
        processActionData(actionstatus,productType.toUpperCase(),trade.getTradedesc1(),tradestatus.getText(),rtradeservices);
		this.trade = trade;
		setTrade(trade);
		getTradeTransfers(transferPanel);
		getTradeTask(taskPanel);
		getTradeSDI(sdiPanel);
	}

	@Override
	public void setUser(Users user) {
		this.user = user;
		tuse.setText(user.getUser_name());
		
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		String action = null;
		if(caction.getSelectedIndex() == -1) {
			return null;
	}
	if(caction.getSelectedIndex() >= 0) {
			action = caction.getSelectedItem().toString();
	}
		return action;
	
	}

	@Override
	public void buildTrade(Trade trade, String actionType) {
	
		if(actionType.equalsIgnoreCase("NEW")) {
			tradeid.setText("0");
			tradestatus.setEditable(true);
			tradestatus.setText("NONE");
			tradestatus.setEditable(false);
			tcurrency.setSelectedItem(new String("INR"));
			tprice.setText("0");
			tquantity.setText("0");
			tradeyield.setValue(0);
			tradeamount.setText("0");
			actionstatus.removeAllElements();
			tsettlement.setText("");
			//processActionData(actionstatus,trade.getTradedesc1());
			actionstatus.insertElementAt("NEW", 0);
		
			caction.setModel(actionstatus);
			String ss = "";
			processTableData(attributeModel);
	        JTable1.setModel(attributeModel);
			 trade.setProductType(productType);
		//	JTable1.setValueAt("",JTable1.getSelectedRow(),1);
			book.setSelectedIndex(-1);
			jcp.setSelectedIndex(-1);
			ttradername.setSelectedIndex(-1);
		}else {
			trade.setId(new Integer(tradeid.getText()).intValue());
	        trade.setCpID(((LegalEntity) cpData.get(jcp.getSelectedIndex())).getId());
	        trade.setTraderID((((LegalEntity) trader.get(ttradername.getSelectedIndex())).getId()));
	        trade.setBookId(((Book) bookData.get(book.getSelectedIndex())).getBookno());
	        trade.setStatus(tradestatus.getText());
	        trade.setPrice(new Double(tprice.getText()).doubleValue());
	        trade.setTradeDate(getValueOfAttribute("Trade Date"));
	        if(!(caction.getSelectedIndex() == -1))
	        trade.setAction(caction.getSelectedItem().toString());
	        trade.setCurrency((String)tcurrency.getSelectedItem());
	        trade.setYield(new Double(tradeyield.getText()).doubleValue());
	        trade.setAttributes(getAttributeValue());
	        trade.setTradeAmount(new Double(tradeamount.getText()));
	        trade.setQuantity(0);
	        trade.setDelivertyDate(tsettlement.getText());
	        trade.setEffectiveDate(tsettlement.getText());
	        //trade.setTradeDate(commonUTIL.getTimeStampToString(commonUTIL.getSQLTimeStamp()));
	        trade.setProductType(productType);
	        
	       // System.out.println(trade.getAttributes());
	      
		}
	}

	@Override
	public void openTrade(Trade trade) {
		this.trade = trade;
		tradeid.setText(new Integer(trade.getId()).toString());
	      //  trade.setCpID(((LegalEntity) cpData.get(jcp.getSelectedIndex())).getId());
	        //trade.setBookId(((Book) bookData.get(book.getSelectedIndex())).getBookno());
	        tradestatus.setText(trade.getStatus());
	      //  tprice.setText(new Integer(trade.getPrice()).toString());
	        tradeDate.setText(trade.getTradeDate());
	        
	    //    processActionData(actionstatus,trade.getTradedesc1());
	        /// imp note : wf understand CASH productType as MM so passing MM as hardcoded
	        processActionData(actionstatus,"BOND",trade.getTradedesc1(),tradestatus.getText(),rtradeservices);
	        book.setSelectedIndex(getBookKey(bookData,trade.getBookId()));
	        jcp.setSelectedIndex(getLEKey(cpData,trade.getCpID()));
	        ttradername.setSelectedIndex(getLEKey(trader,trade.getTraderID()));
	        
	        caction.setSelectedItem(trade.getAction());
	        tsettlement.setText(trade.getDelivertyDate());
	      
	       tcurrency.setSelectedItem((trade.getCurrency()));
	       tradeyield.setText(new Double(trade.getYield()).toString());
	       tradeDate.setText(trade.getTradeDate());
	       tradeamount.setText(new Double(trade.getTradeAmount()).toString());
	      
	       tprice.setText(new Double(trade.getPrice()).toString());
	       tquantity.setText(new Double(trade.getQuantity()).toString());
	       tuse.setText(getUser(trade.getUserID()).getUser_name());
	       setAttribute(trade.getAttributes());
	   //    getTradeTask(taskPanel);
		//	 getTradeSDI(sdiPanel);
		
	}
	private String getAttributeValue() {
		// TODO Auto-generated method stub
		String attributes  = "";
		for(int i=0;i< JTable1.getRowCount();i++ ) {
			String attributename = ((String) JTable1.getValueAt(i, 0)).trim();
			String attributeValue = ((String) JTable1.getValueAt(i, 1)).trim();
			//attributeValue.trim();
			if(attributeValue != null && attributeValue.length() > 0)
			attributes = attributes.trim() + attributename+ "=" + attributeValue + ";";
			
		}
		if(attributes.trim().length() > 0)
		return attributes.substring(0, attributes.length()-1);
		return attributes;
	}
	
	private Users getUser(int i) {
		 try {
	    	 
			setUser((Users) referenceData.selectUser(i));
			return getUser();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
	}
	
	private String getValueOfAttribute(String attributeName) {
		String value = "";
		for(int i=0; i< JTable1.getRowCount();i++) {
		     String attributeN =  JTable1.getValueAt(i, 0).toString();
		     if(attributeN.equalsIgnoreCase(attributeName)) {
		    	 value = JTable1.getValueAt(i, 1).toString();
		    	 System.out.println("value----" + value);
		    	 break;
		     }
		}
		return value;
	}
	
	
	
	public void getTradeTask(BackOfficePanel panel) {
		try {
			System.out.println(panel);
		
			panel.fillJTabel((Vector)remoteTask.selectTaskWhere("tradeId = " + trade.getId()));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Users getUser() {
		return user;
	}
	
	@Override
	public void setPanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		productWindowpanel = (BONDPanel) tradeValue;
		
	}

	@Override
	public void setTradePanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection getCashFlows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pricer getPricer() {
		// TODO Auto-generated method stub
		return null;
	}
	 public void getTradeTransfers(BackOfficePanel panel) {
			try {
				transferPanel.setTrade(trade);
				panel.fillJTabel((Vector)remoteBO.queryWhere("Transfer", "tradeId = " + trade.getId()));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@Override
		public void setTradeTransfers(BackOfficePanel panel) {
			// TODO Auto-generated method stub
			transferPanel = (TransferPanel) panel;
		}


		@Override
		public void setSDIPanel(BackOfficePanel panel) {
			// TODO Auto-generated method stub
			sdiPanel = (SDIPanel) panel;
		}
		

		@Override
		public void setFEESPanel(BackOfficePanel panel) {
			// TODO Auto-generated method stub
			feesPanel = (FeesPanel) panel;
		}


		@Override
		public void setTradePostings(BackOfficePanel panel) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void processTask(TaskEventProcessor taskEvent,Users WindowUser) {
			// TODO Auto-generated method stub
			System.out.println(productType);
			System.out.println("Task" + taskEvent.getUserID());
			System.out.println(" WindowUser " + WindowUser.getId());
		}

		@Override
		public void setTradeApplication(TradeApplication app) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setTradeApplication(
				apps.window.tradewindow.TradeApplication app) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setLimitPanel(BackOfficePanel panel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ArrayList<Component> getFocusOrderList() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ActionMap getHotKeysActionMapper() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public JPanel getHotKeysPanel() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setLimitBreachMarkOnAction(int i) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setSDIPanelInstruction() {
			// TODO Auto-generated method stub
			
		}

		

}
