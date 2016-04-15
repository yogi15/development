package apps.window.reportwindow;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import util.commonUTIL;

import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
import apps.window.reportwindow.util.ReportColumns;
import apps.window.reportwindow.util.ReportSQLGenerator;
import apps.window.util.windowUtil.JTreeChoice;
import beans.StartUPData;
import beans.UserJob;
import beans.Users;

public class JFrameTransferReportApplication extends javax.swing.JFrame {

    /** Creates new form NewJFrame2 */
	Users user = null;
	ReportColumns columns = new ReportColumns();
	ReportSQLGenerator sqlGen = new ReportSQLGenerator();
  	RemoteReferenceData referenceData;
  	RemoteTrade remoteTrade;
  	RemoteProduct remoteproduct;
	String columnSQL;
	SwingReportDemo  demo = null;
    PivotReport pReport = null;
	 JPanel panelViewReportdata = null;
	 String searchCriteria = "";
	 String mainsql = " SELECT ID,TRADEID,PRODUCTID,AMOUNT,EVENTTYPE,TRANSERTYPE,TRANSFERSTATUS,SETTLECURRENCY,PAYERCODE,PAYERROLE, " ;
			// mainsql =  mainsql +		" RECEIVERCODE,RECEIVERROLE,PAYMENTSTATUS,DELIVERYDATE,VALUEDATE,METHOD,RECEIVERINST,ACTION,STATUS,PAYERINST, ";
			 //mainsql =  mainsql +	  "  LINKID,ATTRIBUTES,TRADEVERSIONID,NETTEDTRANSFERID,NETTEDCONFIGID,VERSION,SETTLEAMOUNT,LEID,PRODUCTTYPE,USERID FROM TRANSFER";

	 FilterValues filterValues = null;
		Vector<StartUPData> searchCriteriaA;
		Vector<StartUPData> searchColumn;
		Vector<UserJob> jobs = null;
		RemoteTask remoteTask;
		ServerConnectionUtil de = null;
		 RemoteBOProcess remoteBo = null;
		 private javax.swing.JPanel jPanel1 = null;
		 ReportSearchPanel reportPanel;
		public JFrameTransferReportApplication(String Name,Users user) {
	    	setUser(user);
	        initComponents();
	    }
		
		
		 private void initComponents() {
	    	 pReport = new PivotReport(null); 
	 			try {
	 				de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	 				referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
	 				remoteproduct  = (RemoteProduct)	de.getRMIService("Product");
	 				remoteTask = (RemoteTask) de.getRMIService("Task");
	 				remoteBo = (RemoteBOProcess) de.getRMIService("BOProcess");
					searchCriteriaA = (Vector) referenceData.getStartUPData("SearchCriteria");
					searchColumn = (Vector)  referenceData.getStartUPData("TradeColumn");
					filterValues = new FilterValues(referenceData,remoteTrade,remoteTask,remoteBo);
					remoteTrade = (RemoteTrade)	de.getRMIService("Trade");
					populateReportData(mainsql,false);
					System.out.println("ppp");
			
			}catch(Exception ed) {
				System.out.println(ed);
				
			}
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
		        demo = new SwingReportDemo(pReport);
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
				 getUserTempleates(user.getId());
			 		UserJob job = jobs.elementAt(0);
			 		  Vector detailsJob = job.getDetailsJobs();
			         reportPanel = new ReportSearchPanel("Trade",searchCriteriaA,searchColumn,filterValues,job,detailsJob,remoteTask,remoteTrade,getUser(),referenceData);
			         reportPanel.addReportPanel(jPanel1);
			         reportPanel.setColumnSQL(mainsql);
			         reportPanel.setDatePanel(datep);
			         reportPanel.setDemo(demo);
			         reportPanel.setpReport(pReport);
			         add(reportPanel);
			         final  JTreeChoice choiceColumns = new JTreeChoice(this,columns.getTreeNodes(true));
					  
					   choiceColumns.setLocationRelativeTo(this);
					  
					   columnconfigure.addActionListener(new ActionListener() {
					       public void actionPerformed(ActionEvent arg0) {
								choiceColumns.setSize(380,400);
								choiceColumns.setVisible(true);
								
							}
							
					 
					 	
					 });
					   
					   
					   choiceColumns.addWindowListener(new WindowAdapter() {            
					       public void windowClosing(WindowEvent e) {
					         
					    	     String tableName = "";
					    	     String joinsTable = "";
					        	 String tablerefer = "";
					            	
					              Object obj [] =  choiceColumns.cmodList2.toArray();
					              if(obj.length >0) {
					              String SQLcolumnsName = sqlGen.getSQLColumns(obj);
					              for(int i =0;i<obj.length;i++) { // this to get columns from hashtable to build table name
					            	  tablerefer = tablerefer + (String) obj[i] + ",";
					             }
					             tableName = sqlGen.getSQLTables(tablerefer);
					            columnSQL = SQLcolumnsName + "  from  " + tableName;
					             String sql =" select " +  columnSQL +  " where " + sqlGen.getjoinSQL(tableName,"Transfer" );
					        //     System.out.println(" from sql " + sql);
					             columnSQL= sql;
					             mainsql = sql;
					             reportPanel.setColumnSQL(sql);
					             if(columnSQL.length() > 0)
					                   populateReportData(mainsql,false);
					       }
					             choiceColumns.cmodList2.removeAllElements();
					        }
						});
	 			
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
		
}
