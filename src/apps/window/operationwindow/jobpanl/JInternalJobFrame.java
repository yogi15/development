package apps.window.operationwindow.jobpanl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import util.cacheUtil.AccessDataCacheUtil;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.positionwindow.ManualPositionPanel;
import apps.window.referencewindow.DateCellEditor12;
import apps.window.reportwindow.ReportPanel;
import apps.window.util.windowUtil.ButtonsIconsFactory;
import apps.window.util.windowUtil.JDialogBoxForChoice;
import apps.window.util.windowUtil.JDialogTable;
import beans.FilterBean;
import beans.StartUPData;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;

import com.jidesoft.combobox.MultiSelectListExComboBox;
import com.jidesoft.docking.DockableFrame;
import com.jidesoft.grid.DateCellEditor;
import com.jidesoft.popup.JidePopup;

public class JInternalJobFrame extends JInternalFrame {

	public JobTreeViewPanel searchTreeJobPanel = null;
	String taskStationTabWindow = "TaskStationTabWindow";
	FilterValues filterValues = null;
	ReportPanel reportPanel = null;
	Vector data = new Vector();
	private UserJob job=null;
	Vector<UserJob> jobs = null;
	public   String signalTOremoveNode = "";
	/**
	 * @return the signTOremoveNode
	 */
	public String getSignalTOremoveNode() {
		return signalTOremoveNode;
	}

	/**
	 * @param signTOremoveNode the signTOremoveNode to set
	 */
	public void setSignalTOremoveNode(String signTOremoveNode) {
		this.signalTOremoveNode = signTOremoveNode;
	}

	private Vector<UserJobsDetails> jobdetails;
	/**
	 * @return the reportPanel
	 */
	public ReportPanel getReportPanel() {
		return reportPanel;
	}

	/**
	 * @param reportPanel the reportPanel to set
	 */
	public void setReportPanel(ReportPanel reportPanel) {
		this.reportPanel =  reportPanel;
	}

	String reportType = "";
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	int selectRow = 0;
	int selectCol = 0;

	Vector<StartUPData> searchCriteria;
	DefaultListModel columnNamesListModel;
	Vector<StartUPData> searchColumn;

	JButton jButton0 = null;
	JButton jButton1 = null;
	JButton jButton2 = null;
	JButton jButton3 = null;
	JButton jButton4 = null;
	JButton jButton5 = null;
	private JToolBar jToolBar1;
	Vector<FilterBean> filters = null;
	String templateName[] = {"ID", "Template Name"};
	DefaultTableModel tablemodel = new DefaultTableModel(templateName, 0);
	

