package apps.window.operationwindow.jobpanl;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;

import util.RemoteServiceUtil;
import util.commonUTIL;

import apps.window.reportwindow.JFrameNewReport;
import apps.window.tradewindow.util.StaticDataCacheUtil;
import beans.StartUPData;
import beans.Task;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;

import com.jidesoft.dialog.JideOptionPane;
import com.jidesoft.docking.DefaultDockableHolder;
import com.jidesoft.docking.DefaultDockingManager;
import com.jidesoft.docking.DockContext;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.grid.JideTable;
import com.jidesoft.icons.IconsFactory;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.plaf.UIDefaultsLookup;
import com.jidesoft.swing.JideBoxLayout;
import com.jidesoft.swing.JideScrollPane;
import com.jidesoft.swing.JideSplitPane;
import com.jidesoft.swing.JideTabbedPane;
import com.jidesoft.swing.TabEditingValidator;

import dsEventProcessor.TaskEventProcessor;
import dsManager.TaskManager;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

public class JFrameNewJobPanel extends DefaultDockableHolder {
//	public LocationCustomizationPanel _locationPanel;
	private static final String PROFILE_NAME = "CosmosJobStation";
	private static final String  signTOremoveNode = "DeleteNode";
	private static final String  signTOremoveAllNode = "DeleteAllNode";
	
	 private  JideTabbedPane _tabbedPane;
	    private static boolean _allowDuplicateTabNames = false;
	private static JFrameNewJobPanel frame;
	private static JideSplitPane _jideSplitPane;
	 JPanel  _panel = null;
	int tabCounter =0;
	JJobPanel reportPanel = null;
	DockableFrame tempalteframe;
	private UserJob job=null;
	String name = "";
	
