import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class ReportMainPanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane0;
	private JPanel jPanel0;
	private JPanel jPanel13;
	private JTabbedPane jTabbedPane0;
	private JInternalFrame jInternalFrame0;
	private JPanel jPanel1;
	private JToolBar jToolBar0;
	private JInternalFrame jInternalFrame1;
	private JToolBar jToolBar1;
	private JButton jButton0;
	private JPanel jPanel2;
	private JButton jButton1;
	private JButton jButton2;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JButton jButton3;
	public ReportMainPanel1() {
		initComponents();
	}

	private void initComponents() {
		setBorder(new LineBorder(Color.black, 1, false));
		setLayout(new GroupLayout());
		add(getJSplitPane0(), new Constraints(new Bilateral(7, 12, 202), new Bilateral(7, 12, 202)));
		setSize(1073, 450);
	}

	
	

	
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("jButton3");
		}
		return jButton3;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTree0());
		}
		return jScrollPane0;
	}

	private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree();
			DefaultTreeModel treeModel = null;
			{
				DefaultMutableTreeNode node0 = new DefaultMutableTreeNode("JTree");
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("colors");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("blue");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("violet");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("red");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("yellow");
						node1.add(node2);
					}
					node0.add(node1);
				}
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("sports");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("basketball");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("soccer");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("football");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hockey");
						node1.add(node2);
					}
					node0.add(node1);
				}
				{
					DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("food");
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("hot dogs");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("pizza");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("ravioli");
						node1.add(node2);
					}
					{
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("bananas");
						node1.add(node2);
					}
					node0.add(node1);
				}
				treeModel = new DefaultTreeModel(node0);
			}
			jTree0.setModel(treeModel);
		}
		return jTree0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane0(), new Constraints(new Bilateral(7, 12, 202), new Bilateral(7, 12, 202)));
		}
		return jPanel2;
	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
		
			//jToolBar1.setLayout(new GroupLayout());
			
		}
		return jToolBar1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			//jToolBar0.add(getJ)
		}
		return jToolBar0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJInternalFrame0(), new Constraints(new Bilateral(3, 12, 10), new Leading(3, 433, 10, 10)));
		}
		return jPanel1;
	}

	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());
			javax.swing.plaf.InternalFrameUI ifu= jInternalFrame0.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
			 
			jInternalFrame0.add(getJToolBar1(), new Constraints(new Bilateral(7, 12, 202), new Bilateral(7, 12, 202)));
//		jInternalFrame0.add(getJPanel2(), new Constraints(new Bilateral(7, 12, 202), new Leading(5, 23, 62, 342)));
			
		}
		return jInternalFrame0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setLayout(new GroupLayout());
			jTabbedPane0.add(getJInternalFrame0(),new Constraints(new Bilateral(7, 12, 202), new Bilateral(7, 12, 202)));
			
		}
		return jTabbedPane0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}
	private JPanel getJPanel13() {
		if (jPanel1 == null) {
			jPanel13 = new JPanel();
			jPanel13.setBorder(new LineBorder(Color.black, 1, false));
			jPanel13.setLayout(new GroupLayout());
			jPanel13.add(getJTabbedPane0(), new Constraints(new Bilateral(7, 12, 202), new Bilateral(7, 12, 202)));
		}
		return jPanel1;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(217);
		//	jSplitPane0.setLayout(new GroupLayout());
			jSplitPane0.setLeftComponent(getJPanel13());
			jSplitPane0.setRightComponent(getJPanel0());
		}
		return jSplitPane0;
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.add(new ReportMainPanel1());
		frame.setVisible(true);
		frame.setSize(500,500);
	}

}
