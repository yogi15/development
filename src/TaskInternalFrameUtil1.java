import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TaskInternalFrameUtil1 extends JPanel {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar0;
	private JPanel jPanel0;
	private JInternalFrame jInternalFrame0;
	private JToolBar jToolBar1;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel jPanel1;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TaskInternalFrameUtil1() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(10, 10, 0), new Bilateral(11, 7, 10, 363)));
		setSize(530, 381);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
		}
		return jPanel1;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setFont(new Font("Arial Narrow", Font.PLAIN, 10));
			jButton2.setText("j2");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setFont(new Font("Arial Narrow", Font.PLAIN, 10));
			jButton1.setText("j1");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setFont(new Font("Arial Narrow", Font.PLAIN, 10));
			jButton0.setText("j0");
		}
		return jButton0;
	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setBorder(new LineBorder(Color.black, 1, false));
			jToolBar1.add(Box.createHorizontalGlue());
			jToolBar1.setBorderPainted(false);
			jToolBar1.add(getJButton0());
			jToolBar1.add(getJButton1());
			jToolBar1.add(getJButton2());
		}
		return jToolBar1;
	}

	public JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			jInternalFrame0.setBorder(null);
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());
			jInternalFrame0.add(getJToolBar1(), new Constraints(new Bilateral(5, 13, 474), new Bilateral(5, 13, 474)));
			jInternalFrame0.add(getJPanel1(), new Constraints(new Bilateral(5, 13, 474), new Bilateral(71, 10, 243)));
		}
		return jInternalFrame0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJInternalFrame0(), new Constraints(new Bilateral(6, 10, 38),new Bilateral(6, 10, 38)));
		}
		return jPanel0;
	}

	private JToolBar getJToolBar() {
    	TaskToolBarUtil toolBar = new TaskToolBarUtil();
    	return toolBar.getJToolBar0();
    }

}
