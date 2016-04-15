package apps.window.reportwindow;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.reportwindow.ReportPanel;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
import apps.window.reportwindow.util.ReportColumns;
import apps.window.reportwindow.util.ReportSQLGenerator;
import apps.window.util.windowUtil.JDialogBoxJListSingleSelection;
import apps.window.util.windowUtil.JTreeChoice;
import beans.StartUPData;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;
import com.jidesoft.docking.DefaultDockableHolder;
import com.jidesoft.docking.DefaultDockingManager;
import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.swing.JideScrollPane;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.tree.DefaultMutableTreeNode;
import util.ClassInstantiateUtil;
import util.commonUTIL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

public class JFrameNewReport extends DefaultDockableHolder {
//	public LocationCustomizationPanel _locationPanel;
	private static final String PROFILE_NAME = "CosmosReport";
	private static JFrameNewReport frame;
	DockableFrame tempalteframe;
	private UserJob job=null;
	DefaultListModel<String> listModel = new DefaultListModel<String> ();
	Hashtable<Integer,UserJob> teamplates = new Hashtable<Integer,UserJob>();
	int templateIdSelected = 0;
	JInternalReportFrame internalFrame = null;
	DefaultMutableTreeNode columnsTreeNode = null; // used to show columns
	String name = "";
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	 String templateName  = "";
	public JFrameNewReport(String name,Users user) {
		//Users user1 = new Users();
	//	user.setId(1);
		setUser(user);
		createInternalFrame(name);
		setTitle(name + " : " + user.getUser_name()  + " : " +  " Cosmos Reporting Framework ");
	}
	
	
	/**
	 * @return the job
	 */
	private UserJob getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	private void setJob(UserJob job) {
		this.job = job;
	}

	/**
	 * @return the jobdetails
	 */
	private Vector<UserJobsDetails> getJobdetails() {
		return jobdetails;
	}

	/**
	 * @param jobdetails the jobdetails to set
	 */
	private void setJobdetails(Vector<UserJobsDetails> jobdetails) {
		this.jobdetails = jobdetails;
	}