	public JInternalJobFrame(FilterValues filterValues,Vector<StartUPData> searchCriteria,Vector<StartUPData> searchColumn,String reportType,Users user) {
		setFilterValues(filterValues);
		setSearchCriteria(searchCriteria);
		setSearchColumn(searchColumn);
		setVisible(true);
		setReportType(reportType);
		setLayout(new GroupLayout());
		setUser(user);
		filters = new Vector<FilterBean>();
		javax.swing.plaf.InternalFrameUI ifu = this.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null,null, null, null));
		add(getJToolBar1(), new Constraints(new Bilateral(8, 11, 2),new Leading(6, 26, 17, 17)));
		add(getJPanel1(), new Constraints(new Bilateral(8, 10, 0),new Bilateral(38, 11, 55)));
		

	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setFloatable(false);
		//	jToolBar1.add(getExecute()); // execute 
			jToolBar1.addSeparator();

			jToolBar1.add(getSave()); // save 
			jToolBar1.addSeparator();
			//jToolBar1.add(getJButton2());
		//	jToolBar1.addSeparator();
			jToolBar1.add(getAddJob()); // ADD
			jToolBar1.addSeparator();
			jToolBar1.add(getClearALL()); // clearALL
			jToolBar1.addSeparator();
			jToolBar1.add(getDelete()); // Delete
		}
		return jToolBar1;
	}

	private JPanel getJPanel1() {
		// TODO Auto-generated method stub
		searchTreeJobPanel = new JobTreeViewPanel();
		searchTreeJobPanel.setFilterValues(filterValues);
		searchTreeJobPanel.setUser(getUser());
		return searchTreeJobPanel.getJPanel1();
	}
	private JButton getClearALL() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			
			jButton4.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.CLEARALL));

			jButton4.setToolTipText("Clear All");
			jButton4.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	 if(!AccessDataCacheUtil.getAccessData().isAccessToWindow(user,taskStationTabWindow)) {
	 						commonUTIL.showAlertMessage("User Access Denied to Clear Criteria");
	 						return;
	 					}	
	            //	searchPanel.clearllCriteriaModel();
	            //	searchPanel.getFilterBeanData().clear();
	            	job = null;
	            }
			});
			
		}
		return jButton4;
	}
	private JButton getDelete() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			
			jButton5.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.DELETE));

			jButton5.setToolTipText("Delete Criteria");
			jButton5.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	if(!searchTreeJobPanel.jTree1.isSelectionEmpty())  {
	            		 if(!AccessDataCacheUtil.getAccessData().isAccessToWindow(user,taskStationTabWindow)) {
	 						commonUTIL.showAlertMessage("User Access Denied to Delete Criteria");
	 						return;
	 					}	
	            		TreePath selPath = 	searchTreeJobPanel.jTree1.getSelectionPath();
	            		MutableTreeNode mNode = (MutableTreeNode) selPath.getLastPathComponent();
	            		setSignalTOremoveNode(selPath.toString()+"_DeleteNode");
	            				DefaultTreeModel _treeModel = (DefaultTreeModel) searchTreeJobPanel.jTree1.getModel();
	            		_treeModel.removeNodeFromParent(mNode);
	            		searchTreeJobPanel.jTree1.setModel(_treeModel);
	            		
	            	}
	            }
			});
		}
		return jButton5;
	}
	private JButton getExecute() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			jButton0.setForeground(Color.white);
			ImageIcon icon = new ImageIcon("/resources/icon/hide12.png");
			jButton0.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.REFRESH));

			jButton0.setToolTipText("Execute");
		}jButton0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				/*String sql = "";
				String where = "";
				if(getReportType().equalsIgnoreCase("PNL")) {
					 where =  getFilterValues().createWhere(searchPanel.getFilterBeanData(),"Trade");
				} else {
					 where =  getFilterValues().createWhere(searchPanel.getFilterBeanData(),getReportType());
				}
			//	String where =  getFilterValues().createWhere(searchPanel.getFilterBeanData(),getReportType());
				String sqlW = "";
				String columnSQL = reportPanel.getColumnSQL();
				if(commonUTIL.isEmpty(columnSQL)) {
					commonUTIL.display("Execute Button", "Column are not gettting generated");
					return;
				}
				if( columnSQL.contains("where")) {
					sqlW = columnSQL + " and   " + where;
				} else {
				sqlW = columnSQL + " where   " + where;
				}
				try {
					//commonUTIL.showAlertMessage(sql);
				data = (Vector)	 reportPanel.getRemoteTrade().getTradesforReport(sqlW);
				if(data != null && data.size() > 0) {
					reportPanel.getpReport().setHeader((Vector) data.get(0)); 
					reportPanel.getpReport().setdatatype((Vector) data.get(1));
					reportPanel.getpReport().setData((Vector) data.get(2));
					reportPanel.getDemo().setReport(reportPanel.getpReport());
					reportPanel.getDemo().runNewPanel();
					}
					
				System.out.println(data.size());
				System.out.println("PPP");
			//	jobdataPanel.setDataCreteria(data,filtersValues);
				//jobdataPanel.setJobdetails(filters);
				
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				}
			});
		return jButton0;
	}

	
	
	/*
	 * 1.  Create Table Model
	 * 2.  Create Renederer/Editor  in this  (remember getCellEditorValue method must return value )
	 * 3.  Create Combox
	 * 4.  assign sting [] for combox
	 * 5.  assign combox to editor
	 * 6.  when combox is assign to editor setEditorForRowCol  method is call where for each row and column wise editor is created.
	 * 7.  when column and row is clicked selectEditor is called.
	 * 8.  when combox box of that editor is clicked the jcombox mouse event is called. 
	 * 7.  in mouse event of combox, bean assign to the Table  model  is mapped and displayed in table
	 */
	private JButton getAddJob() {

		if (jButton3 == null) {
			jButton3 = new JButton();
			// jButton3.setText("B4");
			jButton3.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.ADD));
			jButton3.setToolTipText("Add Search Criteria");
		}
	
		jButton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				 if(!AccessDataCacheUtil.getAccessData().isAccessToWindow(user,taskStationTabWindow)) {
						commonUTIL.showAlertMessage("User Access Denied to Add Search Criteria");
						return;
					}			
				 String input =  JOptionPane.showInputDialog(null 
                        ,"Enter Name text:");
            	if(commonUTIL.isEmpty(input))
            		return;
            	if(checkNameAlreadyExists(input,searchTreeJobPanel.root)) {
            		commonUTIL.showAlertMessage("Duplicate Name");
            		return;
            	}
            	addJobNode(input,searchTreeJobPanel.treeModel.getChildCount(searchTreeJobPanel.root));
            	if(input != null && (!input.isEmpty())) {
            		DefaultMutableTreeNode node = new DefaultMutableTreeNode(input);
            		searchTreeJobPanel.root.add(node);
            		searchTreeJobPanel. treeModel.reload();
            		searchTreeJobPanel. jTree1.repaint();
            	}
            }
			
		});
		
		return jButton3;
	}
	
	
	
	
	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			// jButton2.setText("B3");
			jButton2.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.DELETE));
			jButton2.setToolTipText("Load Template");
		}jButton2.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				TreePath[] paths = searchTreeJobPanel.jTree1.getSelectionPaths();
	       	      DefaultTreeModel model = (DefaultTreeModel)searchTreeJobPanel. jTree1.getModel();
	       	      for (int i = 0; i < paths.length; i++)
	       	      {
	       	    	  String name = paths[i].toString();
	       	    	 
	       	        MutableTreeNode node = (MutableTreeNode) paths[i].getLastPathComponent();
	       	  
	       	        if (node != model.getRoot()) {
	       	        	removeJobNode(node.toString(),paths[i].getPathCount());
	       	     //    jobs.remove(name);
	       	         model.removeNodeFromParent(node);
	       	        }
	       	      }
				
			}
		});
		return jButton2;
	}
	private void removeJobNode(String name,int treeNodeCount) {
		int remove = 0;
		try {
		//	System.out.println(jobs.size());
			for(int j=0;j<jobs.size();j++ ) {
				UserJob job = jobs.get(j);
				if(job.getTreeNodeName().equalsIgnoreCase(name)) {
					filterValues.remoteTask.deleteUserJob(job);
					remove = j;
				}
			}
		//	System.out.println("Before "+jobs.size());
		  jobs.remove(remove);
		//  System.out.println("after "+jobs.size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JButton getSave() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			//jButton1.setText("B1");
			// ImageIcon icon = new ImageIcon("/resources/icon/save.png");
			// jButton1.setIcon(icon);
			jButton1.setIcon(ButtonsIconsFactory		.getImageIcon(ButtonsIconsFactory.Buttons.SMALLSAVE));
			jButton1.setToolTipText("Save"); 

		}
		jButton1.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			//	System.out.println(job.getId());
				 if(!AccessDataCacheUtil.getAccessData().isAccessToWindow(user,taskStationTabWindow)) {
						commonUTIL.showAlertMessage("User Access Denied to Save Criteria");
						return;
					}	
				saveJobDetails();
			}
		});
		return jButton1;
	}

	/**
	 * @param searchColumn
	 *            the searchColumn to set
	 */
	public void setSearchColumn(Vector<StartUPData> searchColumn) {
		this.searchColumn = searchColumn;
		if (searchColumn.size() > 0 && searchColumn != null) {
			Iterator<StartUPData> it = searchColumn.iterator();
			columnNamesListModel = new DefaultListModel();
		

		}

	}
	public void loadTemplates(int userID) {
		Vector templates = null;
       try {
    	   templates = (Vector) reportPanel.getRemoteTask().getUserJob(userID);
    	   if(commonUTIL.isEmpty(templates)) {
    		   return;
    	   }
    	   for(int i=0;i<templates.size();i++) {
    		   UserJob userjobs = (UserJob) templates.get(i);
    	       tablemodel.insertRow(i,
						new Object[] { userjobs.getId(), userjobs.getTreeNodeName() });
    	   }
    	   
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
	}

	/**
	 * @param searchPanel
	 *            the searchPanel to set
	 */
	public void setSearchPanel(JobTreeViewPanel searchPanel) {
		this.searchTreeJobPanel = searchPanel;
	}

	/**
	 * @param searchCriteria
	 *            the searchCriteria to set
	 */
	public void setSearchCriteria(Vector<StartUPData> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the filterValues
	 */
	private FilterValues getFilterValues() {
		return filterValues;
	}

	/**
	 * @param filterValues
	 *            the filterValues to set
	 */
	public void setFilterValues(FilterValues filterValues) {
		this.filterValues = filterValues;
	}
    /**
	 * @return the user
	 */
	private Users getUser() {
		return user;
	}

	Users user = null;
	public void setUser(Users user) {
		// TODO Auto-generated method stub
		this.user = user;
	
		//loadTemplates(user.getId());
	}
	public void saveTemplate(String templateName,int userID) {
		if(!commonUTIL.isEmpty(templateName)) {
			job = new UserJob();
			job.setId(0);
			job.setTabid(0);
			job.setUserID(user.getId());
			job.setType(getReportType().toUpperCase());
			job.setTreeNodeName(templateName);
			setTemplateFrame(templateName);
			try {
				job = (UserJob) reportPanel.getRemoteTask().saveUserJob(job);
				tablemodel.insertRow(tablemodel.getRowCount(), new Object[]{ job.getId(), job.getTreeNodeName()} );
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
    DockableFrame dFrame = null;
	public void setTemplateFrame(DockableFrame frame) {
		// TODO Auto-generated method stub
		dFrame = frame;
		
	}
	public void setTemplateFrame(String tempalateName) {
		dFrame.setTitle(tempalateName);
	}
	private void loadJobs(Vector<UserJobsDetails> jobdetails) {
		// TODO Auto-generated method stub
		
		
		
		
	}

	private boolean checkNameAlreadyExists(String name,DefaultMutableTreeNode root) {
		boolean flag = false;
		for(int i=0;i<root.getChildCount();i++) {
			String nodename = root.getChildAt(i).toString();
			if(name.equalsIgnoreCase(nodename)) 
				flag = true;
			
		}
		return flag;
	}
	private void addJobNode(String name,int treeNodeCount) {
		UserJob newJob = new UserJob();
		newJob.setUserID(user.getId());
		newJob.setTreeNodeName(name);
		newJob.setTreeid(treeNodeCount);
		newJob.setType("TASK");
		try {
		newJob =	filterValues.remoteTask.saveUserJob(newJob);
		searchTreeJobPanel.getJobs().add(searchTreeJobPanel.getJobs().size(),newJob);
			//System.out.println(jobs.size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setUserJob(UserJob job2) {
		job = job2;
		
		
		// TODO Auto-generated method stub
		
	}

	public void saveJobDetails() {
		// TODO Auto-generated method stub
		if(job == null) {
			commonUTIL.showAlertMessage("Select Template");
			return;
	}
		
		
		
	}
}
