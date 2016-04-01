

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import productPricing.MMPricing;
import productPricing.Pricer;
import productPricing.pricingUtil.MMCashFlow;
import util.NumericTextField;
import util.RemoteServiceUtil;
import util.commonUTIL;
import apps.window.tradewindow.BackOfficePanel;
import apps.window.tradewindow.CommonPanel;
import apps.window.tradewindow.TradePanel;
import apps.window.tradewindow.FXPanels.TradeAttributesD;
import apps.window.tradewindow.panelWindow.FeesPanel;
import apps.window.tradewindow.panelWindow.SDIPanel;
import apps.window.tradewindow.panelWindow.TaskPanel;
import apps.window.tradewindow.panelWindow.TransferPanel;
import apps.window.tradewindow.util.StaticDataCacheUtil;
import beans.Book;
import beans.Coupon;
import beans.Favorities;
import beans.Flows;
import beans.LegalEntity;
import beans.Product;
import beans.Trade;
import beans.Users;

import com.jidesoft.combobox.TableExComboBox;

import dsEventProcessor.TaskEventProcessor;

//VS4E -- DO NOT REMOVE THIS LINE!
public class TradeTemplate  extends TradePanel {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	 TradeAttributesD attributes = null;
	private JPanel jPanel5;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JButton New;
	private JButton Save;
	private JButton SaveAsNew;
	private JButton Delete;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JTextField TradeID;
	private NumericTextField Amount1;
	private JTextField status;
	private JTable CashFlowTable;
	private JScrollPane jScrollPane1;
	private JButton jButton4;
	private JButton jButton5;
	private JComboBox actionC;
	private JLabel jLabel18;
	Vector<String> currencyData = null;
	Vector<LegalEntity> legalEntityData  = null;
	Vector<LegalEntity> traderData  = null;
	Vector<beans.Book> bookData = null;
	apps.window.tradewindow.TradeApplication app = null;
	 SDIPanel sdiPanel = null;
	 FeesPanel feesPanel = null;
	 TaskPanel taskPanel = null; 
	 TransferPanel transferPanel;
	 MMPricing pricing = null;
	public TableExComboBox counterParty = null;
	public TableExComboBox book = null;
	public TableExComboBox trader = null;
	Trade trade = null;
	Product product = null;
	Coupon coupon = null;
	DecimalFormat format1 = new DecimalFormat("##,###,#######.######");
	
	javax.swing.DefaultComboBoxModel<String> actionstatus = null;
	String productType = "MM";
	String productSubType = "";
	 Hashtable<String,String>  attributeDataValue = new Hashtable<String,String>();
	 DefaultTableModel attributeModel = null;
	 ActionMap actionMap =null;
Users usr = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	
	public TradeTemplate( ) {
		//setUser(user);
		initComponents();
	
	}
	//public MMTradePanel() {
	//	initComponents();
//	}