	private Vector<UserJobsDetails> jobdetails;
	public JFrameNewReport(String title) throws HeadlessException {
		super(title);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
				JFrameNewReport frame = new JFrameNewReport(
						"Trade : Cosmos Reporting  Framework");
				
				frame.createInternalFrame("Trade");
			}
		});
	}

	public URL getImageURL(String s) {

		return this.getClass().getResource(s);
	}

	public void createInternalFrame(String reportType) {
      //  Users user = new Users();
      //  user.setId(1);
      //  setUser(user);
		this.reportType = reportType.replace("Report", "");
		
		URL url = getImageURL("/resources/icon/sql.jpg");
		setReportType( reportType.replace("Report", ""));
		setName(name);
		init();
		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getDockingManager().setUndoLimit(10);
		getDockingManager().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				// refreshUndoRedoMenuItems();
			}
		});
		
		getDockingManager().beginLoadLayoutData();

		// _frame.getDockingManager().setFloatable(false);

		getDockingManager().setInitSplitPriority(DefaultDockingManager.SPLIT_SOUTH_NORTH_EAST_WEST);
		getDockingManager().setProfileKey(PROFILE_NAME);
		setIconImage(Toolkit.getDefaultToolkit().getImage(url));
		getDockingManager().addFrame(createSampleServerFrame(filterValues, searchCriteria,searchColumn,getReportType()));
		getDockingManager().addFrame(createSampleTaskListFrame());
		getDockingManager().loadLayoutData();
		setMenuBar();
		toFront();
		setVisible(true);
		setSize(600, 800);
		// return frame;
	}

	protected DockableFrame createDockableFrame(String key, Icon icon) {
		DockableFrame frame = new DockableFrame(key, icon);
		frame.setPreferredSize(new Dimension(200, 200));
		frame.add(new JideScrollPane(new JTextArea()));

		return frame;
	}

	protected DockableFrame createSampleServerFrame(FilterValues filterValues,
			Vector<StartUPData> searchCriteria, Vector<StartUPData> searchColumn,String reportType) {
		String iconPath = "/resources/icon/report_filter.png";
		Icon icon = getIcon(iconPath);

		tempalteframe = createDockableFrame("Template ", icon);
    
		tempalteframe.getContext().setInitMode(DockContext.STATE_AUTOHIDE);
		tempalteframe.getContext().setInitSide(DockContext.DOCK_SIDE_WEST);
		tempalteframe.getContext().setInitIndex(0);
		 internalFrame =  new JInternalReportFrame(filterValues, searchCriteria, searchColumn,reportType);
		internalFrame.setTemplateFrame(tempalteframe);
		internalFrame.setReportPanel(reportPanel);
		internalFrame.setUser(getUser());
		tempalteframe.add(createScrollPane(internalFrame));
		tempalteframe.setToolTipText("Template ");
		return tempalteframe;
	}

	private Icon getIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		}
		return null;

	}

	static int i = 0;
	private SortableTable _sortableTable;

	protected DockableFrame createSampleTaskListFrame() {

		DockableFrame frameT = new DockableFrame("Report ",JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME1));

		//_sortableTable = new SortableTable(null);
		//JideScrollPane scrollPane = new JideScrollPane(_sortableTable);
		
		frameT.getContentPane().add(reportPanel.loadreport(), BorderLayout.CENTER);
		frameT.setInitSide(DockContext.DOCK_SIDE_CENTER);
		frameT.setInitMode(DockContext.STATE_FRAMEDOCKED);
		frameT.setTitle(frameT.getName());
		frameT.setTabTitle(frameT.getName());
		frameT.setPreferredSize(new Dimension(200, 200));
		frameT.setMinimumSize(new Dimension(100, 100));
		
		return frameT;
	}

	private JScrollPane createScrollPane(Component component) {
		JScrollPane pane = new JideScrollPane(component);
		pane.setVerticalScrollBarPolicy(JideScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return pane;
	}

	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Report");
		JMenu setupd = new JMenu("Report Column Setup");
		JMenu formatMenu = new JMenu("Format");
		JMenuItem columnconfigure = new JMenuItem("Columns ");
		JMenuItem subcolms = new JMenuItem("SubColumns ");
		final JMenuItem loadTemplate = new JMenuItem("Load Template ");
		final JMenuItem newTemplate = new JMenuItem("New Template ");
		final JMenuItem saveTemplate = new JMenuItem("Save Template ");

		menuBar.add(fileMenu);
		setupd.add(columnconfigure);
		setupd.add(subcolms);

		fileMenu.add(newTemplate);
		fileMenu.add(loadTemplate);
		fileMenu.add(saveTemplate);
		menuBar.add(setupd);
		menuBar.add(formatMenu);
		// Create and add simple menu item to one of the drop down menu
/*		JMenuItem newAction = new JMenuItem("New");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem saveAsNewAction = new JMenuItem("Save as New");

		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem opemAction = new JMenuItem("Open ");
		JMenuItem Excel = new JMenuItem("Excel");
		JMenuItem cvs = new JMenuItem("CVS ");
		JMenuItem html = new JMenuItem("HTML ");*/
		
		
		
		
		final JTreeChoice choiceColumns = new JTreeChoice(this,columnsTreeNode);
	
		 
		  loadTemplate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final  JDialogBoxJListSingleSelection  templatesLists = new JDialogBoxJListSingleSelection(listModel);
				 templatesLists.setLocationRelativeTo(loadTemplate);
				templatesLists.setSize(230, 220);
				templatesLists.setVisible(true);
				templatesLists.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						String templateName = templatesLists.jTextField0.getText();
						tempalteframe.setTitle(templateName);
						templateIdSelected = templatesLists.getTemplateId();
						job = (UserJob) teamplates.get(templatesLists.getTemplateId());
						if(job == null) {
							templatesLists.dispose();
							return;
						}
						internalFrame.setUserJob(job);
						if(!commonUTIL.isEmpty(job.getSql())) {
							reportPanel.setColumnSQL(job.getSql());
						}
						
						templatesLists.dispose();
						
					}
				});
				templatesLists.jList0.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						
						
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
						
						
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						
						
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						String templateName = templatesLists.jTextField0.getText();
						tempalteframe.setTitle(templateName);
						templateIdSelected = templatesLists.getTemplateId();
						job = (UserJob) teamplates.get(templatesLists.getTemplateId());
						if(job == null) {
							templatesLists.dispose();
							return;
						}
						internalFrame.setUserJob(job);
						if(!commonUTIL.isEmpty(job.getSql())) {
							reportPanel.setColumnSQL(job.getSql());
						}
						
						templatesLists.dispose();
						
					}
				});
				
			}
		});
		 
		  newTemplate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					final  JDialogBoxJListSingleSelection  templatesLists = new JDialogBoxJListSingleSelection(listModel);
					 templatesLists.setLocationRelativeTo(loadTemplate);
					 templatesLists.setSize(230, 220);
					templatesLists.setVisible(true);
					templatesLists.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							String templateName = templatesLists.jTextField0.getText();
							tempalteframe.setTitle(templateName);
							listModel.insertElementAt(templateName, listModel.size());
							
							UserJob job = new UserJob();
							job.setId(0);
							job.setTabid(0);
							job.setUserID(user.getId());
							job.setType("Report"+getReportType().toUpperCase());
							job.setTreeNodeName(templateName);
							
							try {
								 job = (UserJob) remoteTask.saveUserJob(job);
								teamplates.put(listModel.size()-1, job);
							} catch (RemoteException e1) {
								
								e1.printStackTrace();
							}
							internalFrame.clearSearchCriteria();
							internalFrame.setUserJob(job);
							
							templatesLists.dispose();
						}
					});
					
				}
			});
		  saveTemplate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					internalFrame.saveJobDetails();
					
				}
			});
		  
		 
		
		choiceColumns.setLocationRelativeTo(this);
		columnconfigure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choiceColumns.setSize(519, 400);
				choiceColumns.setVisible(true);
				choiceColumns.cmodList2.clear();
			//	choiceColumns._root.removeAllChildren();
				choiceColumns.fillListWithSQLColumns(reportPanel.getColumnSQL());

			}
		});
		
		choiceColumns.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				String tableName = "";
				String tablerefer = "";

				Object obj[] = choiceColumns.cmodList2.toArray();
				if(obj.length == 0) 
					return;
				if (obj.length > 0) {
					String SQLcolumnsName = sqlGen.getSQLColumns(obj);
					for (int i = 0; i < obj.length; i++) { 
					// this to get columns from hashtable to build table name
						
						tablerefer = tablerefer + (String) obj[i] + ",";
					}
					tableName = sqlGen.getSQLTables(tablerefer);
					if (!tableName.contains(filterValues.getTableName(reportType))) {
						tableName = filterValues.getTableName(reportType) + " " + filterValues.getTableName(reportType).toLowerCase()
								+ ", " + tableName;
					}
					columnSQL = SQLcolumnsName + "  from  " + tableName;
					String sql = " select " + columnSQL 	+ sqlGen.getjoinSQL(tableName, reportType);
					// System.out.println(" from sql " + sql);
					columnSQL = sql;
					reportPanel.setColumnSQL(sql);
					
					UserJob jb = (UserJob) teamplates.get(templateIdSelected);
					if(jb != null) {
						jb.setSql(sql);
						teamplates.put(templateIdSelected, jb);
					}
					// if(columnSQL.length() > 0)
					// reportPanel.populateReportData(columnSQL,false);
				}
				choiceColumns.cmodList2.removeAllElements();
			}
		});
	}
	 public void addSelectedColumns(JTreeChoice choiceColumns) {
			if(!commonUTIL.isEmpty(templateName))
				choiceColumns.fillListWithSQLColumns(reportPanel.getColumnSQL());
		}
	protected ReportPanel getReportPanel(String name) {
		String reportP = "apps.window.reportwindow." + name + "Panel";
		ReportPanel panel = null;

		try {
			Class class1 = ClassInstantiateUtil.getClass(reportP, true);
			panel = (ReportPanel) class1.newInstance();
			// productWindow = (BondPanel)
		} catch (Exception e) {
			System.out.println(e);
		}

		return panel;
	}

	private void init() {
		// super(name);
		//this.reportType = "Trade";// name.replace("Report", "");
	//	Users user = new Users();
	//	user.setId(1);
		 setUser(user);
		de = ServerConnectionUtil.connect("localhost", 1099,
				commonUTIL.getServerIP());

		try {
			remoteBORef = (RemoteReferenceData) de
					.getRMIService("ReferenceData");
			remoteTask = (RemoteTask) de.getRMIService("Task");
			remoteTrade = (RemoteTrade) de.getRMIService("Trade");
			remoteBo = (RemoteBOProcess) de.getRMIService("BOProcess");
			searchCriteria = (Vector) remoteBORef.getStartUPData("SearchCriteria");
			if(reportType.equalsIgnoreCase("PNL")) {
				 searchColumn = (Vector) remoteBORef.getStartUPData("Trade" + "Column");// understand which column to display on 	// selected report
			} else {
			    searchColumn = (Vector) remoteBORef.getStartUPData(reportType + "Column");// understand which column to display on 	// selected report
		}
			
			if(reportType.equalsIgnoreCase("CashPosition") || reportType.equalsIgnoreCase("PNL") || reportType.equalsIgnoreCase("CashLedgerPosition")){
			  if(columnsTreeNode!= null) 
				  columnsTreeNode.removeAllChildren();
				columnsTreeNode = columns.getTreeNodes(true);
		} else  {
			 if(columnsTreeNode!= null) 
				  columnsTreeNode.removeAllChildren();
				 columnsTreeNode = columns.getTreeNodes(false);
		}
			filterValues = new FilterValues(remoteBORef, remoteTrade,remoteTask, remoteBo);
			Vector<UserJob> userjobs = remoteTask.getUserJob(getUser().getId(),"Report"+reportType.toUpperCase());
			processTeamplates(listModel, userjobs);
		 //   reportPanel = new TradePanel();
			//reportPanel.setColumnSQL(reportPanel)
			reportPanel.setJobs(jobs);
	        reportPanel.setReferenceData(remoteBORef);
	        reportPanel.setRemoteBo(remoteBo);
	        reportPanel.setRemoteTask(remoteTask);
	        reportPanel.setRemoteTrade(remoteTrade);
	        reportPanel.setSearchCriteriaA(searchCriteria);
	        reportPanel.setSearchColumn(searchColumn);
	        reportPanel.setFilterValues(filterValues);
	        PivotReport pReport =  new PivotReport(null); 
	    	SwingReportDemo demo =  new SwingReportDemo(pReport);
	    	 reportPanel.setDemo(demo);
	    	reportPanel.setpReport(pReport);
			
			// demo =
	        
	        reportPanel.setUser(user);
	        //reportPanel.setUserJob(job);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameNewReport ", " Constructor ", e);
		}
	}

	Users user = null;
	

	public static ServerConnectionUtil de = null;
	RemoteReferenceData remoteBORef;
	RemoteTask remoteTask;
	RemoteTrade remoteTrade;
	RemoteBOProcess remoteBo;
	FilterValues filterValues = null;
	Vector<StartUPData> searchCriteria;
	Vector<StartUPData> searchColumn;
	Vector<UserJob> jobs = null;
	

	String reportType = "";
	/**
	 * @return the reportType
	 */
	private String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set used to populate the template of TradeReport
	 */
	private void setReportType(String reportType) {
		this.reportType = reportType.replace("Report", "");
		//this.reportType = reportType;
		
		reportPanel = getReportPanel(reportType);
	}

	/**
	 * @return the columnSQL
	 */
	private String getColumnSQL() {
		return columnSQL;
	}

	/**
	 * @param columnSQL the columnSQL to set
	 */
	private void setColumnSQL(String columnSQL) {
		this.columnSQL = columnSQL;
	}
	/**
	 * @return the user
	 */
	private Users getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	private void setUser(Users user) {
		this.user = user;
	}
	/**
	 * @return the jobs
	 */
	private Vector<UserJob> getJobs() {
		return jobs;
	}

	/**
	 * @param jobs the jobs to set
	 */
	private void setJobs(Vector<UserJob> jobs) {
		this.jobs = jobs;
	}
	private void processTeamplates(DefaultListModel<String> listModel,Vector<UserJob> userJobs) {
		if(!commonUTIL.isEmpty(userJobs)) {
			for(int i=0;i<userJobs.size();i++) {
				listModel.add(i, userJobs.get(i).getTreeNodeName());
				teamplates.put(i, userJobs.get(i));
			}
		}
	}
	String columnSQL;
	ReportPanel reportPanel;
	ReportColumns columns = new ReportColumns();
	ReportSQLGenerator sqlGen = new ReportSQLGenerator();

}
