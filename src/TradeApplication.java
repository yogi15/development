import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TradeApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar1;
	private JPanel jPanel1;
	private JTabbedPane jTabbedPane0;
	private JToolBar jToolBar0;
	private JPanel jPanel0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton0;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	//private JPanel jPanel2;
//	private JSplitPane jSplitPane0;
//	private JPanel jPanel3;
//	private JTabbedPane jTabbedPane1;
//	private JPanel jPanel4;
	private JButton jButton3;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel7;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TradeApplication() {
    	initComponents();
    }
    
    //new javax.swing.JPanel();
    
	
	
   
	

	private void initComponents() {
		
		setLayout(new GroupLayout());
	//	init(name,user);
		add(getJPanel0(), new Constraints(new Bilateral(6, 9, 978), new Bilateral(8, 7, 10, 499)));
		
	/*	 if(productWindowpanel != null) {
		        productWindowpanel.setPanelValue(tradeP); 
		        jTabbedPane1.add(productWindowpanel); 
		        final   CashFlowPanel cashFlowp = makeCashFlowPanel(name);
		        jTabbedPane1.add("CashFlow",cashFlowp);
		        jTabbedPane1.setTitleAt(1, "<HTML> C<BR>A<BR>S<BR>H<BR> <BR> <BR>F<BR>L<BR>O<BR>W<BR>");
		        jTabbedPane1.setTabPlacement(JTabbedPane.LEFT);
		        tradeP.setPanelValue(productWindowpanel);    
		        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
		        	 
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						Vector cashFlows = (Vector) productWindowpanel.getCashFlows();
						if(cashFlows != null) {
						cashFlowp.setCashFlows(cashFlows); 
						}
					}

					
		        });
		        
		        } else {
		        	jTabbedPane1.setVisible(false);
				//	bottomPanel.setVisible(false);
					//  add(jTabbedPane1);
					//  setSize(900,600);
		        }*/
	/*	 JMenuBar menuBar = new JMenuBar();
		 setJMenuBar(menuBar);
		 JMenu fileMenu = new JMenu("File");
	   //     cashFlow = new JMenu("CashFlow");
	     //   cashFlow.setEnabled(false);
	        menuBar.add(fileMenu);
	     //   menuBar.add(cashFlow);
	   
	        // Create and add simple menu item to one of the drop down menu
	        JMenuItem newAction = new JMenuItem("New");
	        JMenuItem saveAction = new JMenuItem("Save");
	        JMenuItem saveAsNewAction = new JMenuItem("Save as New");
	        JMenuItem deleteAction = new JMenuItem("Delete");
	        JMenuItem auditAsNewAction = new JMenuItem("Monitor");
	        
	        JMenuItem cutAction = new JMenuItem(".....");
	        JMenuItem exitAction = new JMenuItem("Exit");
	        JMenuItem opemAction = new JMenuItem("Open ");
	       
	        fileMenu.add(newAction);
	        fileMenu.add(saveAction);
	        fileMenu.addSeparator();
	        fileMenu.add(opemAction);
	        fileMenu.add(saveAsNewAction);
	        fileMenu.add(auditAsNewAction);
	        fileMenu.add(deleteAction);
	        fileMenu.add(exitAction);
	        cashFlow.add(cutAction);*/
	    /*    auditAsNewAction.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	if(tradeP.getTrade() == null) {
	            		
	            		commonUTIL.showAlertMessage("Select Trade");
	            		return;
	            	}
	            			
	            	setTradeId(tradeP.getTrade().getId());
	            	 String s [] = {"id","Chanage Date","Change Field","Type","Trade Version","Column Values","Trade Attributes","User","Group"};
	             	DefaultTableModel tablemodel = new DefaultTableModel(s,0);
	             	processTableData(tablemodel);
	             	AuditTradeWindow showAudit= new AuditTradeWindow(tablemodel);
	               showAudit.setTitle("Trade Audit Window");
	               
	               showAudit.setSize(700, 400);
	               showAudit.setFocusable(false);
	               showAudit.setVisible(true);
	               
	            }
	            });
	        
	        
	        saveAsNewAction.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	System.out.println("Call to menu saveASNEW " + trade);
	            	trade = new Trade();
	            	 
	            	 tradeP.buildTrade(trade,"SAVEASNEW");
	            	 if(productWindowpanel != null) {
	        			 productWindowpanel.buildTrade(trade,"SAVEASNEW");
	        		 }
	            	 if(trade.getTradedesc1() != null && trade.getTradedesc1().trim().length() > 0)  {
	            		 
	            		 trade.setUserID(userName.getId());
	            		
	             
	            		 try {
	               	         
				               	 trade.setId(0);
				               	 trade.setStatus("NONE");
				               	 trade.setAction("NEW");
				               	feesPanel.refreshFees();
				             
				              // trade.setFees(feesPanel.getFeesDataV());
				               	 int i= 	remoteTrade.saveTrade(trade);
				               	 tradeId = i;
				               	 if(i == -4) {
				         			commonUTIL.showAlertMessage("Trade is Lock by another User ");
				            			return;
				            		 }
				               	 if(i > 0) 
				               		commonUTIL.showAlertMessage("Trade Saved with  "+ i);
				               		 
				               	 //System.out.println("*************** " +i);
				               	 trade = (Trade) remoteTrade.selectTrade(i);
				              // 	tradeW = trade;
				               	 tradeP.saveASNew(trade);
				                // taskManager.setTradWIndow(tradeP);
	            		 } catch (RemoteException e) {
	            			 // TODO Auto-generated catch block
	            			 e.printStackTrace();
	            		 } 
	                 
	               
	            	 }
	            }
	        });
	        newAction.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	trade.setId(0);
	            	tradeId = 0;
	              tradeP.buildTrade(trade,arg0.getActionCommand());
	              if(productWindowpanel != null) { 
	               productWindowpanel.buildTrade(trade,"NEW"); 
	              }
	             
	              // editMenu.setEnabled(false);
	    			getTradeTransfers(transferPanel);
	    			 getTradePostings(postingPanel);
	    			getTradeTask(taskPanel);
	    			feesPanel.refreshFees();
	               
	            }
	        });
	        deleteAction.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            int tradeID = 0;
	              if(tradeP.getTrade() != null && tradeP.getTrade().getId() > 0) {
	            	  tradeID = tradeP.getTrade().getId();
	            	  Trade trade = new Trade();
	            	  tradeP.buildTrade(trade,"SAVE");
	                  trade.setUserID(userName.getId());
	                  if(productWindowpanel != null) {
	                  productWindowpanel.buildTrade(trade,"SAVE");
	                  }
	                  trade.setVersionID(-1);
	            	  trade.setId(tradeID);
	            	  try {
						remoteTrade.removeTrade(trade);
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	              }
	              
	              trade.setId(0);
	              tradeP.buildTrade(trade,"NEW");
	              if(productWindowpanel != null) { 
	               productWindowpanel.buildTrade(trade,"NEW");
	              }
	             
	              // editMenu.setEnabled(false);
	    			getTradeTransfers(transferPanel);
	    			getTradeTask(taskPanel);
	    			 getTradePostings(postingPanel);
	    			 commonUTIL.showAlertMessage("Trade " + tradeID + " Deleted");
	    			
	            }
	        });
	        
	        saveAction.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	if(tradeP.getAction() == null) {
	            		commonUTIL.showAlertMessage("Select Action");
	            			            		return;
	            	}
	            	tradeP.buildTrade(trade,"SAVE");
	                trade.setUserID(userName.getId());
	                if(productWindowpanel != null) { 
	                productWindowpanel.buildTrade(trade,"SAVE");
	                }
	            
	               try {
	            	 	 trade.setFees(feesPanel.getFeesDataV());
	     		 int i= 	remoteTrade.saveTrade(trade);
	     		 tradeId = i;
	     		 if(i == -4) {
	     			commonUTIL.showAlertMessage("Trade is Lock by another User ");
	        			return;
	        		 }
	     		 if(i == -3) {
	        			commonUTIL.showAlertMessage("Amend not allowed on Cancel Trade");
	        			return;
	        		 }
	     		 if(i > 0) 
	     			commonUTIL.showAlertMessage("Trade Updated");
	  			
	     			 
	     			//System.out.println("*************** " +i);
	     		trade = (Trade) remoteTrade.selectTrade(i);
	     	//	tradeW = trade;
	  			tradeP.saveASNew(trade);
	     		} catch (RemoteException e) {
	     			// TODO Auto-generated catch block
	     			e.printStackTrace();
	     		} 
	                
	    		  
	               
	            }
	        });
	        String s [] = {"Tradeid","ProductName"};
	    	DefaultTableModel tablemodel = new DefaultTableModel(s,0) {
	    		 @Override
	    		    public boolean isCellEditable(int row, int column) {
	    		       //all cells false
	    		       return false;
	    		    }

	    	};
	    	processTableDataOpen(tablemodel,getProductTypeName());
	      final  JDialogTable showAllTrades = new JDialogTable(tablemodel);
	      showAllTrades.setLocationRelativeTo(this);
	      opemAction.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent arg0) {
	        	  String s [] = {"Tradeid","ProductName"};
	        	  showAllTrades.jTable1.removeAll();
	          	DefaultTableModel tablemodel = new DefaultTableModel(s,0);
	          	processTableDataOpen(tablemodel,getProductTypeName());
	          	showAllTrades.jTable1.setModel(tablemodel);
	        	  showAllTrades.setVisible(true);
	        	 
	             
	          }
	      });
	      
	      showAllTrades.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					String tradeid = showAllTrades.jTable1.getValueAt(showAllTrades.jTable1.getSelectedRow(),0).toString();
					int id  =new  Integer(tradeid).intValue();
					try {
						
							 trade = (Trade) remoteTrade.selectTrade(id);
							 tradeId = trade.getId();
						//	 tradeW = trade; // used when task for same trade is publish
							 openTrade(trade);
							showAllTrades.dispose();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					
				
				
	    
	    	
	    }); */
		setSize(1221, 707);
	}

	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			jPanel7 = new JPanel();
			jPanel7.setBorder(new LineBorder(Color.black, 1, false));
			jPanel7.setLayout(new GroupLayout());
		}
		return jPanel7;
	}

	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.setBorder(new LineBorder(Color.black, 1, false));
			jPanel6.setLayout(new GroupLayout());
		}
		return jPanel6;
	}

	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setBorder(new LineBorder(Color.black, 1, false));
			jPanel5.setLayout(new GroupLayout());
		}
		return jPanel5;
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(new LineBorder(Color.black, 1, false));
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJPanel6(), new Constraints(new Bilateral(8, 8, 1129), new Bilateral(7, 323, 10, 282)));
			jPanel4.add(getJPanel7(), new Constraints(new Bilateral(8, 9, 0), new Trailing(8, 309, 329, 329)));
		}
		return jPanel4;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(new LineBorder(Color.black, 1, false));
			jPanel3.setLayout(new GroupLayout());
		}
		return jPanel3;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("jButton3");
			jButton3.setToolTipText("Logs");
		}
		return jButton3;
	}

	/*private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
		return  jPanel4;
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
		//	jTabbedPane1.addTab("jPanel3",new backOfficePanel());
		}
		return jTabbedPane1;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
		}
		return jPanel3;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jSplitPane0.setDividerLocation(343);
			jSplitPane0.setOrientation(JSplitPane.VERTICAL_SPLIT);
			jSplitPane0.setTopComponent(getJTabbedPane1());
		}
		return jSplitPane0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 =new JPanel();
			//jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	} */

	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setIcon(new ImageIcon(getClass().getResource("/resources/icon/new_folder.gif")));
			jButton7.setToolTipText("Delete");
		}
		return jButton7;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setIcon(new ImageIcon(getClass().getResource("/resources/icon/open.jpg")));
			jButton6.setToolTipText("Save As New");
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setIcon(new ImageIcon(getClass().getResource("/resources/icon/open.jpg")));
			jButton5.setToolTipText("Open");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("jButton4");
		}
		return jButton4;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("SWT");
			jButton0.setToolTipText("CashFlow");
		}
		return jButton0;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/new_folder.gif")));
			jButton2.setToolTipText("Save");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setIcon(new ImageIcon(getClass().getResource("/resources/icon/open.jpg")));
			jButton1.setToolTipText("New");
		}
		return jButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJToolBar0(), new Constraints(new Bilateral(6, 12, 958), new Leading(5, 23, 10, 10)));
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(41, 9, 94), new Bilateral(34, 12, 5)));
			jPanel0.add(getJToolBar1(), new Constraints(new Leading(6, 23, 109, 109), new Leading(34, 70, 10, 10)));
		}
		return jPanel0;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
		//	jToolBar0.setBorder(new LineBorder(Color.black, 1, false));
			jToolBar0.add(getJButton1());
			jToolBar0.add(getJButton5());
			jToolBar0.add(getJButton2());
			jToolBar0.add(getJButton6());
			jToolBar0.add(getJButton7());
		}
		return jToolBar0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBorder(new LineBorder(Color.black, 1, false));
			jTabbedPane0.addTab("jPanel2", getJPanel2());
			jTabbedPane0.addTab("jPanel3", getJPanel3());
			jTabbedPane0.addTab("jPanel4",new SDIPanel());
		}
		return jTabbedPane0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
		//	jPanel1.add(getJPanel4(), new Constraints(new Bilateral(4, 5, 0), new Leading(5, 226, 10, 10)));
	//		jPanel1.add(getJSplitPane0(), new Constraints(new Bilateral(4, 5, 7), new Leading(237, 371, 10, 10)));
		}
		return jPanel1;
	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setOrientation(SwingConstants.VERTICAL);
			jToolBar1.setAlignmentX(0.0f);
			jToolBar1.add(getJButton0());
			jToolBar1.add(getJButton3());
			jToolBar1.addComponentListener(new ComponentAdapter() {
	
				public void componentResized(ComponentEvent event) {
					//jToolBar1ComponentComponentResized(event);
				}
			});
		}
		return jToolBar1;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	
	public static void main(String args[]) {
		TradeApplication tr = new TradeApplication();
		tr.setVisible(true);
	}
	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	
	/*private void init(String name,Users user) {
		
		
		setProductTypeName(name);
    	userName = user;
    	setUserName(user);
    	forTaskUser = user;
    	de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getLocalHostName() );
	   	 try {
	   		remoteTrade = (RemoteTrade) de.getRMIService("Trade");
	   		boremote = (RemoteBOProcess) de.getRMIService("BOProcess");
	   		remoteTask = (RemoteTask) de.getRMIService("Task");
	   		remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   		remoteAccount = (RemoteAccount) de.getRMIService("Account");
	   		remoteProduct = (RemoteProduct) de.getRMIService("Product");
			//	System.out.println(remoteTrade);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("TradeApplication ", "init" + name, e);
			}
    	setTitle(name);
    	panelName = name;
    	  setTitle("Trade Window " + getUserName());
	}

	private void initproductTradePanel(String name,Users user) {
		tradeP =  makeTradePanel(name);
	    tradeP.setUser(user);
	    productWindowpanel = makeProductPanel(name);
	   
	    initTransferPanel(name,user);
		initPostingPanel(name,user);
		initSdiPanel(name,user);
		initFeesPanel(name,user);
		initTaskPanel(name,user);
		tradeP.setSDIPanel(sdiPanel);
        tradeP.setTaskPanel(taskPanel);
        tradeP.setTradeTransfers(transferPanel);
        tradeP.setFEESPanel(feesPanel);
        tradeP.setTradePostings(postingPanel);
        
     //   taskManager.setTradWIndow(tradePanel);
	}
	
	
	
	private void initTransferPanel(String name, Users user) {
		transferRule = 	getTransferRuleHandler(name);
        transferRule.setRefDate(remoteReference);
        transferRule.setRemoteBOProcess(boremote);
        transferRule.setRemoteTrade(remoteTrade);
        transferRule.setRemoteProduct(remoteProduct);
		
        transferPanel = (apps.window.tradewindow.panelWindow.TransferPanel) makeBOOperationPanel("Transfer");
        transferPanel.setRemoteProduct(remoteProduct);
        transferPanel.setRemoteBO(boremote);
        transferPanel.setRefData(remoteReference);
        transferPanel.setUser(userName);
		
	}
	private void initPostingPanel(String name,Users user) {
		 postingPanel =(apps.window.tradewindow.panelWindow.PostingPanel) makeBOOperationPanel("Posting");
	        postingPanel.setRemoteAccount(remoteAccount);
	        postingPanel.setRemoteRef(remoteReference);
	}
	
	
	private void initSdiPanel(String name,Users user) {
		  taskPanel = (TaskPanel)  makeBOOperationPanel("Task");
	}
	private void initFeesPanel(String name,Users user) {
		  feesPanel = (FeesPanel) makeBOOperationPanel("Fees");
	}
	
private void initTaskPanel(String name,Users user) {
	 sdiPanel = (apps.window.tradewindow.panelWindow.SDIPanel) makeBOOperationPanel("SDI");
	 sdiPanel.setRule(transferRule);
	}
	
	private void jToolBar1ComponentComponentResized(ComponentEvent event) {
	}
	public Users getUserName() {
		return userName;
	}
	public void setUserName(Users userName) {
		this.userName = userName;
		tradeP.setUser(userName);
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
	
public void setOpenTrade(Trade newTrade) {
		
		tradeP.openTrade(newTrade);
		  if(productWindowpanel != null) {
		productWindowpanel.openTrade(newTrade);
		  }
		trade = newTrade;
		getTradeTask(taskPanel);
		transferPanel.setTrade(trade);
		getTradeTransfers(transferPanel);
		getTradePostings(postingPanel);
		// TODO Auto-generated method stub
		
	}
public void openTrade(Trade trade) {
	tradeP.openTrade(trade);
	 if(productWindowpanel != null) {  
	productWindowpanel.openTrade(trade);
 	 feesPanel.setTrade(trade);
	 }
	//if(e.getClickCount() == 2) 
	 getTradeTask(taskPanel);
	 getTradeTransfers(transferPanel);
	 getTradePostings(postingPanel);
}
public void getTradeTransfers(BackOfficePanel panel) {
	try {
		panel.setTrade(trade);
		panel.fillJTabel((Vector)boremote.queryWhere("Transfer", "tradeId = " + trade.getId()));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void getTradePostings(PostingPanel postingPanel) {
	// TODO Auto-generated method stub
	try {
		postingPanel.fillJTabel((Vector)remoteAccount.getPostingonWhereClause("tradeid = "+trade.getId()));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public void getTradeTask(BackOfficePanel panel) {
	try {
		//System.out.println(trade);
	Vector data = (Vector) remoteTask.selectTaskWhere("tradeId = " + trade.getId());
		panel.fillJTabel((data));
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void getTradeFees(BackOfficePanel panel) {
	
    panel.setTrade(trade);
	
}
	 protected CommonPanel makeProductPanel(String name) {
	        String productWindowName = "apps.window.tradewindow."  + name.toUpperCase() + "Panel";
	        CommonPanel panel = null;
	        
	        try {
	        Class class1 =    ClassInstantiateUtil.getClass(productWindowName,true);
	        panel =  (CommonPanel) class1.newInstance();
	           //  productWindow = (BondPanel) 
	        } catch (Exception e) {
	            System.out.println( e);
	        }

	        return panel;
	    }
	    
	    protected TradePanel makeTradePanel(String name) {
	        String productWindowName = "apps.window.tradewindow."  + name.toUpperCase() + "TradePanel";
	        TradePanel panel = null;
	        
	        try {
	        Class class1 =    ClassInstantiateUtil.getClass(productWindowName,true);
	        panel =  (TradePanel) class1.newInstance();
	           //  productWindow = (BondPanel) 
	        } catch (Exception e) {
	            System.out.println( e);
	        }

	        return panel;
	    }
	
	protected BackOfficePanel makeBOOperationPanel(String name) {
        String productWindowName = "apps.window.tradewindow.panelWindow."  + name + "Panel";
        BackOfficePanel panel = null;
        
        try {
        Class class1 =    ClassInstantiateUtil.getClass(productWindowName,true);
        panel =  (BackOfficePanel) class1.newInstance();
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
            System.out.println( e);
        }

        return panel;
    }
	protected CashFlowPanel makeCashFlowPanel(String name) {
        String productWindowName = "apps.window.tradewindow.cashflowpanel."  + name.toUpperCase() + "CashFlowPanel";
        CashFlowPanel panel = null;
        
        try {
        Class class1 =    ClassInstantiateUtil.getClass(productWindowName,true);
        panel =  (CashFlowPanel) class1.newInstance();
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
            System.out.println( e);
        }

        return panel;
    }
	
	private ProductTransferRule getTransferRuleHandler(String name) {
        String productTransfer = "bo.transfer.rule.Generate"  + name.toUpperCase() + "TransferRule";
        ProductTransferRule transferRuleHandler = null;
        
        try {
        	transferRuleHandler = (ProductTransferRule) rulehandlers.get(name);
        	if(transferRuleHandler == null) {
        Class class1 =    ClassInstantiateUtil.getClass(productTransfer,true);
        transferRuleHandler =  (ProductTransferRule) class1.newInstance();
        rulehandlers.put(name, transferRuleHandler);
        }
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
        	commonUTIL.displayError("JFrameTradeApplication  ", "getTransferRuleHandler <<<<< not able to create Handler ", e);
        }

        return transferRuleHandler;
    }
	
	
	public  synchronized void processTasks(TaskEventProcessor taskEvent) {
		System.out.println(getTitle() + "  " +  tradeId + " version   " + tradeP.getTrade().getId()+ " version   " + tradeP.getTrade().getVersion());
		String userid = getTitle().substring(getTitle().indexOf(":")+1,getTitle().length());
		int uid  = new Integer(userid).intValue();
		if((taskEvent.getUserID() != uid) && (taskEvent.getTradeID() == tradeP.getTrade().getId()))  {
			commonUTIL.showAlertMessage("Trade amended by another user ");
		//	openTrade(taskEvent.getTrade());
		}
	  //  tradeP.processTask(taskEvent,forTaskUser);
	}
	private void processTableData(DefaultTableModel model) {
	   	Vector vector;
		try {
			vector = (Vector) remoteTrade.getAuditedTrade(getTradeId());
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		Audit audit = (Audit) it.next();
	    		Users user = (Users) remoteReference.selectUser(audit.getUserid());
	    		model.insertRow(i, new Object[]{audit.getTradeid(),audit.getChangeDate(),audit.getFieldname(),audit.getType(),audit.getVersion(),audit.getTattribue(),user.getUser_name(),user.getUser_groups()});
	    		i++;
	    		}
	    		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private void processTableDataOpen(DefaultTableModel model,String name) {
	
    	Vector vector;
		try {
			vector = (Vector) remoteTrade.selectforOpen(name.trim());
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		Trade trade = (Trade) it.next();
	    	
	    		model.insertRow(i, new Object[]{trade.getId(),trade.getTradedesc()});
	    		i++;
	    		}
	    		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/

}
