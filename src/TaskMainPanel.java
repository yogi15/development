import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import apps.window.referencewindow.taskPanel.ChildPanel;
import apps.window.referencewindow.taskPanel.childTaskPanel;

import dsServices.RemoteReferenceData;

import util.commonUTIL;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TaskMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
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
	int i =0;
	Vector<String> searchCriteria;
	Vector<String> searchColumn;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TaskMainPanel(RemoteReferenceData remoteRef) {
		setRemoteReferenceData(remoteRef);
		try {
			searchCriteria = (Vector) remoteRef.getSearchCriteria();
			 searchColumn = (Vector)  remoteRef.getSearchColumn("Task");
		} catch(Exception e) {
			commonUTIL.displayError("TaskMainPanel " , "Constructor ", e);
		}
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
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("B3");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("b2");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("B1");
		}
		return jButton0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTree1());
		}
		return jScrollPane1;
	}

	private JTree getJTree1() {
		if (jTree1 == null) {
			jTree1 = new JTree();
			DefaultTreeModel treeModel = null;
			DefaultMutableTreeNode trade = new DefaultMutableTreeNode("Trade");
			DefaultMutableTreeNode Transfer = new DefaultMutableTreeNode("Transfer");
			DefaultMutableTreeNode message = new DefaultMutableTreeNode("Message");
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Jobs");
			root.add(trade);
			root.add(Transfer);
			root.add(message);
			treeModel = new DefaultTreeModel(root);
			jTree1.setModel(treeModel);
		}jTree1.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e){
				// TODO Auto-generated method stub
			
			    TreePath selPath =jTree1.getSelectionPath();
			    if(selPath == null)
			    	return;
			//    commonUTIL.showAlertMessage(selPath.toString());
			  if(i == 0) {
				  jSplitPane0.setRightComponent(jTabbedRightSidePane );
			   ChildPanel childPanel = new ChildPanel();
				  childPanel.setLayout(new GroupLayout());
				  childTaskPanel child = new childTaskPanel();
				  
				//  child.setRemoteReferenceData(getRemoteReferenceData());
				  child.setSearchCriteria(searchCriteria);
				  child.setColumnNames(searchColumn);
				  childPanel.add(child,new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
			     
			   jTabbedRightSidePane.addTab(selPath.toString(),childPanel);
			  
			//   jTabbedRightSidePane.addTab(selPath.toString(),childPanel);
			  
			  } else {
			//	  JPanel panel = new JPanel();
				//  panel.setLayout(new GroupLayout());
				//  panel.add(getJPanel0("Child"),new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
				  ChildPanel childPanel = new ChildPanel();
				  childPanel.setLayout(new GroupLayout());
				  childTaskPanel child = new childTaskPanel();
				  childPanel.add(child,new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
			     
				
				//	childPanel.add(childPanelp);
				  jTabbedRightSidePane.addTab(selPath.toString(),childPanel);
				  jTabbedRightSidePane.setFocusable(false);
			  }
			}
		});
		return jTree1;
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
			jPanel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
			jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJToolBar1(), new Constraints(new Bilateral(3, 3, 149), new Leading(5, 26, 10, 10)));
			jPanel1.add(getJScrollPane1(), new Constraints(new Bilateral(6, 7, 23), new Bilateral(40, 8, 272)));
		}
		return jPanel1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
			jSplitPane0.setDividerLocation(179);
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
			jPanel0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
			
			jTabbedPane0.setTitleAt(0, "<HTML> J<BR>o<BR>b<BR>k<BR>s<BR>e<BR>p<BR>o<BR>r<BR>r<BR>t<BR>");
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
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(179);
		// jTabbedPane0.disable();

		
	}
	private void jTabbedPane0MouseMouseClicked(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(0);

	}
	public void setRemoteReferenceData(RemoteReferenceData remoteBORef) {
		// TODO Auto-generated method stub
		remoteRef = remoteBORef;
	}
	
	public RemoteReferenceData  getRemoteReferenceData() {
		return remoteRef;
	}
	public Vector<String> getSearchCr() {
		return searchCriteria;
	}

	public void setSearchCr(Vector<String> searchCr) {
		this.searchCriteria = searchCr;
	}

	public Vector<String> getSearchColumn() {
		return searchColumn;
	}

	public void setSearchColumn(Vector<String> searchColumn) {
		this.searchColumn = searchColumn;
	}
	RemoteReferenceData remoteRef;

	public static void main(String args[]) {
		JFrame frame = new JFrame();
	//	frame.add(new testTaskPanel());
		

		frame.setVisible(true);
		frame.setSize(500,500);
	}
}
