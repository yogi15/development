package apps.window.operationwindow.jobpanl;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.StartUPData;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;


import dsEventProcessor.TaskEventProcessor;
import dsManager.TaskManager;
import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;

import util.commonUTIL;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TaskMainPanel extends JPanel {
   
	private static final long serialVersionUID = 1L;
	RemoteTask remoteTask;
	RemoteTrade remoteTrade;
	private JSplitPane jSplitPane0;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel0;
	private JInternalFrame jInternalFrame0;
	private JTabbedPane jTabbedRightSidePane;
	private JToolBar jToolBar0;
	private JPanel jPanel1;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JToolBar jToolBar1;
	private JTree jTree1;
	private JScrollPane jScrollPane1;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	Vector<UserJob> jobs = null;
	
	DefaultTreeModel treeModel = null;
	DefaultMutableTreeNode root = null;

	int i =0;
	Vector<StartUPData> searchCriteria;
	Vector<StartUPData> searchColumn;
	Hashtable<String,childTaskPanel> childs = new Hashtable<String,childTaskPanel>();
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	FilterValues filterValues = null;
	public TaskMainPanel(RemoteReferenceData remoteRef,RemoteTask remoteTask,RemoteTrade remoteTrade,RemoteBOProcess remoteBO,Users user) {
		setRemoteTask(remoteTask);
		setRemoteReferenceData(remoteRef);
		setUser(user);
		try {
			
			searchCriteria = (Vector) remoteRef.getStartUPData("SearchCriteria");
			 searchColumn = (Vector)  remoteRef.getStartUPData("TaskColumn");
			 filterValues = new FilterValues(remoteRef,remoteTrade,remoteTask,remoteBO);
		} catch(Exception e) {
			commonUTIL.displayError("TaskMainPanel " , "Constructor ", e);
		}
		initComponents();
	} 
	public TaskMainPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
		setSize(721, 472);
		
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("B4");
			jButton3.setToolTipText("Delete");
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("B3");
			jButton2.setToolTipText("Save");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("b2");
			jButton1.setToolTipText("Add Job");
		}jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String input =  JOptionPane.showInputDialog(null 
                        ,"Enter Name text:");
            	if(commonUTIL.isEmpty(input))
            		return;
            	if(checkNameAlreadyExists(input,root)) {
            		commonUTIL.showAlertMessage("Duplicate Name");
            		return;
            	}
            	addJobNode(input,treeModel.getChildCount(root));
            	if(input != null && (!input.isEmpty())) {
            		DefaultMutableTreeNode node = new DefaultMutableTreeNode(input);
            	   root.add(node);
            	   treeModel.reload();
            	   jTree1.repaint();
            	}
            }
		});
		return jButton1;
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
		newJob =	remoteTask.saveUserJob(newJob);
			jobs.add(jobs.size(),newJob);
			//System.out.println(jobs.size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("B1");
			jButton0.setToolTipText("Remove Job");
		}jButton0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	 TreePath[] paths = jTree1.getSelectionPaths();
       	      DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
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
		return jButton0;
	}
	private void removeJobNode(String name,int treeNodeCount) {
		int remove = 0;
		try {
		//	System.out.println(jobs.size());
			for(int j=0;j<jobs.size();j++ ) {
				UserJob job = jobs.get(j);
				if(job.getTreeNodeName().equalsIgnoreCase(name)) {
					remoteTask.deleteUserJob(job);
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
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTree1());
		}
		return jScrollPane1;
	}

//	public void deleteremoveJob
	
	
	private JTree getJTree1() {
		Vector<UserJob> jobs = null;
		if (jTree1 == null) {
			jTree1 = new JTree();
			
			 root = new DefaultMutableTreeNode("Jobs");
			 treeModel = new DefaultTreeModel(root);
			 jobs = getUserjobs(user.getId()); 
			 
			if((jobs == null) ||  jobs.isEmpty() ||  jobs.size() == 0) {
				jobs = new Vector<UserJob>(); 
				DefaultMutableTreeNode trade = new DefaultMutableTreeNode("Trade");
				DefaultMutableTreeNode transfer = new DefaultMutableTreeNode("Transfer");
				DefaultMutableTreeNode message = new DefaultMutableTreeNode("Message");
				DefaultMutableTreeNode nTransfer = new DefaultMutableTreeNode("NettingTransfer");
				
				root.add(trade);
				root.add(transfer);
				root.add(message);
				root.add(nTransfer);
			     
			    
			     for(int i=0;i<root.getChildCount();i++) {
			    	 UserJob job = new UserJob();
			    	 job.setUserID(user.getId());
			    	job.setId(0);
			    	job.setTreeid(i);
			    	job.setTreeNodeName(root.getChildAt(i).toString());
			    	job.setType("TASK");
			    	jobs.add(job);
			     }
			   saveJobs(jobs);
			} else {
				setJobs(jobs);
				for(int j=0;j<jobs.size();j++ ) {
					UserJob job = jobs.get(j);
					root.add(new DefaultMutableTreeNode(job.getTreeNodeName()));
					i = job.getTabid();
					
					
				}
			}
			treeModel = new DefaultTreeModel(root);
			jTree1.setModel(treeModel);
		}jTree1.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e){
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon(  this.getClass().getResource("/resources/icon/hide12.png"));
			    TreePath selPath =jTree1.getSelectionPath();
			  if( jTree1.getSelectionPath().getParentPath() == null)
				  return;
			    if(selPath == null)
			    	return;
			  
			    if(setFocusonJTabbedPane(selPath.toString(),jTabbedRightSidePane)) 
			    	return;
			    UserJob job = getJobByName(selPath.getLastPathComponent().toString());
			    if(job != null ) {
					    Vector detailsJob = job.getDetailsJobs();
						if((detailsJob != null) && (! detailsJob.isEmpty())) {
							if(jTabbedRightSidePane == null)  {
								jTabbedRightSidePane = new JTabbedPane();
								jSplitPane0.setRightComponent(jTabbedRightSidePane );
								
							} 
							
							 
							   ChildPanel childPanel = getChildPanel(selPath.toString(),job,detailsJob,remoteTask,remoteTrade);
							   childPanel.setName(selPath.toString());
							//   jTabbedRightSidePane.addTab(selPath.toString(),childPanel);
							   jTabbedRightSidePane.addTab(selPath.toString(),icon,childPanel);
							 //  jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
							  jTabbedRightSidePane.setFocusable(false);
							  i = i+1;
							  jTabbedRightSidePane.setSelectedIndex(jTabbedRightSidePane.getTabCount()-1);
							
						} else {
							i = i+1;
							if(jTabbedRightSidePane == null)  {
								jTabbedRightSidePane = new JTabbedPane();
								jSplitPane0.setRightComponent(jTabbedRightSidePane );
							}
							   ChildPanel childPanel = getChildPanel(selPath.toString(),job,null,remoteTask,remoteTrade);
							   childPanel.setName(selPath.toString());
							   jTabbedRightSidePane.addTab(selPath.toString(),icon,childPanel);
							  jTabbedRightSidePane.setFocusable(false);
							//  jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
							  i = i+1;
							  jTabbedRightSidePane.setSelectedIndex(jTabbedRightSidePane.getTabCount()-1);
						}
			    }
			   
			//    commonUTIL.showAlertMessage(selPath.toString());
			  if(i == 0) { 
				  if(job == null) {
				  job = new UserJob();
			      job.setTreeNodeName(selPath.getLastPathComponent().toString());
			      job.setTreeid(jTree1.getSelectionCount());
			      job.setTabid(i);
				  }
				  jTabbedRightSidePane = new JTabbedPane();
				  jSplitPane0.setRightComponent(jTabbedRightSidePane );
				  jSplitPane0.setDividerLocation(179);
				  i = i+1;
				   job.setTabid(i);
			       ChildPanel childPanel =  getChildPanel(selPath.toString(),job,null,remoteTask,remoteTrade);
			       childPanel.setName(selPath.toString());
			       jTabbedRightSidePane.addTab(selPath.toString(),icon,childPanel);
			      // jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
			  } 
			  if(job == null)  {
				  job = new UserJob();
			      job.setTreeNodeName(selPath.toString());
			      job.setTreeid(jTree1.getSelectionCount());
			      job.setTabid(i);
				  ChildPanel childPanel = getChildPanel(selPath.toString(),job,null,remoteTask,remoteTrade);
				  childPanel.setName(selPath.toString());
				  jTabbedRightSidePane.addTab(selPath.toString(),icon,childPanel);
				//  jTabbedRightSidePane.setIconAt(jTabbedRightSidePane.getTabCount()-1 , icon );
				 
				  jTabbedRightSidePane.setFocusable(false);
				  i = i+1;
				  jTabbedRightSidePane.setSelectedIndex(jTabbedRightSidePane.getTabCount()-1);
			  }
			}

			
		});
		return jTree1;
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
	
	private ChildPanel getChildPanel(String name,UserJob job,Vector<UserJobsDetails> jobDetails,RemoteTask remoteTask,RemoteTrade remoteTrade) {
		ChildPanel childPanel = new ChildPanel();
		  childPanel.setLayout(new GroupLayout());
		  childTaskPanel child = new childTaskPanel(name,searchCriteria,searchColumn,filterValues,job,jobDetails,remoteTask,remoteTrade,getUser());
		  child.setRemoteRef(getRemoteReferenceData());
		  child.setUser(getUser());
		  child.setRemoteTask(remoteTask);
	//	  child.setR
		  childs.put(name, child);
		  childPanel.add(child,new Constraints(new Bilateral(5, 5, 0), new Bilateral(4, 7, 10)));
		  return childPanel;
	}
	
	
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setFloatable(false);
			jToolBar1.add(getJButton0());
			jToolBar1.add(getJButton1());
			jToolBar1.add(getJButton2());
			jToolBar1.add(getJButton3());
		}
		return jToolBar1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			//jPanel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel2.setText(" L4");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel1.setText(" L2");
		}jLabel1.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent event) {
				jTabbedPane0MouseMouseClicked(event);
			}
		});
		
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel0.setText(" L1");
		}
		return jLabel0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			//jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJToolBar1(), new Constraints(new Bilateral(3, 3, 149), new Leading(5, 26, 10, 10)));
			jPanel1.add(getJScrollPane1(), new Constraints(new Bilateral(6, 7, 23), new Bilateral(40, 8, 272)));
		}
		return jPanel1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
		//	jToolBar0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jToolBar0.add(Box.createHorizontalGlue());
			jToolBar0.add(getJLabel0());
			jToolBar0.add(getJLabel1());
			jToolBar0.add(getJLabel2());
		}
		return jToolBar0;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(170);
			jSplitPane0.setLeftComponent(getJInternalFrame0());
		}
		return jSplitPane0;
	}

	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
		//	jInternalFrame0.setBackground(Color.LIGHT_GRAY);
			jInternalFrame0.setBorder(null);
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());
			javax.swing.plaf.InternalFrameUI ifu= jInternalFrame0.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
			jInternalFrame0.add(getJToolBar0(), new Constraints(new Bilateral(8, 11, 2), new Leading(6, 26, 17, 17)));
			jInternalFrame0.add(getJPanel1(), new Constraints(new Bilateral(8, 10, 0), new Bilateral(38, 11, 55)));
		}
		return jInternalFrame0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			//jPanel0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(8, 10, 5), new Bilateral(7, 9, 10, 200)));
		}
		return jPanel0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
		//	jTabbedPane0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jTabbedPane0.addTab("JobReport", getJSplitPane0());
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			
			jTabbedPane0.setTitleAt(0, "<HTML>J<BR>o<BR>b<BR>s");
			jTabbedPane0.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent event) {
					jTabbedPane0MouseMouseEntered(event);
				}
	
				public void mouseClicked(MouseEvent event) {
					jTabbedPane0MouseMouseClicked(event);
				}
			});
		}
		return jTabbedPane0;
	}
	
	private void jTabbedPane0MouseMouseEntered(MouseEvent event) {
	//	 jSplitPane0.setOneTouchExpandable(true);
	//	 jSplitPane0.setDividerLocation(171);
		// jTabbedPane0.disable();

		
	}
	private void jTabbedPane0MouseMouseClicked(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 if(jSplitPane0.getDividerLocation() == 160) {
		   jSplitPane0.setDividerLocation(0);
		 } else {
			  jSplitPane0.setDividerLocation(160);
		 }

	}
	public void setRemoteReferenceData(RemoteReferenceData remoteBORef) {
		// TODO Auto-generated method stub
		remoteRef = remoteBORef;
	}
	
	public RemoteReferenceData  getRemoteReferenceData() {
		return remoteRef;
	}
	public Vector<StartUPData> getSearchCr() {
		return searchCriteria;
	}

	public void setSearchCr(Vector<StartUPData> searchCr) {
		this.searchCriteria = searchCr;
	}

	public Vector<StartUPData> getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(Vector<StartUPData> searchColumn) {
		this.searchColumn = searchColumn;
	}
	RemoteReferenceData remoteRef;

	public static void main(String args[]) {
		JFrame frame = new JFrame();
	//	frame.add(new testTaskPanel());
		

		frame.setVisible(true);
		frame.setSize(500,500);
	}
	public void setUser(Users user) {
		// TODO Auto-generated method stub
		this.user = user;
	}
	public Users getUser() {
		return user;
	}
	public RemoteTask getRemoteTask() {
		return remoteTask;
	}
	public void setRemoteTask(RemoteTask remoteTask) {
		this.remoteTask = remoteTask;
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
	
	public void processTasks(TaskEventProcessor task) {
		Enumeration<childTaskPanel > panels = childs.elements();
		while(panels.hasMoreElements()) {
			childTaskPanel panel = panels.nextElement();
			panel.processTask(task);
		
			
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
		}
		
	}
	
	
	public Vector<UserJob> getJobs() {
		return jobs;
	}
	public void setJobs(Vector<UserJob> jobs) {
		this.jobs = jobs;
	}
	Users user = null;
}