	private void initComponents() {
		
		
		currencyData = StaticDataCacheUtil.getDomainValues("Currency");
		actionstatus = new javax.swing.DefaultComboBoxModel<String>();
		bookData = StaticDataCacheUtil.getUserFavBooks(usr.getId(),Favorities.book);
		traderData = StaticDataCacheUtil.getUserFavTrader(usr.getId(), Favorities.trader);
		legalEntityData = StaticDataCacheUtil.getUserFavLegalEntity(usr.getId(), Favorities.CounterParty);
		
	
	
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setFocusable(true);
		setEnabled(true);
		setVisible(true);
		setVerifyInputWhenFocusTarget(true);
		setDoubleBuffered(true);
		setRequestFocusEnabled(true);
		setOpaque(true);
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Leading(1, 866, 8, 8), new Leading(99, 122, 16, 16))); // business 
		add(getJPanel3(), new Constraints(new Leading(0, 866, 12, 12), new Leading(314, 151, 12, 12))); // cashflow
		add(getJPanel0(), new Constraints(new Leading(1, 866, 12, 12), new Leading(3, 95, 10, 10)));  // trade main common fields
		add(getHotKeysPanels(), new Constraints(new Leading(5, 866,10, 10), new Leading(471, 46,10, 10))); // buttons
		add(getJPanel4(), new Constraints(new Leading(882, 334, 10, 10), new Leading(5, 517, 10, 10))); // attributes
		add(getJPanel2(), new Constraints(new Leading(3, 866, 10, 10), new Leading(224, 94, 10, 10))); // bussiness
		setSize(1225, 544);
		  actionMap = new ActionMapUIResource();
		    actionMap.put("action_save", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	buildTrade(trade,"save");
		        }
		    });
		    actionMap.put("action_new", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	//commonUTIL.showAlertMessage("New action performed.");
		        	buildTrade(trade,"NEW");
		        }
		    });
		    actionMap.put("action_del", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	commonUTIL.showAlertMessage("Delete action performed.");
		        }
		    });
		    actionMap.put("action_saveasnew", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	//commonUTIL.showAlertMessage("Save As New action performed.");
		        	buildTrade(trade,"saveAsNew");
		        
		        //	commonUTIL.showAlertMessage("Save As new  action performed.");
		        }
		    });
		    actionMap.put("action_cashflow", new AbstractAction() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	//commonUTIL.showAlertMessage("Save As New action performed.");
		        	buildCashFlow();
		        
		        //	commonUTIL.showAlertMessage("Save As new  action performed.");
		        }

				
		    });
		   
		    processTableData(attributeDataValue,attributeModel,attributes); 
		    setCashFlow(getCashFlowTable(), null, "MM"); // for empty Cash Flow

	}

	
	private JComboBox getActionC() {
		if (actionC == null) {
			actionC = new JComboBox();
			 actionstatus.insertElementAt("NEW", 0);
	   		 actionstatus.setSelectedItem("NEW");
	   		actionC.setModel(actionstatus);
		}
		return actionC;
	}



	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("RollBack");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("RollOver");
		}
		return jButton4;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			 
			
		}
		return jPanel2;
	}

	



	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getCashFlowTable());
		}
		return jScrollPane1;
	}

	
	
	public JTable getCashFlowTable() {
		if (CashFlowTable == null) {
			CashFlowTable = new JTable();
			CashFlowTable.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return CashFlowTable;
	}

	private JTextField getStatus() {
		if (status == null) {
			status = new JTextField();
			status.setBackground(new Color(128, 255, 255));
			status.setEditable(false);
			status.setText("NONE");
		}
		return status;
	}


	private NumericTextField getNominal() {
		if (Amount1 == null) {
			Amount1 = new NumericTextField(10,format1);
		}
		return Amount1;
	}


	private JTextField getTradeID() {
		if (TradeID == null) {
			TradeID = new JTextField();
			TradeID.setText("0");
			TradeID.addKeyListener(new KeyAdapter() {
			@Override
            public void keyTyped(KeyEvent e) {
            	 try {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                
                	 String number = TradeID.getText();
	                   int tradeId = Integer.parseInt(number); 
	                  
						trade = (Trade) RemoteServiceUtil.getRemoteTradeService().selectTrade(tradeId);
					
                 
                }
            	 } catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch(NumberFormatException n) {
						TradeID.setText("");
	                	 commonUTIL.showAlertMessage("Enter Number only " );
	                	 
	                 }
            }
            });
		}
		
		return TradeID;
	}

	private TableExComboBox getTrader() {
		if (trader == null) {
			trader  =  getTraderComboBox(traderData);
			trader.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					int leid = trader.getSelectedIndex();
					// @ yogesh 24/02/2015
					// returns if -1
					if( leid == -1)
						return;
					LegalEntity le = traderData.get(leid);
					trader.setName(String.valueOf(((le)).getId()));
					//trader.getModel().setSelectedItem(leid);
					//System.out.println(counterPary.getName());
					
				}
			});
			
		}
			return trader;
			
			
		
	}

	private TableExComboBox getCounterParty() {
		if (counterParty == null) {
			counterParty =  getCounterPartyComboBox(legalEntityData);
			counterParty.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int leid = counterParty.getSelectedIndex();
					// @ yogesh 24/02/2015
					// returns if -1
					if( leid == -1)
						return;
					LegalEntity le = legalEntityData.get(leid);
					counterParty.setName(String.valueOf(((le)).getId()));
					//System.out.println(counterPary.getName());
					
				}
			});
			
		}
			return counterParty;
	}

	private TableExComboBox getBook() {
		if (book == null) {
			book =  getBookComboBox(bookData);
			book.setEditable(false);
			book.setBorder(null);
			book.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int leid = book.getSelectedIndex();
					// @ yogesh 24/02/2015
					// returns if -1
					if( leid == -1)
						return;
					Book boo = bookData.get(leid);
					book.setName(String.valueOf(((boo)).getBookno()));
					//System.out.println(counterPary.getName());
					
				}
			});
			
		}
			return book;
	}
	

	private JLabel getJLabel9() {
		if (jLabel9 == null) {
			jLabel9 = new JLabel();
			jLabel9.setText("Action");
		}
		return jLabel9;
	}

	private JLabel getJLabel8() {
		if (jLabel8 == null) {
			jLabel8 = new JLabel();
			jLabel8.setText("Status");
		}
		return jLabel8;
	}

	private JButton getJButton3() {
		if (Delete == null) {
			Delete = new JButton();
			Delete.setText("Delete");
		}
		return Delete;
	}

	private JButton getJButton2() {
		if (SaveAsNew == null) {
			SaveAsNew = new JButton();
			SaveAsNew.setText("saveAsnew");
		
			SaveAsNew.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					buildTrade(trade,"saveAsNew");
					
				}
			});
		}
		return SaveAsNew;
	}

	private JButton getJButton1() {
		if (Save == null) {
			Save = new JButton();
			Save.setText("Save");
			Save.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					buildTrade(trade,"save");
					
				}
			});
		}
		return Save;
	}

	private JButton getJButton0() {
		if (New == null) {
			New = new JButton();
			New.setText("New");
			New.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					buildTrade(trade,"NEW");
					
				}
			});
		}
		return New;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			
			
		}
		return jPanel1;
	}

	

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("Trade ID");
		}
		return jLabel5;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Trader");
		}
		return jLabel3;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getTrader(), new Constraints(new Leading(532, 177, 10, 10), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(9, 10, 10), new Leading(49, 12, 12)));
			jPanel0.add(getCounterParty(), new Constraints(new Leading(304, 175, 12, 12), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel2(),  new Constraints(new Leading(211, 12, 12), new Leading(49, 12, 12)));
			jPanel0.add(getBook(), new Constraints(new Leading(67, 138, 10, 10), new Leading(44, 26, 10, 12)));
			jPanel0.add(getJLabel5(),  new Constraints(new Leading(7, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getTradeID(), new Constraints(new Leading(67, 138, 12, 12), new Leading(7, 26, 40, 40)));
			//jPanel0.add(getJLabel1(), new Constraints(new Leading(155, 10, 10), new Leading(15, 12, 12)));
			jPanel0.add(getJLabel9(), new Constraints(new Leading(233, 10, 10), new Leading(10, 12, 12)));
			jPanel0.add(getActionC(), new Constraints(new Leading(304, 174, 12, 12), new Leading(5, 26, 10, 51)));
			jPanel0.add(getJLabel8(), new Constraints(new Leading(483, 10, 10), new Leading(12, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(485, 41, 12, 12), new Leading(49, 25, 12, 12)));
			jPanel0.add(getStatus(), new Constraints(new Leading(535, 170, 12, 12), new Leading(5, 26, 10, 51)));
		}
		return jPanel0;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("CounterParty");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Book");
		}
		return jLabel1;
	}


	private JPanel getHotKeysPanels() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel5.setLayout(new GroupLayout());
			jPanel5.add(getJButton0(), new Constraints(new Leading(19, 10, 10), new Leading(8, 10, 10)));
			jPanel5.add(getJButton1(), new Constraints(new Leading(98, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton2(), new Constraints(new Leading(179, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton4(), new Constraints(new Leading(301, 10, 10), new Leading(8, 12, 12)));
			jPanel5.add(getJButton5(), new Constraints(new Leading(388, 12, 12), new Leading(8, 12, 12)));
		//	jPanel5.add(getJButton5(), new Constraints(new Leading(489, 10, 10), new Leading(8, 12, 12)));
		}
		return jPanel5;
	}
// for Attributes. 
	private JPanel getJPanel4() {
		if (attributes == null) {
			attributes = new TradeAttributesD();
			attributes.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			//attributes.setLayout(new GroupLayout());
			
		}
		return attributes;
	}

	
	
	
// cash flow panel
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
		//	jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Leading(3, 853, 10, 10), new Leading(4, 139, 10, 10)));
		}
		return jPanel3;
	}

	public TaskPanel getTaskPanel() {
		return taskPanel;
	}
	public FeesPanel getFeesPanel() {
		return feesPanel;
	}
	public SDIPanel getSdiPanel() {
		return sdiPanel;
	}

	public void setSdiPanel(SDIPanel sdiPanel) {
		this.sdiPanel = sdiPanel;
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
	public void setLimitPanel(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTradeTransfers(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		transferPanel = (TransferPanel) panel;
		
	}
	public void getTradeTransfers(BackOfficePanel panel) {
		try {
			transferPanel.setTrade(trade);
			panel.fillJTabel((Vector)RemoteServiceUtil.getRemoteBOProcessService().queryWhere("Transfer", "tradeId = " + trade.getId()));
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void setTradePostings(BackOfficePanel panel) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void setUser(Users user) {
		// TODO Auto-generated method stub
		this.usr =user;
		
	}

	@Override
	public String getAction() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void processTask(TaskEventProcessor taskEvent, Users WindowUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLimitBreachMarkOnAction(int i) {
		// TODO Auto-generated method stub
		
	}
	private boolean validateProductData() {
		boolean flag = false;
		
		
		return flag;
		
	}
	@Override
	public void buildTrade(Trade trade, String actionType) {
		// TODO Auto-generated method stub
		if(actionType.equalsIgnoreCase("NEW")) {
			setNewAction(trade);
		}
		if(actionType.equalsIgnoreCase("saveAsNew")) {
			product = new Product();
			coupon = new Coupon();
			actionC.setSelectedItem("NEW");
			actionC.getModel().setSelectedItem("NEW");
			if(validateProductData() && validateTradeData()) {
				setSaveAsNew(trade);
			}
		}
		if(actionType.equalsIgnoreCase("save")) {
			if(validateProductData() && validateTradeData()) {
				setSave(trade);
			}
		}
		
	}
	
	private boolean validateTradeData() {
		boolean flag = false;
		
		return flag;
	}
	private void setSave(Trade trade2) {
		// TODO Auto-generated method stub
		//trade = new Trade();
		
			} 
		
	private void setSaveAsNew(Trade trade2) {
		// TODO Auto-generated method stub
		
			} 
		
		
	//	trade.sett
		
	

	private void setNewAction(Trade tradeNew) {
		// TODO Auto-generated method stub
	
		
		
		
		
	}
	
	

	@Override
	public void openTrade(Trade trade) {
		// TODO Auto-generated method stub
		
		    
		
		
	}
	private void buildCashFlow() {
		// TODO Auto-generated method stub
		pricing = (MMPricing) getPricer();
		setCashFlow(getCashFlowTable(),(Vector<Flows>) getCashFlows(),"MM");
	}
	@Override
	public void setPanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTradePanelValue(CommonPanel tradeValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection getCashFlows() {
		// TODO Auto-generated method stub
		pricing.price(getTrade(),getTrade().getProduct(), getTrade().getProduct().getCoupon());
		 MMCashFlow cashFlow =   pricing.generateCashFlow();
		return cashFlow.getFlows();
	}

	@Override
	public Pricer getPricer() {
		// TODO Auto-generated method stub
		pricing = new MMPricing();
		
		return pricing;
	}
	
	public void setPricing(MMPricing pricing) {
		pricing = (MMPricing) getPricer();
		
	}
 
public void calculatePrice(MMPricing price,Trade trade,Product product,Coupon coupon) {
		
	price.price(trade, product, coupon);
         MMCashFlow cashFlow = price.generateCashFlow();
         
        // setCashFlows(cashFlow.getFlows());
         setPricing(pricing);
         
       
	}
 
@Override
public ActionMap getHotKeysActionMapper() {
	// TODO Auto-generated method stub
	return actionMap;
}

@Override
public JPanel getHotKeysPanel() {
	// TODO Auto-generated method stub
	return jPanel5;
}

@Override
public void setTaskPanel(BackOfficePanel panel) {
	// TODO Auto-generated method stub
	
}

@Override
public void setTradeApplication(apps.window.tradewindow.TradeApplication app1) {
	// TODO Auto-generated method stub
	this.app = app1;
}



}

