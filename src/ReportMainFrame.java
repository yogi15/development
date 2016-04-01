import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class ReportMainFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel1;
	private JInternalFrame jInternalFrame0;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel2;
	private JSplitPane jSplitPane0;
	private JPanel jPanel3;
	private JPanel jPanel0;
	private JToolBar jToolBar0;
	private JButton jButton1;
	private JButton jButton0;
	private JButton jButton2;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public ReportMainFrame() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel2(), new Constraints(new Bilateral(6,12,28), new Bilateral(6,12,28)));
		setSize(924, 437);
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setIcon(new ImageIcon(getClass().getResource("/resources/icon/down.png")));
		}
		return jButton2;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setIcon(new ImageIcon(getClass().getResource("/resources/icon/down.png")));
		}
		return jButton0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setIcon(new ImageIcon(getClass().getResource("/resources/icon/right.png")));
		}
		return jButton1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.add(getJButton1());
			jToolBar0.add(getJButton0());
			jToolBar0.add(getJButton2());
		}
		return jToolBar0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJTabbedPane0(), new Constraints(new Bilateral(6,12,28), new Bilateral(6,12,28)));
		}
		return jPanel3;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setBorder(null);
			jSplitPane0.setDividerLocation(473);
			jSplitPane0.setLeftComponent(getJPanel3());
			jSplitPane0.setRightComponent(getJPanel0());
		}
		return jSplitPane0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJSplitPane0(), new Constraints(new Bilateral(6, 12, 28), new Bilateral(6, 12, 28)));
		}
		return jPanel2;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.addTab("<HTML>T<BR>a<BR>s<BR>k<BR> R<BR>e<BR>p<BR>o<BR>r<BR>t<BR>", getJInternalFrame0());
		}
		return jTabbedPane0;
	}

	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			jInternalFrame0.setBorder(null);
			jInternalFrame0.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			javax.swing.plaf.InternalFrameUI ifu = jInternalFrame0.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setFrameIcon(new ImageIcon(getClass().getResource("")));
			jInternalFrame0.setFont(new Font("Arial Narrow", Font.PLAIN, 11));
			jInternalFrame0.setInheritsPopupMenu(true);
			jInternalFrame0.setLayout(new GroupLayout());
			jInternalFrame0.add(getJPanel1(), new Constraints(new Leading(5, 421, 10, 10), new Leading(38, 10, 10)));
			jInternalFrame0.add(getJToolBar0(), new Constraints(new Leading(329, 82, 10, 10), new Leading(4, 23, 11, 11)));
		}
		return jInternalFrame0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane0(), new Constraints(new Leading(6, 403, 10, 10), new Leading(13, 275, 10, 10)));
		}
		return jPanel1;
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
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.add(new ReportMainFrame());
		frame.setVisible(true);
		frame.setSize(500,500);
		
	}

}
