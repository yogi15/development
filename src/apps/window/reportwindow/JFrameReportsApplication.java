package apps.window.reportwindow;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.operationwindow.jobpanl.JobDatePanel;
import apps.window.reportwindow.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import util.commonUTIL;

import beans.Book;
import beans.LegalEntity;
import beans.StartUPData;
import beans.UserJob;
import beans.Users;

import com.jidesoft.plaf.LookAndFeelFactory;

import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

import apps.window.reportwindow.jideReport.JideView;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
import apps.window.util.windowUtil.JDialogBoxForChoice;
import apps.window.util.windowUtil.JDialogTable;
import apps.window.util.windowUtil.JTreeChoice;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame2.java
 *
 * Created on 7 Apr, 2013, 2:40:21 PM
 */
/**
 *
 * @author MPankaj
 */
public class JFrameReportsApplication extends javax.swing.JFrame {

    /** Creates new form NewJFrame2 */
	Users user = null;
	ReportColumns columns = new ReportColumns();
	ReportSQLGenerator sqlGen = new ReportSQLGenerator();
  	RemoteReferenceData referenceData;
  	String mainSQLKey = "Trade trade";// will hint only transfer table to create subquery on other tables.
  	RemoteTrade remoteTrade;
  	RemoteProduct remoteproduct;
  	javax.swing.DefaultComboBoxModel bookCombo = new javax.swing.DefaultComboBoxModel();
  	 Hashtable bookData = new Hashtable();
  	 javax.swing.DefaultComboBoxModel cpCombo = new javax.swing.DefaultComboBoxModel();
  	 Hashtable cpData = new Hashtable();
  	 javax.swing.DefaultComboBoxModel traderCombo = new javax.swing.DefaultComboBoxModel();
  	 Hashtable trader = new Hashtable();
  	 javax.swing.DefaultComboBoxModel productTypeCombox = new javax.swing.DefaultComboBoxModel();
  	final javax.swing.DefaultComboBoxModel productSubtypeCombox = new javax.swing.DefaultComboBoxModel();
  	 javax.swing.DefaultComboBoxModel productAttributes = new javax.swing.DefaultComboBoxModel();
  	DefaultListModel currencyList = new DefaultListModel();
  	DefaultListModel traderList = new DefaultListModel();
  	String columnSQL;
  	String leId = "";
    String traderid = "";
    String bookID = "";
    String productType = "";
    String productSubType = "";
    SwingReportDemo  demo = null;
    PivotReport pReport = null;
    Hashtable  attributeValue = new Hashtable();
    private javax.swing.JPanel jPanel1 = null;
    private javax.swing.JButton jButton1  = null;;
    private javax.swing.JLabel jLabel1  = null;;
    private javax.swing.JPanel jPanel2  = null;;
    JPanel panelViewReportdata = null;
    String searchCriteria = "";
    String mainsql = " select producttype,id ,tradedesc1,cpID,traderID,status,type,tradeDate,bookId,quantity,price,nominal  from Trade trade";
    FilterValues filterValues = null;
	Vector<StartUPData> searchCriteriaA;
	Vector<StartUPData> searchColumn;
	Vector<UserJob> jobs = null;
	RemoteReferenceData remoteBORef;
	RemoteTask remoteTask;
	//RemoteTrade remoteTrade;
	RemoteBOProcess remoteBo;
	ReportSearchPanel reportPanel;
    public JFrameReportsApplication(String Name,Users user) {
    	setUser(user);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
    	
    	 pReport = new PivotReport(null);
    	
    		
    final	  ReportTradeParamPanel frame = new ReportTradeParamPanel();
    	        
    	   
   	   ServerConnectionUtil de = null;
		try {
			de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
			referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
			 remoteproduct  = (RemoteProduct)	de.getRMIService("Product");
			 remoteTask = (RemoteTask) de.getRMIService("Task");
			 remoteBo = (RemoteBOProcess) de.getRMIService("BOProcess");
				searchCriteriaA = (Vector) referenceData.getStartUPData("SearchCriteria");
				 searchColumn = (Vector)  referenceData.getStartUPData("TradeColumn"); // take only tradeColumn.
				 filterValues = new FilterValues(referenceData,remoteTrade,remoteTask,remoteBo);
			//S
			
			remoteTrade = (RemoteTrade)	de.getRMIService("Trade");
			populateReportData(mainsql,false);
			
			
			//System.out.println("ppp");
		
		}catch(Exception ed) {
			System.out.println(ed);
			
		}
		getBookOnComboBox(bookCombo, bookData);
	    	frame.jComboBox4.setModel(bookCombo);
	    	getMasterDataOnComboBox(productAttributes, "securityCode");
	    	
	    	 frame.jComboBox3.setModel(productAttributes);
	    	getMasterDataOnComboBox(productTypeCombox, "ProductType");
	    	 frame.jComboBox1.setModel(productTypeCombox);
	    	
	    	// Creates a menubar for a JFrame
	        JMenuBar menuBar = new JMenuBar();
	        
	        // Add the menubar to the frame
	        setJMenuBar(menuBar);
	    	JMenu fileMenu = new JMenu("Report");
	        JMenu setupd = new JMenu("Report Column Setup");
	        JMenu formatMenu = new JMenu("Format");
	        JMenuItem columnconfigure = new JMenuItem("Columns ");
	        JMenuItem subcolms = new JMenuItem("SubColumns ");
	       
	        menuBar.add(fileMenu);
	        setupd.add(columnconfigure);
	        setupd.add(subcolms);
	        menuBar.add(setupd);
	        menuBar.add(formatMenu);
	        // Create and add simple menu item to one of the drop down menu
	        JMenuItem newAction = new JMenuItem("New");
	       JMenuItem save = new JMenuItem("Save");
	        JMenuItem saveAsNewAction = new JMenuItem("Save as New");
	       
	        JMenuItem exitAction = new JMenuItem("Exit");
	        JMenuItem opemAction = new JMenuItem("Open ");
	        JMenuItem Excel = new JMenuItem("Excel");
	        JMenuItem cvs = new JMenuItem("CVS ");
	        JMenuItem html = new JMenuItem("HTML ");
	        
	       
	       
		JPanel jpanel2 = new JPanel();
		//jpanel2 = new javax.swing.JPanel();
		// LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
	   
        
        
    
		 demo = new SwingReportDemo(pReport);
//		 jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report"));
        jPanel1 = demo.run();
        jPanel1.setLayout(new BorderLayout());
         DatePanel datep = new DatePanel("Trade");
       
        JPanel pand = new JPanel();
        pand.setLayout(new BorderLayout());
        pand.add(demo.getControlPanel(datep), BorderLayout.WEST);
        jPanel1.add(pand, BorderLayout.NORTH );
        panelViewReportdata =  demo.getView().getJidePanel();
        panelViewReportdata.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
		 jPanel1.add(panelViewReportdata, BorderLayout.CENTER);
		
       //  jPanel1.add(frame,BorderLayout.WEST);
     
         getUserTempleates(user.getId());
 		UserJob job = jobs.elementAt(0);
 		  Vector detailsJob = job.getDetailsJobs();
         reportPanel = new  ReportSearchPanel("Transfer",searchCriteriaA,searchColumn,filterValues,job,detailsJob,remoteTask,remoteTrade,getUser(),referenceData);
         reportPanel.addReportPanel(jPanel1);
         reportPanel.setColumnSQL(mainsql);
         reportPanel.setDatePanel(datep);
         reportPanel.setDemo(demo);
         reportPanel.setpReport(pReport);
         reportPanel.setReportType("trade"); // in small case will used to create where through search criteria.
         add(reportPanel);
        demo.hideButton.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				 if(demo.hideButton.getLabel().equalsIgnoreCase("Hide")) {
						frame.setVisible(false);
						demo.hideButton.setLabel("Show");
				 } else {
					 frame.setVisible(true);
						demo.hideButton.setLabel("Hide");
				 }
				
			}
        	
        });
       
  
   final  JTreeChoice choiceColumns = new JTreeChoice(this,columns.getTreeNodes(true));
  
   choiceColumns.setLocationRelativeTo(this);
  
   columnconfigure.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent arg0) {
			choiceColumns.setSize(380,400);
			choiceColumns.setVisible(true);
			
		}
		
 
 	
 });
   // get book ids 
   frame.jComboBox4.addItemListener( new ItemListener() {

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		 setBookId((Book) bookData.get(frame.jComboBox4.getSelectedIndex()));
	}
	   
   });
  // generates columns name collected from columns tree menu
   // generatest joins of selected columns.
  
   choiceColumns.addWindowListener(new WindowAdapter() {            
       public void windowClosing(WindowEvent e) {
         
    	     String tableName = "";
    	     String joinsTable = "";
        	 String tablerefer = "";
            	
              Object obj [] =  choiceColumns.cmodList2.toArray();
              if(obj.length >0) {
              String SQLcolumnsName = "";//sqlGen.getSQLColumns(obj);
              for(int i =0;i<obj.length;i++) { // this to get columns from hashtable to build table name
            	  tablerefer = tablerefer + (String) obj[i] + ",";
             }
             tableName = sqlGen.getSQLTables(tablerefer);
             if(!tableName.contains(mainSQLKey)) 
                 tableName = tableName + "," + mainSQLKey;
             frame.columns = SQLcolumnsName + "  from  " + tableName;
           
             String sql =" select " +  frame.columns +  " where " + sqlGen.getjoinSQL(tableName,"Trade" );
        //     System.out.println(" from sql " + sql);
             frame.columns = sql;
             mainsql = sql;
             reportPanel.setColumnSQL(sql);
             if(frame.columns.length() > 0)
                   populateReportData(mainsql,false);
       }
             choiceColumns.cmodList2.removeAllElements();
        }
	});
   
   // for search criteria. 
   frame.jButton4.addActionListener(new ActionListener() {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String sqlWhere = "";
		String tradeDates = sqlGen.getDatesWhereClause(frame.jDatePicker1.getSelectedDate(), frame.jDatePicker2.getSelectedDate(),"trade.tradeDate");
		String tradeSettlementDates = sqlGen.getDatesWhereClause(frame.jDatePicker5.getSelectedDate(), frame.jDatePicker6.getSelectedDate(),"trade.deliverydate");
		String productTyp= getProductType();
		String productSubTyp =getProductSubType();
		String currency = frame.jTextField7.getText();
		String bookid = bookID;
		if(( frame.jTextField9.getText().length() == 0))
			 leId = "";
	   sqlWhere =  sqlGen.createBookProductSQL(productTyp, productSubTyp, bookid, leId.trim(),traderid.trim(), currency.trim());
	  sqlWhere = sqlWhere + " and " + tradeDates;// + " and " + tradeSettlementDates;
	//  System.out.println(sqlWhere);
	  searchCriteria = sqlWhere;
	  populateReportData(searchCriteria,true);
	}
	   
   });
   // for productSubType to be populate and setproductType. 
   frame.jComboBox1.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
		    productType = frame.jComboBox1.getSelectedItem().toString();
		    setProductType(productType);
			frame.jComboBox2.removeAll();
			productSubtypeCombox.removeAllElements();
			getMasterDataOnComboBox(productSubtypeCombox, productType + ".subType");
			frame.jComboBox2.setModel(productSubtypeCombox);
		}
		
	});
   frame.jComboBox2.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
		    productSubType = frame.jComboBox2.getSelectedItem().toString();
		    setProductSubType(productSubType);
		}
		
	});
   // for currency
	processlistchoice(currencyList,"Currency");
   	final JDialogBoxForChoice choice12 = new JDialogBoxForChoice(currencyList);
   	frame.jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				choice12.jList3.setModel(currencyList);
				choice12.setLocationRelativeTo(choice12);
				//choice12.setSize(200,200);
				choice12.setVisible(true);
				
			}
   		
   	});
   // currency textbox
   	choice12.addWindowListener(new WindowAdapter() {            
           public void windowClosing(WindowEvent e) {
              // System.out.println("Window closing");
               try {
               	String ss = "";
                 Object obj [] =  choice12.cmodList2.toArray();
                for(int i =0;i<obj.length;i++)
               	 ss = ss + (String) obj[i] + ",";
                if(ss.trim().length() > 0)
               	 frame.jTextField7.setText(ss.substring(0, ss.length()-1));
               } catch (Throwable t) {
                   t.printStackTrace();
               }                
           }
   	});
     //  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       // for legal entity
   	
   	// for legalentity CounterParty 
   	
	 String s [] = {"id","LegalName"};
	 DefaultTableModel tablemodel = new DefaultTableModel(s,0);
	 getLEDataCombo1(tablemodel,"CounterParty");
     final  JDialogTable showLE = new JDialogTable(tablemodel);
     showLE.setLocationRelativeTo(this);
  
  frame.jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
      	
      
      	
      	showLE.setVisible(true);
      	
      }
  }); 
  showLE.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

		@Override
		public void mouseClicked(MouseEvent e) {
			int id  = ((Integer)	showLE.jTable1.getValueAt(showLE.jTable1.getSelectedRow(),0)).intValue();
		
			 String ss = (String)	showLE.jTable1.getValueAt(showLE.jTable1.getSelectedRow(),1);
			 frame.jTextField9.setText(ss);
			 leId = Integer.valueOf(id).toString();
			showLE.dispose();
		}
		
  
  	
  });
  // for trader
    String trader [] = {"id","LegalName"};
  	DefaultTableModel tradertablemodel = new DefaultTableModel(trader,0);
  	getLEDataCombo1(tradertablemodel,"Trader");
    final  JDialogTable showTrader = new JDialogTable(tradertablemodel);
    showTrader.setLocationRelativeTo(this);
    frame.jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        	
        
        	
        	showTrader.setVisible(true);
        	
        }
    }); 
    
    showTrader.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int id  = ((Integer)	showTrader.jTable1.getValueAt(showTrader.jTable1.getSelectedRow(),0)).intValue();
			
				 String ss = (String)	showTrader.jTable1.getValueAt(showTrader.jTable1.getSelectedRow(),1);
				 frame.jTextField11.setText(ss);
				 traderid = Integer.valueOf(id).toString();
				 showTrader.dispose();
			}
			
    
    	
    });
    	
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
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            //	JFrameReportsApplication  	m_frame =   new JFrameReportsApplication();
            //	m_frame.setVisible(true);
          //     m_frame.pack();
           //    m_frame.setSize(1250, 750);
            //   SwingReportDemo demo = new SwingReportDemo(null);
              // frame.add(demo);
            }
        });
    }
    

    private final void getMasterDataOnComboBox( javax.swing.DefaultComboBoxModel combodata,String name) {
		Vector vector = null;
		try {
			vector = (Vector) referenceData.getStartUPData(name);
			
			if(vector.size() > 0) {
			Iterator it = vector.iterator();
	    	int i =0;
	    	//combodata.insertElementAt(" ", 0);
	    	
	    	while(it.hasNext()) {
	    		
	    		StartUPData data = (StartUPData) it.next();
	    	
    		
    			
    		combodata.insertElementAt(data.getName(), i);
    		i++;
    	}	
	    	
			}
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			
	}catch(Exception e) {
		commonUTIL.displayError("JFrameReportApplication","getMasterDataOnComboBox", e);
	}
    	
    }
    private void getBookOnComboBox( javax.swing.DefaultComboBoxModel combodata,Hashtable bookID) {
		Vector vector;
		try {
			vector = (Vector) referenceData.selectALLBooks();
			
				
			if(vector.size() > 0) {
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		Book book = (Book) it.next();
	    	
    		
    			
    		combodata.insertElementAt(book.getBook_name(), i);
    		bookID.put(i, book);
    		i++;
    	}	
	    	//combodata.setSelectedItem(0);
			}
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			
	}catch(Exception e) {
		commonUTIL.displayError("JFrameReportApplicatoin","getBookOnComboBox", e);
	}
    	
    }
    
    
    
    public void getLEDataCombo1(DefaultTableModel model,String leRole) {
		Vector vector;
		try {			
				vector = (Vector) referenceData.selectLEonWhereClause(" role like '%"+leRole + "%'");
			   Iterator it = vector.iterator();
	    	   int i =0;
	    	while(it.hasNext()) {
	    		
	    		LegalEntity le =	(LegalEntity) it.next();
	    		model.insertRow(i, new Object[]{le.getId(),le.getName()});
	    		i++;
    		
    	}	
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameReportApplicatoin","getLEDataCombo1", e);
    			}
    	
    	
    }
    public void processlistchoice(DefaultListModel list,String name ) {
    	Vector vector;
		try {
			vector = (Vector) referenceData.getStartUPData(name);
			
			if(vector.size() > 0) {
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		StartUPData data = (StartUPData) it.next();
	    	
    		
    			
	    		list.addElement(data.getName());

    		i++;
    	}	
			}
		}catch (RemoteException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			
	}catch(Exception e) {
		commonUTIL.displayError("JFrameReportApplication","getMasterDataOnComboBox", e);
	}
    	
    }
    
    
    private Book getBookId(int selectedIndex) {
		// TODO Auto-generated method stub
		return ((Book)bookData.get(selectedIndex));
	}
    private void setBookId (Book book) {
		// TODO Auto-generated method stub
		bookID = Integer.valueOf(book.getBookno()).toString();
	}
    private void populateReportData(String sql,boolean replaceColumns) {
    	Vector v1;
			try {
				String sq = "";
				if(replaceColumns) {
					if(mainsql.contains("where"))
					    sq = mainsql + " and " +  searchCriteria;
					else 
						sq = mainsql + " where " +  searchCriteria;
				}	else  {
					sq = mainsql;
				}
				v1 = (Vector) remoteTrade.getTradesforReport(sq);
				if(v1 != null && v1.size() > 0) {
				pReport.setHeader((Vector) v1.get(0)); 
				pReport.setdatatype((Vector) v1.get(1));
				pReport.setData((Vector) v1.get(2));
				demo.setReport(pReport);
				}
				
			        
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
    // Variables declaration - do not modify

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductSubType() {
		return productSubType;
	}

	public void setProductSubType(String productSubType) {
		this.productSubType = productSubType;
	}
	private void saveJobs(Vector<UserJob> jobs2) {
		// TODO Auto-generated method stub
		if(jobs2 != null || jobs2.size() > 0) {
			for(int i=0;i<jobs2.size();i++) {
				UserJob job = jobs2.elementAt(i);
				
				try {
					job = (UserJob) remoteTask.saveUserJob(job);
					if(jobs == null) 
						this.jobs = new Vector<UserJob>();
					jobs.add(job);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			setJobs(jobs);
		}
		
	}
	private void setJobs(Vector<UserJob> jobs2) {
		// TODO Auto-generated method stub
		this.jobs = jobs2;
	}
	private Vector<UserJob> getUserTempleates(int userid) {
		Vector<UserJob> jobs = null;
		try {
			jobs =	remoteTask.getUserJob(userid,"TRADE"); 
			if((jobs == null) || jobs.isEmpty()) {
				UserJob job = new UserJob();
				job.setId(0);
				job.setTabid(0);
				job.setType("TRADE");
				job.setUserID(userid);
				job.setTreeNodeName("DefaultTemplate");
				jobs = new Vector<UserJob>();
				jobs.add(job);
				saveJobs(jobs);
				//setJobs(jobs2)
			} else {
			setJobs(jobs);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameNewReport ", " getUserTempleates " , e);
			
		}
		return jobs;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
    // End of variables declaration
}
