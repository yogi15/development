package apps.window.reportwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import util.ClassInstantiateUtil;
import util.commonUTIL;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.reportwindow.util.ReportColumns;
import apps.window.reportwindow.util.ReportSQLGenerator;
import apps.window.util.windowUtil.JTreeChoice;
import beans.StartUPData;
import beans.UserJob;
import beans.Users;

import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class JFrameNewReport123 extends JFrame  {
	
	Users user = null;
	 public static  ServerConnectionUtil de = null;
		RemoteReferenceData remoteBORef;
		RemoteTask remoteTask;
		RemoteTrade remoteTrade;
		RemoteBOProcess remoteBo;
		FilterValues filterValues = null;
		Vector<StartUPData> searchCriteria;
		Vector<StartUPData> searchColumn;
		Vector<UserJob> jobs = null;
		String reportType = "";
		String columnSQL;
		ReportPanel reportPanel;
		ReportColumns columns = new ReportColumns();
		ReportSQLGenerator sqlGen = new ReportSQLGenerator();
		public JFrameNewReport123(String name,Users user)  {
		super(name);
		this.reportType = name.replace("Report", "");
		setUser(user);
					de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
					
					try {
						remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
						remoteTask = (RemoteTask) de.getRMIService("Task");
						remoteTrade = (RemoteTrade)  de.getRMIService("Trade");
						remoteBo = (RemoteBOProcess) de.getRMIService("BOProcess");
						searchCriteria = (Vector) remoteBORef.getStartUPData("SearchCriteria");
						 searchColumn = (Vector)  remoteBORef.getStartUPData(reportType + "Column");// understand which column to display on selected report
						 filterValues = new FilterValues(remoteBORef,remoteTrade,remoteTask,remoteBo);
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("JFrameNewReport ", " Constructor " , e);
					}
					 initComponents();
		}
	  public JFrameNewReport123() {
	        initComponents();
	    }

	private void initComponents() {
		getUserTempleates(user.getId());
		UserJob job = jobs.elementAt(0);
		  Vector detailsJob = job.getDetailsJobs();
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
	        reportPanel = getReportPanel(reportType);
	        reportPanel.setJobs(jobs);
	        reportPanel.setReferenceData(remoteBORef);
	        reportPanel.setRemoteBo(remoteBo);
	        reportPanel.setRemoteTask(remoteTask);
	        reportPanel.setRemoteTrade(remoteTrade);
	        reportPanel.setSearchCriteriaA(searchCriteria);
	        reportPanel.setSearchColumn(searchColumn);
	        reportPanel.setFilterValues(filterValues);
	        reportPanel.setUser(user);
	        reportPanel.setUserJob(job);
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
			             if(!tableName.contains(reportType)) {
			            	 tableName = reportType + " "+reportType.toLowerCase() + ", "+tableName;
			             }
			            columnSQL = SQLcolumnsName + "  from  " + tableName;
			             String sql =" select " +  columnSQL + sqlGen.getjoinSQL(tableName ,reportType);
			        //     System.out.println(" from sql " + sql);
			             columnSQL= sql;
			          //   mainsql = sql;
			             reportPanel.setColumnSQL(sql);
			           //  if(columnSQL.length() > 0)
			             //     reportPanel.populateReportData(columnSQL,false);
			       }
			             choiceColumns.cmodList2.removeAllElements();
			        }
				});
			 
			 add(  reportPanel.loadreport());
//	add(new ReportSearchPanel("Trade",searchCriteria,searchColumn,filterValues,job,detailsJob,remoteTask,remoteTrade,getUser()));
		
	}
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	private Vector<UserJob> getUserTempleates(int userid) {
		Vector<UserJob> jobs = null;
		try {
			jobs =	remoteTask.getUserJob(userid,reportType.toUpperCase()); 
			if((jobs == null) || jobs.isEmpty()) {
				UserJob job = new UserJob();
				job.setId(0);
				job.setTabid(0);
				job.setUserID(user.getId());
				job.setType(reportType.toUpperCase());
				job.setTreeNodeName("DefaultTemplate");
				jobs = new Vector<UserJob>();
				jobs.add(job);
				saveJobs(jobs);
				
			}else {
				setJobs(jobs);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("JFrameNewReport ", " getUserTempleates " , e);
				
			}
			return jobs;
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
	protected ReportPanel getReportPanel(String name) {
        String reportP = "apps.window.reportwindow."  + name + "Panel";
        ReportPanel panel = null;
        
        try {
        Class class1 =    ClassInstantiateUtil.getClass(reportP,true);
        panel =  (ReportPanel) class1.newInstance();
           //  productWindow = (BondPanel) 
        } catch (Exception e) {
            System.out.println( e);
        }

        return panel;
    }
}
