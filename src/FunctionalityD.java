import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class FunctionalityD extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private JTable jTable2;
	private JScrollPane jScrollPane2;
	private JTable jTable3;
	private JScrollPane jScrollPane3;
	private JTabbedPane jTabbedPane1;
	private JPanel jPanel1;
	private JPanel jPanel0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton0;
	private JScrollPane jScrollPane4;
	private JPanel jPanel2;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public FunctionalityD() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 818, 10, 10), new Leading(7, 310, 10, 10)));
		setSize(828, 327);
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane4(), new Constraints(new Leading(3, 800, 10, 10), new Bilateral(2, 12, 22)));
			jPanel2.add(getJTabbedPane0(), new Constraints(new Leading(10, 786, 12, 12), new Leading(10, 10, 10)));
		}
		return jPanel2;
	}

	private JScrollPane getJScrollPane4() {
		if (jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		}
		return jScrollPane4;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("TakeUp");
		}
		return jButton0;
	}

	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("SAVE");
		}
		return jButton6;
	}

	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("DEAL");
		}
		return jButton5;
	}

	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("NEW");
		}
		return jButton4;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("INTERNAL");
		}
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("B2B");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("FAV");
		}
		return jButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJPanel1(), new Constraints(new Leading(0, 815, 12, 12), new Leading(5, 39, 10, 10)));
			jPanel0.add(getJPanel2(), new Constraints(new Leading(4, 806, 12, 12), new Leading(50, 251, 10, 10)));
			jPanel0.addComponentListener(new ComponentAdapter() {
	
				public void componentHidden(ComponentEvent event) {
					jPanel0ComponentComponentHidden(event);
				}
			});
		}
		return jPanel0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJButton1(), new Constraints(new Leading(12, 75, 10, 10), new Leading(7, 25, 10, 10)));
			jPanel1.add(getJButton2(), new Constraints(new Leading(99, 75, 12, 12), new Leading(7, 25, 12, 12)));
			jPanel1.add(getJButton3(), new Constraints(new Leading(190, 75, 10, 10), new Leading(7, 25, 12, 12)));
			jPanel1.add(getJButton4(), new Constraints(new Leading(273, 75, 10, 10), new Leading(7, 25, 12, 12)));
			jPanel1.add(getJButton5(), new Constraints(new Leading(363, 75, 10, 10), new Leading(7, 25, 12, 12)));
			jPanel1.add(getJButton6(), new Constraints(new Leading(452, 75, 10, 10), new Leading(7, 25, 12, 12)));
			jPanel1.add(getJButton0(), new Constraints(new Leading(545, 75, 12, 12), new Leading(7, 25, 10, 10)));
		}
		return jPanel1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.addTab("jTable0", getJScrollPane0());
			jTabbedPane1.addTab("jTable1", getJScrollPane1());
			jTabbedPane1.addTab("jTable2", getJScrollPane2());
			jTabbedPane1.addTab("jTable3", getJScrollPane3());
		}
		return jTabbedPane1;
	}

	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setViewportView(getJTable3());
		}
		return jScrollPane3;
	}

	private JTable getJTable3() {
		if (jTable3 == null) {
			jTable3 = new JTable();
			jTable3.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable3;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setVisible(false);
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}

	private JTable getJTable2() {
		if (jTable2 == null) {
			jTable2 = new JTable();
			jTable2.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable2;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setVisible(false);
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setVisible(false);
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}
		return jTable0;
	}

	private void jPanel0ComponentComponentHidden(ComponentEvent event) {
	}

}
