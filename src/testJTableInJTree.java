import java.awt.Component;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;


public class testJTableInJTree extends javax.swing.JFrame {
	

private javax.swing.JScrollPane jScrollPane1;
private javax.swing.JTree jTree1;

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new testJTableInJTree().setVisible(true);
			}
		});
	}
	public testJTableInJTree() {
			initComponents();
				jTree1.setCellRenderer(new MyTreeRenderer());
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("The Root");
				String[] [] data = { {"One", "two"}, {"three", "four"}, {"six","seven"} };
				root.add(new MyTreeNode(data));
				root.add(new DefaultMutableTreeNode("one child"));
				jTree1.setModel(new DefaultTreeModel(root));
}

	

				private void initComponents() {
				jScrollPane1 = new javax.swing.JScrollPane();
				jTree1 = new javax.swing.JTree();
				setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				jScrollPane1.setViewportView(jTree1);

				getContentPane().add(jScrollPane1,
				java.awt.BorderLayout.CENTER);

				pack();
				}
				
				class MyTreeRenderer extends DefaultTreeCellRenderer {

						public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
							if(value instanceof MyTreeNode) {
								String[] [] data = ((MyTreeNode) value).getData();
								JTable table = new JTable(data.length, data[0].length);
								Vector dataVector = new Vector();
								Vector columnRow = new Vector();
								for(int j = 0 ; j < data[0].length; j++) {
									columnRow.add(data[0][j]);
									for(int i = 1; i < data.length; i++) {
										Vector dataRow = new Vector();
										for(int c = 0 ; c < data[i].length; c++) {
											dataRow.add(data[i][c]);
										}
										dataVector.add(dataRow);
									}
								}
								table.setModel(new DefaultTableModel(dataVector,columnRow));
								//	table.setRowHeight(rowheight);
									return table;
										
								
								}return new JLabel(value.toString());
						}
				}

				class MyTreeNode extends DefaultMutableTreeNode {
					private String[] [] data = null;
					MyTreeNode(String[] [] data) {
						this.data = data;
					}
					String[] [] getData() {
						return this.data;
					}
}
 
}