	Hashtable<String,JInternalJobReportFrame> childs = new Hashtable<String,JInternalJobReportFrame>();
	DefaultListModel<String> listModel = new DefaultListModel<String> ();
	public static void main(String args[]) {
		Users user = new Users();
		user.setId(1);
		JFrameNewJobPanel job = new JFrameNewJobPanel("Testing",user);
		
		job.setVisible(true);
		job.setSize(500,500);
	}
	JInternalJobFrame internalTreeJobFrame = null;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the job
	 */
	private UserJob getJob() {
		return job;
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
	public JFrameNewJobPanel(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * @param job the job to set
	 */
	private void setJob(UserJob job) {
		this.job = job;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public URL getImageURL(String s) {

		return this.getClass().getResource(s);
	}
	public void createInternalFrame(String jobStation) {
	      //  Users user = new Users();
	      //  user.setId(1);
	      //  setUser(user);
			this.reportType = jobStation;
			
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
		//	setMenuBar();
			toFront();
			setVisible(true);
			setSize(600, 800);
			// return frame;
		}
	private Icon getIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		}
		return null;

	}

	private JScrollPane createScrollPane(Component component) {
		JScrollPane pane = new JideScrollPane(component);
		pane.setVerticalScrollBarPolicy(JideScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return pane;
	}
	private void init() {
		// super(name);
		//this.reportType = "Trade";// name.replace("Report", "");
	//	Users user = new Users();
	//	user.setId(1);
	
		 
		try {
			taskManager = new TaskManager("localhost",commonUTIL.getLocalHostName(),"TaskManager","TASK");
		    
			taskManager.start(taskManager);
			taskManager.setNewJobPanel(this);
			remoteBORef = RemoteServiceUtil.getRemoteReferenceDataService();
			remoteTask =  RemoteServiceUtil.getRemoteTaskService();
			remoteTrade =  RemoteServiceUtil.getRemoteTradeService( );
			remoteBo =  RemoteServiceUtil.getRemoteBOProcessService();
			searchCriteria = StaticDataCacheUtil.getDomainValues("SearchCriteria"); 
			filterValues = new FilterValues(remoteBORef, remoteTrade,remoteTask, remoteBo);
			 searchColumn = StaticDataCacheUtil.getDomainValues("TaskColumn");  
			Vector<UserJob> userjobs = remoteTask.getUserJob(getUser().getId(),"Report"+reportType.toUpperCase());
			processTeamplates(listModel, userjobs);
			reportPanel = new JJobPanel();
			reportPanel.setJobs(jobs);
	        reportPanel.setReferenceData(remoteBORef);
	        reportPanel.setRemoteBo(remoteBo);
	        reportPanel.setRemoteTask(remoteTask);
	        reportPanel.setRemoteTrade(remoteTrade);
	        reportPanel.setSearchCriteriaA(searchCriteria);
	       // reportPanel.setSearchColumn(searchColumn);
	        reportPanel.setFilterValues(filterValues);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameNewReport ", " Constructor ", e);
		}
	}
	private UserJob getJobByName(String jobName) {
		// TODO Auto-generated method stub
		UserJob job = null;
		jobs = getJobs();
		for(int j=0;j<jobs.size();j++ ) {
			 job = jobs.get(j);
			if(job.getTreeNodeName().equalsIgnoreCase(jobName)) {
			  break;
			} else {
				job = null;
			}
			
		}
			
			
		return job;
	}
	private Vector<UserJob> getUserjobs(int userid) {
		Vector<UserJob> jobs = null;
		try {
			jobs =	remoteTask.getUserJob(userid,"TASK");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError("MainTaskPanel ", " getUserjobs" , e);
			
		}
		return jobs;
	}
	private boolean setFocusonJTabbedPane(String name,JTabbedPane jTabbedRightSidePane) {
		boolean flag = false;
		if(jTabbedRightSidePane == null) 
			return flag;
		for(int i=0;i<jTabbedRightSidePane.getTabCount();i++) {
			String tabname = jTabbedRightSidePane.getTitleAt(i);
			if(tabname.equalsIgnoreCase(name)) {
				jTabbedRightSidePane.setSelectedIndex(i);
				flag = true;
			}
			
		}
		return flag;
	}

	 private  JideSplitPane createSplitPane( UserJob job,String name) {
		 JTable table = new JideTable(new DefaultTableModel(10, 3));

	        _jideSplitPane = new JideSplitPane(JideSplitPane.HORIZONTAL_SPLIT);
	        _jideSplitPane.setProportionalLayout(true);
	        _jideSplitPane.setOneTouchExpandable(true);
	        Vector<Task> tasks = new Vector<Task>();
	        DataPanel jobdata = new DataPanel(job.getTreeNodeName(),tasks,filterValues);
	        jobdata.setUser(getUser());
	        jobdata.setName(job.getTreeNodeName());
	        JInternalJobReportFrame reportFrame = new JInternalJobReportFrame(filterValues, searchCriteria, searchColumn,reportType);
	        
	        reportFrame.setUserJob(job);
	        
	        reportFrame.setJobdataPanel(jobdata);
	        childs.put(job.getTreeNodeName(),reportFrame);
	       
	        _jideSplitPane.add(reportFrame , JideBoxLayout.FLEXIBLE);
	        _jideSplitPane.add(jobdata.getJPanel6(), JideBoxLayout.VARY);
	        return _jideSplitPane;
	    }
	protected DockableFrame createSampleTaskListFrame() {

		DockableFrame frameT = new DockableFrame("Job Station ",JideIconsFactory.getImageIcon(JideIconsFactory.DockableFrame.FRAME1));

		//_sortableTable = new SortableTable(null);
		//JideScrollPane scrollPane = new JideScrollPane(_sortableTable);
		 _panel = new JPanel(new BorderLayout());
	        _panel.setOpaque(true);
	        _panel.setBackground(UIDefaultsLookup.getColor("control"));
	        _panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
	      //  _tabbedPane = createTabbedPane();

	      //  _panel.add(_tabbedPane, BorderLayout.CENTER);
		frameT.getContentPane().add(_panel, BorderLayout.CENTER);
		frameT.setInitSide(DockContext.DOCK_SIDE_CENTER);
		frameT.setInitMode(DockContext.STATE_FRAMEDOCKED);
		frameT.setTitle(frameT.getName());
		frameT.setTabTitle(frameT.getName());
		frameT.setPreferredSize(new Dimension(200, 200));
		frameT.setMinimumSize(new Dimension(100, 100));
		
		return frameT;
	}

	protected DockableFrame createSampleServerFrame(FilterValues filterValues,
			Vector<StartUPData> searchCriteria, Vector<StartUPData> searchColumn,String reportType) {
		String iconPath = "/resources/icon/report_filter.png";
		Icon icon = getIcon(iconPath);
		
		tempalteframe = createDockableFrame("Template ", icon);
    
		tempalteframe.getContext().setInitMode(DockContext.STATE_AUTOHIDE);
		tempalteframe.getContext().setInitSide(DockContext.DOCK_SIDE_WEST);
		tempalteframe.getContext().setInitIndex(0);
		internalTreeJobFrame =  new JInternalJobFrame(filterValues, searchCriteria, searchColumn,reportType,user);
		internalTreeJobFrame.setTemplateFrame(tempalteframe);
		internalTreeJobFrame.setReportPanel(reportPanel);
		
		//internalFrame.setUser(getUser());
		tempalteframe.add(createScrollPane(internalTreeJobFrame));
		
		tempalteframe.setToolTipText("Job Station ");
		internalTreeJobFrame.searchTreeJobPanel.jTree1.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e){
				 removeTabonDelete( internalTreeJobFrame.getSignalTOremoveNode());
				  if(internalTreeJobFrame.searchTreeJobPanel.jTree1.isSelectionEmpty())
		        	   return;
				  TreePath selPath =internalTreeJobFrame.searchTreeJobPanel.jTree1.getSelectionPath();
					
				 
				  if( internalTreeJobFrame.searchTreeJobPanel.jTree1.getSelectionPath().getParentPath() == null)
					  return;
				    if(selPath == null)
				    	return;
				    if(setFocusonJTabbedPane(selPath.toString(),_tabbedPane)) 
				    	return;
				    UserJob job = getJobByName(selPath.getLastPathComponent().toString());
				    if(job != null ) {
					    Vector detailsJob = job.getDetailsJobs();
						if((detailsJob != null) && (! detailsJob.isEmpty())) {
								if(_tabbedPane == null)  {
										_tabbedPane = createTabbedPane();							
										_tabbedPane.addTab(selPath.toString(), createSplitPane(job,selPath.toString()));
										_panel.add(_tabbedPane, BorderLayout.CENTER);
										_tabbedPane.setFocusable(false);
										tabCounter = tabCounter+1;
										_tabbedPane.setSelectedIndex(_tabbedPane.getTabCount()-1);
								} else {
									tabCounter =tabCounter+1;
									_tabbedPane.addTab(selPath.toString(), createSplitPane(job,selPath.toString()));						  
									   _tabbedPane.setFocusable(false);
									   _tabbedPane.setSelectedIndex(_tabbedPane.getTabCount()-1);
								}

						      // 
						} else {
							//tabCounter =tabCounter+1;
							if(_tabbedPane == null)  {
								_tabbedPane= createTabbedPane();
								_panel.add(_tabbedPane, BorderLayout.CENTER);
							}						 
							   _tabbedPane.addTab(selPath.toString(), createSplitPane(job,selPath.toString()));						  
							   _tabbedPane.setFocusable(false);
							//  jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
							   tabCounter = tabCounter+1;
							   _tabbedPane.setSelectedIndex(_tabbedPane.getTabCount()-1);
						}					
					}
				    if(tabCounter == 0) { 
						  if(job == null) {
								  job = new UserJob();
							      job.setTreeNodeName(selPath.getLastPathComponent().toString());
							      job.setTreeid(internalTreeJobFrame.searchTreeJobPanel.jTree1.getSelectionCount());
							      job.setTabid(tabCounter);
						  }
						  	_tabbedPane= createTabbedPane();
							_panel.add(_tabbedPane, BorderLayout.CENTER);
							tabCounter =tabCounter+1;
							   job.setTabid(tabCounter);
							   _tabbedPane.addTab(selPath.toString(), createSplitPane(job,selPath.toString()));
				    }
				    if(job == null)  {
						  job = new UserJob();
					      job.setTreeNodeName(selPath.toString());
					      job.setTreeid(internalTreeJobFrame.searchTreeJobPanel.jTree1.getSelectionCount());
					      job.setTabid(tabCounter);
						  
					      _tabbedPane.addTab(selPath.toString(), createSplitPane(job,selPath.toString()));
						//  jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
						 
					      _tabbedPane.setFocusable(false);
					      tabCounter =tabCounter+1;
					      _tabbedPane.setSelectedIndex(_tabbedPane.getTabCount()-1);
					  }
		           
		            
		         // _tabbedPane.setMnemonicAt(i, mnemonics[i]);
				}
			});
			return tempalteframe;
	}

	
	
	protected void removeTabonDelete(  String signremoveNode) {
		// TODO Auto-generated method stub
		if(commonUTIL.isEmpty(signremoveNode))
			return;
		int tabTobeRemoved  = 0;
		String tabName = signremoveNode.substring(0, signremoveNode.indexOf("_"));
		String deleteSignal = signremoveNode.substring( signremoveNode.indexOf("_")+1,signremoveNode.length());
		if(signTOremoveNode.equalsIgnoreCase(deleteSignal))  {
		if(_tabbedPane != null) {
			for(int i=0;i < _tabbedPane.getTabCount();i++) {
				String tabTitle = _tabbedPane.getTitleAt(i);
				if(tabTitle.equalsIgnoreCase(tabName)) {
					tabTobeRemoved = i;
					break;
				}
			}
		}
		
		_tabbedPane.removeTabAt(tabTobeRemoved);
		tabCounter =tabCounter-1;
		internalTreeJobFrame.setSignalTOremoveNode("");
		if(tabCounter == 0) {
			
			_panel.remove(_tabbedPane);
			_tabbedPane = null;
		}
		}
	}
	protected DockableFrame createDockableFrame(String key, Icon icon) {
		DockableFrame frame = new DockableFrame(key, icon);
		frame.setPreferredSize(new Dimension(200, 200));
		frame.add(new JideScrollPane(new JTextArea()));

		return frame;
	}
	public JFrameNewJobPanel(String name,Users user) {
		//Users user1 = new Users();
	//	user.setId(1);
		setUser(user);
	//	init();
		createInternalFrame(name);
		setTitle(name + " : " + user.getUser_name()  + " : " +  " Cosmos Job Station ");
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
	
	
	TaskManager taskManager = null;
	

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
		
		//reportPanel = getReportPanel(reportType);
	}

	private void processTeamplates(DefaultListModel<String> listModel,Vector<UserJob> userJobs) {
		if(!commonUTIL.isEmpty(userJobs)) {
			for(int i=0;i<userJobs.size();i++) {
				listModel.add(i, userJobs.get(i).getTreeNodeName());
			//	teamplates.put(i, userJobs.get(i));
			}
		}
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
	public void setUser(Users user) {
		this.user = user;
	}
	/**
	 * @return the jobs
	 */
	private Vector<UserJob> getJobs() {
		if(jobs == null) {
			jobs = internalTreeJobFrame.searchTreeJobPanel.getJobs();
			setJobs(jobs);
		}
		return jobs;
	}

	/**
	 * @param jobs the jobs to set
	 */
	private void setJobs(Vector<UserJob> jobs) {
		this.jobs = jobs;
	}
	 private static JideTabbedPane createTabbedPane() {
	        final JideTabbedPane tabbedPane = new JideTabbedPane(JideTabbedPane.TOP);
	        tabbedPane.setOpaque(true);

	        final String[] titles = new String[]{
	                "Mail",
	                "Calendar",
	                "Contacts",
	                "Tasks",
	                "Notes",
	                "Folder List",
	                "Shortcuts",
	                "Journal"
	        };

	        final int[] mnemonics = new int[]{
	                KeyEvent.VK_M,
	                KeyEvent.VK_C,
	                KeyEvent.VK_O,
	                KeyEvent.VK_T,
	                KeyEvent.VK_N,
	                KeyEvent.VK_F,
	                KeyEvent.VK_S,
	                KeyEvent.VK_J
	        };

	       

	        for (int i = 0; i < titles.length; i++) {
	        //    JScrollPane scrollPane = new JScrollPane(new JTextArea());
	        //    scrollPane.setPreferredSize(new Dimension(530, 530));
	        ////    tabbedPane.addTab(titles[i], null, scrollPane);
	          //  tabbedPane.setMnemonicAt(i, mnemonics[i]);
	        }

	      //  tabbedPane.setEnabledAt(2, false);

	        tabbedPane.setTabEditingValidator(new TabEditingValidator() {
	            public boolean alertIfInvalid(int tabIndex, String tabText) {
	                if (tabText.trim().length() == 0) {
	                    JideOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(tabbedPane), "'" + tabText + "' is an invalid name for a tab title.", "Invalid Tab Title", JideOptionPane.ERROR_MESSAGE, null);
	                    return false;
	                }

	                if (_allowDuplicateTabNames)
	                    return true;

	                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
	                    if (tabText.trim().equalsIgnoreCase(tabbedPane.getDisplayTitleAt(i)) && i != tabbedPane.getSelectedIndex()) {
	                        JideOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(tabbedPane), "There is already a tab with the title of '" + tabText + "'.", "Invalid Tab Title", JideOptionPane.ERROR_MESSAGE, null);
	                        return false;
	                    }
	                }
	                return true;
	            }

	            public boolean isValid(int tabIndex, String tabText) {
	                if (tabText.trim().length() == 0)
	                    return false;

	                if (_allowDuplicateTabNames)
	                    return true;
	                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
	                    if (tabText.trim().equalsIgnoreCase(tabbedPane.getDisplayTitleAt(i)) && i != tabbedPane.getSelectedIndex()) {
	                        return false;
	                    }
	                }
	                return true;
	            }

	            public boolean shouldStartEdit(int tabIndex, MouseEvent event) {
	                return true;
	            }
	        });
	        return tabbedPane;
	    }
	 public void clearALL() {
		 if(_tabbedPane != null) {
			 _tabbedPane.removeAll();
			 _tabbedPane = null;
			 internalTreeJobFrame.searchTreeJobPanel.jTree1.removeAll();
			 internalTreeJobFrame.searchTreeJobPanel.jTree1 = null;
			 internalTreeJobFrame.searchTreeJobPanel = null;
			 	
			 
		 }
	 }
	public void processTasks(TaskEventProcessor taskEvent) {
		// TODO Auto-generated method stub
		Enumeration<JInternalJobReportFrame> panels = childs.elements();
		while(panels.hasMoreElements()) {
			JInternalJobReportFrame panel = panels.nextElement();
			
			panel.getJobdataPanel().addtaskData(taskEvent);
		
			
		}
		
	}

}
