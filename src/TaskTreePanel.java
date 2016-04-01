import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TaskTreePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public TaskTreePanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(17, 0, 39), new  Bilateral(17, 0, 39)));
		setSize(316, 244);
	}

	public JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
		//	jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0());
		}
		return jPanel0;
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

}
